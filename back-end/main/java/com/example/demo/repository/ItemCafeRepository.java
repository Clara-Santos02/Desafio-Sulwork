package com.example.demo.repository;

import com.example.demo.model.ItemCafe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ItemCafeRepository extends JpaRepository<ItemCafe, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO item_cafe (nome_item, data, status, colaborador_id) " +
                   "VALUES (:nomeItem, :data, :status, :colaboradorId)", nativeQuery = true)
    void inserirItem(@Param("nomeItem") String nomeItem, @Param("data") LocalDate data,
                     @Param("status") String status, @Param("colaboradorId") Integer colaboradorId);

    @Query(value = "SELECT * FROM item_cafe WHERE nome_item = :nomeItem AND data = :data", nativeQuery = true)
    Optional<ItemCafe> procurarPorNomeEData(@Param("nomeItem") String nomeItem, @Param("data") LocalDate data);

    @Query(value = "SELECT * FROM item_cafe WHERE colaborador_id = :colaboradorId AND data = :data", nativeQuery = true)
    List<ItemCafe> procurarPorColaboradorEData(@Param("colaboradorId") Integer colaboradorId, @Param("data") LocalDate data);

    @Modifying
    @Transactional
    @Query(value = "UPDATE item_cafe SET status = :status WHERE id = :id", nativeQuery = true)
    void atualizarStatus(@Param("id") Integer id, @Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM item_cafe WHERE id = :id", nativeQuery = true)
    void deletarItem(@Param("id") Integer id);
}