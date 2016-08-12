/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * An {@link NewsAdapter} knows how to create a list item layout for each earthquake
 * in the data source (a list of {@link NewsEvent} objects).
 *
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class NewsAdapter extends ArrayAdapter<NewsEvent> {

    /**
     * The part of the location string from the USGS service that we use to determine
     * whether or not there is a location offset present ("5km N of Cairo, Egypt").
     */
    private static final String LOCATION_SEPARATOR = " of ";

    /**
     * Constructs a new {@link NewsAdapter}.
     *
     * @param context of the app
     * @param newsEvents is the list of newsEvents, which is the data source of the adapter
     */
    public NewsAdapter(Context context, List<NewsEvent> newsEvents) {
        super(context, 0, newsEvents);
    }

    /**
     * Returns a list item view that displays information about the earthquake at the given position
     * in the list of earthquakes.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Find the earthquake at the given position in the list of earthquakes
        NewsEvent currentNewsEvent = getItem(position);

        TextView topicView = (TextView) listItemView.findViewById(R.id.topic);
        String topic = currentNewsEvent.getTopic();
        topicView.setText(topic);

        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        String title = currentNewsEvent.getTitle();
        titleView.setText(title);

        TextView authorView = (TextView) listItemView.findViewById(R.id.author);
        String author = currentNewsEvent.getAuthor();
        authorView.setText(author);

        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        String date = currentNewsEvent.getDate();
        dateView.setText(date);

        TextView timeView = (TextView) listItemView.findViewById(R.id.time);
        String time = currentNewsEvent.getTime();
        timeView.setText(time);

        return listItemView;
    }
}
