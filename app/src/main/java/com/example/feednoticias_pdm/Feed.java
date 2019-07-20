package com.example.feednoticias_pdm;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.feednoticias_pdm.adapter.NoticiaAdapter;
import com.example.feednoticias_pdm.model.NoticiaEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Feed extends Activity {

    private static final int MENU_CONF_ID = 1;
    private static final int MENU_PERFIL_ID = 2;
    private static final int MENU_SAIR_ID = 3;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Layout base
        FrameLayout rootLayout = new FrameLayout(this);
        FrameLayout.LayoutParams rootParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT
        );
        rootLayout.setLayoutParams(rootParams);

        // Container
        LinearLayout container = new LinearLayout(this);
        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        container.setLayoutParams(containerParams);
        container.setOrientation(LinearLayout.VERTICAL);

        // Toolbar
        Toolbar toolbar = new Toolbar(this);
        ViewGroup.LayoutParams toolbarParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        toolbar.setLayoutParams(toolbarParams);
        toolbar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.toolbarColor)));
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle("Feed");

        // Menu
        Menu menu = toolbar.getMenu();
        menu.add(Menu.NONE, MENU_CONF_ID, Menu.NONE, "Configuração")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // TODO: Ir para atividade de configuracao
                        return true;
                    }
                });

        menu.add(Menu.NONE, MENU_PERFIL_ID, Menu.NONE, "Perfil")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Todo: Ir para tela de perfil
                        return true;
                    }
                });

        menu.add(Menu.NONE, MENU_SAIR_ID, Menu.NONE, "Sair")
                .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        // Todo: Desautenticar usuário antes de voltar para tela de login
                        finish();
                        return true;
                    }
                });

        // ListView
        listView = new ListView(this);
        ViewGroup.LayoutParams listViewParams = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        listView.setLayoutParams(listViewParams);

        container.addView(toolbar);
        container.addView(listView);

        rootLayout.addView(container);
        setContentView(rootLayout);

        // *** APENAS PARA TESTE
        // *** AS NOTICIAS VIRAO DE OUTRA FONTE FUTURAMENTE
        List<NoticiaEntity> noticiaEntities = getNoticiasFromFonte();

        setNoticias(noticiaEntities);
    }

    public void setNoticias (List<NoticiaEntity> noticias) {
        listView.setAdapter(new NoticiaAdapter(this, noticias));
    }

    // *** METODO APENAS PARA INSTANCIAR NOTICIAS DE TESTE
    public List<NoticiaEntity> getNoticiasFromFonte() {
        List<NoticiaEntity> noticias = new ArrayList<>();

        NoticiaEntity noticiaA = new NoticiaEntity();
        noticiaA.setTitulo("19 das 33 das vítimas de incêndio no Japão morreram no acesso ao telhado");
        noticiaA.setDescricao("Os corpos de 19 das 33 pessoas que morreram na quinta-feira (18) em um incêndio...");
        noticiaA.setAutor("João Almeida");
        noticiaA.setTexto("Para compartilhar esse conteúdo, Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade dPara compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.Para compartilhar esse conteúdo, por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.e seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.por favor utilize o link https://www1.folha.uol.com.br/mercado/2019/07/bolsonaro-diz-que-multa-de-40-do-fgts-inibe-criacao-de-empregos.shtml ou as ferramentas oferecidas na página. Textos, fotos, artes e vídeos da Folha estão protegidos pela legislação brasileira sobre direito autoral. Não reproduza o conteúdo do jornal em qualquer meio de comunicação, eletrônico ou impresso, sem autorização da Folhapress (pesquisa@folhapress.com.br). As regras têm como objetivo proteger o investimento que a Folha faz na qualidade de seu jornalismo. Se precisa copiar trecho de texto da Folha para uso privado, por favor logue-se como assinante ou cadastrado.");
        noticiaA.setAtualizadoEm(new Date());

        noticias.add(noticiaA);
        noticias.add(noticiaA);
        noticias.add(noticiaA);

        return noticias;
    }
}
