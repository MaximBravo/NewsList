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


public class NewsAdapter extends ArrayAdapter<NewsEvent> {


    private static final String LOCATION_SEPARATOR = " of ";


    public NewsAdapter(Context context, List<NewsEvent> newsEvents) {
        super(context, 0, newsEvents);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

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
