package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Pair;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.aenima.androidjokes.Constants;
import com.udacity.aenima.androidjokes.ShowJokeActivity;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

public class RetrieveJokeAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApi = null;
    private static Context mContext;

    public RetrieveJokeAsyncTask() {

    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });

            myApi = builder.build();
        }

        mContext = params[0];

        try {
            return myApi.tellAJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Intent intent = new Intent(mContext, ShowJokeActivity.class);
        intent.putExtra(Constants.JOKE_STRING_EXTRA, result);
        mContext.startActivity(intent);
    }
}
