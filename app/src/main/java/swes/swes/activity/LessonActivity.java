package swes.swes.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

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

        bmb.setButtonEnum(ButtonEnum.Ham);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
        bmb.setNormalColor(getResources().getColor(R.color.aya1));
        bmb.setHighlightedColor(getResources().getColor(R.color.aya1));
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            HamButton.Builder builder = new HamButton.Builder()
                    .normalImageRes(R.drawable.ic_home)
                    .normalText("Butter Doesn't fly!")
                    .subNormalText("Little butter Doesn't fly, either!")
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            // When the boom-button corresponding this builder is clicked.
                            Toast.makeText(getApplicationContext(), "Clicked on Index " + index, Toast.LENGTH_SHORT).show();
                           if (index==0)
                           {Intent intent =new Intent(getApplicationContext(), YoutubeActivity.class);
                            startActivity(intent);}
                        }
                    });
            bmb.addBuilder(builder);
        }


    }
}
