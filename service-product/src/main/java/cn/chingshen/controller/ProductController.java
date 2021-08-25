package cn.chingshen.controller;

import cn.chingshen.domain.Product;
import cn.chingshen.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie
 */
@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("products/{id}")
    public Product show(@PathVariable("id") Integer id) {

        return productService.findById(id);
    }
}
