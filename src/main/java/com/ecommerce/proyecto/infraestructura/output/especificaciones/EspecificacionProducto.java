package com.ecommerce.proyecto.infraestructura.output.especificaciones;

import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosProductosDto;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CategoriasJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.DescuentosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.ProductosJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.StockJpa;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EspecificacionProducto {

    private EspecificacionProducto(){};

    private static Specification<ProductosJpa> porNombre(String nombre){
        return (((root, query, cb) -> nombre==null?null:cb.like(root.get("nombre"), "%"+nombre.toLowerCase()+"%")));
    }

    private static Specification<ProductosJpa> porColor(String color){
        return (((root, query, cb) -> {

            if (color == null || color.isEmpty()){
                return null;
            }

            query.distinct(true);

            Join<ProductosJpa, StockJpa> stockJoin = root.join("stocks");

            return cb.equal(cb.lower(stockJoin.get("color")),color.toLowerCase());
        }));
    }

    private static Specification<ProductosJpa> porPrecio(Integer minPrecio, Integer maxPrecio){
        return (((root, query, cb) -> {

            if (minPrecio == null && maxPrecio == null) return null;

            List<Predicate> predicates = new ArrayList<>();

            if (minPrecio != null){
                if (minPrecio != 0){
                    predicates.add(cb.greaterThanOrEqualTo(root.get("precio"),minPrecio));
                }
            }

            if (maxPrecio != null ){
                if (maxPrecio != 0){
                    predicates.add(cb.lessThanOrEqualTo(root.get("precio"),maxPrecio));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));

        }));
    }

    private static Specification<ProductosJpa> porCategorias(List<String> categorias){
        return (((root, query, cb) -> {

            if (categorias == null || categorias.isEmpty()) {
                return null;
            }

            query.distinct(true);

            Join<ProductosJpa, CategoriasJpa> categoriasJoin = root.join("categorias");

            Predicate todas = categoriasJoin.get("caracteristica").in(categorias);

            query.groupBy(root.get("id"));

            query.having(cb.equal(cb.countDistinct(categoriasJoin.get("id")), categorias.size()));

            return todas;
        }));
    }

    private static Specification<ProductosJpa> porDescuento(Integer descuento){
        return (((root, query, cb) -> {

            if (descuento == null || descuento == 0) {
                return null;
            }

            query.distinct(true);

            Join<ProductosJpa, DescuentosJpa> descuentosJoin = root.join("descuento");

            return cb.greaterThanOrEqualTo(descuentosJoin.get("descuento"), descuento);
        }));
    }

    public static Specification<ProductosJpa> crearEspecificacionProductos(FiltrosProductosDto filtros){
        return Specification.where(porNombre(filtros.getNombre())
                .and(porColor(filtros.getColor())
                .and(porPrecio(filtros.getMinPrecio(), filtros.getMaxPrecio()))
                .and(porCategorias(filtros.getCategorias()))
                .and(porDescuento(filtros.getDescuento()))));
    }
}
