package com.example.feednoticias_pdm;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.feednoticias_pdm.database.DatabaseHelper;
import com.example.feednoticias_pdm.model.UsuarioEntity;
import com.example.feednoticias_pdm.session.UserSession;

public class Editar extends Activity {

    private DatabaseHelper db;
    private Button b1;
    private EditText et1, et2, et3, et4;
    private LinearLayout ll;

    private UsuarioEntity user;

    @TargetApi(Build.VERSION_CODES.N)
    @android.support.annotation.RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = UserSession.getLoggedUser();
        if(user == null){
            finish();
        }

        ll = new LinearLayout(this);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll);

        //instanciando conexâo
        db = new DatabaseHelper(this);

        //add toolbar
        Toolbar toolbar = new Toolbar(this);
        LinearLayout.LayoutParams toolBarParams = new LinearLayout.LayoutParams(
                Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT);
        toolbar.setLayoutParams(toolBarParams);
        toolbar.setBackgroundColor(Color.rgb(96,96,96));
        toolbar.setPopupTheme(R.style.Widget_AppCompat_ActionBar);
        toolbar.setVisibility(View.VISIBLE);
        toolbar.setTitle("Editar Perfil");
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });
        toolbar.setTitleTextColor(Color.WHITE);

        ll.addView(toolbar, 0);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);

        //editText Email
        et2 = new EditText(this);
        et2.setId(View.generateViewId());
        et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        et2.setHint("Email");
        et2.setText(user.getEmail());
        et2.setEnabled(false);
        ll.addView(et2, layoutParams);

        //editText Nome
        et1 = new EditText(this);
        et1.setId(View.generateViewId());
        et1.setInputType(InputType.TYPE_CLASS_TEXT);
        et1.setHint("Nome");
        et1.setText(user.getNome());
        ll.addView(et1, layoutParams);

        //editText Senha
        et3 = new EditText(this);
        et3.setId(View.generateViewId());
        et3.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et3.setHint("Nova Senha");
        ll.addView(et3, layoutParams);

        //editText Confirmar Senha
        et4 = new EditText(this);
        et4.setId(View.generateViewId());
        et4.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et4.setHint("Confirmar nova senha");
        ll.addView(et4, layoutParams);

        //botão Editar
        b1 = new Button(this);
        b1.setId(View.generateViewId());
        b1.setText("Salvar");
        ll.addView(b1, layoutParams);

        //add funcionalidade no botão cadastro
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = et1.getText().toString(); //nome
                String email = et2.getText().toString(); //email
                String novaSenha = et3.getText().toString(); //senha
                String confNovaSenha = et4.getText().toString(); //senha2
                if(nome.equals("")){
                    Toast.makeText(getApplicationContext(), "Nome vazio",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!novaSenha.equals(confNovaSenha)){
                    Toast.makeText(getApplicationContext(), "Senhas nao correspondem", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Caso atualizar senha
                if(!novaSenha.isEmpty()){
                    user.setSenha(novaSenha);
                }

                user.setNome(nome);
                db.atualizarConta(user);
                Toast.makeText(getApplicationContext(), "Perfil salvo", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
}