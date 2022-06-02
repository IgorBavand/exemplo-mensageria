package com.teste.pedidos.repository;

import com.teste.pedidos.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>, JpaSpecificationExecutor<Cliente> {
    @Query("Select p from Cliente p where p.emailCliente = :email")
    Optional<Cliente> findClientByEmail(String email);
}
