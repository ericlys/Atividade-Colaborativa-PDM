package com.example.feednoticias_pdm.model;

import java.io.Serializable;
import java.util.Date;

public class NoticiaEntity implements Serializable {

    private String titulo;
    private String descricao;
    private String texto;
    private String autor;
    private Date atualizadoEm;

    public NoticiaEntity() {
    }

    public NoticiaEntity(String titulo, String descricao, String texto) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.texto = texto;
    }

    public NoticiaEntity(String titulo, String descricao, String texto, String autor, Date atualizadoEm) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.texto = texto;
        this.autor = autor;
        this.atualizadoEm = atualizadoEm;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getAtualizadoEm() {
        return atualizadoEm;
    }

    public void setAtualizadoEm(Date atualizadoEm) {
        this.atualizadoEm = atualizadoEm;
    }
}
