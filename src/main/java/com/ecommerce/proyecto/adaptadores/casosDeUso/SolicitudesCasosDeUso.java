package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.SolicitudesMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.SolicitudesPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarSolicitudesDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarSolicitudDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.SolicitudesDto;
import com.ecommerce.proyecto.dominio.enums.EstadoSolicitud;
import com.ecommerce.proyecto.dominio.enums.Estados;
import com.ecommerce.proyecto.dominio.enums.MotivosCancelacion;
import com.ecommerce.proyecto.dominio.enums.MotivosDevolucion;
import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.modelos.Solicitudes;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.ISolicitudesRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SolicitudesCasosDeUso implements SolicitudesPuerto {

    private final SolicitudesMapper solicitudesMapper;
    private final ISolicitudesRepositorio solicitudesRepo;
    private final IOrdenesRepositorio ordenesRepo;

    @Override
    public SolicitudesDto guardarSolicitud(GuardarSolicitudDto dto) {
        try {

            Ordenes orden = ordenesRepo.findByReferencia(dto.getReferencia());

            switch (orden.getEstado()){
                case CANCELADA, ENTREGADA, REGRESADA: throw new RuntimeException("A la orden no se le pueden hacer solicitudes");
            }

            Solicitudes nuevaSolicitud = Solicitudes.builder()
                    .estadoSolicitud(EstadoSolicitud.CREADA)
                    .motivoCancelacion(!Objects.equals(dto.getMotivoCancelacion(), "") ? MotivosCancelacion.valueOf(dto.getMotivoCancelacion().toUpperCase()):null)
                    .motivoDevolucion(!Objects.equals(dto.getMotivoDevolucion(), "") ? MotivosDevolucion.valueOf(dto.getMotivoDevolucion().toUpperCase()):null)
                    .orden(orden)
                    .build();

            SolicitudesDto respuesta = solicitudesMapper.deResponseSolicitudes(solicitudesRepo.guardarSolicitud(nuevaSolicitud));
            respuesta.setEstadoSolicitud(nuevaSolicitud.getEstadoSolicitud().getDescripcion());
            respuesta.setMotivoCancelacion(nuevaSolicitud.getMotivoCancelacion() != null ? nuevaSolicitud.getMotivoCancelacion().getDescripcion():null);
            respuesta.setMotivoDevolucion(nuevaSolicitud.getMotivoDevolucion() != null?nuevaSolicitud.getMotivoDevolucion().getDescripcion():null);
            respuesta.setReferencia(nuevaSolicitud.getOrden().getReferencia());
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al guardar la solicitud: " + e.getMessage());
        }
    }

    @Override
    public SolicitudesDto actualizarSolicitud(ActualizarSolicitudesDto dto) {
        try {
            Solicitudes solicitud = solicitudesRepo.findById(dto.getId());
            solicitud.setEstadoSolicitud(EstadoSolicitud.valueOf(dto.getEstadoSolicitud().toUpperCase()));
            SolicitudesDto respuesta = solicitudesMapper.deResponseSolicitudes(solicitudesRepo.guardarSolicitud(solicitud));
            respuesta.setEstadoSolicitud(solicitud.getEstadoSolicitud().getDescripcion());
            respuesta.setMotivoCancelacion(solicitud.getMotivoCancelacion() != null ? solicitud.getMotivoCancelacion().getDescripcion():null);
            respuesta.setMotivoDevolucion(solicitud.getMotivoDevolucion() != null?solicitud.getMotivoDevolucion().getDescripcion():null);
            respuesta.setReferencia(solicitud.getOrden().getReferencia());
            return respuesta;
        }catch (Exception e){
            throw new RuntimeException("Error al actualizar la solicitud: "+ e.getMessage());
        }
    }
}
