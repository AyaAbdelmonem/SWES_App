package swes.swes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import swes.swes.PostsFeature.PostsMainActivity;
import swes.swes.R;
import swes.swes.youtube.YoutubeActivity;

public class LessonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        TextView textView = (TextView) findViewById(R.id.bmb_textView_label);
        BoomMenuButton bmb = (BoomMenuButton)findViewById(R.id.bmb);
        textView.setText("See Lesson ");

        bmb.setButtonEnum(ButtonEnum.TextOutsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_3);
        bmb.setNormalColor(getResources().getColor(R.color.aya1));
        bmb.setHighlightedColor(getResources().getColor(R.color.aya1));
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_2);
        String txt;
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {

                 if (i<4) {
                     txt="Index 0-3 ";
                     TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                             .normalImageRes(R.drawable.ic_home)
                             .normalText(String.valueOf(i))
                             .listener(new OnBMClickListener() {
                                 @Override
                                 public void onBoomButtonClick(int index) {
                                     // When the boom-button corresponding this builder is clicked.
                                     Toast.makeText(getApplicationContext(), "Clicked on Index " + index, Toast.LENGTH_SHORT).show();
                                     if (index == 0) {
                                         Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                                         startActivity(intent);
                                     }
                                     if  (index == 1) {
                                         Intent intent = new Intent(getApplicationContext(), TestActivity_new.class);
                                         startActivity(intent);
                                     }


                                 }
                             });
                     bmb.addBuilder(builder);
                 }
                 else  if (i< bmb.getPiecePlaceEnum().pieceNumber())
                 {
                     txt="Index 4-9 HERE HERE";
                     TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                             .normalImageRes(R.drawable.ic_home)
                             .normalText(txt)
                             .listener(new OnBMClickListener() {
                                 @Override
                                 public void onBoomButtonClick(int index) {
                                     // When the boom-button corresponding this builder is clicked.
                                     Toast.makeText(getApplicationContext(), "Clicked on Index " + index, Toast.LENGTH_SHORT).show();
                                     if (index == 0) {
                                         Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                                         startActivity(intent);
                                     }


                                 }
                             });
                     bmb.addBuilder(builder);
                 }
            else {
                     txt="Index 4-9 HERE HERE";
                     TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                             .normalImageRes(R.drawable.ic_home)
                             .normalText(txt)
                             .listener(new OnBMClickListener() {
                                 @Override
                                 public void onBoomButtonClick(int index) {
                                     // When the boom-button corresponding this builder is clicked.
                                     Toast.makeText(getApplicationContext(), "Clicked on Index " + index, Toast.LENGTH_SHORT).show();
                                     if (index == 0) {
                                         Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                                         startActivity(intent);
                                     }


                                 }
                             });
                     bmb.addBuilder(builder);

                 }
        }


    }
}
