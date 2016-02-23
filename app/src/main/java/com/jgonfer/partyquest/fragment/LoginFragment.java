package com.jgonfer.partyquest.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jgonfer.partyquest.R;

public class LoginFragment extends Fragment {

    private LoginFragmentListener mListener;
    private TextView alert_message;

    public LoginFragment() {

    }

    public void setListener(LoginFragmentListener listener) {
        mListener = listener;
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
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
        View layout = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText user_name = (EditText) layout.findViewById(R.id.user_name_edit_text);

        Button submit = (Button) layout.findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = user_name.getText().toString();
                mListener.submitTry(!name.isEmpty());
                alert_message.setVisibility(name.isEmpty() ? View.VISIBLE : View.GONE);
                if (!name.isEmpty()) {
                    mListener.submitName(name);
                }
            }
        });

        alert_message = (TextView) layout.findViewById(R.id.alert_message_label);

        return layout;
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        mListener.submitTry(false);
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
        super.onDetach();
        mListener = null;
    }

    public interface LoginFragmentListener {
        void submitTry(boolean success);
        void submitName(String name);
    }

}
