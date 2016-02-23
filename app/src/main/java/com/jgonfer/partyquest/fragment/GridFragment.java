package com.jgonfer.partyquest.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jgonfer.partyquest.R;
import com.jgonfer.partyquest.adapter.GridItem;
import com.jgonfer.partyquest.adapter.GridViewAdapter;
import com.jgonfer.partyquest.utils.Constant;

import java.util.ArrayList;

public class GridFragment extends Fragment {

    private GridView mGridView;
    private ProgressBar mProgressBar;
    private GridViewAdapter mGridAdapter;
    private ArrayList<GridItem> mGridData;

    public static GridFragment newInstance() {
        GridFragment fragment = new GridFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public GridFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_grid, container, false);

        mGridView = (GridView) layout.findViewById(R.id.gridView);

        //Initialize with empty data
        mGridData = new ArrayList<>();
        mGridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item_layout, mGridData);
        mGridView.setAdapter(mGridAdapter);

        new AsyncImagesTask().execute();

        return layout;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    //Downloading data asynchronously
    public class AsyncImagesTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            Integer result = 1;

            GridItem item;
            for (int i = 0; i < Constant.kImagesCollection.length; i++) {
                item = new GridItem();
                item.setImage(Constant.kImagesUrl + Constant.kImagesCollection[i]);
                mGridData.add(item);
            }

            return result;
        }

        @Override
        protected void onPostExecute(Integer result) {
            // Download complete. Let us update UI
            if (result == 1) {
                mGridAdapter.setGridData(mGridData);
            } else {
                Toast.makeText(getActivity(), "Failed to fetch data!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
