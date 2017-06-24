package swes.swes.PostsFeature;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import swes.swes.R;
import swes.swes.other.CircleTransformation;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public ImageView profilepic;
    public TextView numStarsView;
    public TextView bodyView;;
    private StorageReference mStorageRef;

    public PostViewHolder(View itemView ) {
        super(itemView);
        titleView = (TextView) itemView.findViewById(R.id.post_title);
        authorView = (TextView) itemView.findViewById(R.id.post_author);
        starView = (ImageView) itemView.findViewById(R.id.star);
        profilepic = (ImageView) itemView.findViewById(R.id.post_author_photo);
        numStarsView = (TextView) itemView.findViewById(R.id.post_num_stars);
        bodyView = (TextView) itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(Post post, View.OnClickListener starClickListener) {
        titleView.setText(post.title);
        authorView.setText(post.author);
        numStarsView.setText(String.valueOf(post.starCount));
        bodyView.setText(post.body);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        try {
            mStorageRef.child(profilepic.getContext().getString(R.string.fb_pp_folder)).child((post.uid+"pp")).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {

                    Picasso.with(profilepic.getContext()).load(uri).placeholder(R.drawable.ic_action_account_circle_40).transform(new CircleTransformation()).fit().centerCrop().into(profilepic);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d("Profile pic",exception.getMessage().toString());
                }
            });


        } catch (Exception e) {
            Log.d("User P.P", e.getMessage());
        }

        starView.setOnClickListener(starClickListener);
    }
}
