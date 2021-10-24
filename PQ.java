import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PQ<E> {
	
	private ArrayList<Node> list = new ArrayList<Node>();
	
	// function that allows us to immediately print it out
	public String toString() {
		String output = "";
		for(Node n: list) {
			output += n.data.toString();
			output += "  ";
		}
		return output;
	}
	
	// this is the add function that adds the nodes in terms of their frequency
	// I tried to do binary search, but it didn't really work for me
	public void add(E e, int priority) {
		// edge case for when there nothing in the list
		if(list.size() == 0) {
			list.add(new Node(e, priority));
			return;
		}
		// edge case for when there are only 1 element in the list
		if(list.size() == 1) {
			if(priority > list.get(list.size()-1).priority) {
				list.add(new Node(e, priority));
				return;
			}
			else {
				list.add(0, new Node(e, priority));
				return;
			}
		}
		// can you look over what is wrong with my binary search
		// i don't reallu understand what is wrong
		// it runs but it doesn't do anything
		/* int start = 0;
		int end = list.size();
		boolean run = true;
		
		//System.out.println("end  " + list.get(end).priority);
		// edge case of when the priority is the biggest
		
		if (start == end) {
			list.add(start, new Node(e, priority));
			return;
		}
		
		if (priority > list.get(end-1).priority) {
			list.add(new Node(e, priority));
			return;
		}
		
		// edge case of when the priority is the smallest
		if(priority < list.get(0).priority) {
			list.add(start, new Node(e, priority));
			return;
		}
		
		// other search
		while(run){
			// when the start and the end are equal, we are done with our search
			if (start == end) {
				list.add(start, new Node(e, priority));
				return;
			}
			
			else if(list.get(end-1).priority == priority) {
				list.add(end, new Node(e, priority));
				return;
			}
			
			else if(list.get(start).priority == priority) {
				list.add(start, new Node(e, priority));
				return;
			}
			
			else if(list.get((start+end)/2).priority == priority) {
				list.add(start+end/2, new Node(e, priority));
				return;	
			}

			else if(list.get(((start+end)/2)).priority > priority) {
				end = (start+end)/2-1;
			}
			
			else if(list.get((start+end)/2).priority < priority) {
				start = (start+end)/2 + 1;
			}
			
			else {
				System.out.println(priority);
			}
		}*/
		
		// main case for when there are two terms in the list
		// loop through every term from smallest to biggest
        for(int i = 0; i < list.size(); i++) {
			if(list.get(i).priority > priority) {
				list.add(i, new Node(e, priority));
				return;
			}
		}
		list.add(new Node(e, priority));
		
	}
	
	// function for removing the first term of a list
	public E pop() {
		Node temp =  list.get(0);
		list.remove(0);
		return temp.data;
	}
	
	// returns the priority of an element
	public int getPriority() {
		return list.get(0).priority;
	}
	
	// this is just the node class and a node contains a random type of data and the priority
	public class Node {	
		E data;
		int priority;
			
		public Node(E data, int inte) {
			this.data = data;
			priority = inte;
		}
	}
	
	// returns the size of the list
	public int size() {
		return list.size();
	}
	
}
