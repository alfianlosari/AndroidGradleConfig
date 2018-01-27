package com.udacity.gradle.builditbigger.tasks;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

import javax.annotation.Nullable;

/**
 * Created by alfianlosari on 27/01/18.
 */

public class EndPointsAsyncTask extends AsyncTask<Void, Void, String> {


    private MyApi myApiService = null;
    private EndPointAsyncTaskCompletionHandler mHandler;

    public EndPointsAsyncTask(EndPointAsyncTaskCompletionHandler handler) {
        mHandler = handler;
    }

    @Override
    protected String doInBackground(Void... voids) {
        if(myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            myApiService = builder.build();
        }

        try {
            return myApiService.sayHi("test").execute().getData();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        if (mHandler != null) {
            mHandler.onResultCompleted(result);
        }
    }

    public interface EndPointAsyncTaskCompletionHandler {
        void onResultCompleted(@Nullable String string);
    }


}