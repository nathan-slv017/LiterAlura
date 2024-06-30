package com.example.leituraApplication.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String autor;
    private Integer downloads;
    private String idioma;
    private Integer anoDeNascimentoDoAutor;
    private Integer anoDeFalecimentoDoAutor;

    public Integer getAnoDeNascimentoDoAutor() {
        return anoDeNascimentoDoAutor;
    }

    public void setAnoDeNascimentoDoAutor(Integer anoDeNascimentoDoAutor) {
        this.anoDeNascimentoDoAutor = anoDeNascimentoDoAutor;
    }

    public Integer getAnoDeFalecimentoDoAutor() {
        return anoDeFalecimentoDoAutor;
    }

    public void setAnoDeFalecimentoDoAutor(Integer anoDeFalecimentoDoAutor) {
        this.anoDeFalecimentoDoAutor = anoDeFalecimentoDoAutor;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    // Construtor padrão
    public Livro() {
    }

    // Construtor parametrizado
    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "----- LIVRO -----" + '\n' +
                "   Titulo: " + titulo + '\n' +
                "   Autor: " + autor + '\n' +
                "   Idioma: " + idioma + '\n' +
                "   Número de downloads: " + downloads + '\n' +
                "-----------------";
    }
}
