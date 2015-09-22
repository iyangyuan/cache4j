package org.yangyuan.cache.factory;

import org.yangyuan.cache.node.NodeAbstract;

/**
 * 链表节点创建工厂接口
 * @author 杨元
 *
 */
public interface INodeFactory<K, V> {
    
    public NodeAbstract<K, V> createNode(K key, V value);
    
}
