package com.example.loginfile.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginfile.R;
import com.example.loginfile.model.Usuario;
import com.example.loginfile.ui.registro.RegistroActivity;


import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Button registrar ;
    private Button loginbtn;
    private EditText email;
    private EditText password;
    private TextView error;

    private MainViewModel mainViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        mainViewModel.getMLDUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                if(usuario!=null ){
                    Intent intent = new Intent(getApplicationContext(), RegistroActivity.class);
                    intent.putExtra("valido",1);
                    startActivity(intent);
                }else{
                    error.setVisibility(View.VISIBLE);
                    error.setText("Ese usuario no existe");
                }
            }
        });
    }
    public void inicializar(){
        loginbtn= findViewById(R.id.login);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        error= findViewById(R.id.error);

        mainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
    }

    public void Registrar (View view){
        Intent registrar = new Intent(this, RegistroActivity.class);
        registrar.putExtra("registrar",0);
        startActivity(registrar);
    }

    public void login (View view){
        File carpeta=getFilesDir();
        String emailText = email.getText().toString();
        String passwordText = password.getText().toString();
        if(!emailText.isEmpty() && !passwordText.isEmpty()){
            mainViewModel.login(this,emailText,passwordText,carpeta);
        }else{
            error.setVisibility(View.VISIBLE);
            error.setText("complete los dos campos para logiarse");
        }


    }
}
