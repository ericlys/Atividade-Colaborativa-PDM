package com.example.feednoticias_pdm.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.feednoticias_pdm.Noticia;
import com.example.feednoticias_pdm.model.NoticiaEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class NoticiaAdapter extends ArrayAdapter<NoticiaEntity> {

    Context context;
    List<NoticiaEntity> noticias;

    public NoticiaAdapter(Context context, List<NoticiaEntity> objects) {
        super(context, 0, objects); // Nao existe recurso de template da linha
        this.context = context;
        this.noticias = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linha = new LinearLayout(context);
        linha.setOrientation(LinearLayout.VERTICAL);

        final NoticiaEntity noticia = noticias.get(position);

        TextView titulo = new TextView(context);
        titulo.setTypeface(null, Typeface.BOLD);
        titulo.setText(noticia.getTitulo());
        linha.addView(titulo);

        TextView descricao = new TextView(context);
        descricao.setText(noticia.getDescricao());
        linha.addView(descricao);

        TextView autor = new TextView(context);
        autor.setTypeface(null, Typeface.ITALIC);
        autor.setText("Autor: "+noticia.getAutor());
        linha.addView(autor);

        TextView atualizadoEm = new TextView(context);
        atualizadoEm.setTypeface(null, Typeface.ITALIC);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String dataFormatada = dateFormat.format(noticia.getAtualizadoEm()); // Formatando timestamp
        atualizadoEm.setText("Atualiado em: " + dataFormatada);

        linha.addView(atualizadoEm);

        // Setando OnClick ao clicar no item da lista
        linha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Noticia.class);
                intent.putExtra("Noticia", noticia);
                context.startActivity(intent);
            }
        });

        return linha;
    }
}
