package com.example.leituraApplication.service;

import com.example.leituraApplication.model.Livro;
import com.example.leituraApplication.repository.LivroRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                String idioma = livroJson.getAsJsonArray("languages").get(0).getAsString();

                String autor = livroJsonSobreAutor.get("name").getAsString();
                Integer anoDeNascimentoDoAutor = livroJsonSobreAutor.get("birth_year").getAsInt();
                Integer anoDeFalecimentoDoAutor = livroJsonSobreAutor.get("death_year").getAsInt();

                Livro livro = new Livro();
                livro.setTitulo(livroTitulo);
                livro.setAutor(autor);
                livro.setDownloads(downloads);
                livro.setIdioma(idioma);
                livro.setAnoDeNascimentoDoAutor(anoDeNascimentoDoAutor);
                livro.setAnoDeFalecimentoDoAutor(anoDeFalecimentoDoAutor);

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
        List<String> autores = livroRepository.findDistinctAutores();
        autores.forEach(System.out::println);
    }

    public void autoreVivoEmUmDeterminadoAno(Integer ano) {
        List<String> autoresVivos = livroRepository.findAutoresVivosEmAno(ano);
        if (autoresVivos.isEmpty()) {
            System.out.println("Nenhum autor estava vivo no ano: " + ano);
        } else {
            autoresVivos.forEach(System.out::println);
        }
    }

    public void livrosEmUmDeterminadoIdioma(String idioma) {
        List<String> listaDeLivros = livroRepository.findTitulosPorIdioma(idioma);
        listaDeLivros.forEach(System.out::println);
    }
}
