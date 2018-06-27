package com.example.asus.shologutionlinegaming;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();

        String id = FirebaseInstanceId.getInstance().getToken();

        for (int i = 0; i < 1000; i++)
            System.out.println("firebase id" + id);
    }
}
