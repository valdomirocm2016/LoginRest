package com.example.services;



import com.example.entities.Product;
import com.example.entities.User;
import com.example.entities.UserLog;
import com.example.entities.UserLogReturn;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndPoint {

    @POST("users/login")
    Call<UserLogReturn> autenticar(@Body UserLog userLog);

    @POST("users")
    Call<User> cadastrarNovoUsuario(@Body User user);

    @GET("products")
    Call<Product> todosProdutos();
}
