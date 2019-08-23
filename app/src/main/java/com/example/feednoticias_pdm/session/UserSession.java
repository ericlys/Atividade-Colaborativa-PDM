package com.example.feednoticias_pdm.session;

import android.support.annotation.NonNull;

import com.example.feednoticias_pdm.model.UsuarioEntity;

// Singleton class to controll user login session
public class UserSession {

    // logged in user
    private static UsuarioEntity user;

    public static void startSession(@NonNull UsuarioEntity u){
        user = u;
    }

    public static UsuarioEntity getLoggedUser(){
        return user;
    }

    // log out
    public static void close() {
        user = null;
    }

}
