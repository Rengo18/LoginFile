package com.example.loginfile.ui.login;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginfile.model.Usuario;
import com.example.loginfile.request.ApiClient;

import java.io.File;

public class MainViewModel extends AndroidViewModel {
    private MutableLiveData<Usuario> mldUsuario;
    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Usuario> getMLDUsuario(){
        if(mldUsuario==null){
            mldUsuario= new MutableLiveData<>();

        }
        return mldUsuario;
    }

    public void login (Context context, String email, String passord, File carpeta){
        Usuario u = ApiClient.login(context,email,passord,carpeta);
        mldUsuario.setValue(u);
    }
}
