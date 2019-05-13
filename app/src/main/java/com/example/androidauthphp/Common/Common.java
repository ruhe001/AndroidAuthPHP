package com.example.androidauthphp.Common;

import com.example.androidauthphp.Remote.IMyAPI;
import com.example.androidauthphp.Remote.RetrofitClient;

public class Common {
    public static final String BASE_URL="http://topsisfhj.xyz/";

    public static IMyAPI getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(IMyAPI.class);
    }
}

