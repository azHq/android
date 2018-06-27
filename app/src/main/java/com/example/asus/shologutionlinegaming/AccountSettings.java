package com.example.asus.shologutionlinegaming;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import id.zelory.compressor.Compressor;


public class AccountSettings extends AppCompatActivity {


    Button changeProfile,changeStatus,changeUserName;
    public static  final int Gallery_Pick=1;

    CircleImageView circleImageView;
    TextView status,userName;
    StorageReference storageReference,filePath,storageRefForThum;

    Bitmap compressBitmap;

    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private  FirebaseUser firebaseUser;

    private ProgressDialog loadingbar;


    String currentUserUID;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference(),userReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);


        loadingbar=new ProgressDialog(this);

        changeUserName=(Button)findViewById(R.id.changeName);
        changeStatus=(Button)findViewById(R.id.button12);
        changeProfile=(Button)findViewById(R.id.button11);

        circleImageView=(CircleImageView) findViewById(R.id.circleImageView);
        status=(TextView) findViewById(R.id.textView23);
        userName=(TextView) findViewById(R.id.textView24);

        storageReference= FirebaseStorage.getInstance().getReference().child("Profile_Image");
        storageRefForThum=FirebaseStorage.getInstance().getReference().child("thumb_Image");

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(getApplicationContext());
        mAuth=FirebaseAuth.getInstance();

        firebaseUser=mAuth.getCurrentUser();


        currentUserUID=firebaseUser.getUid();
        userReference=databaseReference.child("Users").child(currentUserUID);

        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                String image=dataSnapshot.child("profile_image").getValue().toString();
                String name=dataSnapshot.child("userName").getValue().toString();
                String stat=dataSnapshot.child("status").getValue().toString();

                status.setText(stat);
                userName.setText(name);
                if(image!=null&&!image.equals("default_image"))Picasso.get().load(image).placeholder(R.drawable.azaz12).into(circleImageView);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        changeUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder=new AlertDialog.Builder(AccountSettings.this);

                LayoutInflater inflater=(AccountSettings.this).getLayoutInflater();

                final View  v=inflater.inflate(R.layout.log_in,null);

                builder.setView(v);

                final EditText name=(EditText) v.findViewById(R.id.editText);

                builder.setTitle("Change UserName");
                builder.setMessage("Enter your Name");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String s=name.getText().toString();
                       if(s.length()!=0) {

                           userName.setText(s);
                           currentUserUID = firebaseUser.getUid();
                           databaseReference.child("Users").child(currentUserUID).child("userName").setValue(s);
                           Toast.makeText(AccountSettings.this,"Successfully changed your profile name",Toast.LENGTH_LONG).show();
                       }
                       else{

                           Toast.makeText(AccountSettings.this,"Please enter your name",Toast.LENGTH_LONG).show();
                       }
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent t=new Intent(getApplicationContext(),AccountSettings.class);
                        startActivity(t);
                        finish();
                    }
                });

                builder.create();
                builder.show();


            }
        });

        changeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent galleryIntent=new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,Gallery_Pick);



            }
        });

        changeStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder=new AlertDialog.Builder(AccountSettings.this);

                LayoutInflater inflater=(AccountSettings.this).getLayoutInflater();

                final View  v=inflater.inflate(R.layout.status,null);

                builder.setView(v);

                final EditText status=(EditText) v.findViewById(R.id.editText6);

                builder.setTitle("Change Status");
                builder.setMessage("Enter Your New Status");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String s=status.getText().toString();
                       if(s.length()!=0)
                       {
                           status.setText(s);
                           currentUserUID = firebaseUser.getUid();
                           databaseReference.child("Users").child(currentUserUID).child("status").setValue(s);
                           Toast.makeText(AccountSettings.this,"Successfully changed your profile name",Toast.LENGTH_LONG).show();
                       }
                       else{

                           Toast.makeText(AccountSettings.this,"Please enter your Status",Toast.LENGTH_LONG).show();
                       }


                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Intent t=new Intent(getApplicationContext(),AccountSettings.class);
                        startActivity(t);
                        finish();
                    }
                });

                builder.create();
                builder.show();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==Gallery_Pick&&resultCode==RESULT_OK&&data!=null){

            Uri imageUri=data.getData();

            CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
                    .setAspectRatio(1,1)
                    .start(this);

        }

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK)
            {

                loadingbar.setTitle("Updating profile image");
                loadingbar.setMessage("Please wait.......!");
                loadingbar.show();

                Uri resultUri = result.getUri();


                File filePathForCompress=new File(resultUri.getPath());

                try{
                    compressBitmap=new Compressor(this)
                                    .setMaxHeight(200)
                                    .setMaxWidth(200)
                                    .setQuality(50)
                                    .compressToBitmap(filePathForCompress);

                }catch(IOException e){

                    e.printStackTrace();
                }

                ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                compressBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArray);

                final byte[] thumByte=byteArray.toByteArray();


                currentUserUID=firebaseUser.getUid();
                StorageReference filePath=storageReference.child(currentUserUID+".jpg");
                final StorageReference thumbPath=storageRefForThum.child(currentUserUID+".jpg");



                filePath.putFile(resultUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                        if(task.isSuccessful()){




                            final String downloadUrl=task.getResult().getDownloadUrl().toString();
                            UploadTask uploadTask=thumbPath.putBytes(thumByte);



                            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> thumb_task) {


                                    String thumbDownloadUrl=thumb_task.getResult().getDownloadUrl().toString();
                                    DatabaseReference imageReferenceforThumb=databaseReference.child("Users").child(currentUserUID).child("thumb_image");

                                    imageReferenceforThumb.setValue(thumbDownloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if(task.isSuccessful()){

                                                loadingbar.dismiss();

                                            }

                                        }
                                    });
                                }
                            });


                            DatabaseReference imageReference=databaseReference.child("Users").child(currentUserUID).child("profile_image");

                            imageReference.setValue(downloadUrl).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){

                                        loadingbar.dismiss();
                                        Toast.makeText(getApplicationContext(),"Image uploaded successfully",Toast.LENGTH_LONG).show();
                                    }

                                }
                            });



                        }
                        else{

                            loadingbar.dismiss();

                            Toast.makeText(getApplicationContext(),"Image is not uploaded",Toast.LENGTH_LONG).show();
                        }
                    }
                });



            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }









}
