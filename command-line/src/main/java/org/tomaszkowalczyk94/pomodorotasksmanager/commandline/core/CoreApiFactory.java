package org.tomaszkowalczyk94.pomodorotasksmanager.commandline.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dongliu.gson.GsonJava8TypeAdapterFactory;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CoreApiFactory {

    public CoreApi create() {
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8080/")
                .addConverterFactory(GsonConverterFactory.create(createGson()))
                .build()
                .create(CoreApi.class);
    }

    private Gson createGson() {
        return new GsonBuilder().registerTypeAdapterFactory(new GsonJava8TypeAdapterFactory()).create();
    }

}
