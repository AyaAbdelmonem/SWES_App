package swes.swes.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.squareup.picasso.Picasso;

import swes.swes.R;
import swes.swes.classes.Lesson;
import swes.swes.youtube.YoutubeActivity;

public class LessonActivity extends AppCompatActivity {
    Gson gson = new Gson();
    Lesson lesson = new Lesson();
    ImageView mUpButton;
    TextView lesson_desc;
    ImageView lesson_pic;
    private StorageReference mStorageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        TextView textView = (TextView) findViewById(R.id.bmb_textView_label);
        BoomMenuButton bmb = (BoomMenuButton)findViewById(R.id.bmb);
        textView.setText("Start learning");
        mStorageRef = FirebaseStorage.getInstance().getReference();
        mUpButton = (ImageView) findViewById(R.id.action_up);
        lesson_desc = (TextView) findViewById(R.id.lesson_desc);
        lesson_pic =(ImageView) findViewById(R.id.iv_lesson_pic);
        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSupportNavigateUp();
            }
        });
       Intent intent = getIntent();
        String lessonJson = intent.getStringExtra(getString(R.string.lesson_extra));
        lesson = gson.fromJson(lessonJson,Lesson.class);
        Toast.makeText(this,lesson.getLesson_name(),Toast.LENGTH_LONG).show();
        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);
        bmb.setNormalColor(getResources().getColor(R.color.aya1));
        bmb.setHighlightedColor(getResources().getColor(R.color.aya1));
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

            if(i==0) {
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.ic_video )
                        .normalText("Video")
                        .subNormalText("See the video of the lesson")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                // When the boom-button corresponding this builder is clicked.
                               // Toast.makeText(getApplicationContext(), "Clicked on Index " + index, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                                     intent.putExtra("Lesson_video",lesson.getLesson_video());
                                    startActivity(intent);

                            }
                        });
                bmb.addBuilder(builder);
            }
            if(i==1) {
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.ic_video)
                        .normalText("Story")
                        .subNormalText("See the story of the lesson")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                // When the boom-button corresponding this builder is clicked.

                                    Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                                    intent.putExtra("Lesson_story",lesson.getStory());
                                    startActivity(intent);

                            }
                        });
                bmb.addBuilder(builder);
            }
            if(i==2) {
                HamButton.Builder builder = new HamButton.Builder()
                        .normalImageRes(R.drawable.ic_casestudy)
                        .normalText("Test")
                        .subNormalText("Solve the test of the lesson")
                        .listener(new OnBMClickListener() {
                            @Override
                            public void onBoomButtonClick(int index) {
                                // When the boom-button corresponding this builder is clicked.

                                Intent intent = new Intent(getApplicationContext(), TestActivity_new.class);
                                String test = gson.toJson(lesson.getTest());
                                intent.putExtra("Lesson_test",test);
                                    startActivity(intent);
                            }
                        });
                bmb.addBuilder(builder);
            }
        }

        try {
            mStorageRef.child(getString(R.string.fb_lessons_pics_folder)).child(lesson.getLesson_photo()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                @Override
                public void onSuccess(Uri uri) {
                    Picasso.with(LessonActivity.this).load(uri).placeholder(R.drawable.temp_pp).fit().centerCrop().into(lesson_pic);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Toast.makeText(LessonActivity.this, exception.getMessage().toString(), Toast.LENGTH_LONG).show();
                    // Handle any errors
                }
            });
        }catch (Exception e)
        {
            Log.d("user Acoount class", e.getMessage());

        }
        lesson_desc.setText(lesson.getDesc());

    }
}
