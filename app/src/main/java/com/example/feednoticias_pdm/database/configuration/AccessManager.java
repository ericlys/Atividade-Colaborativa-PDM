package com.example.feednoticias_pdm.database.configuration;

import android.content.Context;
import android.content.SharedPreferences;

public class AccessManager {
    private final static String TOKEN_FILE = "token_manager";
    private final static String EMAIL_KEY = "email";
    private final static String NAME_KEY = "_name_";
    private final static String TOKN_KEY = "_tokn_";
    private final Context ctx;

    public AccessManager(Context ctx){
        this.ctx = ctx;
    }

    public boolean checkToken(){
        SharedPreferences sp = ctx.getSharedPreferences(TOKEN_FILE, Context.MODE_PRIVATE);
        return sp.getString(TOKN_KEY, null) != null;
    }

    public void store(AccessDTO dto){
        if (dto == null) throw new IllegalArgumentException("Mandou nulo");
        SharedPreferences sp = ctx.getSharedPreferences(TOKEN_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(TOKN_KEY, dto.getToken());
        editor.putString(NAME_KEY, dto.getName());
        editor.putString(EMAIL_KEY, dto.getEmail());
        editor.commit();
    }

    public void remove(){
        SharedPreferences sp = ctx.getSharedPreferences(TOKEN_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(TOKN_KEY);
        editor.remove(NAME_KEY);
        editor.remove(EMAIL_KEY);
        editor.commit();
    }

    public AccessDTO get(){
        if (checkToken()) {
            SharedPreferences sp = ctx.getSharedPreferences(TOKEN_FILE, Context.MODE_PRIVATE);
            String t  = sp.getString(TOKN_KEY, null);
            String n  = sp.getString(NAME_KEY, null);
            String c = sp.getString(EMAIL_KEY, null);
            AccessDTO dto = new AccessDTO();
            dto.setEmail(c);
            dto.setName(n);
            dto.setToken(t);
            return dto;
        }
        return null;
    }


}
