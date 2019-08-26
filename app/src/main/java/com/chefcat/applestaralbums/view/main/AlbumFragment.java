package com.chefcat.applestaralbums.view.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chefcat.applestaralbums.R;
import com.chefcat.applestaralbums.model.data.Album;
import com.chefcat.applestaralbums.view.base.BaseFragment;
import com.chefcat.applestaralbums.view.main.AlbumRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AlbumFragment extends Fragment {

    private int mColumnCount = 2;
    private BaseFragment.OnListFragmentInteractionListener mListener;

    private AlbumRecyclerViewAdapter adapter;
    private GridLayoutManager manager;
    private List<Album> albums = new ArrayList<>();

    @BindView(R.id.list) public RecyclerView albumList;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public AlbumFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album, container, false);
        ButterKnife.bind(this, view);

        // Set the adapter
        Context context = view.getContext();
        manager = new GridLayoutManager(context, mColumnCount);
        adapter = new AlbumRecyclerViewAdapter(albums, mListener);

        albumList.setLayoutManager(manager);
        albumList.setAdapter(adapter);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? manager.getSpanCount() : 1;
            }
        });

        return view;
    }

    public void setItems(List<Album> albums){
        this.albums = albums;
        if(albumList != null) {
            adapter = new AlbumRecyclerViewAdapter(albums, mListener);
            albumList.setAdapter(adapter);
        } else {
            Log.e("Null", "Set Items");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseFragment.OnListFragmentInteractionListener) {
            mListener = (BaseFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
}
