package cn.chingshen.service.fallback;

import cn.chingshen.domain.Product;
import cn.chingshen.service.ProductService;

import java.util.List;

/**
 * @author charlie
 */
public class ProductServiceFallback implements ProductService {
    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }
}
