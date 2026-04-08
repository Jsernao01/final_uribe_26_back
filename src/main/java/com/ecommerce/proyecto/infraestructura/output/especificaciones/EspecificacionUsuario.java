package com.ecommerce.proyecto.infraestructura.output.especificaciones;

import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosUsuariosDto;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.UsuariosJpa;
import org.springframework.data.jpa.domain.Specification;

public class EspecificacionUsuario {

    private EspecificacionUsuario(){};

    private static Specification<UsuariosJpa> porNombres(String nombres){
        return ((root, query, cb) -> nombres==null?null:cb.like(root.get("nombres"), "%"+nombres.toLowerCase()+"%"));
    }

    private static Specification<UsuariosJpa> porApellidos(String apellidos){
        return ((root, query, cb) -> apellidos==null?null:cb.like(root.get("apellidos"), "%"+apellidos.toLowerCase()+"%"));
    }

    private static Specification<UsuariosJpa> porCorreo(String correo){
        return ((root, query, cb) -> correo==null?null:cb.like(root.get("correo"), "%"+correo.toLowerCase()+"%"));
    }

    public static Specification<UsuariosJpa> crearEspecificacionUsuarios(FiltrosUsuariosDto filtros){
        return Specification.where(porNombres(filtros.getNombres()))
                .and(porApellidos(filtros.getApellidos()))
                .and(porCorreo(filtros.getCorreo()));
    }

}
