package org.yangyuan.cache.test;

import org.yangyuan.cache.core.impl.CacheConfig;
import org.yangyuan.cache.core.impl.TwoQueuesCache;
import org.yangyuan.cache.factory.INodeFactory;
import org.yangyuan.cache.factory.impl.CacheNodeFactory;

public class ThreadTest {
    
    private static CacheConfig config = CacheConfig.custom().setMaxElement(3);
    private static INodeFactory<String, Object> cachefFactory = new CacheNodeFactory<String, Object>();
    private static TwoQueuesCache<String, Object> tqc = new TwoQueuesCache<String, Object>(config, cachefFactory);
    
    private static Thread threadRead1 = new Thread(new Runnable() {
        @Override
        public void run() {
            
            tqc.get("k");
            tqc.get("k");
            tqc.get("k");
            tqc.get("k");
            tqc.get("k");
            
        }
    });
    
    private static Thread threadRead2 = new Thread(new Runnable() {
        @Override
        public void run() {
            
            tqc.get("k");
            tqc.get("k");
            tqc.get("k");
            tqc.get("k");
            tqc.get("k");
            
        }
    });
    
    private static Thread threadWrite1 = new Thread(new Runnable() {
        @Override
        public void run() {
            
            tqc.put("k", "v1");
            tqc.put("k", "v1");
            tqc.put("k", "v1");
            
            tqc.put("k", "v2");
            
        }
    });
    
    public static void main(String[] args) throws Exception {
        threadRead1.start();
        threadRead2.start();
        threadWrite1.start();
        
        while(threadRead1.isAlive() || threadRead2.isAlive() || threadWrite1.isAlive()){}
        
        if(!"v2".equals(tqc.get("k"))){
            throw new Exception("");
        }
        
        System.out.println("all test passed");
    }
    
}
