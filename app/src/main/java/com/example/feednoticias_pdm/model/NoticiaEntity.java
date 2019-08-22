package com.example.feednoticias_pdm.model;

import java.io.Serializable;
import java.util.Date;

public class NoticiaEntity implements Serializable {

    private String titulo;
    private String descricao;
    private String texto;
    private String autor;
    private Date atualizadoEm = new Date();

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NoticiaEntity)) return false;

        NoticiaEntity that = (NoticiaEntity) o;

        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;
        if (descricao != null ? !descricao.equals(that.descricao) : that.descricao != null)
            return false;
        if (texto != null ? !texto.equals(that.texto) : that.texto != null) return false;
        return autor != null ? autor.equals(that.autor) : that.autor == null;
    }

    @Override
    public int hashCode() {
        int result = titulo != null ? titulo.hashCode() : 0;
        result = 31 * result + (descricao != null ? descricao.hashCode() : 0);
        result = 31 * result + (texto != null ? texto.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        return result;
    }
}
