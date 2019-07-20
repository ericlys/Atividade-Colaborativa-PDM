package com.example.feednoticias_pdm;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.feednoticias_pdm.model.NoticiaEntity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Noticia extends Activity {

    private TextView tv, tv1, tv2, tv3, tv4;
    private LinearLayout ll, l2;
    private ScrollView scrollView;

    private NoticiaEntity noticia = new NoticiaEntity();


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //recuperando noticia
        //receber a notícia da feed activity
        Intent intent = getIntent();
        noticia = (NoticiaEntity) intent.getSerializableExtra("Noticia");
        System.out.println(noticia.toString());

        //criando layout principal da página _ LinearLayout - que funcionará como contêiner para a widget scrollView
        ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        //add toolbar
        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams toolBarParams = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        toolbar.setLayoutParams(toolBarParams);
        toolbar.setBackgroundColor(Color.TRANSPARENT);
        toolbar.setPopupTheme(R.style.Widget_AppCompat_ActionBar);
        toolbar.setTitle(noticia.getTitulo());                                                         //add título da notícia aqui!!
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        toolbar.setTitleTextColor(Color.BLACK);

        ll.addView(toolbar, 0);

        // ScrollView
        scrollView = new ScrollView(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        scrollView.setLayoutParams(layoutParams);
        ll.addView(scrollView);

        //criando layout2 para ficar dentro da scrollView
        l2 = new LinearLayout(this);
        LinearLayout.LayoutParams LinearParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        l2.setOrientation(LinearLayout.VERTICAL);
        l2.setLayoutParams(LinearParams);
        //adiciona LinearLayout em scrollView
        scrollView.addView(l2);

        //
        //

        //textView Título
        tv = new TextView(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv.setId(View.generateViewId());
        tv.setText(noticia.getTitulo());
        tv.setTypeface(Typeface.DEFAULT_BOLD);
        tv.setTextSize(25);
        l2.addView(tv,params);

        //textView1 Autor
        tv1 = new TextView(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv1.setId(View.generateViewId());
        tv1.setText(noticia.getAutor());
        tv1.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));
        tv1.setTextSize(14);
        l2.addView(tv1,params1);

        //textView2 Data
        tv2 = new TextView(this);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv2.setId(View.generateViewId());
        DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
        tv2.setText(dateFormat.format(noticia.getAtualizadoEm()));
        tv2.setTextSize(14);
        l2.addView(tv2,params2);

        //textView3 Descrição
        tv3 = new TextView(this);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv3.setId(View.generateViewId());
        tv3.setText(noticia.getDescricao());
        tv3.setTextSize(20);
        l2.addView(tv3,params3);

        //textView4 Texto
        tv3 = new TextView(this);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        tv3.setId(View.generateViewId());
        tv3.setText(noticia.getTexto());
        tv3.setTextSize(20);
        l2.addView(tv3,params4);





    }
}
