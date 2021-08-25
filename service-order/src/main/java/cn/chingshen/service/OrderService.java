package cn.chingshen.service;

import cn.chingshen.domain.Order;

/**
 * @author charlie
 */
public interface OrderService {
    /**
     * @param order
     * @return
     */
    public Order store(Order order);
}
