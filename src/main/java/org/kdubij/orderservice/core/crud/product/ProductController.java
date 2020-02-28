package org.kdubij.orderservice.core.crud.product;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kdubij.orderservice.core.order.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/products")
class ProductController {

    private final ProductService productService;

    @GetMapping
    List<Product> retrieveProducts() {
        log.info("[Product RETRIEVE] START");
        var products = productService.getProducts();
        log.info("[Product RETRIEVE] END");
        return products;
    }

    @PostMapping
    Product createProduct(@RequestBody @Valid CreateProductRequest createProductRequest) {
        log.info("[Product CREATE] START {productRequest={}}", createProductRequest);
        var product = productService.create(createProductRequest);
        log.info("[Product CREATE] END");
        return product;
    }

    @PutMapping("/{productId}")
    Product updateProduct(@RequestBody @Valid UpdateProductRequest productRequest, @PathVariable String productId) {
        log.info("[Product UPDATE] START {productId={}, productRequest={}}", productRequest);
        var product = productService.update(productRequest, productId);
        log.info("[Product UPDATE] END");
        return product;
    }

}
