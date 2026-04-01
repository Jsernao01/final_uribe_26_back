package com.ecommerce.proyecto.adaptadores.casosDeUso;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.ecommerce.proyecto.adaptadores.mapeos.OrdenesMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.DescuentosPuerto;
import com.ecommerce.proyecto.adaptadores.puertos.input.OrdenesPuerto;
import com.ecommerce.proyecto.adaptadores.puertos.input.StockPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContenidoStock;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarOrdenDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.DescuentoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.OrdenesDto;
import com.ecommerce.proyecto.dominio.enums.Estados;
import com.ecommerce.proyecto.dominio.modelos.Carrito;
import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.repositorios.ICarritoRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdenesCasosDeUso implements OrdenesPuerto {

    private final IOrdenesRepositorio ordenesRepo;
    private final IUsuariosRepositorio usuariosRepo;
    private final ICarritoRepositorio carritoRepo;
    private final DescuentosPuerto descuentosPuerto;
    private final OrdenesMapper ordenesMapper;
    private final StockPuerto stockPuerto;

    @Override
    public String crearOrden(UUID idCliente) {
        try {
            Integer referencia;
            Optional<Ordenes> ultimaReferencia= ordenesRepo.buscarUltimaReferencia();
            referencia = ultimaReferencia.map(orden -> Integer.parseInt(orden.getReferencia().substring(4))+1).orElse(0);

            Ordenes nuevaOrden = new Ordenes();
            nuevaOrden.setReferencia("ref-"+referencia.toString());
            nuevaOrden.setCliente(usuariosRepo.findById(idCliente));
            nuevaOrden.setEstado(Estados.DECLARADA);

            return ordenesRepo.guardarOrden(nuevaOrden).getReferencia();

        }catch (Exception e){
            throw new RuntimeException(e.getCause());
        }
    }

    @Override
    public OrdenesDto guardarOrden(String referencia) {
        try{
            Ordenes orden = ordenesRepo.findByReferencia(referencia);
            List<Carrito> carritos = carritoRepo.findAllByReferencia(referencia);
            int precioTotal = 0;
            for (Carrito carrito : carritos) {
                DescuentoDto descuento = descuentosPuerto
                        .buscarDescuentoPorProducto(carrito.getStock().getProducto().getId());

                if (descuento != null && descuento.getDescuento() != null) {
                    precioTotal += carrito.getPrecioParcial() - (carrito.getPrecioParcial() * descuento.getDescuento() / 100);
                } else {
                    precioTotal += carrito.getPrecioParcial();
                }

                if (carrito.getStock().getCantidad()<carrito.getCantidad()){
                    throw new RuntimeException("No hay suficientes prendas de "+carrito.getStock().getProducto().getNombre()+" en stock");
                }
            }
            stockPuerto.actualizarCantidad(carritos.stream().map(e-> ActualizarContenidoStock.builder()
                    .cantidad(e.getStock().getCantidad() - e.getCantidad())
                    .idStock(e.getStock().getId())
                    .build()).toList());
            orden.setPrecioTotal(precioTotal);
            orden.setEstado(Estados.CREADA);
            orden.setFecha(LocalDateTime.now());

            ordenesRepo.guardarOrden(orden);

            OrdenesDto response = ordenesMapper.deResponseOrdenes(orden);
            response.setEstado(orden.getEstado().getDescripcion());
            response.setNombreCliente(orden.getCliente().getNombres());
            response.setTipoDocumento(orden.getCliente().getTipoDocumento().getDescripcion());
            response.setDocumento(orden.getCliente().getDocumento());

            return response;
        }catch (Exception e){
            throw new RuntimeException("error al guardar la orden, " + e.getMessage());
        }
    }

    @Override
    public OrdenesDto cambiarEstado(ActualizarOrdenDto dto) {
        try {
            Ordenes orden = ordenesRepo.findByReferencia(dto.getReferencia());
            orden.setEstado(Estados.valueOf(dto.getEstado().toUpperCase()));
            OrdenesDto respuesta = ordenesMapper.deResponseOrdenes(ordenesRepo.guardarOrden(orden));

            respuesta.setNombreCliente(orden.getCliente().getNombres());
            respuesta.setTipoDocumento(orden.getCliente().getTipoDocumento().getDescripcion());
            respuesta.setDocumento(orden.getCliente().getDocumento());

            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al intentar cambiar al estado: "+dto.getEstado(),e);
        }
    }
}
