package com.viceversus.rxandroidexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 5/9/17.
 */

public class FilmAdapter extends ArrayAdapter<Film> {
    private final List<Film> films;
    private final Context context;
    private final LayoutInflater inflater;

    public FilmAdapter(Context context, int resourceId, ArrayList<Film> films) {
        super(context, resourceId, films);

        this.context = context;
        this.films = films;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.film_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Film film = getItem(position);
        if (film != null) {
            holder.title.setText(film.getTitle());
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return films.size();
    }

    @Override
    public Film getItem(int position) {
        return films.get(position);
    }

    public class ViewHolder {
        TextView title;
    }
}
