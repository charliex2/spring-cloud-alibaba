package cn.chingshen.controller;

import cn.chingshen.domain.Order;
import cn.chingshen.domain.Product;
import cn.chingshen.service.OrderService;
import cn.chingshen.service.ProductService;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author charlie
 */
@RestController
public class OrderController {

    OrderService orderService;
    ProductService productService;

    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    /**
     * blockHandler: 处理资源内部发生 BlockException;
     * fallback: 处理资源内容发生的 throwable 错误;
     * blockHandler, fallback
     */
    @GetMapping("orders")
//    @SentinelResource(value = "orders.store", blockHandler = "createBlockHandler", fallback = "createFallback")
    @SentinelResource(value = "orders.store")
    public Order create(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "age", required = false) Integer age) {
        Product product = productService.findById(1);
        Order order = new Order(1, "hello", product.getId(), product.getName(), 20);
        return orderService.store(order);
    }

//    /**
//     * Block Handler 。可以与 blockClass 联用，将这些方法移动到一个 Class 中
//     * - 参数必须与原方法一致，最后可以有一个 Exception
//     * - 返回值必须一致
//     *
//     * @param name
//     * @param age
//     * @param e
//     * @return
//     */
//    public Order createBlockHandler(String name, Integer age, BlockException e) {
//        // todo
//        return null;
//    }
//
//    /**
//     * FallBack。 可以用 fallback 联用，将这些方法移动到一个 Class 中
//     * - 参数必须与原方法一致，最后可以有一个 Exception
//     * - 返回值必须一致
//     *
//     * @param name
//     * @param age
//     * @param e
//     * @return
//     */
//    public Order createFallback(String name, Integer age, Throwable e) {
//        // todo
//        return null;
//    }
}
