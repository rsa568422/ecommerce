package com.icodeap.ecommerce.infrastructure.mapper;

import com.icodeap.ecommerce.domain.OrderProduct;
import com.icodeap.ecommerce.infrastructure.entity.OrderProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductMapper.class})
public interface OrderProductMapper {

    @Mappings({
            @Mapping(source = "pk.order", target = "order"),
            @Mapping(source = "pk.product", target = "product"),
            @Mapping(source = "quantity", target = "quantity")
    })
    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);

    Iterable<OrderProduct> toOrderProducts(Iterable<OrderProductEntity> orderProductEntities);

    List<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntities);

    @InheritInverseConfiguration
    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
}
