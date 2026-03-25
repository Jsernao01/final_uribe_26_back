package com.ecommerce.proyecto.dominio.repositorios;

import com.ecommerce.proyecto.dominio.modelos.Ordenes;

import java.util.Optional;

public interface IOrdenesRepositorio {
    Ordenes guardarOrden(Ordenes orden);
    Ordenes findByReferencia(String referencia);
    Optional<Ordenes> buscarUltimaReferencia ();
}
