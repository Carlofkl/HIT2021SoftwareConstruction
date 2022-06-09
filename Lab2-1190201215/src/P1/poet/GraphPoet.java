/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    // AF(graph) = 文章中所欲哦字符串构成的图
    // Representation invariant:
    // 保存语料库生成的图
    // Safety from rep exposure:
    // graph设置为private
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
     //   throw new RuntimeException("not implemented");
    	BufferedReader reader = new BufferedReader(new FileReader(corpus));
    	String line;
    	String[] row;
    	List<String> mylist=new ArrayList<>();
    	Map<String,Integer> walks = new HashMap<>();
    	while((line=reader.readLine())!=null) {  //将文件一行字符存入list中
    		row = line.split(" ");
    		mylist.addAll(Arrays.asList(row));
    	}
    	reader.close();
    	for(int i=0;i<mylist.size()-1;i++) {
    		String from=mylist.get(i).toLowerCase();  //变为小写String
    		String to=mylist.get(i+1).toLowerCase();
    		int before=0;
    		String word = from+to;
    		if(walks.containsKey(word)) {
    			before=walks.get(word);
    		}
    		walks.put(word, before+1);
    		graph.set(from, to, before+1);   //graph中加入所有点边
    	}
    	checkRep();
    }
    
    // TODO checkRep
    public void checkRep() {
    	assert graph != null;
    }
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        //throw new RuntimeException("not implemented");
    	StringBuilder build = new StringBuilder();
    	List<String> myWalks = new ArrayList<>();
    	String[] shuru = input.split(" ");
    	myWalks.addAll(Arrays.asList(shuru));  //将待输入字符串按空格分隔存入list中
    	Map<String, Integer> sources = new HashMap<>();
		Map<String, Integer> targets = new HashMap<>();
		for(int i=0;i<myWalks.size()-1;i++) {
			build.append(myWalks.get(i)).append(" ");    //每次在build中加入一个点
			String source=myWalks.get(i).toLowerCase();
			String target=myWalks.get(i+1).toLowerCase();  //一次将两个相邻点变为小写
			targets = graph.targets(source);  //得到前一个源点的所有终点表
			sources = graph.sources(target);  //得到后一个终点的所有源点表
			int max=0;
			String word="";  //待加入的点word
			for(String a:targets.keySet()) {   //从终点表中选取点
				if(sources.containsKey(a)&&sources.get(a)+targets.get(a)>max) { 
					//如果终点表中的点在源点表中出现并且长度大于max，则更新max，并确定暂时的word
					max=sources.get(a)+targets.get(a);
					word=a;
				}
			}
			if(max>0) {
				build.append(word+" ");  //加入word点
			}
		}
		build.append(myWalks.get(myWalks.size()-1));  //加入并未考虑的最后一个点
		return build.toString();
    }
    
    // TODO toString()
    public String toString() {
    	String tmp = graph.toString();
    	return tmp;
    }
}
