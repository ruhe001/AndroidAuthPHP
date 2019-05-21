package com.example.jodierizkyskripsi.Common;

import com.example.jodierizkyskripsi.Remote.IMyAPI;
import com.example.jodierizkyskripsi.Remote.RetrofitClient;

public class Common {
    public static final String BASE_URL="http://topsisfhj.xyz/";

    public static IMyAPI getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(IMyAPI.class);
    }
}

