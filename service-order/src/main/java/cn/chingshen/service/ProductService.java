package cn.chingshen.service;

import cn.chingshen.domain.Product;
import cn.chingshen.service.fallback.ProductServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author charlie
 */
@FeignClient(value = "service-product",
//        fallback = ProductServiceFallback.class,
        fallbackFactory = ProductServiceFallback.class
)
public interface ProductService {

    @GetMapping("/products/{id}")
    Product findById(@PathVariable Integer id);

    @GetMapping("products")
    List<Product> getProducts();
}
