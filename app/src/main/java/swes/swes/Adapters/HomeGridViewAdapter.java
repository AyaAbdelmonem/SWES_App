package swes.swes.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.HamButton;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import swes.swes.R;

/**
 * Created by Sameh on 4/21/2017.
 */

public class HomeGridViewAdapter extends BaseAdapter {

    private Context mContext;

    private View HomeView;
    // Keep all Images in array
    public Integer[] mThumbIds = {
     1,2,3,4,5,6,7
    };

    // Constructor
    public HomeGridViewAdapter(Context c,View HomeView){
        mContext = c;
        this.HomeView=HomeView;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        /*ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(mThumbIds[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(70, 70));*/
/*
        BoomMenuButton bmb=null;
        for (int j = 0; j < 7; j++) {
             bmb = new BoomMenuButton(mContext);
            bmb.setButtonEnum(ButtonEnum.Ham);
            bmb.setPiecePlaceEnum(PiecePlaceEnum.HAM_4);
            // bmb.setBackgroundColor(getResources().getColor(R.color.dot_dark_screen4));
            bmb.setNormalColor(mContext.getResources().getColor(R.color.dot_dark_screen4));
            bmb.setHighlightedColor(mContext.getResources().getColor(R.color.dot_dark_screen4));
            bmb.setButtonPlaceEnum(ButtonPlaceEnum.HAM_4);


            bmb.setLayoutParams(new GridView.LayoutParams(120,120));
            LinearLayout ll = (LinearLayout)HomeView.findViewById(R.id.fragment_home);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView label =new TextView(mContext);

            label.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.WRAP_CONTENT, GridView.LayoutParams.WRAP_CONTENT));

            label.setText("bmb at    now"+position);

            ll.addView(label, lp);


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
                            }
                        });
                bmb.addBuilder(builder);
            }
            return bmb;

        }
        return bmb;*/
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
            bmb.setNormalColor(mContext.getResources().getColor(R.color.dot_dark_screen4));
            bmb.setHighlightedColor(mContext.getResources().getColor(R.color.dot_dark_screen4));
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
