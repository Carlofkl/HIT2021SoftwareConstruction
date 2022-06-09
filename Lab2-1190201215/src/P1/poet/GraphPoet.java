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
    // AF(graph) = ����������Ŷ�ַ������ɵ�ͼ
    // Representation invariant:
    // �������Ͽ����ɵ�ͼ
    // Safety from rep exposure:
    // graph����Ϊprivate
    
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
    	while((line=reader.readLine())!=null) {  //���ļ�һ���ַ�����list��
    		row = line.split(" ");
    		mylist.addAll(Arrays.asList(row));
    	}
    	reader.close();
    	for(int i=0;i<mylist.size()-1;i++) {
    		String from=mylist.get(i).toLowerCase();  //��ΪСдString
    		String to=mylist.get(i+1).toLowerCase();
    		int before=0;
    		String word = from+to;
    		if(walks.containsKey(word)) {
    			before=walks.get(word);
    		}
    		walks.put(word, before+1);
    		graph.set(from, to, before+1);   //graph�м������е��
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
    	myWalks.addAll(Arrays.asList(shuru));  //���������ַ������ո�ָ�����list��
    	Map<String, Integer> sources = new HashMap<>();
		Map<String, Integer> targets = new HashMap<>();
		for(int i=0;i<myWalks.size()-1;i++) {
			build.append(myWalks.get(i)).append(" ");    //ÿ����build�м���һ����
			String source=myWalks.get(i).toLowerCase();
			String target=myWalks.get(i+1).toLowerCase();  //һ�ν��������ڵ��ΪСд
			targets = graph.targets(source);  //�õ�ǰһ��Դ��������յ��
			sources = graph.sources(target);  //�õ���һ���յ������Դ���
			int max=0;
			String word="";  //������ĵ�word
			for(String a:targets.keySet()) {   //���յ����ѡȡ��
				if(sources.containsKey(a)&&sources.get(a)+targets.get(a)>max) { 
					//����յ���еĵ���Դ����г��ֲ��ҳ��ȴ���max�������max����ȷ����ʱ��word
					max=sources.get(a)+targets.get(a);
					word=a;
				}
			}
			if(max>0) {
				build.append(word+" ");  //����word��
			}
		}
		build.append(myWalks.get(myWalks.size()-1));  //���벢δ���ǵ����һ����
		return build.toString();
    }
    
    // TODO toString()
    public String toString() {
    	String tmp = graph.toString();
    	return tmp;
    }
}
