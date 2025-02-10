package com.devamisoft.SistemaInventario.specification;

import com.devamisoft.SistemaInventario.models.InventarioMovimiento;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InventarioMovimientoFilter {

    public static Specification<InventarioMovimiento> byFilter(String searchValue) {
        return (root, query, criteriaBuilder) -> {
            if (searchValue == null || searchValue.isEmpty()) {
                return criteriaBuilder.conjunction(); // Always true
            }

            String searchPattern = "%" + searchValue.toLowerCase() + "%";

            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("tipoMovimiento")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("observacion")), searchPattern));

            if (root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("local"))) {
                root.join("local", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("local", JoinType.LEFT).get("nombre")), searchPattern));

            if (root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("producto"))) {
                root.join("producto", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("producto", JoinType.LEFT).get("nombre")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("producto", JoinType.LEFT).get("descripcion")), searchPattern));

            if (root.getJoins().stream().noneMatch(j -> j.getAttribute().getName().equals("usuario"))) {
                root.join("usuario", JoinType.LEFT);
            }
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("usuario", JoinType.LEFT).get("nombreCompleto")), searchPattern));
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.join("usuario", JoinType.LEFT).get("correo")), searchPattern));

            return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<InventarioMovimiento> byMonthAndYear(int month, int year) {
        return (root, query, criteriaBuilder) -> {
            Expression<Integer> monthExpression = criteriaBuilder.function("month", Integer.class, root.get("createdAt"));
            Expression<Integer> yearExpression = criteriaBuilder.function("year", Integer.class, root.get("createdAt"));

            Predicate monthPredicate = criteriaBuilder.equal(monthExpression, month);
            Predicate yearPredicate = criteriaBuilder.equal(yearExpression, year);

            return criteriaBuilder.and(monthPredicate, yearPredicate);
        };
    }
}