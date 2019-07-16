package com.example.feednoticias_pdm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class login extends Activity {

    private Button b1;
    private TextView tv1, tv2;
    private EditText et1, et2;
    private LinearLayout ll;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);


        b1 = new Button(this);
        b1.setText("Login");

        et1 = new EditText(this);
        et2 = new EditText(this);
        tv1 = new TextView(this);
        tv2 = new TextView(this);


        b1.setId(1);
        et1.setId(2); //login
        et2.setId(3);  //senha
        tv1.setId(4);
        tv2.setId(5);


        RelativeLayout.LayoutParams buttonParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.addRule(RelativeLayout.CENTER_VERTICAL);

        RelativeLayout.LayoutParams textParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        textParams.addRule(RelativeLayout.ABOVE, b1.getId());
        textParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textParams.setMargins(0, 0, 0, 80);

        RelativeLayout.LayoutParams viewParams =
                new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);

        viewParams.addRule(RelativeLayout.ABOVE, b1.getId());
        viewParams.addRule(RelativeLayout.ALIGN_LEFT);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());



        tv1.setText("E-mail:");
        tv2.setText("Senha:");

        et1.setWidth(px);
        et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        et2.setWidth(px);
        et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);


// Adicione nossos componentes ao Leyout
        ll.addView(tv1, viewParams);
        ll.addView(et1, textParams);
        ll.addView(tv2,viewParams);
        ll.addView(et2, textParams);
        ll.addView(b1, buttonParams);
        setContentView(ll);

    }
}

