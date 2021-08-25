package cn.chingshen.config;

import cn.chingshen.response.Response;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author charlie
 */
@Configuration
class SentinelExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        String msg = "接口返回异常";
        if (e instanceof FlowException) {
            msg = "流控导致的异常";
        } else if (e instanceof DegradeException) {
            msg = "熔断导致的异常";
        } else if (e instanceof ParamFlowException) {
            msg = "热点控制导致的异常";
        } else if (e instanceof SystemBlockException) {
            msg = "系统规则导致的异常";
        } else if (e instanceof AuthorityException) {
            msg = "授权控制导致的异常";
        }
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        Response<Object> res = new Response<>(500, msg, null);
        httpServletResponse.getWriter().write(res.toString());
    }
}
