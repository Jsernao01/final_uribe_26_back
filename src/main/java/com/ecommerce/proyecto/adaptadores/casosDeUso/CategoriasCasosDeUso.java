package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.CategoriasMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CategoriasPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCategoriaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CategoriaDto;
import com.ecommerce.proyecto.dominio.modelos.Categorias;
import com.ecommerce.proyecto.dominio.repositorios.ICategoriasRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoriasCasosDeUso implements CategoriasPuerto {

    private final CategoriasMapper categoriasMapper;
    private final ICategoriasRepositorio categoriasRepo;
    private final IProductosRepositorio productosRepo;

    @Override
    @Transactional
    public List<CategoriaDto> guardarCategoria(GuardarCategoriaDto dto) {
     try {
         List<Categorias> categorias = categoriasMapper.deStringList(dto.getCaracteristicas());
         if (categorias == null || categorias.isEmpty()) {
             throw new IllegalArgumentException("Las características no pueden estar vacías");
         }
         categorias.forEach(categoria -> categoria.setProducto(productosRepo.findByid(dto.getIdProducto())));
         return categoriasMapper.deResponseCategoriaList(categoriasRepo.guardar(categorias));
     }catch (Exception e){
         throw new RuntimeException("Error al agregar una categoria: " + e.getMessage());
     }
    }

    @Override
    public boolean eliminarCategoria(UUID idCategoria) {
        try {
            return categoriasRepo.eliminar(idCategoria);
        }catch (Exception e){
            throw new RuntimeException("Error al eliminar categoria: "+ e.getMessage());
        }

    }
}
