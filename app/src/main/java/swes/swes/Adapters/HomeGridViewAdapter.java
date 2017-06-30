package swes.swes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.gson.Gson;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import java.util.ArrayList;
import java.util.List;

import swes.swes.R;
import swes.swes.activity.CaseStudyActivity;
import swes.swes.activity.LessonActivity;
import swes.swes.classes.Lesson;
import swes.swes.classes.Level;

/**
 * Created by Sameh on 4/21/2017.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private Context mContext;

    BoomMenuButton bmb;

    private  int Length;
   List<Level> levels;
   // private View HomeView;
    // Keep all Images in array
    public Integer[] mThumbIds = {
     1,2,3,4,5,6,7,8
    };

    // Constructor
    public HomeGridViewAdapter(Context c ,List<Level> levelMap){
        mContext = c;
     //   this.HomeView=HomeView;
        this.Length=levelMap.size();
        levels =levelMap;
    }

    @Override
    public int getCount() {
        return Length;
    }

    @Override
    public Object getItem(int position) {
        return Length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View grid;
        final LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final List<Lesson> lessons = new ArrayList<>();
        if(levels.get(position).getLessons()!=null) {
         for (Lesson l : levels.get(position).getLessons()) {
            if (l != null) {
            lessons.add(l);

             }
        }
        }
        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
         bmb  = (BoomMenuButton) grid.findViewById(R.id.grid_bmb);
            textView.setText(levels.get(position).getLevelName());

            bmb.setButtonEnum(ButtonEnum.Ham);
            switch (lessons.size()) {
                case 0:
                    bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_1);
                    bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_1);
                    break;
                case 1:
                    bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_2);
                    bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_2);
                    break;
                case 2:
                    bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_3);
                    bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_3);
                    break;
                case 3:
                    bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
                    bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);
                    break;
                case 4:
                    bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_5);
                    bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_5);
                    break;
                default:
                    bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_6);
                    bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_6);
                    break;
            }
            bmb.setNormalColor(mContext.getResources().getColor(R.color.aya1));
            bmb.setHighlightedColor(mContext.getResources().getColor(R.color.aya1));
            for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
                if(i==(bmb.getPiecePlaceEnum().pieceNumber()-1)){
                    HamButton.Builder builder = new HamButton.Builder()
                            .normalImageRes(R.drawable.ic_casestudy)
                            .normalText("casestudy")
                            .listener(new OnBMClickListener() {
                                @Override
                                public void onBoomButtonClick(int index) {
                                    // When the boom-button corresponding this builder is clicked.
                                    //Toast.makeText(mContext, "Clicked on Index " + index + " Position " + position, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(mContext, CaseStudyActivity.class);
//
                                    intent.putExtra(mContext.getString(R.string.lesson_extra),levels.get(position).getCaseStudy());
                                    mContext.startActivity(intent);
                                }

                            });

                    bmb.addBuilder(builder);
                }
                else {

                    final int finalI = i;
                    HamButton.Builder builder = new HamButton.Builder()
                                .normalImageRes(R.drawable.swes_ic_white)
                                .normalText(lessons.get(i).getLesson_name())
                                .listener(new OnBMClickListener() {
                                    @Override
                                    public void onBoomButtonClick(int index) {
                                        // When the boom-button corresponding this builder is clicked.
                                        Intent intent = new Intent(mContext, LessonActivity.class);
                                        Gson gson = new Gson();
                                        String lessonJson = gson.toJson(lessons.get(finalI));
                                        intent.putExtra(mContext.getString(R.string.lesson_extra),lessonJson);
                                        mContext.startActivity(intent);
                                    }

                                });
                        bmb.addBuilder(builder);

                }
                }



            }else{
                grid = (View) convertView;
            }



            return grid;
        }

    }


