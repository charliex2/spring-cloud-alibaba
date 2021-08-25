package cn.chingshen.service.serviceImpl;

import cn.chingshen.dao.ProductDao;
import cn.chingshen.domain.Product;
import cn.chingshen.service.ProductService;
import org.springframework.stereotype.Service;

/**
 * @author charlie
 */
@Service
public class ProductServiceImpl implements ProductService {

    ProductDao productDao;

    public ProductServiceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product findById(Integer id) {
        return productDao.findById(id).get();
    }
}
