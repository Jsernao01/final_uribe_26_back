package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.CarritoMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CarritoPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarCarritoProductoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCarritoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CarritoDto;
import com.ecommerce.proyecto.dominio.modelos.Carrito;
import com.ecommerce.proyecto.dominio.modelos.Stock;
import com.ecommerce.proyecto.dominio.repositorios.ICarritoRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IStockRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarritoCasosDeUso implements CarritoPuerto {

    private final CarritoMapper carritoMapper;
    private final ICarritoRepositorio carritoRepo;
    private final IOrdenesRepositorio ordenesRepo;
    private final IStockRepositorio stockRepo;


    @Override
    public CarritoDto guardarCarrito(GuardarCarritoDto dto, String referencia) {
        try {
            Stock stock = stockRepo.findById(dto.getIdStock());
            Carrito nuevoProducto = Carrito.builder()
                    .cantidad(dto.getCantidad())
                    .stock(stock)
                    .precioParcial(dto.getCantidad()*stock.getProducto().getPrecio())
                    .referencia(ordenesRepo.findByReferencia(referencia))
                    .build();
            return carritoMapper.deResponseCarrito(carritoRepo.guardar(nuevoProducto));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public CarritoDto actualizarCarrito(ActualizarCarritoProductoDto dto) {
        try{
            Carrito carrito = carritoRepo.findById(dto.getId());
            carrito.setCantidad(dto.getCantidad());
            carrito.setPrecioParcial(dto.getCantidad()*carrito.getStock().getProducto().getPrecio());
            return carritoMapper.deResponseCarrito(carritoRepo.guardar(carrito));
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean eliminarCarrito(UUID id) {
        try {
            Carrito carrito = carritoRepo.findById(id);
            carritoRepo.eliminar(carrito);
            return true;
        }catch (Exception e){
            throw new RuntimeException("error al eliminar un producto del carrito");
        }
    }
}
