package com.chefcat.applestaralbums.model.db.async;

import android.os.AsyncTask;

import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.view.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

public class GetAlbumsAsync extends AsyncTask<Void, Void, List<Album>> {
    private WeakReference<BaseActivity> activityReference;

    public GetAlbumsAsync(BaseActivity context){
        activityReference = new WeakReference<>(context);
    }

    @Override
    protected List<Album> doInBackground(Void... voids) {
        return activityReference.get().appDatabase.albumDao().getAll();
    }

    @Override
    protected void onPostExecute(List<Album> result) {

    }
}
