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
import java.util.Map.Entry;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();

	// Abstraction function:
	// AF(vertices) = 图中所有的点
	// Representation invariant:
	// vertices中不能有重复的点
	// Safety from rep exposure:
	// vertices是可变类型，加了final以及defensive copies

	// TODO constructor
	private void checkRep() {
		Set<Vertex<L>> verticesTest = new HashSet<>();
		verticesTest.addAll(vertices);
		assert verticesTest.size() == vertices.size();
	}
	// TODO checkRep

	@Override
	public boolean add(L vertex) {
		// throw new RuntimeException("not implemented");
		for (Vertex<L> v : vertices) {
			if (v.getName().equals(vertex)) {
				System.out.println(vertex.toString() + "点已经存在");
				return false;
			}
		}
		vertices.add(new Vertex<L>(vertex));
		checkRep();
		return true;
	}

	/**
	 * Add, change, or remove a weighted directed edge in this graph. If weight is
	 * nonzero, add an edge or update the weight of that edge; vertices with the
	 * given labels are added to the graph if they do not already exist. If weight
	 * is zero, remove the edge if it exists (the graph is not otherwise modified).
	 * 
	 * @param source label of the source vertex
	 * @param target label of the target vertex
	 * @param weight nonnegative weight of the edge
	 * @return the previous weight of the edge, or zero if there was no such edge
	 */
	@Override
	public int set(L source, L target, int weight) {
		// throw new RuntimeException("not implemented");
		if (weight < 0) {
			System.out.println("权值为负数");
			return -1;
		}
		this.add(target);
		this.add(source);
		int preWeight = 0;
		for (Vertex<L> v : vertices) {
			if (v.getName().equals(source)) {
				if (v.getTargets() != null) {
					preWeight = v.getTargetWeight(target);
				}
				v.addTarget(target, weight);
			}
			if (v.getName().equals(target)) {
				if (v.getSources() != null) {
					preWeight = v.getSourceWeight(source);
				}
				v.addSource(source, weight);
			}
		}
		return preWeight;
	}

	/**
	 * Remove a vertex from this graph; any edges to or from the vertex are also
	 * removed.
	 * 
	 * @param vertex label of the vertex to remove
	 * @return true if this graph included a vertex with the given label; otherwise
	 *         false (and this graph is not modified)
	 */
	@Override
	public boolean remove(L vertex) {
		// throw new RuntimeException("not implemented");
    	Iterator<Vertex<L>> it=vertices.iterator();
    	while(it.hasNext()) {
    		Vertex<L> tmp = it.next();
    		if(tmp.getName().equals(vertex)) {
    			it.remove();
    			checkRep();
    			return true;
    		}
    		else {
    			if (tmp.getSources().containsKey(vertex)) {
					tmp.removeSource(vertex);
				}
				if (tmp.getTargets().containsKey(vertex)) {
					tmp.removeTarget(vertex);
				}
    		}
    	}
    	checkRep();
    	return false;
	}

	/**
	 * Get all the vertices in this graph.
	 * 
	 * @return the set of labels of vertices in this graph
	 */
	@Override
	public Set<L> vertices() {
		// throw new RuntimeException("not implemented");
		Set<L> tmp = new HashSet<>();
		for (Vertex<L> v : vertices) {
			tmp.add(v.getName());
		}
		checkRep();
		return tmp;
	}

	/**
	 * Get the source vertices with directed edges to a target vertex and the
	 * weights of those edges.
	 * 
	 * @param target a label
	 * @return a map where the key set is the set of labels of vertices such that
	 *         this graph includes an edge from that vertex to target, and the value
	 *         for each key is the (nonzero) weight of the edge from the key to
	 *         target
	 */
	@Override
	public Map<L, Integer> sources(L target) {
		// throw new RuntimeException("not implemented");
		for (Vertex<L> v : vertices) {
			if (v.getName().equals(target))
				return v.getSources();
		}
		checkRep();
		return null;
	}

	@Override
	public Map<L, Integer> targets(L source) {
		// throw new RuntimeException("not implemented");
		for (Vertex<L> v : vertices) {
			if (v.getName().equals(source))
				return v.getTargets();
		}
		checkRep();
		return null;
	}

	// TODO toString()
	public String toString() {
//		StringBuilder sb = new StringBuilder("");
//		for(Vertex<L> v : vertices) {
//			sb.append(v.toString());
//		}
//		return sb.toString();
		String tmp = "";
		for (Vertex<L> v : vertices) {
			tmp = tmp + v.toString();
		}
		return tmp;
	}
}

/**
 * TODO specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {

	// TODO fields
	private L name;
	private Map<L, Integer> sources;
	private Map<L, Integer> targets;
	// Abstraction function:
	// AF(name) = 点的名字
	// AF(sources) = 指向该点所有源点及对应边的权重
	// AF(targets) = 该点指向的所有终点及对应边的权重
	// Representation invariant:
	// 每个边的权重大于0
	// Safety from rep exposure:
	// name, sources, targets设置为private

	// TODO constructor
	public Vertex(L name) {
		this.name = name;
		this.sources = new HashMap<>();
		this.targets = new HashMap<>();
	}
	// TODO checkRep
	private void checkRep() {
		Set<L> s = sources.keySet();
		if (s != null) {
			Iterator<L> it = s.iterator();
			while(it.hasNext()) {
				L v = it.next();
				Integer value = sources.get(v);
				assert value > 0;
			}
		}
		Set<L> t = sources.keySet();
		if (t != null) {
			Iterator<L> it = t.iterator();
			while (it.hasNext()) {
				L v = it.next();
				Integer value = sources.get(v);
				assert value > 0;
			}
		}
	}
	// TODO methods
	public L getName() {
		return name;
	}

	public Map<L, Integer> getSources() {
		return new HashMap<L, Integer>(sources);
	}

	public Map<L, Integer> getTargets() {
		return new HashMap<L, Integer>(targets);
	}

	public void addSource(L source, int weight) {
		sources.put(source, weight);
	}

	public void addTarget(L target, int weight) {
		targets.put(target, weight);
	}

	public int getSourceWeight(L source) {
		if (!sources.containsKey(source)){
			return 0;
		}
		checkRep();
		return sources.get(source);
	}

	public int getTargetWeight(L target) {
		if (!targets.containsKey(target))
			return 0;
		return targets.get(target);
	}

	public void removeSource(L source) {
		sources.remove(source);
	}

	public void removeTarget(L target) {
		targets.remove(target);
	}
	// TODO toString()
	public String toString() {
		String t = "";
		if(sources.containsKey(name)) {
			t =  sources.get(name).toString();
		}else
			t =  "";
		return "指向点" + name + "的点和边的权值为：" + t + sources.toString()+ ", 指向" + name + "的点和边的权值为：" + targets.toString() + "\n"; 
//		return name.toString();
		
			
	}
}
