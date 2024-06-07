package com.icodeap.ecommerce.infrastructure.configuration;

import com.icodeap.ecommerce.application.repository.ProductRepository;
import com.icodeap.ecommerce.application.service.ProductService;
import com.icodeap.ecommerce.application.service.UploadFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }

    @Bean
    public UploadFile uploadFile() {
        return new UploadFile();
    }
}
