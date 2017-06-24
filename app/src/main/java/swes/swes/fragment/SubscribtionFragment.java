package swes.swes.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import swes.swes.R;

import static swes.swes.R.id.checkBox_casestudies;
import static swes.swes.R.id.checkBox_lessons;
import static swes.swes.R.id.checkBox_levels;
import static swes.swes.R.id.checkBox_tests;
import static swes.swes.R.id.checkBox_videos;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SubscribtionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SubscribtionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscribtionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    String Event=null;
    Spinner spinner;
    CheckBox checkBox_v;
    CheckBox checkBox_c;
    CheckBox checkBox_les;
    CheckBox checkBox_lev;
    CheckBox checkBox_t;

    public SubscribtionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubscribtionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubscribtionFragment newInstance(String param1, String param2) {
        SubscribtionFragment fragment = new SubscribtionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_subscribtion, container, false);

// If a notification message is tapped, any data accompanying the notification
        // message is available in the intent extras. In this sample the launcher
        // intent is fired when the notification is tapped, so any accompanying data would
        // be handled here. If you want a different intent fired, set the click_action
        // field of the notification message to the desired intent. The launcher intent
        // is used when no click_action is specified.
        //
        // Handle possible data accompanying notification message.
        // [START handle_data_extras]
        if (getActivity().getIntent().getExtras() != null) {
            for (String key : getActivity().getIntent().getExtras().keySet()) {
                Object value = getActivity().getIntent().getExtras().get(key);
                //Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        // [END handle_data_extras]



        checkBox_c=(CheckBox)view.findViewById(checkBox_casestudies) ;
        checkBox_les=(CheckBox)view.findViewById(checkBox_lessons) ;
        checkBox_lev=(CheckBox)view.findViewById(checkBox_levels) ;
        checkBox_t=(CheckBox)view.findViewById(checkBox_tests) ;
        checkBox_v=(CheckBox)view.findViewById(checkBox_videos) ;

        Button subscribeButton = (Button) view.findViewById(R.id.subscribeButton);
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox_v.isChecked()){
                    // [START subscribe_topics]
                    FirebaseMessaging.getInstance().subscribeToTopic(checkBox_v.getText().toString());
                    // [END subscribe_topics]
                }
                if(checkBox_c.isChecked()){
                    // [START subscribe_topics]
                    FirebaseMessaging.getInstance().subscribeToTopic("CaseStudies");
                    // [END subscribe_topics]
                }
                 if(checkBox_lev.isChecked()){
                    // [START subscribe_topics]
                    FirebaseMessaging.getInstance().subscribeToTopic("Levels");
                    // [END subscribe_topics]
                }
                if(checkBox_les.isChecked()){
                    // [START subscribe_topics]
                    FirebaseMessaging.getInstance().subscribeToTopic("Lessons");
                    // [END subscribe_topics]
                }
                if(checkBox_t.isChecked()){
                    // [START subscribe_topics]
                    FirebaseMessaging.getInstance().subscribeToTopic(checkBox_t.getText().toString());
                    // [END subscribe_topics]
                }

                if(!checkBox_t.isChecked()&&!checkBox_les.isChecked()&&!checkBox_lev.isChecked()&&!checkBox_v.isChecked()&&!checkBox_c.isChecked()){
                    Toast.makeText(getActivity(), "Please, Choose an event ", Toast.LENGTH_SHORT).show();
                }
                else {
                Toast.makeText(getActivity(), "Your subscription events are saved ", Toast.LENGTH_SHORT).show();}


            }
        });



    return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
/*            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
