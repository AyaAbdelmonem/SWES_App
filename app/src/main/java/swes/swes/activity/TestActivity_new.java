package swes.swes.activity;//package swes.swes.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import swes.swes.R;
import swes.swes.classes.Question;
import swes.swes.classes.Test;

public class TestActivity_new extends AppCompatActivity {
    private ViewPager pager;
    private MyFragmentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_new);


       String test_string= getIntent().getStringExtra("Lesson_test");
        Test test =new Test();
        Gson gson =new Gson();
        test=(Test) gson.fromJson(test_string,Test.class);
        List<Question> questions =new ArrayList<>();
       //  Log.d("T111111111111",test.getQuestions().get(0).getQuestion());
        if (test==null)
        {
            Toast.makeText(this, "No test at this lesson", Toast.LENGTH_SHORT).show();
            onBackPressed();
            return;

        }
        for(Question question : test.getQuestions()){
            if(question!=null){
                questions.add(question);
            }
        }


        pager = (ViewPager)findViewById(R.id.container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        String[] data = {test.getQuestions().get(0).getQuestion(),
//                test.getQuestions().get(1).getQuestion()
//        };

        ArrayList<String> test_Quest_List=new ArrayList<>();
        if (test!=null) {
            if (questions.size() > 0) {
                for (int i = 0; i < questions.size(); i++) {
                    test_Quest_List.add(questions.get(i).getQuestion());
                }
            }

            ArrayList<String> test_right_List = new ArrayList<>();
            if (questions.size() > 0) {
                for (int i = 0; i < questions.size(); i++) {
                    test_right_List.add(questions.get(i).getRightAnswer());
                }
            }


            ArrayList<String> test_ans_List = new ArrayList<>();
            if (questions.size() > 0) {
                for (int i = 0; i < questions.size(); i++) {
                    test_ans_List.add(questions.get(i).getAnswers().get(0)
                            + "---" + questions.get(i).getAnswers().get(1)
                            + "---" + questions.get(i).getAnswers().get(2)
                            + "---" + questions.get(i).getAnswers().get(3)

                    );
                }
            }

            String[] quest_arr = new String[test_Quest_List.size()];
            quest_arr = test_Quest_List.toArray(quest_arr);

            String[] ans_arr = new String[test_ans_List.size()];
            ans_arr = test_ans_List.toArray(ans_arr);

            String[] right_arr = new String[test_right_List.size()];
            right_arr = test_right_List.toArray(right_arr);


            if (quest_arr.length > 0) {
                adapter = new MyFragmentAdapter(getSupportFragmentManager(),
                       questions.size()
                        , this, quest_arr, ans_arr, right_arr);
                pager.setAdapter(adapter);
            }

        }
        else {
            Toast.makeText(this, "No test at this lesson", Toast.LENGTH_SHORT).show();
            onBackPressed();
        }

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
        private String[] answers;

        private String[] RightAnswer;


        public MyFragmentAdapter(FragmentManager fm, int slideCount, Context context, String[] data,String[]
                                 ans,String[] rightAnswer
                                 ) {
            super(fm);
            this.slideCount = slideCount;
            this.context = context;
            this.data = data;
            this.answers=ans;
            this.RightAnswer=rightAnswer;
        }

        @Override
        public Fragment getItem(int position) {
            return new MyFragment(data[position],answers[position], RightAnswer[position], context);
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

        private String answers;

        private String RightAns;

        Button btnDisplay;
        RadioButton radioButton;
        RadioGroup radioGroup;

        public MyFragment(String text, String ans, String right, Context context) {
            this.text = text;
            this.answers = ans;
            this.RightAns = right;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_test_activity_new, null);
            ((TextView) view.findViewById(R.id.section_label)).setText(text);


            radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
            btnDisplay = (Button) view.findViewById(R.id.btnDisplay);


            RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
            radioButton1 = (RadioButton) view.findViewById(R.id.answer1);
            radioButton2 = (RadioButton) view.findViewById(R.id.answer2);
            radioButton3 = (RadioButton) view.findViewById(R.id.answer3);
            radioButton4 = (RadioButton) view.findViewById(R.id.answer4);


            StringTokenizer tokens = new StringTokenizer(answers, "---");

            String first = tokens.nextToken();// this will contain "Fruit"
            String second = tokens.nextToken();
            String third = tokens.nextToken();
            String fourth = tokens.nextToken();

            radioButton1.setText(first);
            radioButton2.setText(second);
            radioButton3.setText(third);
            radioButton4.setText(fourth);
            btnDisplay.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    // get selected radio button from radioGroup
                    int selectedId = radioGroup.getCheckedRadioButtonId();

                    // find the radiobutton by returned id
                    radioButton = (RadioButton)view.findViewById(selectedId);

                    try{
                    if (radioButton.getText().toString().equals(RightAns))
                    {

                        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                        dialog.setCancelable(false);
                        dialog.setTitle("Result");
                        dialog.setMessage("Great! Right answer" );
                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //Action for "Delete".
                            }
                        });

                        final AlertDialog alert = dialog.create();
                        alert.show();
                    }
                    else{
                        AlertDialog.Builder dialog = new AlertDialog.Builder(view.getContext());
                        dialog.setCancelable(false);
                        dialog.setTitle("Result");
                        dialog.setMessage("Sorry! Wrong answer" );
                        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                //Action for "Delete".
                            }
                        });

                        final AlertDialog alert = dialog.create();
                        alert.show();
                    }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }

                }

            });




            return view;
        }


    }

}
