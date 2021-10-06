import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PQ<E> {
	
	private ArrayList<Node> list = new ArrayList<Node>();
	
	public String toString() {
		String output = "";
		for(Node n: list) {
			output += n.data.toString();
			output += "  ";
		}
		return output;
	}
	
	public void add(E e, int priority) {
		if(list.size() == 0) {
			list.add(new Node(e, priority));
			return;
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).priority > priority) {
				list.add(i, new Node(e, priority));
				return;
			}
		}
		list.add(new Node(e, priority));
		
	}
	
	public E pop() {
		Node temp =  list.get(list.size()-1);
		list.remove(list.size()-1);
		return temp.data;
	}
	
	public class Node {	
		E data;
		int priority;
			
		public Node(E data, int inte) {
			this.data = data;
			priority = inte;
		}
	}
	
	public int size() {
		return list.size();
	}
	
	
}