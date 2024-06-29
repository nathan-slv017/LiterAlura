package com.example.leituraApplication.service;

import com.example.leituraApplication.model.Livro;
import com.example.leituraApplication.repository.LivroRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final ConsumoApi consumoApi;

    @Autowired
    public LivroService(LivroRepository livroRepository, ConsumoApi consumoApi) {
        this.livroRepository = livroRepository;
        this.consumoApi = consumoApi;
    }

    public void buscarLivroPorTitulo(String titulo) {
        JsonObject response = consumoApi.buscarLivroPorTitulo(titulo);
        if (response != null) {
            JsonArray results = response.getAsJsonArray("results");
            if (results.size() > 0) {
                JsonObject livroJson = results.get(0).getAsJsonObject();
                String livroTitulo = livroJson.get("title").getAsString();
                String autor = livroJson.getAsJsonArray("authors").get(0).getAsJsonObject().get("name").getAsString();

                Livro livro = new Livro();
                livro.setTitulo(livroTitulo);
                livro.setAutor(autor);

                livroRepository.save(livro);
                System.out.println("Livro salvo no banco de dados: " + livroTitulo + " por " + autor);
            } else {
                System.out.println("Nenhum livro encontrado com o título: " + titulo);
            }
        }
    }

    public void listarLivrosRegistrados() {
        Iterable<Livro> livros = livroRepository.findAll();
        livros.forEach(livro -> System.out.println("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor()));
    }
}
