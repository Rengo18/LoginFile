package com.example.loginfile.request;

import android.content.Context;
import android.widget.Toast;

import com.example.loginfile.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ApiClient {


    public  static  void  guardar(Context context, Usuario usuario,File carpeta){
        File archivo=new File(carpeta,"usuario.dat");
        try {
            FileOutputStream fo = new FileOutputStream((archivo));
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            DataOutputStream dos=new DataOutputStream((bo));

            dos.writeLong(usuario.getDni());
            dos.writeUTF(usuario.getNombre());
            dos.writeUTF(usuario.getApellido());
            dos.writeUTF(usuario.getEmail());
            dos.writeUTF(usuario.getPassword());

            bo.flush();
            fo.close();

        }catch (IOException io){

            Toast.makeText(context, "Se produjo un error al guardar el usuario", Toast.LENGTH_SHORT).show();
        }


    }

    public static Usuario leer(Context context,File carpeta){
        File archivo=new File(carpeta,"usuario.dat");
        Usuario usuario=new Usuario();
    if(archivo.exists()){


        try {
            FileInputStream fi = new FileInputStream(archivo);
            BufferedInputStream bi =new BufferedInputStream(fi);
            DataInputStream dis= new DataInputStream(bi);


            usuario.setDni(dis.readLong());
            usuario.setNombre(dis.readUTF());
            usuario.setApellido(dis.readUTF());
            usuario.setEmail(dis.readUTF());
            usuario.setPassword(dis.readUTF());


            fi.close();
            return usuario;

        }catch (IOException io){
            Toast.makeText(context, "Se Produjo un error al intentar recuperar el usuario ", Toast.LENGTH_SHORT).show();
        }
    }
    return  usuario;
    }

    public  static Usuario login(Context context, String mail, String password,File carpeta) {
        File archivo = new File(carpeta, "usuario.dat");
        Usuario usuario = null;
        if (archivo.exists()) {


            try {
                FileInputStream fi = new FileInputStream(archivo);
                BufferedInputStream bi = new BufferedInputStream(fi);
                DataInputStream dis = new DataInputStream(bi);

                long dni=dis.readLong();
                String nombre= dis.readUTF();
                String apellido= dis.readUTF();
                String email= dis.readUTF();
                String pass= dis.readUTF();



                fi.close();
                if(mail.equals(email)&&password.equals(pass)){
                    usuario=new Usuario(dni,apellido,nombre,email,pass);
                    return  usuario;
                }else {
                    return usuario;
                }


            } catch (IOException io) {
                Toast.makeText(context, "Se Produjo un error al intentar recuperar el usuario ", Toast.LENGTH_SHORT).show();
            }


        }
        return usuario;
    }

}
