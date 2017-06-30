package swes.swes.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Random;

import swes.swes.R;
import swes.swes.classes.Student;


public class UserAccountActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef ;
    private  String uid;
    private  Student student;
    private FirebaseAuth auth;
    private StorageReference mStorageRef;
    private static  final  int  GALLERY_INTENT = 2;
    private View mUpButton;
    EditText et_name;
    TextView tv_email;
    ImageView iv_pp;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        if (SettingsActivity.theme == 1) {
            setTheme(R.style.DarkAppTheme);
        }
        auth = FirebaseAuth.getInstance();
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        et_name = (EditText)findViewById(R.id.editText_name);
        tv_email = (TextView) findViewById(R.id.editText_email);
        iv_pp= (ImageView)findViewById(R.id.iv_pp);
        mUpButton = findViewById(R.id.action_up);
        mUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSupportNavigateUp();
            }
        });
        if (auth.getCurrentUser() != null) {
            uid=auth.getCurrentUser().getUid();

        }

        progressDialog =new ProgressDialog(this);
        progressDialog.setMessage("Loading Inforamtion");
        progressDialog.show();
        getCurrentStudent();
    }

    public Student getCurrentStudent(){
        final  Student s = new Student();
        myRef = database.getReference(getString(R.string.fb_users));
        mStorageRef = FirebaseStorage.getInstance().getReference();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                s.setEmail(dataSnapshot.child(uid).child(getString(R.string.fb_users_email)).getValue(String.class));
                s.setName(dataSnapshot.child(uid).child(getString(R.string.fb_users_name)).getValue(String.class));
                s.setStudentPicture(dataSnapshot.child(uid).child(getString(R.string.fb_users_pp)).getValue(String.class));
                et_name.setText(s.getName());
                tv_email.setText(s.getEmail());
                try {
                    mStorageRef.child(getString(R.string.fb_pp_folder)).child(s.getStudentPicture()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        public void onSuccess(Uri uri) {
                            Picasso.with(UserAccountActivity.this).load(uri).placeholder(R.drawable.temp_pp).fit().centerCrop().into(iv_pp, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {
                                    progressDialog.dismiss();
                                }

                                @Override
                                public void onError() {

                                }
                            });
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Toast.makeText(UserAccountActivity.this, exception.getMessage().toString(), Toast.LENGTH_LONG).show();
                            // Handle any errors
                        }
                    });
                }catch (Exception e)
                {
                    Log.d("user Acoount class", e.getMessage());

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Firebaseclass", "Failed to read value.", error.toException());
            }
        });
        return s;
    }

   public void saveChanges(View view){
       myRef = database.getReference(getString(R.string.fb_users));
       myRef.child(uid).child(getString(R.string.fb_users_name)).setValue(et_name.getText().toString());
       Toast.makeText(UserAccountActivity.this,getString(R.string.info_changed_msg_suc),Toast.LENGTH_LONG).show();
    }
    public void editPic(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, GALLERY_INTENT);
    }



    public void oldPassword(View view){
        Intent intent = new Intent(UserAccountActivity.this,OldPasswordActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLERY_INTENT ){
            Uri uri = data.getData();
            myRef = database.getReference(getString(R.string.fb_users));
            mStorageRef = FirebaseStorage.getInstance().getReference();
            StorageReference picRef = mStorageRef.child(getString(R.string.fb_pp_folder).toString()).child(auth.getCurrentUser().getUid().toString()+"pp");
            picRef.putFile(uri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        // Get a URL to the uploaded content
                        Uri downloadUrl = taskSnapshot.getDownloadUrl();
                        myRef.child(auth.getCurrentUser().getUid().toString()).child(getString(R.string.fb_users_pp)).setValue(auth.getCurrentUser().getUid().toString()+"pp");
                        Random rand = new Random();
                        int pickedNumber = rand.nextInt(40) + 1;
                        myRef.child(auth.getCurrentUser().getUid().toString()).child("is_changed").setValue(pickedNumber);
                        Toast.makeText(getApplicationContext(),getString(R.string.upload_msg_suc).toString(),Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),getString(R.string.upload_msg_fail).toString(),Toast.LENGTH_LONG).show();
                    }
                });
        }
    }
}
