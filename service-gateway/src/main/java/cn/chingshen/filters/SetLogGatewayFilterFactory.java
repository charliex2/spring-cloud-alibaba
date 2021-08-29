package cn.chingshen.filters;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author charlie
 */
@Component
public class SetLogGatewayFilterFactory
        extends AbstractGatewayFilterFactory<SetLogGatewayFilterFactory.Config> {

    public SetLogGatewayFilterFactory() {
        super(Config.class);
    }

    /**
     * 读取配置文件中的参数，赋值到配置类中
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("cacheLogOpen", "consoleLogOpen");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                if (config.isCacheLogOpen()) {
                    System.out.println("cache log is open");
                }

                if (config.isConsoleLogOpen()) {
                    System.out.println("console log is open");
                }

                exchange.getResponse().getHeaders().add("hello", "world");
                return chain.filter(exchange);
            }
        };
    }


    @Data
    @NoArgsConstructor
    public static class Config {
        private boolean cacheLogOpen;
        private boolean consoleLogOpen;
    }
}
