package com.ecommerce.proyecto.adaptadores.casosDeUso;

import com.ecommerce.proyecto.adaptadores.mapeos.StockMapper;
import com.ecommerce.proyecto.adaptadores.puertos.input.StockPuerto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.ActualizarContenidoStock;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarStockDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.StockDto;
import com.ecommerce.proyecto.dominio.modelos.Productos;
import com.ecommerce.proyecto.dominio.modelos.Stock;
import com.ecommerce.proyecto.dominio.repositorios.IProductosRepositorio;
import com.ecommerce.proyecto.dominio.repositorios.IStockRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockCasosDeUso implements StockPuerto {

    private final StockMapper stockMapper;
    private final IStockRepositorio stockRepo;
    private final IProductosRepositorio productosRepo;

    @Override
    public List<StockDto> guardarStock(List<GuardarStockDto> dto, UUID idProducto) {
        try {
            List<Stock> stockNoAsignado = stockMapper.deGuardarStockList(dto);
            Productos producto = productosRepo.findByid(idProducto);
            stockNoAsignado.forEach(e-> e.setProducto(producto));

            List<Stock> stockAsignado = stockRepo.guardar(stockNoAsignado);
            return stockMapper.deRespuestaStockList(stockAsignado);

        }catch (Exception e){
            throw new RuntimeException("Error al guardar el stock: "+ e.getMessage());
        }
    }

    @Override
    public Boolean eliminarStock(UUID idStock) {
        return stockRepo.eliminarStock(idStock);
    }

    @Override
    public List<StockDto> actualizarCantidad(List<ActualizarContenidoStock> dtoList) {
        try {
            List<Stock> stocks = dtoList.stream().map(e -> {
                        Stock stock = stockRepo.findById(e.getIdStock());
                        stock.setCantidad(e.getCantidad());
                        return stock;
                    }
            ).toList();

            return stockMapper.deRespuestaStockList(stockRepo.guardar(stocks));
        }catch (Exception e){
            throw new RuntimeException("Error al cambiar el stock de un producto: "+ e.getMessage());
        }
    }
}
