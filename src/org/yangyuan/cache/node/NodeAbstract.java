package org.yangyuan.cache.node;

/**
 * 链表节点抽象
 * @author 杨元
 *
 */
public abstract class NodeAbstract<K, V> {
    
    /**
     * 前驱节点
     */
    private NodeAbstract<K, V> prevNode;
    
    /**
     * 后继节点
     */
    private NodeAbstract<K, V> nextNode;
    
    /**
     * 节点键
     */
    private K key;
    
    /**
     * 节点值
     */
    private V value;


    public NodeAbstract<K, V> getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(NodeAbstract<K, V> prevNode) {
        this.prevNode = prevNode;
    }

    public NodeAbstract<K, V> getNextNode() {
        return nextNode;
    }

    public void setNextNode(NodeAbstract<K, V> nextNode) {
        this.nextNode = nextNode;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    
}
