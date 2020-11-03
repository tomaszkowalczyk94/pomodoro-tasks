package org.tomaszkowalczyk94.commandline.core;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoreApiFactory {

    public CoreApi create() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(CoreApi.class);
    }

}
