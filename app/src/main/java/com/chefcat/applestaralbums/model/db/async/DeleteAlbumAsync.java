package com.chefcat.applestaralbums.model.db.async;

import android.os.AsyncTask;

import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.view.base.BaseActivity;

import java.lang.ref.WeakReference;

public class DeleteAlbumAsync extends AsyncTask<Void, Void, Boolean> {
    private WeakReference<BaseActivity> activityReference;
    private Album album;

    public DeleteAlbumAsync(BaseActivity context, Album album){
        activityReference = new WeakReference<>(context);
        this.album = album;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        activityReference.get().appDatabase.albumDao().delete(album);
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
//            activityReference.get().onGetAsyncReply();
        }
    }
}
