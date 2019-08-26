package com.chefcat.applestaralbums.view.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.chefcat.applestaralbums.R;
import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.model.sharedpref.SharedPreferenceService;
import com.chefcat.applestaralbums.util.TimeHelper;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumDetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_album)             ImageView ivAlbum;
    @BindView(R.id.tv_track_name)        TextView tvTrackName;
    @BindView(R.id.tv_track_genre)       TextView tvTrackGenre;
    @BindView(R.id.tv_track_time)        TextView tvTrackTime;
    @BindView(R.id.tv_release_date)      TextView tvReleaseDate;
    @BindView(R.id.tv_track_price)       TextView tvTrackPrice;
    @BindView(R.id.tv_track_description) TextView tvTrackDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Album album = (Album) getIntent().getSerializableExtra("album");

        Picasso.get().load(album.getLargeImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(ivAlbum);


        tvTrackName.setText(album.getTrackName());
        tvTrackGenre.setText(album.getPrimaryGenreName());
        tvTrackTime.setText(TimeHelper.milliToTime(album.getTrackTimeMillis()));
        tvReleaseDate.setText(TimeHelper.timeStampStringToShortDate(album.getReleaseDate()));
        tvTrackPrice.setText(album.getTrackPrice());
        tvTrackDescription.setText(album.getLongDescription());
    }


    @Override
    public void onBackPressed() {
        SharedPreferenceService.saveLastInfo(AlbumDetailsActivity.this, 0);
        super.onBackPressed();
    }
}