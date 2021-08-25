package cn.chingshen.dao;

import cn.chingshen.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author charlie
 */
public interface ProductDao extends JpaRepository<Product, Integer> {
}
