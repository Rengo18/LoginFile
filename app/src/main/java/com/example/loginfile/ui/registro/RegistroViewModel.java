package com.example.loginfile.ui.registro;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginfile.model.Usuario;
import com.example.loginfile.request.ApiClient;

import java.io.File;

public class RegistroViewModel extends AndroidViewModel {
    public RegistroViewModel(@NonNull Application application) {
        super(application);
    }
    private MutableLiveData<Usuario> mldUsuario;

    public LiveData<Usuario> getMLDUsuario(){
        if(mldUsuario==null){
            mldUsuario= new MutableLiveData<>();

        }
        return mldUsuario;
    }

    public void Mostrar(Context context,File carpeta){
        Usuario usuario= ApiClient.leer(context,carpeta);
        mldUsuario.setValue(usuario);
    }

    public void guardar(Context context,Usuario usuario,File carpeta ){
        ApiClient.guardar(context,usuario,carpeta);
    }
}
