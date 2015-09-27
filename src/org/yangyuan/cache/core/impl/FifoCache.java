package org.yangyuan.cache.core.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.yangyuan.cache.core.ICache;
import org.yangyuan.cache.factory.INodeFactory;
import org.yangyuan.cache.linked.impl.CacheLinked;
import org.yangyuan.cache.node.NodeAbstract;

/**
 * FIFO 先进先出缓存淘汰算法实现
 * @author 杨元
 *
 */
public class FifoCache<K, V> implements ICache<K, V>{
    
    /**
     * 链表
     */
    private CacheLinked<K, V> linked = null;
    
    /**
     * 链表节点工厂
     */
    private INodeFactory<K, V> nodeFactory = null;
    
    /**
     * 配置
     */
    private CacheConfig config = null;
    
    /**
     * 未命中计数
     */
    private AtomicInteger missCount = new AtomicInteger();
    
    /**
     * 命中计数
     */
    private AtomicInteger hitCount = new AtomicInteger();
    
    /**
     * 构造方法
     * @param config  配置对象
     * @param nodeFactory  链表节点对象创建工厂
     */
    public FifoCache(CacheConfig config, INodeFactory<K, V> nodeFactory){
        this.config = config;
        this.linked = new CacheLinked<K, V>();
        this.nodeFactory = nodeFactory;
    }
    
    @Override
    public void put(K key, V value) {
        //创建链表节点
        NodeAbstract<K, V> node = nodeFactory.createNode(key, value);
        
        synchronized (this) {
            //防止元素重复
            this.remove(key);
            
            //判断元素数量是否到达上限
            if(this.size() == config.getMaxElement()){
                //先进先出
                linked.shift();
            }
            
            linked.push(node);
        }
    }

    @Override
    public V get(K key) {
        
        NodeAbstract<K, V> node = linked.get(key);
        if(node != null){
            this.hitCount.incrementAndGet();
            return node.getValue();
        }
        
        this.missCount.incrementAndGet();
        return null;
    }

    @Override
    public double hitRatio() {
        double _hitCount = this.hitCount.doubleValue();
        double _missCount = this.missCount.doubleValue();
        double dividend = _hitCount + _missCount;
        
        if(dividend == 0){
            return 0.0d;
        }
        
        return _hitCount / dividend;
    }

    @Override
    public void clear() {
        this.linked = new CacheLinked<K, V>();
        this.hitCount.set(0);
        this.missCount.set(0);
    }

    @Override
    public int size() {
        return this.linked.size();
    }

    @Override
    public NodeAbstract<K, V> getNode(K key) {
        return linked.get(key);
    }

    @Override
    public void remove(K key) {
        synchronized (this) {
            linked.remove(key);
        }
    }

    public AtomicInteger getMissCount() {
        return missCount;
    }

    public AtomicInteger getHitCount() {
        return hitCount;
    }

    
    
    
}
