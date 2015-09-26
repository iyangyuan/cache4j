package org.yangyuan.cache.test;

import org.yangyuan.cache.core.impl.CacheConfig;
import org.yangyuan.cache.core.impl.TwoQueuesCache;
import org.yangyuan.cache.factory.INodeFactory;
import org.yangyuan.cache.factory.impl.CacheNodeFactory;

public class BaseTest {
    
    public static void main(String[] args) throws Exception{
        
        CacheConfig config = CacheConfig.custom().setMaxElement(3);
        INodeFactory<String, Object> cachefFactory = new CacheNodeFactory<String, Object>();
        TwoQueuesCache<String, Object> tqc = new TwoQueuesCache<String, Object>(config, cachefFactory);
        
        tqc.put("a", "av");
        
        if(!"av".equals(tqc.get("a").toString())){
            throw new Exception("赋值");
        }
        
        if(tqc.size() != 1){
            throw new Exception("计数");
        }
        
        tqc.put("b", "bv");
        tqc.put("c", "cv");
        
        if(tqc.get("a") != null){
            throw new Exception("队列淘汰");
        }
        
        if(tqc.get("b") == null || tqc.get("c") == null){
            throw new Exception("队列淘汰");
        }
        
        if(tqc.size() != 2){
            throw new Exception("计数");
        }
        
        if(tqc.get("c") == null){
            throw new Exception("取值");
        }
        
        if(tqc.size() != 2){
            throw new Exception("计数");
        }
        
        tqc.put("d", "dv");
        
        if(tqc.size() != 3){
            throw new Exception("计数");
        }
        
        if(tqc.get("b") == null){
            throw new Exception("取值");
        }
        
        if(tqc.size() != 2){
            throw new Exception("计数");
        }
        
        if(tqc.get("b") == null || tqc.get("c") != null){
            throw new Exception("队列淘汰");
        }
        
        tqc.put("e", "ev");
        
        if(tqc.size() != 3){
            throw new Exception("计数");
        }
        
        tqc.put("f", "fv");
        
        if(tqc.size() != 3){
            throw new Exception("计数");
        }
        
        if(tqc.get("b") == null || tqc.get("e") == null || tqc.get("d") != null || tqc.get("f") == null){
            throw new Exception("队列淘汰");
        }
        
        tqc.put("e", "ev");
        tqc.put("g", "gv");
        
        if(tqc.get("f") != null){
            throw new Exception("队列淘汰");
        }
        
        tqc.put("b", "bv");
        
        if(tqc.get("e") != null){
            throw new Exception("队列淘汰");
        }
        
        tqc.put("h", "hv");
        
        if(tqc.get("g") != null || tqc.get("h") == null || tqc.get("b") == null){
            throw new Exception("队列淘汰");
        }
        
        System.out.println("all test passed");
    }
    
}
