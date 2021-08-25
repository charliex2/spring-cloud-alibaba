### Gateway 执行流程
1. HttpWebHandlerAdapter 组装网关上下文
2. DispatcherHandler 循环遍历 Mapping，获取 Handler
3. RoutePredicateHandlerMapping 
   匹配路由信息，通过路由断言，判断路由是否可用
    断言失败，重复 2；断言成功，则进入下一步。
4. FilteringWebHandler 创建过滤器链，调用过滤器

### 内置断言工厂
1. 基于 Datetime 类型的断言工厂
   1. AfterRoutePredicateFactory
   2. BeforeRoutePredicateFactory
   3. BetweenRoutePredicateFactory
   ```yaml
   - After=2019-12-12T12:00:00.789+08:00[Asia/Shanghai]
   ```
2. 基于远程地址的断言工厂
    1. RemoteAddrRoutePredicateFactory
   ```yaml
   - RemoteAddr=192.168.1.1/24 
   ```
3. 基于 Cookie 的断言工厂
    1. CookieRoutePredicateFactory. 判断请求 Cookie 是否具有给定名称且值与正则表达式匹配。
   ```yaml
   - Cookie=chocolate,ch
   ```
4. 基于 Header 的断言工厂
   1. HeaderRoutePredicateFactory. 判断 Header 是否具有给定名称且值与正则表达式匹配。
   ```yaml
   - Header=X-Requestid,\d+
   ```
5. 基于 Host 的断言工厂
   1. HostRoutePredicateFactory. 判断请求 Host 是否满足匹配规则。
   ```yaml
   - Host=**.testhost.org
   ```
6. 基于 Method 的断言工厂
   MethodRoutePredicateFactory. 请求类型是否与指定的了类型相匹配。
   ```yaml
   - Method=GET
   ```
7. 基于 Path 的断言工厂
   PathRoutePredicateFactory. 请求 URI 部分是否满足路径规划。
   ```yaml
   - Path=/foo/{segment}
   ```
8. 基于 Query 的断言工厂
   QueryRoutePredicateFactory, 接受两个参数，请求 param 和正则表达式，判断请求参数是否有给定值并且值与值与正则表达式匹配
   ```yaml
   - Query=ba,fo
   ```
9. 基于路由权重的断言工厂
   WeightRoutePredicateFactory, 接受一个[组名,权重]，然后同组内按权重比例转发
   ```yaml
   # route1
   - Weight=group2,9
   # route2
   - Weight=group2,1
   ```



### 自定义断言
