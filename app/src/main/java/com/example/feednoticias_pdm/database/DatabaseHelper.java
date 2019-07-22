package com.example.feednoticias_pdm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //nome do banco de dados
    private  static final String BANCO_DADOS ="Login.db";
    private static  int VERSAO = 1;

    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table usuario (email text primary key, nome text, senha text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //inserindo no banco de dados
    public boolean inserir(String nome, String email, String senha){
     SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("email", email);
        contentValues.put("senha", senha);
        long ins = db.insert("usuario",null,contentValues);
        if (ins==-1)return false;
        else return true;
    }

    //checar se o email existe
    public Boolean checarEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuario where email=?",new String[]{email});
        if(cursor.getCount()>0)return false;
        else return true;
    }

    //Fazer autenticação;
    public boolean autenticacao(String email, String senha){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuario where email=? and senha=?", new String[]{email, senha});
        if(cursor.getCount()>0)return true;
        return false;
    }

}

