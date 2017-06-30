package swes.swes.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Map;

import swes.swes.Adapters.CourseDetailsAdapter;
import swes.swes.R;
import swes.swes.classes.CourseInfo;
import swes.swes.classes.Ref;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private StorageReference mStorageRef;
    private CourseInfo courseInfo = new CourseInfo();
       private ImageView iv_course_pic;
       private TextView tv_desc;
        private TextView tv_desc_title;
        private TextView tv_pre_title;
        private ListView lv_prereq;
        private SwipeRefreshLayout swipeRefreshLayout;
        DatabaseReference ref;
        //ProgressDialog progressDialog ;

                ArrayList<Map<String, String>> prerequiste = new ArrayList<>();
       private GoogleApiClient client;


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("CC");
    String value=null;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    NotificationCompat.Builder builder;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view= inflater.inflate(R.layout.fragment_home, container, false);




        database = FirebaseDatabase.getInstance();
        final ViewGroup header = (ViewGroup) inflater.inflate(R.layout.course_lv_header, null, false);
        lv_prereq = (ListView) view.findViewById(R.id.lv_prereq);
        lv_prereq.addHeaderView(header, null, false);
        ref = database.getReference(getString(R.string.fb_course_data));
        mStorageRef = FirebaseStorage.getInstance().getReference();
        iv_course_pic = (ImageView) view.findViewById(R.id.iv_course_pic);
        tv_desc = (TextView)view. findViewById(R.id.tv_course_desc);
        tv_desc_title = (TextView)  view.findViewById(R.id.tv_course_desc_title);
        tv_pre_title = (TextView)  view.findViewById(R.id.tv_course_prereq);
        swipeRefreshLayout = (SwipeRefreshLayout)  view.findViewById(R.id.swipe_refresh_layout);
//        progressDialog = new ProgressDialog(getContext());
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.show();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        swipeRefreshLayout.setRefreshing(false);                    }
                }
        );


        lv_prereq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //TODO: put url of book for each prerequisite

            }
        });
        fetchResults();

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
           /* throw new RuntimeException(context.toString()
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

    public void DrawBmb(View view)
    {
        BoomMenuButton bmb = new BoomMenuButton(getActivity());
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
       // bmb.setBackgroundColor(getResources().getColor(R.color.dot_dark_screen4));
        bmb.setNormalColor(getResources().getColor(R.color.dot_dark_screen4));
        bmb.setHighlightedColor(getResources().getColor(R.color.dot_dark_screen4));
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);

        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.ic_language)
                    .normalText("Butter Doesn't fly!")
                    .subNormalText("Little butter Doesn't fly, either!")
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(getActivity(), "Clicked " + index, Toast.LENGTH_SHORT).show();
                        }
                    });
            bmb.addBuilder(builder);
        }




        LinearLayout ll = (LinearLayout)view.findViewById(R.id.fragment_home);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(View.TEXT_ALIGNMENT_CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;

        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        ll.addView(bmb, params);

    }
    void fetchResults() {

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // get course info
                courseInfo.setPhoto(dataSnapshot.child(getString(R.string.fb_course_photo)).getValue(String.class));
                courseInfo.setDesc(dataSnapshot.child(getString(R.string.fb_course_desc)).getValue(String.class));
                DataSnapshot snapshot = dataSnapshot.child(getString(R.string.fb_prerequiste));
                //get prerequiste
                GenericTypeIndicator<ArrayList<Ref>> t = new GenericTypeIndicator<ArrayList<Ref>>() {
                };
                ArrayList<Ref> read_list = snapshot.getValue(t);
                courseInfo.setPrerequisets(read_list);
                Log.d("FIREBASETEST", "Value is: " + courseInfo.getPrerequisets().size());
                // get photo download uri
                mStorageRef.child(courseInfo.getPhoto()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // display photo
                        Picasso.with(getContext()).load(uri).fit().centerCrop().into(iv_course_pic, new com.squareup.picasso.Callback() {
                            @Override
                            public void onSuccess() {
                   swipeRefreshLayout.setRefreshing(false);
                            }

                            @Override
                            public void onError() {

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle any errors
                    }
                });
                CourseDetailsAdapter detailAdapter = new CourseDetailsAdapter(getContext(), courseInfo);
                lv_prereq.setAdapter(detailAdapter);
                // set texts of text views
                tv_desc.setText(courseInfo.getDesc());
                tv_desc_title.setText(getString(R.string.course_desc));
                tv_pre_title.setText(getString(R.string.course_prereq));
               // progressDialog.dismiss();

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("FIREBASETEST", "Failed to read value.", error.toException());
            }
        });

        lv_prereq.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Uri webpage = Uri.parse(courseInfo.getPrerequisets().get(position-1).getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(webIntent);
            }
        });


    }
}
