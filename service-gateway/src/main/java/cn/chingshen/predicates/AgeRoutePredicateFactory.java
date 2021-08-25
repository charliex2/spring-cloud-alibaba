package cn.chingshen.predicates;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 自定义断言
 * - class name 必须是 XxxxRoutePredicateFactory
 * - extends AbstractRoutePredicateFactory
 *
 * @author charlie
 */
@Component
public class AgeRoutePredicateFactory extends AbstractRoutePredicateFactory<AgeRoutePredicateFactory.Config> {
    public AgeRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("min", "max");
        // return Collections.singleToList("xxx")
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                // 接受前台传入的 age 参数
                // 是否能为空
                // 如果不为空，则进行路由逻辑判断
                String ageStr = serverWebExchange.getRequest().getQueryParams().getFirst("age");
                if (!StringUtils.isEmpty(ageStr)) {
                    int age = Integer.parseInt(ageStr);
                    return age >= config.min && age <= config.max;
                }
                return false;
            }
        };
    }


    @Data
    @NoArgsConstructor
    public static class Config {
        private int min;
        private int max;
    }
}
