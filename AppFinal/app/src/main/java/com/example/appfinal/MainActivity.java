package com.example.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //usuario Katherin
    //senha 1234
    private EditText usuario;
    private EditText senha;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            usuario = findViewById(R.id.usuario);
            senha = findViewById(R.id.senha);
            btnLogin = findViewById(R.id.btnLogin);




            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String usu=usuario.getText().toString();
                    String senh=senha.getText().toString();

                    if(usu.equals("Katherin") && senh.equals("1234")){
                        Intent intent = new Intent(MainActivity.this, Inicio.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MainActivity.this, "Senha ou usuario errado", Toast.LENGTH_SHORT).show();
                    }




                }
            });
    }


}