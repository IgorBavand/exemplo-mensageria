package com.teste.pedidos.repository.specification;

import com.teste.pedidos.model.Cliente;
import com.teste.pedidos.model.Cliente_;
import com.teste.pedidos.service.filter.ClienteFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClienteSpecification implements Specification<Cliente> {

    private static final long serialVersionUID = 6970841708640167374L;
    private final ClienteFilter filter;

    public static ClienteSpecification of(ClienteFilter filter) {
        if (filter == null) {
            return null;
        }
        return new ClienteSpecification(filter);
    }

    @Override
    public Predicate toPredicate(Root<Cliente> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();

        if(filter.getNomeCliente() != null){
            predicates.add(criteriaBuilder.like(root.get(Cliente_.nomeCliente), "%" + filter.getNomeCliente() + "%"));
        }
        if(filter.getCodigoCliente() != null){
            predicates.add(root.get(Cliente_.codigoCliente).in(filter.getCodigoCliente()));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

    }
}
