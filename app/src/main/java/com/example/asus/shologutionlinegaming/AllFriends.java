package com.example.asus.shologutionlinegaming;


import android.app.ProgressDialog;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;


public class AllFriends extends Fragment {

    private Toolbar toolbar;
    private View myMainView;
    private RecyclerView friendList;
    private String currentUsersId;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference,requestReference,notificationReference;
    FirebaseUser firebaseUser;
    public  int currentUserPosition;
    private ProgressDialog loadingbar,gameStart,requestAccept;

    public String senderName,receiverName;

    public AllFriends() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        myMainView=inflater.inflate(R.layout.fragment_all_friends, container, false);

        friendList=(RecyclerView) myMainView.findViewById(R.id.frnd);

        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser!=null) currentUsersId=firebaseUser.getUid();
        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users");
        notificationReference=FirebaseDatabase.getInstance().getReference().child("Notifications");
        requestReference=FirebaseDatabase.getInstance().getReference();

        friendList.setLayoutManager(new LinearLayoutManager(getContext()));
        loadingbar=new ProgressDialog(getContext());

        gameStart=new ProgressDialog(getContext());







        return myMainView;

    }

    @Override
    public void onStart() {
        super.onStart();



        FirebaseRecyclerAdapter<Friends,FriendsViewHolder> friendsView=
                new FirebaseRecyclerAdapter<Friends, FriendsViewHolder>
                (

                        Friends.class,
                        R.layout.all_users_displauy,
                        FriendsViewHolder.class,
                        databaseReference

                )
        {
            @Override
            protected void populateViewHolder(FriendsViewHolder viewHolder, final Friends model, final int position) {

                if(!currentUsersId.equals(getRef(position).getKey())){

                    viewHolder.setUserName(model.getUserName());
                    viewHolder.setProfileImage(model.getThumb_image());
                    viewHolder.setActivity(model.isActivity());

                }
                else{

                    viewHolder.mView.setVisibility(View.GONE);
                    viewHolder.mView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
                    currentUserPosition=position;

                }


                viewHolder.viewProfile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String id=getRef(position).getKey();

                        Intent t=new Intent(getContext(),UsersProfile.class);
                        t.putExtra("ID",id);
                        startActivity(t);
                    }
                });

                viewHolder.sendRequest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       final String id=getRef(position).getKey();
                        loadingbar.setTitle("Sending friend request");
                        loadingbar.setMessage("Please wait.......");
                        loadingbar.show();


                        requestReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                senderName=dataSnapshot.child("Users").child(currentUsersId).child("userName").getValue().toString();
                                receiverName=dataSnapshot.child("Users").child(id).child("userName").getValue().toString();

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                        //if(model.isActivity()){


                            requestReference.child("Allrequest").child(currentUsersId).child(id).child("request_type").setValue("send")
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {


                                            if(task.isSuccessful()){
                                                requestReference.child("Allrequest").child(id).child(currentUsersId).child("request_type").setValue("receive")
                                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if(task.isSuccessful()){

                                                                    requestReference.child("Allrequest").child(id).child("sender").setValue(currentUsersId);

                                                                    HashMap<String,String> notificationsData=new HashMap<String,String>();
                                                                    notificationsData.put("from",currentUsersId);
                                                                    notificationsData.put("type","request");

                                                                    notificationReference.child(id).push().setValue(notificationsData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                        @Override
                                                                        public void onComplete(@NonNull Task<Void> task) {

                                                                            if(task.isSuccessful()){

                                                                                loadingbar.dismiss();



                                                                                 Toast.makeText(getContext(),"Request sent successfully",Toast.LENGTH_LONG).show();

                                                                                requestReference.child("play").child(currentUsersId+":"+id).child("state").setValue("pending");

                                                                                gameStart.setTitle("Please wait few minutes for starting game");
                                                                                gameStart.setMessage("Request is pending.......");
                                                                                gameStart.show();

                                                                               /*try{
                                                                                   Thread.sleep(10000);

                                                                               }catch (InterruptedException e){

                                                                               }*/

                                                                                requestReference.child("play").addValueEventListener(new ValueEventListener() {
                                                                                    @Override
                                                                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                                                                        String state=dataSnapshot.child(currentUsersId+":"+id).child("state").getValue().toString();

                                                                                        if(state.equals("accept")){

                                                                                            Toast.makeText(getContext(),"Request accept",Toast.LENGTH_LONG).show();
                                                                                            startGame(currentUsersId+":"+id,senderName+":"+receiverName,senderName,"From");


                                                                                        }
                                                                                        else{
                                                                                            gameStart.dismiss();
                                                                                            Toast.makeText(getContext(),"Request is not accept",Toast.LENGTH_LONG).show();
                                                                                        }

                                                                                        gameStart.dismiss();


                                                                                    }

                                                                                    @Override
                                                                                    public void onCancelled(DatabaseError databaseError) {

                                                                                    }
                                                                                });




                                                                            }

                                                                        }
                                                                    });




                                                                }
                                                            }
                                                        });
                                            }
                                        }
                                    });



                        /*}
                        else{
                            loadingbar.dismiss();

                            Toast.makeText(getContext(),"Your friend isn't on network connection",Toast.LENGTH_LONG).show();
                        }*/


                    }
                });

            }
        };

        friendsView.notifyItemRemoved(currentUserPosition);


        friendList.setAdapter(friendsView);



    }

    public static class FriendsViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public Button sendRequest,viewProfile;

        public FriendsViewHolder(View itemView) {
            super(itemView);

            mView=itemView;

            sendRequest=(Button)mView.findViewById(R.id.sendRequest);
            viewProfile=(Button)mView.findViewById(R.id.viewProfile);



        }

        public void setUserName(String name){

            TextView userName=(TextView) mView.findViewById(R.id.username);
            userName.setText(name);
        }

        public void setProfileImage(String image){

            CircleImageView circleImageView=(CircleImageView) mView.findViewById(R.id.allUsersProfileImage);
            if(image!=null&&!image.equals("default_image")) Picasso.get().load(image).placeholder(R.drawable.azaz12).into(circleImageView);
        }

        public void setActivity(boolean online){

            ImageView circleImageForOnline=(ImageView) mView.findViewById(R.id.onlineStatus);
            if(online){
                circleImageForOnline.setVisibility(View.VISIBLE);
            }
            else {

                circleImageForOnline.setVisibility(View.INVISIBLE);
            }
        }


    }

    public void startGame(String idPair,String playerGameId,String otherplayer,String type){

        requestReference.child("play").child(idPair).child(playerGameId).removeValue();

        Intent t=new Intent(getContext(),OnlineGameActivity.class);
        t.putExtra("idPair",idPair);
        t.putExtra("player_session",playerGameId);
        t.putExtra("user_name",receiverName);
        t.putExtra("other_player",otherplayer);
        t.putExtra("login_uid",currentUsersId);
        t.putExtra("request_type",type);

        startActivity(t);



    }




}
