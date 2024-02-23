package com.example.appfinal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.MealViewHolder> {
        private List<MealCategory> mealCategories;
        private Context context;
        private String receta;



    public  AdaptadorRecycler(List<MealCategory> mealCategories, Context context) {
            this.mealCategories = mealCategories;
            this.context = context;
        }

        @NonNull
        @Override
        public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
            return new MealViewHolder(view);




        }




        @Override
        public void onBindViewHolder(@NonNull MealViewHolder holder, @SuppressLint("RecyclerView") int position) {
            MealCategory mealCategory = mealCategories.get(position);

            // Asigna los valores a los elementos visuales en el diseño de elemento de lista
            holder.nomeComida.setText(mealCategory.getStrMeal());
           // holder.idComida.setText(String.valueOf(mealCategory.getIdMeal()));

            // Carga la imagen de la comida utilizando Glide
            Glide.with(context)
                    .load(mealCategory.getStrMealThumb())
                    .into(holder.imagem);

            holder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    MealCategory mealCategory = mealCategories.get(position); //esto obtiene el elemento que voy a clicar

                    // Crea un intent para abrir la actividad de detalle y pasa los datos necesarios
                    String mealId = String.valueOf(mealCategory.getIdMeal());
                    String thumb=String.valueOf(mealCategory.getStrMealThumb());
                    String nome=String.valueOf(mealCategory.getStrMeal());
                    Intent intent = new Intent(context, DetalleComida.class);
                    intent.putExtra("nome", nome);
                    intent.putExtra("thumb", thumb);
                    intent.putExtra("mealId", mealId);
                    context.startActivity(intent);//pasa solo el id para hacer la llamada del api
                }
            });
    }








        @Override
        public int getItemCount() {
            return mealCategories.size();
        }

        public class MealViewHolder extends RecyclerView.ViewHolder {
            ImageView imagem;
            TextView nomeComida;
           // TextView idComida;

            public MealViewHolder(@NonNull View itemView) {
                super(itemView);
                imagem = itemView.findViewById(R.id.imagemComida);
                nomeComida = itemView.findViewById(R.id.tvNomeComida);
               // idComida = itemView.findViewById(R.id.tvId);
            }
        }

    }

