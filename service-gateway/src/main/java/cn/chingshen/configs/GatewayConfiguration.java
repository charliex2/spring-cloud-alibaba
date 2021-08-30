package cn.chingshen.configs;

import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author charlie
 * 要使用 Sentinel 对 Gateway 进行限流操作，只需要注入响应的 Filter 和 ExceptionHandler 即可。
 */
@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;


    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolverProvider, ServerCodecConfigurer serverCodecConfigurer) {
        viewResolvers = viewResolverProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 注入 Sentinel Gateway Filter
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * Gateway 过滤规则
     */
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();

        rules.add(new GatewayFlowRule("order_route")
                .setCount(1)
                .setIntervalSec(1)
        );

        GatewayRuleManager.loadRules(rules);
    }


    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }


    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = (serverWebExchange, throwable) -> {
            Map<String, String> body = new HashMap<>(2);
            body.put("code", "0");
            body.put("message", "api rate is limited");

            return ServerResponse.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(body);
        };

        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }


    @PostConstruct
    public void initApiDefinitions() {
        Set<ApiDefinition> definitions = new HashSet<>();

        definitions.add(new ApiDefinition().setPredicateItems(new HashSet<ApiPredicateItem>() {
            {
                add(new ApiPathPredicateItem().setPattern("/srv-orders/orders/**"));
            }
        }));

        // add more ApiDefinition

        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

}
