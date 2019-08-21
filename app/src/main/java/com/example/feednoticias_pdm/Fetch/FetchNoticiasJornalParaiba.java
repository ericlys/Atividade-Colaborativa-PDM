package com.example.feednoticias_pdm.Fetch;

import android.text.Html;

import com.example.feednoticias_pdm.model.NoticiaEntity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FetchNoticiasJornalParaiba implements FetchNoticiaStrategy {

    @Override
    public List<NoticiaEntity> fetch() {
        ArrayList<NoticiaEntity> noticias = new ArrayList<>();

        try{
            URL url = new URL("http://www.jornaldaparaiba.com.br/feed");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("item");

            int numDeElementos = nodelist.getLength();

            for(int i = 0; i < numDeElementos; i++){
                Element item = (Element) nodelist.item(i);

                Node tituloTag = item.getElementsByTagName("title").item(0);
                String titulo = tituloTag.getTextContent();

                Node sumarioTag = item.getElementsByTagName("description").item(0);
                String sumario = sumarioTag.getTextContent();

                Node autorTag = item.getElementsByTagName("dc:creator").item(0);
                String autor = autorTag.getTextContent();

                NoticiaEntity noticia = new NoticiaEntity();
                noticia.setTitulo(Html.fromHtml(titulo).toString());
                noticia.setDescricao(Html.fromHtml(sumario).toString());
                noticia.setTexto("---"); // Não possui conteudo, apenas descricao
                noticia.setAutor(autor);
                noticia.setAtualizadoEm(new Date());

                noticias.add(noticia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return noticias;
    }

}
