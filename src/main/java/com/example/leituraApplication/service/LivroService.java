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
                JsonObject livroJsonSobreAutor = livroJson.getAsJsonArray("authors").get(0).getAsJsonObject();

                String livroTitulo = livroJson.get("title").getAsString();
                Integer downloads = livroJson.get("download_count").getAsInt();
                String idioma = livroJson.get("languages").getAsString();

                String autor = livroJsonSobreAutor.get("name").getAsString();
                Integer anoDeNascimentoDoAutor = livroJsonSobreAutor.get("birth_year").getAsInt();
                Integer anoDeFalecimentoDoAutor = livroJsonSobreAutor.get("death_year").getAsInt();

                Livro livro = new Livro();
                livro.setTitulo(livroTitulo);
                livro.setAutor(autor);
                livro.setDownloads(downloads);
                livro.setIdioma(idioma);
                livro.setAnoDeNascimentoDoAltor(anoDeNascimentoDoAutor);
                livro.setAnoDeFalecimentoDoAltor(anoDeFalecimentoDoAutor);

                livroRepository.save(livro);
                System.out.println(livro.toString());
            } else {
                System.out.println("Nenhum livro encontrado com o título: " + titulo);
            }
        }
    }

    public void listarLivrosRegistrados() {
        Iterable<Livro> livros = livroRepository.findAll();
        livros.forEach(livro -> System.out.println(livro));
    }

    public void listaDeAutoresRegistrados() {

    }

    public void autoreVivoEmUmDeterminadoAno() {
    }

    public void livrosEmUmDeterminadoIdioma() {
    }
}
