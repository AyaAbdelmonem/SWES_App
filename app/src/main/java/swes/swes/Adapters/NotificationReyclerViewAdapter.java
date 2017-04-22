package swes.swes.Adapters;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import swes.swes.Models.NotificationModel;
import swes.swes.R;
import swes.swes.activity.SettingsActivity;

/**
 * Created by Sameh on 4/21/2017.
 */

public class NotificationReyclerViewAdapter extends RecyclerView.Adapter<NotificationReyclerViewAdapter.ViewHolder>
{

    ArrayList<NotificationModel>  models;


    public NotificationReyclerViewAdapter(ArrayList<NotificationModel> model) {
        this.models = model;
    }

    @Override
    public NotificationReyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_card_row, viewGroup, false);
        if (SettingsActivity.theme == 1)
       view.setBackgroundColor(0xff424242);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NotificationReyclerViewAdapter.ViewHolder viewHolder, int i) {

        viewHolder.textView_title.setText(models.get(i).getTittle());
        viewHolder.textView_details.setText(models.get(i).getDetails());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_title;
        private  TextView textView_details;
        public ViewHolder(View view) {
            super(view);

            textView_title = (TextView)view.findViewById(R.id.notification_text_title);
            textView_details= (TextView)view.findViewById(R.id.notification_text_details);

        }
    }

}
