package com.example.appfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Inicio extends AppCompatActivity {

    private Spinner categorias;
    private Button btnProcurar;

    private List<MealCategory> mealCategories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        categorias = findViewById(R.id.categorias);
        btnProcurar = findViewById(R.id.btnProcura);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.opciones_categorias, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorias.setAdapter(adapter);

        btnProcurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String categoriaSeleccionada = categorias.getSelectedItem().toString();
                String categoria = obtenerCategoria(categoriaSeleccionada);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                DBmeal dBmeal = retrofit.create(DBmeal.class);

                Call<MealResponse> call = dBmeal.searchMealsByCategoria(categoria);

                call.enqueue(new Callback<MealResponse>() {
                    @Override
                    public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "sucesso respom.", Toast.LENGTH_SHORT).show();

                            MealResponse mealResponse = response.body();
                            if (mealResponse != null) {
                                mealCategories = mealResponse.getMeals();
                                if (!mealCategories.isEmpty()) {

                                    MealCategory receta = mealCategories.get(0);
                                    Intent intent = new Intent(Inicio.this, Recetas.class);
                                    intent.putParcelableArrayListExtra("categories", new ArrayList<>(mealCategories));
                                    intent.putExtra("receta", receta);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), "No se encontraron recetas para esta categoría.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "Error al obtener las recetas.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Error en la respuesta del servidor.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<MealResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error en la solicitud: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private String obtenerCategoria(String categoriaSeleccionada) {
        switch (categoriaSeleccionada) {
            case "Beef":
                return "Beef";
            case "Chicken":
                return "Chicken";
            case "Dessert":
                return "Dessert";
            case "Lamb":
                return "Lamb";
            case "Miscellaneous":
                return "Miscellaneous";
            case "Pasta":
                return "Pasta";
            case "Pork":
                return "Pork";
            case "Seafood":
                return "Seafood";
            case "Side":
                return "Side";
            case "Starter":
                return "Starter";
            case "Vegan":
                return "Vegan";
            case "Vegetarian":
                return "Vegetarian";
            case "Breakfast":
                return "Breakfast";
            case "Goat":
                return "Goat";
            default:
                return "";
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            Toast.makeText(getApplicationContext(), "Retornando a home", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getApplicationContext(), Inicio.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
