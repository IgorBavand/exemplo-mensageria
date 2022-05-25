package com.teste.pedidos.repository.specification;

import com.teste.pedidos.model.Pedido;
import com.teste.pedidos.model.Pedido_;
import com.teste.pedidos.service.filter.PedidoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class PedidoSpecification implements Specification<Pedido> {

    private static final long serialVersionUID = 6970841708640167374L;
    private final PedidoFilter filter;

    public static PedidoSpecification of(PedidoFilter filter) {
        if (filter == null) {
            return null;
        }
        return new PedidoSpecification(filter);
    }

    @Override
    public Predicate toPredicate(Root<Pedido> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();


        if (filter.getCodigoCliente() != null) {
            predicates.add(root.get(Pedido_.codigoCliente).in(filter.getCodigoCliente()));
        }

        if (filter.getEnderecoEntrega() != null){
            predicates.add(root.get(Pedido_.enderecoEntrega).in(filter.getEnderecoEntrega()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
