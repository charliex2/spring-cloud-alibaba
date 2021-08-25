package cn.chingshen.dao;

import cn.chingshen.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author charlie
 */
public interface OrderDao extends JpaRepository<Order, Integer> {
}
