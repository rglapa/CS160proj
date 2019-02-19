package datamining;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Tokenizer {
	
	private boolean[] ASCII;
	private ArrayList<String> tokens = new ArrayList<String>();
	
	/**
	 * ctor for Tokenizer
	 * no param
	 * Creates ASCII array
	 */
	public Tokenizer() {
		ASCII = new boolean[256];
		for(int i = 0; i < ASCII.length; i++) {
			if(i >= 97 && i <= 122) // a-z
				ASCII[i] = true;
			else if (i >= 65 && i <= 90) // A-Z
				ASCII[i] = true;
			else
				ASCII[i] = false;
		}
	}
	
	/**
	 * tokenizes an individual line
	 * @param line is the line to be tokenized
	 */
	public void tokenizeLine(String line) {
		
		line = line + " "; // ez exit
		Stack<String> buffer = new Stack<String>(); // buffer to hold strings ready to be tokenized
		String temp = ""; // temp hold in strings aren't ready to be tokenized
		boolean tokenRdy = false; // check if contents of buffer are a token
		boolean emailDetected = false; // check if contents of buffer are a email
		
		for(int i = 0; i < line.length(); i++) {
			int asciiBit = (int) line.charAt(i); // turn current letter into ascii value
			
			// if a-z or A-Z 
			if(ASCII[asciiBit] == true)
				temp = temp + line.substring(i, i + 1);
			
			// @ detected
			else if(asciiBit == 64) {
				if(!temp.isEmpty()) {
					buffer.push(temp); // push in letters before @
					buffer.push(line.substring(i, i + 1)); // push in @
					temp = ""; // wipe
				}
			}
			
			// . detected after @
			else if(asciiBit == 46 && !buffer.isEmpty()) {
				if(buffer.peek().equals("@")) {
					// if there are letters after . (like example@test.com)
					if(ASCII[(int) line.charAt(i + 1)] == true) {
						temp = temp + line.substring(i, i + 1);
						emailDetected = true;
					}
					// no letters after, get rid of @ (like example@test. Hello)
					else
						if(!temp.contains("."))
							buffer.pop();
				}
			}
			
			// not a-z or A-Z
			else {
				// store string as ready to be tokenized & clean up temp
				if(temp.length() > 0) {
					if(!emailDetected) {
						buffer.push(temp);
						temp = "";
						tokenRdy = true;
					}
					else {
						buffer.push(temp);
						temp = "";
						tokenRdy = true;
					}
				}
			}
			
			if(tokenRdy) {
				if(emailDetected) {
					// pop 3 times because emails have 3 parts (test @ mail.com)
					String tail = buffer.pop(); // mail.com
					String mid = buffer.pop(); // @
					String head = buffer.pop(); // test
					String email = head + mid + tail;
					tokens.add(email);
					emailDetected = false;
				}
				else
					tokens.add(buffer.pop());
				tokenRdy = false;
			}
		}
	}
	
	public ArrayList<String> getTokens() {
		return tokens;
	}
	
	public static void main(String[] args) throws IOException {
		Tokenizer test = new Tokenizer();
		
		// file reading stuff
		//FileReader fr = new FileReader("Data1_07449817ascii.txt");
		//BufferedReader br = new BufferedReader(fr);
		//String line;
		//while((line = br.readLine()) != null)
			//test.tokenizeLine(line);
		//br.close();
		//fr.close();
	
		test.tokenizeLine("first.last@mail. com");
		
		ArrayList<String> tokens = test.getTokens();
		for(int i = 0; i < tokens.size(); i++){
			int count = i + 1;
			System.out.println("Token" + count + " " + tokens.get(i));
		}
	}
	
	/*Using virtual input technique*/
	/*Read one file at a time*/
	/*Everything inside the file including words, blank spaces, special characters should be treated as a token
	 *Multiple blanks continuously should be treated as a single blank .
	 * eg: "Discovery       of an object"=> this sentence should be read as "Discovery of an object"   */
}
