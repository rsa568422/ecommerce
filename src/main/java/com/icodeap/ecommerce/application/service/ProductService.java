package com.icodeap.ecommerce.application.service;

import com.icodeap.ecommerce.application.repository.ProductRepository;
import com.icodeap.ecommerce.domain.Product;
import com.icodeap.ecommerce.domain.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

public class ProductService {

    private final ProductRepository productRepository;
    private final UploadFile uploadFile;

    public ProductService(ProductRepository productRepository, UploadFile uploadFile) {
        this.productRepository = productRepository;
        this.uploadFile = uploadFile;
    }

    public Iterable<Product> getProducts() {
        return productRepository.getProducts();
    }

    public Iterable<Product> getProductsByUser(User user) {
        return productRepository.getProductsByUser(user);
    }

    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    public Product saveProduct(Product product, MultipartFile multipartFile) throws IOException {
        HttpSession httpSession = null;
        if (Objects.isNull(product.getId())) {
            var user = new User();
            user.setId(Integer.parseInt(httpSession.getAttribute("iduser").toString()));
            product.setUser(user);
            product.setDateCreated(LocalDateTime.now());
            product.setDateUpdated(LocalDateTime.now());
            product.setImage(uploadFile.upload(multipartFile));
        } else {
            var productDB = productRepository.getProductById(product.getId());
            if (multipartFile.isEmpty()) {
                product.setImage(productDB.getImage());
            } else {
                if (!"default.jpg".equals(productDB.getImage())) {
                    uploadFile.delete(productDB.getImage());
                }
                product.setImage(uploadFile.upload(multipartFile));
            }
            product.setCode(productDB.getCode());
            product.setUser(productDB.getUser());
            product.setDateCreated(productDB.getDateCreated());
            product.setDateUpdated(LocalDateTime.now());
        }
        return productRepository.saveProduct(product);
    }

    public void deleteProductById(Integer id) {
        productRepository.deleteProductById(id);
    }
}
