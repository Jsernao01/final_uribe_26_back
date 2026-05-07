package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.ProductosMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CategoriasPuerto;
import com.ecommerce.proyecto.adaptadores.puertos.input.ProductosPuerto;
import com.ecommerce.proyecto.adaptadores.puertos.input.StockPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.FiltrosProductosDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCategoriaDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CategoriaDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.ProductosDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.StockDto;
import com.ecommerce.proyecto.dominio.modelos.Categorias;
import com.ecommerce.proyecto.dominio.modelos.Descuentos;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.dominio.modelos.Stock;
import com.ecommerce.proyecto.dominio.repositorios.ICategoriasRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IDescuentosRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IStockRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductosCasosDeUso implements ProductosPuerto {

    private final ProductosMapper productosMapper;
    private final IProductosRepositorio productosRepo;
    private final StockPuerto stockPuerto;
    private final IStockRepositorio stockRepo;
    private final CategoriasPuerto categoriasPuerto;
    private final ICategoriasRepositorio categoriasRepo;
    private final IDescuentosRepositorio descuentosRepo;

    @Override
    public ProductosDto guardarProducto(GuardarProductoDto producto) {
        try{
            Productos nuevoProducto = productosRepo.guardar(productosMapper.deGuardarProducto(producto));

            List<StockDto> stockAsignado = stockPuerto.guardarStock(producto.getStock(), nuevoProducto.getId());

            GuardarCategoriaDto categoriaDto = GuardarCategoriaDto.builder()
                    .idProducto(nuevoProducto.getId())
                    .caracteristicas(producto.getCategorias())
                    .build();
            List<CategoriaDto> categorias = categoriasPuerto.guardarCategoria(categoriaDto);

            return ProductosDto.builder()
                    .id(nuevoProducto.getId())
                    .nombre(nuevoProducto.getNombre())
                    .precio(nuevoProducto.getPrecio())
                    .lanzamiento(nuevoProducto.getLanzamiento())
                    .stock(stockAsignado.stream().map(e-> ProductosDto.InStockDto.builder()
                            .color(e.getColor())
                            .talla(e.getTalla())
                            .cantidad(e.getCantidad())
                            .build()).toList())
                    .categorias(categorias.stream().map(e-> ProductosDto.InCategoriasDto.builder()
                            .id(e.getId())
                            .caracteristica(e.getCaracteristica())
                            .build()).toList())
            .build();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public boolean cambiarActivacionProducto(UUID idProducto) {
        try {
            Productos producto = productosRepo.findByid(idProducto);
            producto.setActivo(!producto.getActivo());
            productosRepo.guardar(producto);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductosDto> filtrarProductos(FiltrosProductosDto filtros) {
        try {
            List<ProductosDto> response = productosMapper.deResponseProductoList(productosRepo.filtrarProductos(filtros));

            response.forEach(producto->{

                Descuentos descuento = descuentosRepo.buscarDescuentoPorProductoActivo(producto.getId()).orElse(null);
                List<Stock> stock = stockRepo.findByProducto(producto.getId());
                List<Categorias> categorias = categoriasRepo.findByProducto(producto.getId());

                producto.setDescuento(descuento==null?null:descuento.getDescuento());
                producto.setStock(stock.stream().map(s-> ProductosDto.InStockDto.builder()
                        .talla(s.getTalla())
                        .color(s.getColor())
                        .cantidad(s.getCantidad())
                        .build()).toList());
                producto.setCategorias(categorias.stream().map(c-> ProductosDto.InCategoriasDto.builder()
                        .id(c.getId())
                        .caracteristica(c.getCaracteristica().getDescripcion())
                        .build()).toList());
            });

            return response;

        }catch (Exception e){
            throw new RuntimeException("Error al filtrar productos: " + e.getMessage());
        }
    }
}
