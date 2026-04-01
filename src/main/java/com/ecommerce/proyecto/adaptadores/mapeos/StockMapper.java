package com.ecommerce.proyecto.adaptadores.mapeos;

import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarProductoDto;
import com.ecommerce.proyecto.dominio.dtos.peticiones.GuardarStockDto;
import com.ecommerce.proyecto.dominio.dtos.respuesta.StockDto;
import com.ecommerce.proyecto.dominio.modelos.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "talla", expression = "java(dto.getTalla().toUpperCase())")
    @Mapping(target = "producto", ignore = true)
    Stock deGuardarStock (GuardarStockDto dto);

    List<Stock> deGuardarStockList (List<GuardarStockDto> dtoList);

    @Mapping(target = "nombreProducto", expression = "java(stock.getProducto().getNombre())")
    StockDto deRespuestaStock(Stock stock);

    List<StockDto> deRespuestaStockList(List<Stock> list);


}
