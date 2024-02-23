package com.example.appfinal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DBmeal {

@GET("filter.php")
    Call<MealResponse> searchMealsByCategoria(@Query("i")String categoria);


    @GET("lookup.php")
    Call<MealResponse2> searchMealsById(@Query("i") String id); //esto necesito agarrar el id de la comida en las categorias pra traer la receta e ingredientes

}
