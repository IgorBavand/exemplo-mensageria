package com.teste.entregas.repository;

import com.teste.entregas.model.Entrega;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, UUID>, JpaSpecificationExecutor<Entrega> {

}
