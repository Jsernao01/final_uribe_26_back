package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.puertos.input.OrdenesPuerto;
import com.ecommerce.proyecto.dominio.modelos.Ordenes;
import com.ecommerce.proyecto.dominio.repositorios.IOrdenesRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IUsuariosRepositorio;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrdenesCasosDeUso implements OrdenesPuerto {

    private static final Logger log = LoggerFactory.getLogger(OrdenesCasosDeUso.class);
    private final IOrdenesRepositorio ordenesRepo;
    private final IUsuariosRepositorio usuariosRepo;

    @Override
    public String crearOrden(UUID idCliente) {
        try {
            Integer referencia;
            Optional<Ordenes> ultimaReferencia= ordenesRepo.buscarUltimaReferencia();
            referencia = ultimaReferencia.map(orden -> Integer.parseInt(orden.getReferencia().substring(4))+1).orElse(0);

            Ordenes nuevaOrden = new Ordenes();
            nuevaOrden.setReferencia("ref-"+referencia.toString());
            nuevaOrden.setCliente(usuariosRepo.findById(idCliente));

            return ordenesRepo.guardarOrden(nuevaOrden).getReferencia();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
