package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.DescuentosMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.DescuentosPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarDescuentoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarDescuentoDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.DescuentoDto;
import com.ecommerce.proyecto.dominio.enums.DescuentoObjetivo;
import com.ecommerce.proyecto.dominio.enums.TiposDescuento;
import com.ecommerce.proyecto.dominio.modelos.Descuentos;
import com.ecommerce.proyecto.dominio.repositorios.IDescuentosRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DescuentosCasosDeUso implements DescuentosPuerto {

    private final DescuentosMapper descuentosMapper;
    private final IDescuentosRepositorio descuentosRepo;
    private final IUsuariosRepositorio usuariosRepo;
    private final IProductosRepositorio productosRepo;

    @Override
    public DescuentoDto guardarDescuento(GuardarDescuentoDto descuento) {
        try{
            Descuentos descuentoNoAsignado = descuentosMapper.deGuardarDescuento(descuento);
            descuentoNoAsignado.setTipoDescuento(TiposDescuento.valueOf(descuento.getTipoDescuento().toUpperCase()));
            descuentoNoAsignado.setFechaCreacion(LocalDateTime.now());
            descuentoNoAsignado.setActivo(true);

            TiposDescuento tipo = null;

            if (TiposDescuento.valueOf(descuento.getTipoDescuento().toUpperCase()) == TiposDescuento.TEMPORADA) tipo = TiposDescuento.TEMPORADA;
            if (TiposDescuento.valueOf(descuento.getTipoDescuento().toUpperCase()) == TiposDescuento.UNICO) tipo = TiposDescuento.UNICO;

            descuentoNoAsignado.setTipoDescuento(tipo);

            if (descuento.getObjetivo() == DescuentoObjetivo.CLIENTE) descuentoNoAsignado.setCliente(usuariosRepo.findById(descuento.getIdObjetivo()));
            if (descuento.getObjetivo() == DescuentoObjetivo.PRENDA) descuentoNoAsignado.setProducto(productosRepo.findByid(descuento.getIdObjetivo()));

            DescuentoDto respuesta = descuentosMapper.deResponseDescuentos(descuentosRepo.guardarDescuento(descuentoNoAsignado));

            respuesta.setTipoDescuento(descuentoNoAsignado.getTipoDescuento().getDescripcion());
            if (descuentoNoAsignado.getProducto()!=null){
                respuesta.setNombreObjeivo(descuentoNoAsignado.getProducto().getNombre());
                respuesta.setObjetivo(DescuentoObjetivo.PRENDA.getDescripcion());
            }
            if (descuentoNoAsignado.getCliente()!=null){
                respuesta.setNombreObjeivo(descuentoNoAsignado.getCliente().getNombres());
                respuesta.setObjetivo(DescuentoObjetivo.CLIENTE.getDescripcion());
            }

            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("error al guardar el descuento: "+e.getMessage());
        }
    }

    @Override
    public DescuentoDto buscarDescuentoPorProducto(UUID idProducto) {
        try {
            Optional<Descuentos> descuento = descuentosRepo.buscarDescuentoPorProductoActivo(idProducto);
            return descuento
                    .map(descuentosMapper::deResponseDescuentos)
                    .map(responseDto -> {
                        Descuentos descuentoExistente = descuento.orElseThrow();

                        responseDto.setTipoDescuento(descuentoExistente.getTipoDescuento().getDescripcion());
                        responseDto.setObjetivo(DescuentoObjetivo.PRENDA.getDescripcion());
                        responseDto.setNombreObjeivo(descuentoExistente.getProducto().getNombre());

                        return responseDto;
                    })
                    .orElse(null);
        }catch (Exception e){
            throw new RuntimeException("Error al buscar descuentos del producto con id: "+idProducto+", "+ e.getMessage());
        }
    }

    @Override
    public DescuentoDto cambioDeActivacionDescuento(UUID idDescuento) {
        try {
            Descuentos descuento = descuentosRepo.findById(idDescuento).orElseThrow();
            descuento.setActivo(!descuento.getActivo());
            DescuentoDto respuesta = descuentosMapper.deResponseDescuentos(descuentosRepo.guardarDescuento(descuento));

            respuesta.setTipoDescuento(descuento.getTipoDescuento().getDescripcion());
            if (descuento.getProducto()!=null){
                respuesta.setNombreObjeivo(descuento.getProducto().getNombre());
                respuesta.setObjetivo(DescuentoObjetivo.PRENDA.getDescripcion());
            }
            if (descuento.getCliente()!=null){
                respuesta.setNombreObjeivo(descuento.getCliente().getNombres());
                respuesta.setObjetivo(DescuentoObjetivo.CLIENTE.getDescripcion());
            }
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("error al cambiar la activacion del descuento: "+e.getMessage());
        }
    }

    @Override
    public Boolean eliminarDescuento(UUID idDescuento){
        return descuentosRepo.eliminarDescuento(idDescuento);
    }

    @Override
    public DescuentoDto actualizarDescuento(ActualizarDescuentoDto dto) {
        try {
            Descuentos descuento = descuentosRepo.findById(dto.getId()).orElseThrow();
            if (dto.getDescuento()!=null) descuento.setDescuento(dto.getDescuento());
            if (dto.getRazon()!=null) descuento.setRazon(dto.getRazon());
            if (dto.getInicio()!=null) descuento.setInicio(dto.getInicio());
            if (dto.getFin()!=null) descuento.setFin(dto.getFin());

            DescuentoDto respuesta = descuentosMapper.deResponseDescuentos(descuentosRepo.guardarDescuento(descuento));
            respuesta.setTipoDescuento(descuento.getTipoDescuento().getDescripcion());
            if (descuento.getProducto()!=null){
                respuesta.setNombreObjeivo(descuento.getProducto().getNombre());
                respuesta.setObjetivo(DescuentoObjetivo.PRENDA.getDescripcion());
            }
            if (descuento.getCliente()!=null){
                respuesta.setNombreObjeivo(descuento.getCliente().getNombres());
                respuesta.setObjetivo(DescuentoObjetivo.CLIENTE.getDescripcion());
            }
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar el descuento:"+ e.getMessage());
        }
    }
}
