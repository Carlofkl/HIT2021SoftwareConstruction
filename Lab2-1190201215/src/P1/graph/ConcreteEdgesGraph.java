/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    // AF(vertices) = 
    // AF(edges) = 
    // Representation invariant:
    // edge长度大于0，始点终点不为空
    // vertex必须在vertices中
    // 两点间只有一条边
    // Safety from rep exposure:
    // vertices和edges设为private
    // vertices和edges设为final
    
    // TODO constructor
    
    // TODO checkRep
    public void checkRep() {
    	for (Edge<L> edge : edges) {
    		assert vertices.contains(edge.getSource());
    		assert vertices.contains(edge.getTarget());
    		assert edge.getWeight() > 0;
    	}
    }
    @Override public boolean add(L vertex) {
        //throw new RuntimeException("not implemented");
    	if(vertices.contains(vertex)) {
    		System.out.println("该点已经存在");
    		return false;
    	}
    	return vertices.add(vertex);
    }
    
    @Override public int set(L source, L target, int weight) {
        //throw new RuntimeException("not implemented");
    	int preWeight = 0;
    	if (weight < 0) {
    		System.out.println("权值为负数");
    		return -1;
    	}
    	Iterator<Edge<L>> it = edges.iterator();
    	while (it.hasNext()) {
    		Edge<L> e = it.next();
    		if (e.getSource().equals(source) && e.getTarget().equals(target)) {
    			preWeight = e.getWeight();
    			it.remove();	
    		}
    	}
    	if (weight == 0) {
    		return preWeight;
    	}
    	Edge<L> newEdge = new Edge<L>(source, target, weight);
    	edges.add(newEdge);
    	//checkRep();
    	return preWeight;
    }
    
    @Override public boolean remove(L vertex) {
        //throw new RuntimeException("not implemented");
    	if (vertices.contains(vertex)) {
    		vertices.remove(vertex);
    		Iterator<Edge<L>> it = edges.iterator();
    		while(it.hasNext()) {
    			Edge<L> e = it.next();
    			if (e.getSource().equals(vertex) || e.getTarget().equals(vertex))
    				it.remove();
    		}
    		return true;
    	}
    	checkRep();
    	return false;
    }
    
    @Override public Set<L> vertices() {
        //throw new RuntimeException("not implemented");
    	return new HashSet<L>(vertices);
    }
    
    @Override public Map<L, Integer> sources(L target) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> mapTarget = new HashMap<>();
    	for (Edge<L> e : edges) {
    		if (e.getTarget().equals(target))
    			mapTarget.put(e.getSource(), e.getWeight());
    	}
    	checkRep();
    	return mapTarget;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        //throw new RuntimeException("not implemented");
    	Map<L, Integer> mapSource = new HashMap<>();
    	for (Edge<L> e : edges) {
    		if (e.getSource().equals(source))
    			mapSource.put(e.getTarget(), e.getWeight());
    	}
    	checkRep();
    	return mapSource;
    }
    
    // TODO toString()
    public String toString() {
    	String tmp = "";
    	for (Edge<L> e : edges) {
    		tmp = tmp + e.toString();
    	}
    	return tmp;
    }
}

/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODO fields
	private final L source, target;
	private final int weight;
    // Abstraction function:
    /* AF(source) = 边的始点
     * AF(target) = 边的终点
     * AF(weight) = 边的权重
     */
	
    // Representation invariant:
    // weight > 0  source != null, target != null
 
    // Safety from rep exposure:
    // source, target, weight 设置为private
    
    // TODO constructor
    public Edge(L source, L target, int weight) {
    	this.source = source;
    	this.target = target;
    	this.weight = weight;
    }

    // TODO checkRep
        public void checkRep() {
    	assert source != null;
    	assert target != null;
    	assert weight > 0;
    }
    // TODO methods
    public L getSource() {
    	return source;
    }
    public L getTarget() {
    	return target;
    }
    public int getWeight() {
    	return weight;
    }
    public boolean sameEdge() {
    	if (source.equals(target))
    		return true;
    	return false;
    }
    // TODO toString()
    public String toString() {
    	return source + "到" + target + "的权重为" + weight + '\n';
    }
}
