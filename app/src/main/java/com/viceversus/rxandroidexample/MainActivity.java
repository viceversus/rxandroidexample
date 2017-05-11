package com.viceversus.rxandroidexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.jakewharton.rxbinding.view.RxView;
import com.jakewharton.rxbinding.widget.RxTextView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ListView listView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.editText);

        Subject<String> QuantityRequested = PublishSubject.create();
        Subject<Boolean> SearchRequested = PublishSubject.create();

        SearchRequested
            .subscribeOn(Schedulers.io())
            .withLatestFrom(QuantityRequested, (b, quantity) -> {
                return quantity;
            })
            .observeOn(Schedulers.io())
            .flatMap(quantityRequested -> {
                return StudioGhibliServiceImpl
                    .getInstance()
                    .getFilmData(quantityRequested);
            })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(films -> {
                FilmAdapter adapter = new FilmAdapter(this, R.layout.film_item, new ArrayList<Film>(films));
                listView.setAdapter(adapter);
            });

        RxView.clicks(button)
            .debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(aVoid -> {
                SearchRequested.onNext(true);
            });

        RxTextView.textChanges(editText)
            .subscribe(charSequence -> {
                QuantityRequested.onNext(charSequence.toString());
            });

    }
}
