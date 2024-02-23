package com.example.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetalleComida extends AppCompatActivity {

    private TextView instrucciones;
    private TextView nombreComida;
    private ImageView imagem;
private Button salvar;
    String receta;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_comida);

        salvar=findViewById(R.id.salvar);

        instrucciones = findViewById(R.id.instrucciones);
        nombreComida= findViewById(R.id.nomeComida);
        imagem= findViewById(R.id.imagemComidaIn);


        nombreComida.setText(getIntent().getStringExtra("nome"));
        Glide.with(this)
                .load(getIntent().getStringExtra("thumb"))
                .into(imagem);


        contenidoDeApi();

        DB db = new DB(this);





        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = nombreComida.getText().toString();
                String imagen = getIntent().getStringExtra("thumb");

                // Guardar datos en la base de datos
                long result = db.insertComida(nombre, imagen);
                if (result != -1) {
                    Toast.makeText(getApplicationContext(), "Comida guardada en la base de datos", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Error al guardar la comida en la base de datos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }









protected void contenidoDeApi(){

        id=getIntent().getStringExtra("mealId");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.themealdb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DBmeal dBmeal = retrofit.create(DBmeal.class);

        Call<MealResponse2> call = dBmeal.searchMealsById(id);
        call.enqueue(new Callback<MealResponse2>() {
            @Override
            public void onResponse(Call<MealResponse2> call, Response<MealResponse2> response) {
                if (response.isSuccessful()) {
                    MealResponse2 mealResponse = response.body();
                    String x=mealResponse.toString();
                    if (mealResponse != null && mealResponse.getComida() != null) {
                        Meal me=mealResponse.getComida();
                        receta = me.getStrInstructions();
                        String n= me.getStrMeal();
                        instrucciones.setText(receta);
                        Toast.makeText(getApplicationContext(), "Se encontró la receta.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "No se encontró la receta para esta comida.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Error al obtener la receta.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MealResponse2> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error en la respuesta del servidor.", Toast.LENGTH_SHORT).show();
            }
        });


    } @Override
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
        }else if(item.getItemId()==R.id.receitasSalvas){
            DB comida = null;
            MealCategory c =comida.obtenerComidaDesdeBaseDeDatos();

            Intent intent = new Intent(DetalleComida.this, ComidaSalva.class);

            intent.putExtra("comida", c);

            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }



    }






