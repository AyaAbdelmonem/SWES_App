package swes.swes.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import swes.swes.R;
import swes.swes.classes.CourseInfo;

/**
 * Created by win on 05/05/2017.
 */

public class CourseDetailsAdapter extends BaseAdapter {
    private Context mContext;
    private CourseInfo courseInfo;


    public CourseDetailsAdapter(Context c,CourseInfo ci) {
        mContext = c;
        courseInfo = ci;

    }


    //TODO: edit getcount
    public int getCount() {
        return courseInfo.getPrerequisets().size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_item, parent, false);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_title);
        tv.setText(courseInfo.getPrerequisets().get(position).getName());
        Log.d("CourseAdapter","value is"+courseInfo.getPrerequisets().get(position).getName());

        return convertView ;

    }


}


