package com.example.leituraApplication.repository;

import com.example.leituraApplication.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {
    @Query("SELECT DISTINCT l.autor FROM Livro l")
    List<String> findDistinctAutores();
}
