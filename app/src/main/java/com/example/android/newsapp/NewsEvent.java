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

/**
 * An {@link NewsEvent} object contains information related to a single earthquake.
 */
public class NewsEvent {

    /** Magnitude of the earthquake */
    private String mTopic;

    private String mTitle;

    private String mAuthor;

    private String mDate;

    private String mTime;

    private String mUrl;

    public NewsEvent(String topic, String title, String a, String date, String time, String url) {
        mTopic = topic;
        mTitle = title;
        mAuthor = a;
        mDate = date;
        mTime = time;
        mUrl = url;
    }

    /**
     * Returns the topic of the earthquake.
     */
    public String getTopic() {
        return mTopic;
    }

    /**
     * Returns the location of the earthquake.
     */
    public String getTitle() {
        return mTitle;
    }

    public String getAuthor() { return mAuthor; }

    public String getDate() { return mDate; }
    /**
     * Returns the time of the earthquake.
     */
    public String getTime() {
        return mTime;
    }

    /**
     * Returns the website URL to find more information about the earthquake.
     */
    public String getUrl() {
        return mUrl;
    }
}
