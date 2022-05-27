package com.teste.pedidos.repository.specification;

import com.teste.pedidos.model.Produto;
import com.teste.pedidos.model.Produto_;
import com.teste.pedidos.service.filter.ProdutoFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ProdutoSpecificartion implements Specification<Produto> {

    private static final long serialVersionUID = 6970841708640167374L;
    private final ProdutoFilter filter;

    public static ProdutoSpecificartion of(ProdutoFilter filter) {
        if (filter == null) {
            return null;
        }
        return new ProdutoSpecificartion(filter);
    }

    @Override
    public Predicate toPredicate(Root<Produto> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();


        if (filter.getCodigoProduto() != null) {
            predicates.add(root.get(Produto_.CODIGO_PRODUTO).in(filter.getCodigoProduto()));
        }

        if (filter.getNomeProduto() != null){
            predicates.add(root.get(Produto_.NOME_PRODUTO).in(filter.getNomeProduto()));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
