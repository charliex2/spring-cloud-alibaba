package cn.chingshen.service.fallback;

import cn.chingshen.domain.Product;
import cn.chingshen.service.ProductService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author charlie
 */
@Slf4j
public class ProductServiceFallbackFactory implements FallbackFactory<ProductService> {
    @Override
    public ProductService create(Throwable throwable) {
        return new ProductService() {
            @Override
            public Product findById(Integer id) {
                // 可以打印 Exception
                log.error("{}", throwable);
                // todo
                return null;
            }

            @Override
            public List<Product> getProducts() {
                return null;
            }
        };
    }
}
