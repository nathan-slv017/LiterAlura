package com.example.leituraApplication.repository;

import com.example.leituraApplication.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
