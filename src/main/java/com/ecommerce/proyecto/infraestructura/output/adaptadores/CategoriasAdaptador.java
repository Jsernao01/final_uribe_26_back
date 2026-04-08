package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Categorias;
import com.ecommerce.proyecto.dominio.repositorios.ICategoriasRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.CategoriasJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.CategoriasJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.ICategoriasJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoriasAdaptador implements ICategoriasRepositorio {

    private final CategoriasJpaMapper categoriasMapper;
    private final ICategoriasJpaRepositorio categoriasRepo;

    @Override
    public List<Categorias> guardar(List<Categorias> categorias) {
        try{
            List<CategoriasJpa> categoriasNuevas = categoriasMapper.aEntidadLista(categorias);
            return categoriasMapper.aModeloLista(categoriasRepo.saveAll(categoriasNuevas));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean eliminar(UUID idCategoria) {
        try {
            categoriasRepo.delete(categoriasRepo.findById(idCategoria).orElseThrow());
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Categorias> findByProducto(UUID idProducto) {
        try {
            return categoriasMapper.aModeloLista(categoriasRepo.findAllByProductoId(idProducto));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
