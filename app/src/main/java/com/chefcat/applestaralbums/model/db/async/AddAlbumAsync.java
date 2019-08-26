package com.chefcat.applestaralbums.model.db.async;

import android.os.AsyncTask;

import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.view.base.BaseActivity;

import java.lang.ref.WeakReference;

//public class AddAlbumAsync extends AsyncTask<Void,Void,Boolean> {
//    private WeakReference<BaseActivity> activityReference;
//    private Album album;
//
//    public AddAlbumAsync(BaseActivity context, Album album) {
//        activityReference = new WeakReference<>(context);
//        this.album = album;
//    }
//
//    @Override
//    protected Boolean doInBackground(Void... objs) {
//        activityReference.get().appDatabase.albumDao().insert(album);
//        return true;
//    }
//
//    @Override
//    protected void onPostExecute(Boolean result) {
//        if (result){
//            activityReference.get().onGetAsyncReply();
//        }
//    }
//}
