# cat-aop-starter

在dianping-cat的基础上进行封装，支持AOP，在方法上加上注解即可使用：

dianping-cat的地址为：https://github.com/dianping/cat

## 导入依赖

### maven下载jar包

正在上传到mvn中心仓库

### 手动打jar包

下载源码后，用IDE打开，由于cat-client.jar不在mvn中心仓库中，所以一般在网上下载不了，于是我就把cat-client.jar放到src/main/resources/lib下了，可以手动导入
导入完后可以用IDE打jar包，注意把所有外部依赖都extract进jar包

## 如何使用

首先要在客户机根目录和项目目录下进行配置
参考CAT的文档：https://github.com/dianping/cat/tree/master/lib/java

然后在方法上开启@EnableCat注解即可，有2个参数可供选择

-   transactionName表示本次执行过程的名字
-   countMetricName表示统计方法被调用次数

```java
@GetMapping("/a")
@EnableCat(transactionName = "test2", countMetricName = "test2")
public String testA() {
    int a = 1 / 0;
    return "test...";
}
```

