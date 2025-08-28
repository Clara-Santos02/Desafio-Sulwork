package com.example.demo.repository;

import com.example.demo.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO colaborador (nome, cpf) VALUES (:nome, :cpf)", nativeQuery = true)
    void inserirColaborador(@Param("nome") String nome, @Param("cpf") String cpf);

    @Query(value = "SELECT * FROM colaborador WHERE cpf = :cpf", nativeQuery = true)
    Optional<Colaborador> procurarPorCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT * FROM colaborador WHERE nome = :nome", nativeQuery = true)
    Optional<Colaborador> procurarPorNome(@Param("nome") String nome);

    @Modifying
    @Transactional
    @Query(value = "UPDATE colaborador SET nome = :nome, cpf = :cpf WHERE id = :id", nativeQuery = true)
    void atualizarColaborador(@Param("id") Integer id, @Param("nome") String nome, @Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM colaborador WHERE id = :id", nativeQuery = true)
    void deletarColaborador(@Param("id") Integer id);

}