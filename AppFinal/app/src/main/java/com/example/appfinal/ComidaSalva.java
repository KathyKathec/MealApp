package com.example.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class ComidaSalva extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida_salva);





        MealCategory comida = getIntent().getParcelableExtra("comida");

        TextView nombre = findViewById(R.id.nombreTe);
        nombre.setText(comida.getStrMeal());

        ImageView imagenImageView = findViewById(R.id.imagemIm);
        Glide.with(this).load(comida.getStrMealThumb()).into(imagenImageView);

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

            Intent intent = new Intent(ComidaSalva.this, ComidaSalva.class);

            intent.putExtra("comida", c);

            startActivity(intent);


        }
        return super.onOptionsItemSelected(item);
    }
}
