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

@Service
@RequiredArgsConstructor
public class CarritoCasosDeUso implements CarritoPuerto {

    private final ICarritoRepositorio carritoRepo;
    private final IProductosRepositorio productosRepo;
    private final IOrdenesRepositorio ordenesRepo;


    @Override
    public List<CarritoDto> guardarCarrito(List<GuardarCarritoDto> dto) {
        try {
            List<Carrito> carritos = dto.stream().map(e-> Carrito.builder()
                    .referencia(ordenesRepo.findByReferencia(e.getReferencia()))
                    .precioParcial(e.getPrecioParcial())
                    .cantidad(e.getCantidad())
                    .producto(productosRepo.findByid(e.getProducto()))
                    .build()).toList();
            List<Carrito> nuevoCarrito = carritoRepo.guardar(carritos);
            return nuevoCarrito.stream().map(e-> CarritoDto.builder()
                    .id(e.getId())
                    .referencia(e.getReferencia().getReferencia())
                    .precioParcial(e.getPrecioParcial())
                    .cantidad(e.getCantidad())
                    .build()).toList();
        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }
}
