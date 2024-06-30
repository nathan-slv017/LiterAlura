package com.example.leituraApplication.principal;

import com.example.leituraApplication.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Principal {

    private final LivroService livroService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public Principal(LivroService livroService) {
        this.livroService = livroService;
    }

    public void exibeMenu() {
        int opcao = -1;

        while (opcao != 0) {

            String menu = """
                    ----------------------
                    Escolha o número da sua opção:
                    1- Buscar livro pelo título
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em um determinado ano
                    5- Listar livros em um determinado idioma
                    0- Sair
                    """;
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao){
                case 1:
                    System.out.println("Digite o titulo do livro: ");
                    String livroBuscado = scanner.nextLine();
                    livroService.buscarLivroPorTitulo(livroBuscado);

                    break;
                case 2:
                    livroService.listarLivrosRegistrados();
                    break;
                case 3:
                    livroService.listaDeAutoresRegistrados();
                    break;
                case 4:
                    System.out.println("Informe um ano especifico");
                    Integer ano = scanner.nextInt();
                    livroService.autoreVivoEmUmDeterminadoAno(ano);
                    break;
                case 5:
                    System.out.println("""
                Insira o idioma para realizar a busca:
                es- espanhol
                en- inglês
                fr- francês
                pt- português
                """);
                    String idioma = scanner.nextLine();
                    livroService.livrosEmUmDeterminadoIdioma(idioma);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;

            }

        }
    }
}
