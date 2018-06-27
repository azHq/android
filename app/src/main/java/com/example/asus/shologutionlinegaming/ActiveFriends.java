package com.example.asus.shologutionlinegaming;


import android.content.Intent;
import android.os.Bundle;
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

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class ActiveFriends extends Fragment {

    private Toolbar toolbar;
    private View myMainView;
    private RecyclerView friendList;
    private String currentUsersId;

    FirebaseAuth mAuth;
    DatabaseReference databaseReference,userReference;
    FirebaseUser firebaseUser;
    public  int currentUserPosition;


    public ActiveFriends() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        myMainView=inflater.inflate(R.layout.fragment_all_friends, container, false);

        friendList=(RecyclerView) myMainView.findViewById(R.id.frnd);

        mAuth=FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        if(firebaseUser!=null) currentUsersId=firebaseUser.getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users");



        friendList.setLayoutManager(new LinearLayoutManager(getContext()));





        return myMainView;
    }


    @Override
    public void onStart() {
        super.onStart();



        FirebaseRecyclerAdapter<Friends,FriendsViewHolder> friendsView=
                new FirebaseRecyclerAdapter<Friends,FriendsViewHolder>
                        (

                                Friends.class,
                                R.layout.all_users_displauy,
                                FriendsViewHolder.class,
                                databaseReference

                        )
                {
                    @Override
                    protected void populateViewHolder(FriendsViewHolder viewHolder, Friends model, final int position) {

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

}
