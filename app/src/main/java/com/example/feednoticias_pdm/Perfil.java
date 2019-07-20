package com.example.feednoticias_pdm;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toolbar;

public class Perfil extends Activity {

    private TextView tv, tv1, tv2, tv3;
    private LinearLayout ll;
    private RelativeLayout relativeLayout;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //criando layout principal da página
        relativeLayout = new RelativeLayout(this);
        setContentView(relativeLayout);

        //criando layout secundario na página para conter toda a extrutura
        ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setId(View.generateViewId());
        ll.setBackgroundColor(Color.rgb(255,25,36));
        relativeLayout.addView(ll);


        //add toolbar
        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams toolBarParams = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        toolbar.setLayoutParams(toolBarParams);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbarColor)));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Perfil do usuário");
        ll.addView(toolbar, 0);//título da página

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
        tv = new TextView(this);
        tv.setId(View.generateViewId());
        tv.setText("Email:");
        tv.setTextSize(20);
        ll.addView(tv);

    }
}
