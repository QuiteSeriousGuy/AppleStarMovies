package com.chefcat.applestaralbums.view.main;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chefcat.applestaralbums.R;
import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.util.TimeHelper;
import com.chefcat.applestaralbums.view.base.BaseFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumRecyclerViewAdapter extends RecyclerView.Adapter<AlbumRecyclerViewAdapter.ViewHolder> {

    private final List<Album> mValues;
    private final BaseFragment.OnListFragmentInteractionListener mListener;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public AlbumRecyclerViewAdapter(List<Album> items, BaseFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View layoutView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_header, parent, false);
            return new ViewHolder(layoutView);
        }

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        if(position == 0) {
            if(holder.mItem.getReleaseDate() == null) {
                holder.mView.setVisibility(View.GONE);
            } else {
                holder.mView.setVisibility(View.VISIBLE);
                holder.tvHeader.setText("Last Visit: " + TimeHelper.longDateStringToTime(holder.mItem.getReleaseDate()));
            }
        } else {
            holder.mView.setVisibility(View.VISIBLE);
            Picasso.get().load(holder.mItem.getLargeImage())
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.ivAlbum);

            holder.tvTrackName.setText(holder.mItem.getTrackName());
            holder.tvTrackGenere.setText(holder.mItem.getPrimaryGenreName());
            holder.tvTrackPrice.setText(holder.mItem.getTrackPrice());
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (null != mListener) {
                        // Notify the active callbacks interface (the activity, if the
                        // fragment is attached to one) that an item has been selected.
                        mListener.onListFragmentInteraction(holder.mItem, holder.ivAlbum);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        View mView;

        @Nullable @BindView(R.id.tv_header) TextView tvHeader;

        @Nullable @BindView(R.id.iv_album)       ImageView ivAlbum;
        @Nullable @BindView(R.id.tv_track_name)  TextView tvTrackName;
        @Nullable @BindView(R.id.tv_track_genre) TextView tvTrackGenere;
        @Nullable @BindView(R.id.tv_track_price) TextView tvTrackPrice;

        public Album mItem;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            mView = view;
        }
    }
}
