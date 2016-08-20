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

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity
        implements LoaderCallbacks<List<NewsEvent>> {

    private static final String LOG_TAG = NewsActivity.class.getName();


    private static final String GUARDIAN_REQUEST_URL =
            "http://content.guardianapis.com/search?q=debates&api-key=test&show-tags=contributor";

    private static final int NEWS_LOADER_ID = 1;

    private NewsAdapter mAdapter;

    private TextView mEmptyStateTextView;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        updateUi();

        mySwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(LOG_TAG, "onRefresh called from SwipeRefreshLayout");
                        updateUi();
                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );
    }

    public void updateUi(){
        ListView newsListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(mEmptyStateTextView);


        mAdapter = new NewsAdapter(this, new ArrayList<NewsEvent>());

        newsListView.setAdapter(mAdapter);

        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewsEvent currentNewsEvent = mAdapter.getItem(position);

                Uri currentNewsEventUri  = Uri.parse(currentNewsEvent.getUrl());

                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, currentNewsEventUri );

                startActivity(websiteIntent);
            }
        });

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView.setText(R.string.no_internet_connection);
        }
    }

    @Override
    public Loader<List<NewsEvent>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, GUARDIAN_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsEvent>> loader, List<NewsEvent> newsEvents) {

        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.no_news);

        mAdapter.clear();

        if (newsEvents != null && !newsEvents.isEmpty()) {
            mAdapter.addAll(newsEvents);
        }
        ListView newsListView = (ListView) findViewById(R.id.list);

        newsListView.setEmptyView(findViewById(R.id.empty_view));
    }

    @Override
    public void onLoaderReset(Loader<List<NewsEvent>> loader) {
        mAdapter.clear();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_refresh) {
            Log.i(LOG_TAG, "Refresh menu item selected");


            mySwipeRefreshLayout.setRefreshing(true);
            updateUi();
            mySwipeRefreshLayout.setRefreshing(false);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
