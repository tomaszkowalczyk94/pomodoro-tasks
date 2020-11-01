package org.tomaszkowalczyk94.commandline.core;

import retrofit2.Retrofit;

public class CoreApiFactory {

    public CoreApi create() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .build()
                .create(CoreApi.class);
    }

}
