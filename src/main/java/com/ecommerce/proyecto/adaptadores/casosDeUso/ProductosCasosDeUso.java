package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.CategoriasMapper;
import com.ecommerce.proyecto.adaptadores.mapeos.ProductosMapper;
import com.ecommerce.proyecto.adaptadores.mapeos.StockMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.ProductosPuerto;
import com.ecommerce.proyecto.adaptadores.puertos.input.StockPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.ProductosDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.StockDto;
import com.ecommerce.proyecto.dominio.modelos.Categorias;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.dominio.modelos.Stock;
import com.ecommerce.proyecto.dominio.repositorios.ICategoriasRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IStockRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductosCasosDeUso implements ProductosPuerto {

    private final ProductosMapper productosMapper;
    private final CategoriasMapper categoriasMapper;
    private final IProductosRepositorio productosRepo;
    private final StockPuerto stockPuerto;
    private final ICategoriasRepositorio categoriasRepo;

    @Override
    public ProductosDto guardarProducto(GuardarProductoDto producto) {
        try{
            Productos nuevoProducto = productosRepo.guardar(productosMapper.deGuardarProducto(producto));

            List<StockDto> stockAsignado = stockPuerto.guardarStock(producto.getStock(), nuevoProducto.getId());

            List<Categorias> categoriasNoAsignadas = categoriasMapper.deStringList(producto.getCategorias());
            categoriasNoAsignadas.forEach(e-> e.setProducto(nuevoProducto));

            List<Categorias> categoriasAsignadas = categoriasRepo.guardar(categoriasNoAsignadas);

            return ProductosDto.builder()
                    .id(nuevoProducto.getId())
                    .nombre(nuevoProducto.getNombre())
                    .precio(nuevoProducto.getPrecio())
                    .lanzamiento(nuevoProducto.getLanzamiento())
                    .stock(stockAsignado.stream().map(e-> ProductosDto.StockDto.builder()
                            .color(e.getColor())
                            .talla(e.getTalla())
                            .cantidad(e.getCantidad())
                            .build()).toList())
                    .categorias(categoriasAsignadas.stream().map(e-> ProductosDto.CategoriasDto.builder()
                            .id(e.getId())
                            .caracteristica(e.getCaracteristica().getDescripcion())
                            .build()).toList())
            .build();
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }
}
