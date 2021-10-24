import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
//BufferedBitWriter by Mr. David

public class ReadingTxt<E> {
	static HashMap<Character, Integer> frq = new HashMap<Character, Integer>();
	static PQ<Branch<Character>> queue = new PQ<Branch<Character>>();
	static HashMap<Character, String> code = new HashMap<Character, String>();
	static HashMap<Character, String> codes = new HashMap<Character, String>();
	String encoded = "";
	String fileName = "";
	
	// essentially nothing fancy; 
	// asks the user for the input of the file name
	public void filePicker() {
		Scanner in = new Scanner(System.in);
		System.out.println("What is the name of the file you want to compress : ");
		fileName = in.nextLine();
		System.out.println(fileName);
		readFile();
	}
	// this function reads the file that we are trying to compress
	// after reading it by letter, it will also map the letter in a frequency map
	public void readFile() {
		File file = new File(fileName);
		FileReader in;
		try {
			in = new FileReader(fileName);
			for(int i = in.read(); i!=-1; i = in.read()) {
				char letter = (char)i;
				// if the letter doesn't exist in the frq yet, put in a new one
				if (frq.get(letter) == null) {
					frq.put(letter, 1);
				}
				// if it does, increase its frequencies by 1
				else {
					frq.put(letter, frq.get(letter)+1);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// this builds the priority queue based on the previous frequency list
	public void buildPQ() {
		// adds every character to the queue based on frequency
		// it uses a function in PQ
		for(Character c: frq.keySet()) {
			queue.add(new Branch<Character>(c), frq.get(c));
		}
		
		// creates branches that connect the leaves or other branches
		while(queue.size() > 1) {
			int p = queue.getPriority();
			Branch<Character> b = queue.pop();
			int p2 = queue.getPriority();
			queue.add(new Branch<Character>(b,queue.pop()),p+p2);
		}
	}
	
	// this function will iterate itself to create the code based the tree
	// basically if the branch is at the end, we will put information and its code
	// if it is a branch, we will extend to its left and right and this process will continue
	public void genCode(String code, Branch<Character> branch) {
		if(branch.isLeaf) {
			codes.put(branch.info, code);

		}else {
			genCode(code+"0",branch.left);
			genCode(code+"1",branch.right);
		}
	}
	
	// this function actually compresses the file
	// it will also write the compressed code in a new file
	public void encoding() throws IOException {
		try {
			int l = 0;
			// reads the file again
			FileReader in = new FileReader(new File(fileName));
			// creates a new file to write the encoded message in
			BufferedBitWriter bitwriter = new BufferedBitWriter("compfile");
			for(int i = in.read(); i!=-1; i = in.read()) {
				char letter = (char)i;
				String str = codes.get(letter);
				// for every 0s and 1s: Mr. Friedman's code is used to break it down into bits
				for(int j = 0; j < str.length(); j++) {
					int num = Integer.parseInt(str.substring(j, j+1));
					l++;
					if(num == 0) {
						bitwriter.writeBit(false);
					}
					else {
						bitwriter.writeBit(true);
					}
				}
			}
			bitwriter.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// this function writes a new codeMap
	public void genCodeMap() throws IOException {
		// writes a new file called codeMap
		FileWriter file = new FileWriter("codeMap");
		// for every key in this, write the code and the corresponding letter with one space in between
		for(Character key: codes.keySet()) {
			file.write(codes.get(key) + " " + key + "\n");
		}
		file.close();
	}
	
	public static void main(String args[]) throws IOException {
		ReadingTxt run = new ReadingTxt();
		run.filePicker();
		run.readFile();
		run.buildPQ();
		run.genCode("", queue.pop());
		run.genCodeMap();
		run.encoding();
	}
}
