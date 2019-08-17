package com.example.feednoticias_pdm.Fetch;

import com.example.feednoticias_pdm.model.NoticiaEntity;

import java.util.List;

public interface FetchNoticiaStrategy {
    public List<NoticiaEntity> fetch();
}
