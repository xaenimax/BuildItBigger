package com.udacity.gradle.builditbigger.free;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.aenima.androidjokes.Constants;
import com.udacity.aenima.androidjokes.ShowJokeActivity;
import com.udacity.aenima.javajokes.JokeDispenser;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.RetrieveJokeAsyncTask;


public class MainActivity extends AppCompatActivity implements RetrieveJokeAsyncTask.RetrieveJokeListener{

    private InterstitialAd mInterstitialAd;
    private ProgressBar spinner;
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.setAdListener(mAdListener);

        spinner = findViewById(R.id.loading_pb);
        spinner.setVisibility(View.GONE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void tellJoke(View view) {
        //Step 1
        //Toast.makeText(this, JokeDispenser.getAJoke(), Toast.LENGTH_SHORT).show();
        //Step 2
        //Intent intent = new Intent(this, ShowJokeActivity.class);
        //intent.putExtra(Constants.JOKE_STRING_EXTRA, JokeDispenser.getAJoke());
        //startActivity(intent);
        //show interstitial ad
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.i(this.getClass().getName(), "Failed to load Interstitial Ad");
        }


    }

    //AdListener
    AdListener mAdListener = new AdListener(){
        @Override
        public void onAdClosed() {
            super.onAdClosed();
            //Step 3
            spinner.setVisibility(View.VISIBLE);
            RetrieveJokeAsyncTask task = new RetrieveJokeAsyncTask(MainActivity.this);
            task.execute(MainActivity.this);
            if (mIdlingResource != null) {
                mIdlingResource.setIdleState(true);
            }
        }
    };

    @Override
    public void onTaskExecuted() {
        spinner.setVisibility(View.GONE);
    }

    @VisibleForTesting
    @NonNull
    public SimpleIdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }
}
