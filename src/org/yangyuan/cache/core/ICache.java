package org.yangyuan.cache.core;

import org.yangyuan.cache.node.NodeAbstract;

/**
 * 缓存接口
 * @author 杨元
 *
 * @param <K>
 * @param <V>
 */
public interface ICache<K, V> {
    
    /**
     * 缓存
     * @param key  键
     * @param value  值
     */
    public void put(K key, V value);
    
    /**
     * 获取缓存数据
     * @param key  键
     * @return
     */
    public V get(K key);
    
    /**
     * 获取缓存节点对象
     * @param key
     * @return
     */
    public NodeAbstract<K, V> getNode(K key);
    
    /**
     * 移除缓存数据
     * @param key
     */
    public void remove(K key);
    
    /**
     * 缓存元素数量
     */
    public int size();
    
    /**
     * 获取命中率
     * @return
     */
    public double hitRatio();
    
    /**
     * 清空缓存
     */
    public void clear();
    
}
