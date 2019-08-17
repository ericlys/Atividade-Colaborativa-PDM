package com.example.feednoticias_pdm.Fetch;

import android.util.Log;

import com.example.feednoticias_pdm.model.NoticiaEntity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class FetchNoticiasDiarioDoSertao implements  FetchNoticiaStrategy{

    @Override
    public List<NoticiaEntity> fetch() {
        ArrayList<NoticiaEntity> noticias = new ArrayList<>();

        try{
            URL url = new URL("https://www.diariodosertao.com.br/feed/atom");

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

            NodeList nodelist = doc.getElementsByTagName("entry");

            int numDeElementos = nodelist.getLength();

            for(int i = 0; i < numDeElementos; i++){
                Element item = (Element) nodelist.item(i);

                Node tituloTag = item.getElementsByTagName("title").item(0);
                String titulo = tituloTag.getTextContent();

                Node sumarioTag = item.getElementsByTagName("summary").item(0);
                String sumario = sumarioTag.getTextContent();

                Node conteudoTag = item.getElementsByTagName("content").item(0);
                String conteudo = conteudoTag.getTextContent();

                Node autorTag = item.getElementsByTagName("name").item(0);
                String autor = autorTag.getTextContent();

                Node atualizadoTag = item.getElementsByTagName("updated").item(0);
                String atualizadoEm = atualizadoTag.getTextContent().substring(0,10);
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date date = format.parse(atualizadoEm);

                NoticiaEntity noticia = new NoticiaEntity();
                noticia.setTitulo(titulo);
                noticia.setDescricao(sumario);
                noticia.setTexto(conteudo);
                noticia.setAutor(autor);
                noticia.setAtualizadoEm(date);

                noticias.add(noticia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return noticias;
    }

}
