package com.example.individualassignment3;

import com.example.individualassignment3.Entities.CNResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CNService {
    //Initialise interface and ensure "dev" category is retrieved via GET
    @GET("/jokes/random?category=dev")
    Call<CNResponse> getValue();
}
