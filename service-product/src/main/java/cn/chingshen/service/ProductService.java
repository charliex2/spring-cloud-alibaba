package cn.chingshen.service;

import cn.chingshen.domain.Product;
import org.springframework.stereotype.Service;

/**
 * @author charlie
 */
public interface ProductService {

    /**
     * @param id
     * @return
     */
    public Product findById(Integer id);
}
