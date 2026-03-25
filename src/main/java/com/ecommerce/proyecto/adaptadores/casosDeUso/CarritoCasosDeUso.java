package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.CarritoMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.CarritoPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarCarritoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.CarritoDto;
import com.ecommerce.proyecto.dominio.modelos.Carrito;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.dominio.repositorios.ICarritoRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IOrdenesJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarritoCasosDeUso implements CarritoPuerto {

    private final CarritoMapper carritoMapper;
    private final ICarritoRepositorio carritoRepo;
    private final IOrdenesRepositorio ordenesRepo;
    private final IProductosRepositorio productosRepo;


    @Override
    public CarritoDto guardarCarrito(GuardarCarritoDto dto, UUID idCliente, String referencia) {
        try {

            Carrito nuevoProducto = carritoMapper.deGuardarCarrito(dto);
            Productos producto = productosRepo.findByid(dto.getProducto());
            nuevoProducto.setProducto(producto);
            nuevoProducto.setPrecioParcial(producto.getPrecio()*producto.getPrecio());
            nuevoProducto.setReferencia(ordenesRepo.findByReferencia(referencia));

            return carritoMapper.deResponseCarrito(carritoRepo.guardar(nuevoProducto));

        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }
}
