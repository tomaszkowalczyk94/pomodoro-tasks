package org.tomaszkowalczyk94.commandline.core;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

import java.util.List;

public interface CoreApi {

    @GET("tasks/")
    Call<List<TaskDto>> tasks();

    @POST("tasks/")
    Call<TaskDto> createTask(@Body TaskDto taskDto);

}
