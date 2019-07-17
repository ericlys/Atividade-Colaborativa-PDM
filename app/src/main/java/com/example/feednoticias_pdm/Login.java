package com.example.feednoticias_pdm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Login extends Activity {

    private Button b1,b2;
    private TextView tv, tv1, tv2, tv3;
    private EditText et1, et2;
    private RelativeLayout relativeLayout;



    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 250, r.getDisplayMetrics());

        relativeLayout = new RelativeLayout(this);

        //textView Título
        tv = new TextView(this);
        tv.setId(View.generateViewId());
        tv.setText("Login");
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        tv.setTextSize(70);
        layoutParams.setMargins(100, 10,0,0);
        relativeLayout.addView(tv, layoutParams);

        //textView1 Email
        tv1 = new TextView(this);
        tv1.setId(View.generateViewId());
        tv1.setText("E-mail:");
        RelativeLayout.LayoutParams lp0 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp0.setMargins(120, 250,0,0);
        relativeLayout.addView(tv1, lp0);

        //editText Email
        et1 = new EditText(this);
        et1.setId(View.generateViewId());
        et1.setWidth(px);
        et1.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp.addRule(RelativeLayout.BELOW, tv1.getId());
        relativeLayout.addView(et1, lp);

        //textView2 Senha
        tv2 = new TextView(this);
        tv2.setId(View.generateViewId());
        tv2.setText("Senha:");
        RelativeLayout.LayoutParams lp2 = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.addRule(RelativeLayout.BELOW, et1.getId());
        lp2.setMargins(120, 30,0,0);
        relativeLayout.addView(tv2, lp2);

        //editText Senha
        et2 = new EditText(this);
        et2.setId(View.generateViewId());
        et2.setWidth(px);
        et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        RelativeLayout.LayoutParams lp3 = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp3.addRule(RelativeLayout.CENTER_HORIZONTAL);
        lp3.addRule(RelativeLayout.BELOW, tv2.getId());

        relativeLayout.addView(et2, lp3);

        //botão login
        b1 = new Button(this);
        b1.setId(View.generateViewId());
        b1.setText("Entrar");
        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.BELOW, et2.getId());
        relativeLayout.addView(b1, buttonParams);

        //textView3 Não possui conta
        tv3 = new TextView(this);
        tv3.setId(View.generateViewId());
        tv3.setText("Não possui conta. ");
        RelativeLayout.LayoutParams lp4 = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp4.addRule(RelativeLayout.BELOW, b1.getId());
        lp4.setMargins(120, 50,0,0);
        relativeLayout.addView(tv3, lp4);

        //botão Cadastre-se
        b2 = new Button(this);
        b2.setId(View.generateViewId());
        b2.setText("Cadastre-se");
        RelativeLayout.LayoutParams buttonParams2 =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams2.addRule(RelativeLayout.ALIGN_BASELINE, tv3.getId());
        buttonParams2.setMargins(350, 0,0,0);
        b2.setBackground(null);
        b2.setTextColor(Color.BLUE);

        relativeLayout.addView(b2, buttonParams2);


        //ir para tela de cadastro
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Cadastro.class));
            }
        });

        setContentView(relativeLayout);

    }


}
