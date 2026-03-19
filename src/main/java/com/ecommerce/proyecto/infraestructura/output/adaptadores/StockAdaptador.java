package com.ecommerce.proyecto.infraestructura.output.adaptadores;

import com.ecommerce.proyecto.dominio.modelos.Stock;
import com.ecommerce.proyecto.dominio.repositorios.IStockRepositorio;
import com.ecommerce.proyecto.infraestructura.output.persistence.entidades.StockJpa;
import com.ecommerce.proyecto.infraestructura.output.persistence.mapeos.StockJpaMapper;
import com.ecommerce.proyecto.infraestructura.output.persistence.repositorios.IStockJpaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class StockAdaptador implements IStockRepositorio {

    private final StockJpaMapper stockMapper;
    private final IStockJpaRepositorio stockRepo;

    @Override
    public List<Stock> guardar(List<Stock> stock) {
        try{
            List<StockJpa> stockNuevo = stockMapper.aEntidadLista(stock);
            return stockMapper.aModeloLista(stockRepo.saveAll(stockNuevo));
        }catch (Exception e){
            throw new RuntimeException("Error al guardar el stock", e);
        }
    }
}
