package com.icodeap.ecommerce.infrastructure.mapper;

import com.icodeap.ecommerce.domain.OrderProduct;
import com.icodeap.ecommerce.infrastructure.entity.OrderProductEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {OrderMapper.class, ProductMapper.class})
public interface OrderProductMapper {

    OrderProduct toOrderProduct(OrderProductEntity orderProductEntity);

    Iterable<OrderProduct> toOrderProducts(Iterable<OrderProductEntity> orderProductEntities);

    List<OrderProduct> toOrderProductList(Iterable<OrderProductEntity> orderProductEntities);

    @InheritInverseConfiguration
    OrderProductEntity toOrderProductEntity(OrderProduct orderProduct);
}
