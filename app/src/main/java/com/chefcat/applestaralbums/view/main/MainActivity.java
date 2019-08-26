package com.chefcat.applestaralbums.view.main;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.chefcat.applestaralbums.R;

import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.model.data.response.GetAlbumResponse;
import com.chefcat.applestaralbums.model.network.APIService;
import com.chefcat.applestaralbums.model.sharedpref.SharedPreferenceService;
import com.chefcat.applestaralbums.util.Constant;
import com.chefcat.applestaralbums.view.base.BaseActivity;
import com.chefcat.applestaralbums.view.base.BaseFragment;
import com.chefcat.applestaralbums.view.detail.AlbumDetailsActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements BaseFragment.OnListFragmentInteractionListener {

    @BindView(R.id.view_pager) public ViewPager viewPager;
    @BindView(R.id.tabs)       public TabLayout tabs;

    private SectionsPagerAdapter sectionsPagerAdapter;
    private String lastVisit;

    @Override
    protected void onDestroy() {
        SharedPreferenceService.saveLastIn(MainActivity.this);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        lastVisit = SharedPreferenceService.getLastIn(MainActivity.this);

        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        getMainAlbums();
                        SharedPreferenceService.saveLastScreen(MainActivity.this, Constant.MAIN_SCREEN);
                        break;
                    case 1:
                        getSavedAlbums();
                        SharedPreferenceService.saveLastScreen(MainActivity.this, Constant.VIEWED_SCREEN);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) { }

            @Override
            public void onPageScrolled(int i, float v, int i1) { }
        });


        int lastScreen = SharedPreferenceService.getLastScreen(MainActivity.this);
        long lastInfo = SharedPreferenceService.getLastInfo(MainActivity.this);

        // If has an id saved, show details
        if(lastInfo != 0) {
            new GetAlbumWithId(MainActivity.this, lastInfo).execute();
        }

        // If last screen is Viewed screen, show
        if(lastScreen == Constant.VIEWED_SCREEN) {
            viewPager.setCurrentItem(1);
        }

        getMainAlbums();
    }

    public void getMainAlbums() {
        new GetAlbumsAsync(this).execute();
    }

    public void getSavedAlbums() {
        new GetSavedAlbumsAsync(this).execute();
    }

    public String getLastVisit() {
        return lastVisit;
    }


    /**
     *  ASYNC Classes
     *
     */
    public static class GetAlbumsAsync extends AsyncTask<Void, Void, List<Album>> {
        private WeakReference<MainActivity> activityReference;

        public GetAlbumsAsync(MainActivity context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Album> doInBackground(Void... voids) {
            return activityReference.get().appDatabase.albumDao().getAll();
        }

        @Override
        protected void onPostExecute(List<Album> result) {
            List<Album> modifiedList = new ArrayList<>();
            modifiedList.add(Album.headerDateAlbum(activityReference.get().getLastVisit()));
            Collections.sort(result);
            modifiedList.addAll(result);

            activityReference.get().sectionsPagerAdapter.getAlbumMainFragment().setItems(modifiedList);

            // If Only has less than 2 items, which means header only, get from API
            if(modifiedList.size() < 2) {
                activityReference.get().getDataFromAPI();
            }
        }
    }

    public static class GetSavedAlbumsAsync extends AsyncTask<Void, Void, List<Album>> {
        private WeakReference<MainActivity> activityReference;

        public GetSavedAlbumsAsync(MainActivity context){
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected List<Album> doInBackground(Void... voids) {
            return activityReference.get().appDatabase.albumDao().getAllViewed();
        }

        @Override
        protected void onPostExecute(List<Album> result) {
            List<Album> modifiedList = new ArrayList<>();
            modifiedList.add(Album.headerDateAlbum(null));
            modifiedList.addAll(result);

            activityReference.get().sectionsPagerAdapter.getAlbumViewedFragment().setItems(modifiedList);
        }
    }

    public static class AddAlbumsAsync extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<MainActivity> activityReference;
        private List<Album> albums;

        public AddAlbumsAsync(MainActivity context, List<Album> albums) {
            activityReference = new WeakReference<>(context);
            this.albums = albums;
        }

        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get().appDatabase.albumDao().insertAll(albums);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) { }
    }

    public static class GetAlbumWithId extends AsyncTask<Void,Void,Album> {
        private WeakReference<MainActivity> activityReference;
        private long id;

        public GetAlbumWithId(MainActivity context, long id) {
            activityReference = new WeakReference<>(context);
            this.id = id;
        }

        @Override
        protected Album doInBackground(Void... objs) {
            return activityReference.get().appDatabase.albumDao().getAlbumWithId(id);
        }

        @Override
        protected void onPostExecute(Album album) {
            Intent intent = new Intent(activityReference.get(), AlbumDetailsActivity.class);
            intent.putExtra("album", album);
            activityReference.get().startActivity(intent);
        }
    }

    public static class SaveAlbumAsync extends AsyncTask<Void,Void,Boolean> {
        private WeakReference<MainActivity> activityReference;
        private Album album;

        public SaveAlbumAsync(MainActivity context, Album album) {
            activityReference = new WeakReference<>(context);
            this.album = album;
        }

        @Override
        protected Boolean doInBackground(Void... objs) {
            activityReference.get().appDatabase.albumDao().updateAlbumViewed("1",
                    (new Date()).toString(),
                    album.getTrackId());
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) { }
    }

    /**
     *  ASYNC Classes END
     *
     */


    /**
     *   Network Classes
     *
     */
    public void getDataFromAPI(){
        Call<GetAlbumResponse> call = APIService.appleAlbums().getAlbumsBy(Constant.DEFAULT_SEARCH_TERM,
                Constant.DEFAULT_SEARCH_COUNTRY,
                Constant.DEFAULT_SEARCH_MEDIA,
                Constant.DEFAULT_SEARCH_ATTRIBUTE,
                Constant.DEFAULT_SEARCH_LIMIT);

        call.enqueue(new Callback<GetAlbumResponse>() {
            @Override
            public void onResponse(Call<GetAlbumResponse> call, Response<GetAlbumResponse> response) {
                if(response.isSuccessful() && response.body() != null) {
                    List<Album> modifiedList = new ArrayList<>();
                    modifiedList.add(Album.headerDateAlbum(getLastVisit()));
                    Collections.sort(response.body().results);
                    modifiedList.addAll(response.body().results);
                    sectionsPagerAdapter.getAlbumMainFragment().setItems(modifiedList);
                    new AddAlbumsAsync(MainActivity.this,response.body().results).execute();
                }
            }

            @Override
            public void onFailure(Call<GetAlbumResponse> call, Throwable t) {

            }
        });
    }

    /**
     *  Network Classes End
     *
     */

    /**
     *  Overrides
     *
     */
    @Override
    public void onFragmentAttached() { }

    @Override
    public void onFragmentDetached(String tag) { }

    @Override
    public void onListFragmentInteraction(Album album, ImageView thumbnail) {
        new SaveAlbumAsync(this, album).execute();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            SharedPreferenceService.saveLastInfo(MainActivity.this, album.getTrackId());

            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation( this, thumbnail, this.getString(R.string.picture_transition_name));
            Intent intent = new Intent(this, AlbumDetailsActivity.class);
            intent.putExtra("album", album);
            startActivity(intent, options.toBundle());
        }
    }
}