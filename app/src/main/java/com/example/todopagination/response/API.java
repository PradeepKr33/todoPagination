package com.example.todopagination.response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    @GET("todos")
    Call<ResponseTodo> getTodos(@Query("skip") int skip, @Query("limit") int limit, @Query("total") int total);

}
