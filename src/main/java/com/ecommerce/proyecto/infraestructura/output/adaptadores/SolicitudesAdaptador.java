package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Solicitudes;
import com.ecommerce.proyecto.dominio.repositorios.ISolicitudesRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.SolicitudesJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.SolicitudesJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.ISolicitudesJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class SolicitudesAdaptador implements ISolicitudesRepositorio {

    private final ISolicitudesJpaRepositorio solicitudesJpaRepo;
    private final SolicitudesJpaMapper solicitudesJpaMapper;

    @Override
    public Solicitudes guardarSolicitud(Solicitudes solicitud) {
        try {
            SolicitudesJpa nuevaSolicitud = solicitudesJpaMapper.aEntidad(solicitud);
            nuevaSolicitud.setMotivoDevolucion(solicitud.getMotivoDevolucion());
            return solicitudesJpaMapper.aModelo(solicitudesJpaRepo.save(nuevaSolicitud));
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Solicitudes findById(UUID id) {
        try {
            SolicitudesJpa solicitud = solicitudesJpaRepo.findById(id).orElseThrow();
            Solicitudes response = solicitudesJpaMapper.aModelo(solicitud);
            response.setMotivoDevolucion(solicitud.getMotivoDevolucion());
            return response;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
