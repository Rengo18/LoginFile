package com.example.loginfile.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.loginfile.R;
import com.example.loginfile.model.Usuario;
import com.example.loginfile.ui.login.MainActivity;
import com.example.loginfile.ui.login.MainViewModel;

import java.io.File;

public class RegistroActivity extends AppCompatActivity {
    int accion;
    EditText dni;
    EditText nombre;
    EditText apellido;
    EditText email;
    EditText password;
    TextView error;
    RegistroViewModel registroViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
        accion=1;
        registroViewModel.getMLDUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni()+"");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getEmail());
                password.setText(usuario.getPassword());
            }
        });
        if(accion==getIntent().getExtras().getInt("valido")){
            mostrarUsuario();
        }
    }

    public void inicializar(){
        dni=findViewById(R.id.dni);
        nombre=findViewById(R.id.nombre);
        apellido= findViewById(R.id.apellido);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        error= findViewById(R.id.error);
        registroViewModel =  ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroViewModel.class);
    }
    public void mostrarUsuario(){
        File carpeta=getFilesDir();
        registroViewModel.Mostrar(getApplicationContext(),carpeta);
    }

    public void guardar(View view){
        File carpeta =getFilesDir();
        Usuario u = new Usuario();
        if(!dni.getText().toString().isEmpty()&&!nombre.getText().toString().isEmpty() && !apellido.getText().toString().isEmpty() && !email.getText().toString().isEmpty()&& !password.getText().toString().isEmpty()){
            u.setDni(Long.parseLong(dni.getText().toString()));
            u.setNombre(nombre.getText().toString());
            u.setApellido(apellido.getText().toString());
            u.setEmail(email.getText().toString());
            u.setPassword(password.getText().toString());
            registroViewModel.guardar(getApplicationContext(),u,carpeta);
            error.setVisibility(View.INVISIBLE);
            Intent login = new Intent(this, MainActivity.class);

            startActivity(login);

        }else{
            error.setVisibility(View.VISIBLE);
            error.setText("complete todos los campos");
        }

    }
}
