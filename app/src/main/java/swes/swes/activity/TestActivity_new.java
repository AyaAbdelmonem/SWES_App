package swes.swes.activity;//package swes.swes.activity;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Vector;

import swes.swes.R;
import swes.swes.classes.Test;

public class TestActivity_new extends AppCompatActivity {
    private ViewPager pager;
    private MyFragmentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_new);


       String test_string= getIntent().getStringExtra("test");
        Test test =new Test();
        Gson gson =new Gson();
        test=(Test) gson.fromJson(test_string,Test.class);

        Log.d("T111111111111",test.getQuestions().get(0).getQuestion());



        pager = (ViewPager)findViewById(R.id.container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] data = {test.getQuestions().get(0).getQuestion(),
                test.getQuestions().get(1).getQuestion()
        };

        adapter = new MyFragmentAdapter(getSupportFragmentManager(), 2, this, data);
        pager.setAdapter(adapter);

//        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                reload();
//            }
//        });



    }



    private void reload() {
        String[] data = {"changed1", "changed2", "changed3", "changed4", "changed5", "changed6"};
        //adapter = new MyFragmentAdapter(getSupportFragmentManager(), 6, this, data);
        adapter.setData(data);
        adapter.notifyDataSetChanged();
        pager.invalidate();

        //pager.setCurrentItem(0);
    }

    class MyFragmentAdapter extends FragmentPagerAdapter {
        private int slideCount;
        private Context context;
        private String[] data;

        public MyFragmentAdapter(FragmentManager fm, int slideCount, Context context, String[] data) {
            super(fm);
            this.slideCount = slideCount;
            this.context = context;
            this.data = data;
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(data[position], context);
        }

        @Override
        public int getCount() {
            return slideCount;
        }

        public void setData(String[] data) {
            this.data = data;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return  data[position];
        }
    }
    @SuppressLint("ValidFragment")
    public static    class MyFragment extends Fragment {
        private String text;

        Button btnDisplay;
        RadioGroup radioGroup;
        RadioButton radioButton;

        public MyFragment(String text, Context context) {
            this.text = text;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_test_activity_new, null);
            ((TextView)view.findViewById(R.id.section_label)).setText(text);

            radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
            btnDisplay = (Button) view.findViewById(R.id.btnDisplay);
//                    ((Button)view.findViewById(R.id.load_radio_buttons_btn)).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addRadioButtons(4);
//            }
//        });

           addListenerOnButton();


            return view;
        }
//        public void addRadioButtons(int number) {
//
//            for (int row = 0; row < 1; row++) {
//                RadioGroup ll = new RadioGroup(getActivity());
//                ll.setOrientation(LinearLayout.HORIZONTAL);
//
//                for (int i = 1; i <= number; i++) {
//                    RadioButton rdbtn = new RadioButton(getActivity());
//                    rdbtn.setId((row * 2) + i);
//                    rdbtn.setText("Radio " + rdbtn.getId());
//                    ll.addView(rdbtn);
//                }
//                ((RadioGroup) getActivity().findViewById(R.id.radio_group)).addView(ll);
//            }
//        }

        public void addListenerOnButton() {



            btnDisplay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton) getActivity().findViewById(selectedId);

                    Toast.makeText(getActivity(),
                            radioButton.getText(), Toast.LENGTH_SHORT).show();

                }

            });

        }





    }

}
