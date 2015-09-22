package org.yangyuan.cache.linked;

import java.util.HashMap;
import java.util.Map;

import org.yangyuan.cache.node.NodeAbstract;
import org.yangyuan.cache.node.impl.GuardNode;


/**
 * 链表抽象
 * @author 杨元
 *
 */
public abstract class LinkedAbstract<K, V> {
    
    /**
     * 头节点
     */
    private NodeAbstract<K, V> headNode;
    
    /**
     * 尾节点
     */
    private NodeAbstract<K, V> tailNode;
    
    /**
     * 用于查找链表节点的map
     * 使得查找节点的时间为O(1)
     */
    private Map<K, NodeAbstract<K, V>> searchMap = new HashMap<K, NodeAbstract<K, V>>();
    
    public LinkedAbstract() {
        //初始化一个“空”链表
        this.headNode = new GuardNode<K, V>();
        this.tailNode = this.headNode;
        this.headNode.setNextNode(this.tailNode);
        this.tailNode.setPrevNode(this.headNode);
    }
    
    /**
     * 头部插入节点
     * @param node
     * @return
     */
    public NodeAbstract<K, V> unshift(NodeAbstract<K, V> node){
        
        return this.insertAfter(this.headNode, node);
        
    }
    
    /**
     * 尾部插入节点
     * @param node
     * @return
     */
    public NodeAbstract<K, V> push(NodeAbstract<K, V> node){
        
        return this.insertBefore(this.tailNode, node);
        
    }
    
    /**
     * 某节点前插入节点
     * @param targetNode  目标节点
     * @param node  要插入的节点
     * @return
     */
    public NodeAbstract<K, V> insertBefore(NodeAbstract<K, V> targetNode, NodeAbstract<K, V> node){
        
        //插入节点
        node.setPrevNode(targetNode.getPrevNode());
        node.getPrevNode().setNextNode(node);
        node.setNextNode(targetNode);
        targetNode.setPrevNode(node);
        
        //记录
        saveToSearchMap(node);
        
        return node;
    }
    
    /**
     * 某节点后插入节点
     * @param targetNode  目标节点
     * @param node  要插入的节点
     * @return
     */
    public NodeAbstract<K, V> insertAfter(NodeAbstract<K, V> targetNode, NodeAbstract<K, V> node){
        
        //插入节点
        node.setNextNode(targetNode.getNextNode());
        node.getNextNode().setPrevNode(node);
        targetNode.setNextNode(node);
        node.setPrevNode(targetNode);
        
        //记录
        saveToSearchMap(node);
        
        return node;
    }
    
    /**
     * 根据key获取某个节点
     * @param key
     * @return
     */
    public NodeAbstract<K, V> get(K key){
        
        return this.searchMap.get(key);
        
    }
    
    /**
     * 删除某个节点
     * @param node  节点对象
     */
    public void remove(NodeAbstract<K, V> node){
        
        node.getNextNode().setPrevNode(node.getPrevNode());
        node.getPrevNode().setNextNode(node.getNextNode());
        
        this.deleteFromSearchMap(node);
    }
    
    /**
     * 删除某个节点
     * @param key  节点key
     */
    public void remove(K key){
        
        NodeAbstract<K, V> node = this.get(key);
        
        if(node != null){
            this.remove(node);
        }
        
    }
    
    /**
     * 从链表头删除一个节点
     * @return
     */
    public NodeAbstract<K, V> shift(){
        
        NodeAbstract<K, V> node = this.headNode.getNextNode();
        
        if(node instanceof GuardNode){
            //链表已空
            return null;
        }else{
            this.remove(node);
            return node;
        }
        
    }
    
    /**
     * 从链表尾删除一个节点
     * @return
     */
    public NodeAbstract<K, V> pop(){
        
        NodeAbstract<K, V> node = this.tailNode.getPrevNode();
        
        if(node instanceof GuardNode){
            //链表已空
            return null;
        }else{
            this.remove(node);
            return node;
        }
    }
    
    /**
     * 链表元素个数
     * @return
     */
    public int size(){
        
        return this.searchMap.keySet().size();
        
    }
    
    /**
     * 将节点存入查找map
     * @param node
     */
    protected void saveToSearchMap(NodeAbstract<K, V> node){
        
        this.searchMap.put(node.getKey(), node);
        
    }
    
    /**
     * 将节点从查找map中移除
     * @param node
     */
    protected void deleteFromSearchMap(NodeAbstract<K, V> node){
        
        this.searchMap.remove(node.getKey());
        
    }
    
}
