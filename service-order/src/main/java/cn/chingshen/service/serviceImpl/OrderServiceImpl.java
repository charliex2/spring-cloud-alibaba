package cn.chingshen.service.serviceImpl;

import cn.chingshen.dao.OrderDao;
import cn.chingshen.domain.Order;
import cn.chingshen.service.OrderService;
import org.springframework.stereotype.Service;

/**
 * @author charlie
 */
@Service
public class OrderServiceImpl implements OrderService {
    OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order store(Order order) {
        return orderDao.save(order);
    }
}
