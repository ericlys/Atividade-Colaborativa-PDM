package com.example.feednoticias_pdm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.feednoticias_pdm.database.DatabaseHelper;
import com.example.feednoticias_pdm.database.configuration.AccessDTO;
import com.example.feednoticias_pdm.database.configuration.AccessManager;
import com.example.feednoticias_pdm.model.UsuarioEntity;

public class Perfil extends Activity {

    private TextView tv1, tv2, tv3,tv4;
    private LinearLayout ll;
    private Button b1;
    private RelativeLayout relativeLayout;
    private DatabaseHelper db;

    private UsuarioEntity user;

    @TargetApi(Build.VERSION_CODES.N)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AccessDTO dto;
        AccessManager tm = new AccessManager(this);
        dto = tm.get();
        //instanciando conexâo
        db = new DatabaseHelper(this);
        user = db.buscarUsuario(dto.getEmail());
        if(user == null){
            // caso nao exista usuario salvo na sessao
            // tratar aqui
            finish();
        }

        //criando layout principal da página
        relativeLayout = new RelativeLayout(this);
        setContentView(relativeLayout);

        //criando layout secundario na página para conter toda a extrutura
        ll = new LinearLayout(this);
        Display display = getWindowManager().getDefaultDisplay();
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setPadding(50,150,50,50);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(View.TEXT_ALIGNMENT_CENTER);
        ll.setId(View.generateViewId());
        relativeLayout.addView(ll);


        //add toolbar
        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams toolBarParams = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        toolbar.setLayoutParams(toolBarParams);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbarColor)));
        toolbar.setTitle("Perfil");
        relativeLayout.addView(toolbar, 0);//título da página

        //botão de voltar para a página anterior
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        toolbar.setTitleTextColor(Color.BLACK);

        //textView Email
        tv1 = new TextView(this);
        tv1.setId(View.generateViewId());
        tv1.setText("Email:");
        tv1.setTextSize(20);
        ll.addView(tv1);

        //textView Email
        tv2 = new TextView(this);
        tv2.setId(View.generateViewId());
        tv2.setText(user.getEmail());
        tv2.setTextSize(20);
        ll.addView(tv2);

        //textView Nome
        tv3 = new TextView(this);
        tv3.setId(View.generateViewId());
        tv3.setText("Nome:");
        tv3.setPadding(0,25,100,0);
        tv3.setTextSize(20);
        ll.addView(tv3);

        //textView Nome
        tv4 = new TextView(this);
        tv4.setId(View.generateViewId());
        tv4.setText(user.getNome());
        tv4.setTextSize(20);
        tv4.setPadding(0,0,0,100);
        ll.addView(tv4);

        //botão Atualizar dados
        b1 = new Button(this);
        b1.setId(View.generateViewId());
        b1.setText("Atulizar dados");
        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        //buttonParams.addRule(RelativeLayout.BELOW, et2.getId());
        ll.addView(b1);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Perfil.this, Editar.class));
                finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        tv2.setText(user.getEmail());
        tv4.setText(user.getNome());
    }

}
