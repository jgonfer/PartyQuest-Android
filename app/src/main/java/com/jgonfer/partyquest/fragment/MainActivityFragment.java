package com.jgonfer.partyquest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jgonfer.partyquest.utils.Constant;
import com.jgonfer.partyquest.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private MainFragmentListener mListener;

    public static MainActivityFragment newInstance() {
        MainActivityFragment fragment = new MainActivityFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MainActivityFragment() {

    }

    public void setListener(MainFragmentListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);

        LinearLayout a1 = (LinearLayout) layout.findViewById(R.id.a1_view);
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.assignmentSelected(Constant.kAssignment1);
            }
        });

        LinearLayout a2 = (LinearLayout) layout.findViewById(R.id.a2_view);
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.assignmentSelected(Constant.kAssignment2);
            }
        });

        return layout;
    }

    public interface MainFragmentListener {
        void assignmentSelected(int position);
    }
}
