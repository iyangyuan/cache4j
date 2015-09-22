package org.yangyuan.cache.core.impl;

/**
 * 缓存配置
 * @author 杨元
 *
 */
public class CacheConfig {
    
    /**
     * 缓存元素个数上限
     */
    private int maxElement;

    private CacheConfig(){
        
    }
    
    /**
     * 自定义配置
     * @return
     */
    public static CacheConfig custom(){
        return new CacheConfig();
    }
    
    public int getMaxElement() {
        return maxElement;
    }

    public CacheConfig setMaxElement(int maxElement) {
        this.maxElement = maxElement;
        return this;
    }
}
