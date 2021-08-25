package cn.chingshen.predicates;

import lombok.Data;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
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
    public AgeRoutePredicateFactory(Class<Config> configClass) {
        super(configClass);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("min", "max");
        // return Collections.singleToList("xxx")
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return null;
    }


    @Data
    public static class Config {
        private int min;
        private int max;
    }
}
