package com.example.appfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Recetas extends AppCompatActivity {

    private static final String ARG_RECETA = "receta";
    private static final String ARG_MEAL_CATEGORIES = "categories";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetas);

        // Obtén los datos enviados desde Inicio
        List<MealCategory> mealCategories = getIntent().getParcelableArrayListExtra(ARG_MEAL_CATEGORIES);
        MealCategory receta = getIntent().getParcelableExtra(ARG_RECETA);

        // Configura el RecyclerView y el adaptador utilizando los datos recibidos
        RecyclerView recyclerView = findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        AdaptadorRecycler adapter = new AdaptadorRecycler(mealCategories, this);
        recyclerView.setAdapter(adapter);
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
        }else if(item.getItemId()==R.id.receitasSalvas){
            DB comida = null;
            MealCategory c =comida.obtenerComidaDesdeBaseDeDatos();

            Intent intent = new Intent(Recetas.this, ComidaSalva.class);

            intent.putExtra("comida", c);

            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }

}
