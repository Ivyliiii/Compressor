import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class ReadingTxt<E> {
	static HashMap<Character, Integer> frq = new HashMap<Character, Integer>();
	static PQ<Branch<Character>> queue = new PQ<Branch<Character>>();
	static HashMap<Character, String> code = new HashMap<Character, String>();
	static HashMap<Character, String> compress = new HashMap<Character, String>();

	
	public void readFile() {
		File file = new File("file");
		FileReader in;
		try {
			in = new FileReader(file);
			for(int i = in.read(); i!=-1; i = in.read()) {
				char letter = (char)i;
				if (frq.get(letter) == null) {
					frq.put(letter, 1);
				}
				else {
					frq.put(letter, frq.get(letter)+1);
				}
			}
			
			for(char c : frq.keySet()) {
				System.out.println(c);
				System.out.println(frq.get(c));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buildPQ() {
		for(Character c: frq.keySet()) {
			queue.add(new Branch<Character>(c), frq.get(c));
			System.out.println(queue.size());
		}
		int num = 0;
		while(queue.size() > 1) {
			num++;
			System.out.println(num);
			int p = queue.getPriority();
			Branch<Character> b = queue.pop();
			int p2 = queue.getPriority();
			queue.add(new Branch<Character>(b,queue.pop()),p+p2);
		}
	}
	
	public void genCode(String code, Branch<Character> branch) {
		if(branch.isLeaf) {
			compress.put(branch.info, code);

		}else {
			genCode(code+"0",branch.left);
			genCode(code+"1",branch.right);
		}
	}
	
	public static void main(String args[]) {
		ReadingTxt run = new ReadingTxt();
		run.readFile();
		run.buildPQ();
		run.genCode("", queue.pop());
		System.out.println(compress.size());
		for(Character branch: compress.keySet()) {
			System.out.println(branch + "   " + compress.get(branch));
		}
	}
}
