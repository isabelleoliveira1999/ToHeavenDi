package com.isa.silva.idizimo.Respo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.isa.silva.idizimo.Utils.Constants.URL_WEBSERVICE;

public class ConectApi{

    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_WEBSERVICE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}

