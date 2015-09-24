# 简介

`cache4j`是一款轻量级`java`内存缓存框架，实现`FIFO`、`LRU`、`TwoQueues`缓存模型，使用非常方便。  

`cache4j`为`java`开发者提供一种更加轻便的内存缓存方案，杀鸡焉用`EhCache`？  

# 特性

+ 支持并发。
+ 使用简单。
+ 因为简单，所以通用，命中率稳定。

# 使用示例
    
    // 配置
    CacheConfig config = CacheConfig.custom().setMaxElement(10000);  // 缓存元素个数上限
    // 缓存节点工厂
    INodeFactory<String, Object> cachefFactory = new CacheNodeFactory<String, Object>();
    // 缓存模型
    TwoQueuesCache<String, Object> tqc = new TwoQueuesCache<String, Object>(config, cachefFactory);
    
    // 缓存
    tqc.put("a", "av");
    // 获取缓存元素
    tqc.get("a");


