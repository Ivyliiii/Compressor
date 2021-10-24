import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Decompressor {
	static HashMap<String, String> codes = new HashMap<String, String>();
	
	// this function is to read the codeMap by line and create a new map that connects the cdoes
	// to the individual characters
	public void readFile() throws IOException{
		File file = new File("codeMap");
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(file));
			 for(String s = in.readLine(); s != null; s = in.readLine()) {
				String temp = ""; // keeps track of the code
				// this variable keeps track of the number of code we have in the line so we can cut the character
				int num = 0;
				// if the lines has no code --> skip it 
				// specifically the empty new lines
				if(s.isEmpty()) {
					System.out.println("skiped");
					continue;
				}
				
				// gets the numbers at the start of a line so the code of the character
				for(int i = 0; i < s.length(); i++) {
					// only if it is a 0 or 1
					if((int)(s.charAt(i)) == 48 || (int)(s.charAt(i)) == 49) {
						temp += s.charAt(i);
						num++;
					}
					else {
						// if it is no longer code, skep this loop
						break;
					}
				}
				// setting the string equal to the character
				s = s.substring(num);
				
				// if there is not character behind the code, map it with a new line
				if((s.isEmpty()) || s.equals(" ")) {
					codes.put(temp, "\n");
				}
				
				// if it just normal characters
				else {
					// keeps tract of the character
					String l = "";
					// runs through the remainder of the line
					// this will start running at the second character because I placed a empty space
					// infront of every character to distinguish 0s in the text and 0s in the code
					for(int i = 1; i < s.length(); i++) {
						l += s.charAt(i);
					}
					// maps the codes
					codes.put(temp, l);
					temp = "";
					num = 0;
				}
			 }
			 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// this function turns the compressed file back to a file with regular words
	// uses Mr. Friedman's code to read through the encoded thing by bits
	public void rewrite() throws IOException {
		try {
			BufferedBitReader bitReader = new BufferedBitReader("compfile");
			// writes the decompressed stuff into a few file
			FileWriter file = new FileWriter("decompressed");
			// keeps track of the current code
			String curr = "";
			// if there is still more to the file
			while(bitReader.hasNext()) {
				if(bitReader.readBit()) {
					curr += "1";
				}
				else {
					curr += "0";
				}
				// checks to see if the current codes represent a letter
				// if it does write the character into the new file
				if(codes.get(curr) != null) {
					file.write(codes.get(curr));
					//System.out.println(codes.get(curr));
					curr = "";
				}
			}
			bitReader.close();
			file.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]) throws IOException {
		Decompressor run = new Decompressor();
		run.readFile();
		System.out.println(codes.size());
		for(String c: codes.keySet()) {
			System.out.println(c + codes.get(c));
		}
		run.rewrite();
	}
}
