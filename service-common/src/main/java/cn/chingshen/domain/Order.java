package cn.chingshen.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author charlie
 */
@Entity(name = "t_orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String username;
    private Integer productId;
    private String productName;
    private Integer amount;

    public Order(Integer userId, String username, Integer productId, String productName, Integer amount) {
        this.userId = userId;
        this.username = username;
        this.productId = productId;
        this.productName = productName;
        this.amount = amount;
    }
}
