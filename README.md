### 启动 Nacos

```shell
cd nacos/bin
sh startup.sh -m standalone
```

### 启动多个服务实例

修改 VM Option

```
-Dserver.port=8082
```

### 启动 sentinel dashboard

```shell
cd sentinel
java -Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.2.jar
```

默认账号密码

```shell
账号: sentinel / 密码: sentinel
```