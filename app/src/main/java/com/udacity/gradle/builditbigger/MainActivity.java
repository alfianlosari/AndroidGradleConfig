package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.alfianlosari.jokedisplay.JokeActivity;
import com.udacity.gradle.builditbigger.tasks.EndPointsAsyncTask;
import com.udacity.gradle.builditbigger.utils.SimpleIdlingResource;


public class MainActivity extends AppCompatActivity implements
        EndPointsAsyncTask.EndPointAsyncTaskCompletionHandler {

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String flavor = BuildConfig.FLAVOR;
        if (flavor.equals(getResources().getString(R.string.free))) {
            setContentView(R.layout.activity_main_free);
        } else {
            setContentView(R.layout.activity_main_paid);
        }
    }

    public void tellJoke(View view) {
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }
        new EndPointsAsyncTask(this).execute();
    }

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    public void onResultCompleted(@javax.annotation.Nullable String result) {
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(true);
        }

        if (result != null) {
            Intent intent = new Intent(this, JokeActivity.class);
            intent.putExtra(JokeActivity.JOKE_KEY, result);
            startActivity(intent);
        }
    }
}
