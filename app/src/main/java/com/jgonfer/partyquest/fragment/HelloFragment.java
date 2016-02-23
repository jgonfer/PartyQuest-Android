package com.jgonfer.partyquest.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jgonfer.partyquest.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class HelloFragment extends Fragment {
    private Timer mTimer;
    private Context ctx;
    private long startTime;
    private long delayTime;
    private long intervalTime;

    private TextView time_label;

    public String name = "";

    public HelloFragment() {
        // Required empty public constructor
    }

    public static HelloFragment newInstance() {
        HelloFragment fragment = new HelloFragment();
        Bundle args = new Bundle();
        //args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_hello, container, false);

        TextView name_label = (TextView) layout.findViewById(R.id.hello_label);
        name_label.setText("Hello " + name + "!");

        time_label = (TextView) layout.findViewById(R.id.time_label);

        timerStart();

        return layout;
    }

    protected class TheTimerTask extends TimerTask {
        @Override public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateTimeLabel();
                }
            });
        }
    }

    protected void timerStart() {
        mTimer = new Timer();
        mTimer.schedule(new TheTimerTask(), 0, 1); // 100 milli seconds
    }

    protected void timerStop() {
        mTimer.cancel();
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    private void updateTimeLabel() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS");
        String currentDateandTime = sdf.format(new Date());
        time_label.setText("Current time: " + currentDateandTime);
    }

    /*
        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            try {
                mListener = (OnFragmentInteractionListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }
    */
    @Override
    public void onDetach() {
        timerStop();

        super.onDetach();
    }
}
