package com.icodeap.ecommerce.infrastructure.mapper;

import com.icodeap.ecommerce.domain.Stock;
import com.icodeap.ecommerce.infrastructure.entity.StockEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface StockMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "unitIn", target = "unitIn"),
            @Mapping(source = "unitOut", target = "unitOut"),
            @Mapping(source = "balance", target = "balance"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "product", target = "product"),
            @Mapping(source = "dateCreated", target = "dateCreated")
    })
    Stock toStock(StockEntity stockEntity);

    Iterable<Stock> toStocks(Iterable<StockEntity> stockEntities);

    @InheritInverseConfiguration
    StockEntity toStockEntity(Stock stock);
}
