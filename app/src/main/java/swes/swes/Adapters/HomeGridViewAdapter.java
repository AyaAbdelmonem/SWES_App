package swes.swes.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import swes.swes.R;
import swes.swes.activity.LessonActivity;

/**
 * Created by Sameh on 4/21/2017.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private Context mContext;

    private  int Length;
   // private View HomeView;
    // Keep all Images in array
    public Integer[] mThumbIds = {
     1,2,3,4,5,6,7,8
    };

    // Constructor
    public HomeGridViewAdapter(Context c ,int size){
        mContext = c;
     //   this.HomeView=HomeView;
        this.Length=size;
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
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            grid = new View(mContext);
            grid = inflater.inflate(R.layout.grid_single, null);
            TextView textView = (TextView) grid.findViewById(R.id.grid_text);
            BoomMenuButton bmb = (BoomMenuButton)grid.findViewById(R.id.grid_bmb);
            textView.setText("We are at "+ position);

            bmb.setButtonEnum(ButtonEnum.Ham);
            bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
            bmb.setNormalColor(mContext.getResources().getColor(R.color.aya1));
            bmb.setHighlightedColor(mContext.getResources().getColor(R.color.aya1));
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
                                Toast.makeText(mContext, "Clicked on Index " + index+ " Position "+position, Toast.LENGTH_SHORT).show();
                                Intent intent =new Intent(mContext, LessonActivity.class);
                                mContext.startActivity(intent);
                            }
                        });
                bmb.addBuilder(builder);
            }
        } else {
            grid = (View) convertView;
        }

        return grid;

    }

}
