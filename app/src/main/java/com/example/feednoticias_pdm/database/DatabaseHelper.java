package com.example.feednoticias_pdm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.feednoticias_pdm.model.NoticiaEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        db.execSQL("Create table feed (id integer primary key autoincrement not null, titulo text, descricao text, texto text, autor text, atualizadoEm date)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //inserindo no banco de dados usuario
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

    //checar se o email do usuario existe
    public Boolean checarEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuario where email=?",new String[]{email});
        if(cursor.getCount()>0)return false;
        else return true;
    }

    //Fazer autenticação do usuario;
    public boolean autenticacao(String email, String senha){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from usuario where email=? and senha=?", new String[]{email, senha});
        if(cursor.getCount()>0)return true;
        return false;
    }

    //inserindo feed no banco de dados
    public boolean addfeed(NoticiaEntity noticiaEntity){
        // Não insere caso a noticia ja esteja salva
        if(contains(noticiaEntity)) return false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("titulo", noticiaEntity.getTitulo());
        contentValues.put("descricao", noticiaEntity.getDescricao());
        contentValues.put("texto", noticiaEntity.getTexto());
        contentValues.put("autor", noticiaEntity.getAutor());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(noticiaEntity.getAtualizadoEm());

        contentValues.put("atualizadoEm", formattedDate);
        long ins = db.insert("feed",null,contentValues);
        if (ins==-1)return false;
        else return true;
    }

    public boolean contains(NoticiaEntity noticia) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from feed where titulo=? and descricao=?",
                new String[]{noticia.getTitulo(), noticia.getDescricao()});
        return cursor.getCount()>0;
    }

    //buscar feed no banco
    public List<NoticiaEntity> allfeeds(){
        List<NoticiaEntity> ne = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from feed order by id desc", null);

        if(cursor.moveToFirst()){
          do {

              SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
              try {
                  ne.add( new NoticiaEntity(cursor.getString(cursor.getColumnIndex("titulo")),
                                            cursor.getString(cursor.getColumnIndex("descricao")),
                                            cursor.getString(cursor.getColumnIndex("texto")),
                                            cursor.getString(cursor.getColumnIndex("autor")),
                                            formatter.parse(cursor.getString(cursor.getColumnIndex("atualizadoEm")))));
              } catch (ParseException e) {
                  e.printStackTrace();
              }

          }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  ne;
    }


}

