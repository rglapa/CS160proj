package datascience;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class Tokenizer {


	/*Using virtual input technique*/
	/*Read one file at a time*/
	/*Everything inside the file including words, blank spaces, special characters should be treated as a token
	 *Multiple blanks continuously should be treated as a single blank .
	 * eg: "Discovery       of an object"=> this sentence should be read as "Discovery of an object"   */

	public void sayHello()
	{
		System.out.println("Hello from tokenizer");
	}

	private static int read(Stack<Integer> buffer, FileReader is) throws IOException {
		int c = 0;

		try {
			if(!buffer.empty()) c = buffer.pop();
			else c = is.read();
		} catch(Exception e) {
			System.out.println(e);
		}

		return c;
	}

	private static void push_back(Stack<Integer> buffer, int c) {
		buffer.push(c);
	}

	public static void readTokens1(String fileName) {
		String str = fileName;
        byte[] byteList = str.getBytes(StandardCharsets.US_ASCII);
        String token1 = "";
        String token2 = "";
        String token3 = "";
        ArrayList<String> manTokList = new ArrayList<>();
        boolean[] array = new boolean[256];
        for(int i = 0;i<256;i++) {
            array[i] = true;
        }
        for(int i = 0;i<65;i++) {
            array[i] = false;
        }
        for(int i = 91;i<97;i++) {
            array[i] = false;
        }
        for(int i=123;i<array.length;i++) {
            array[i] = false;
        }

        for(int i = 0;i<byteList.length;i++) {
            if(array[byteList[i]] == true) {
                char ch = (char) byteList[i];
                token1 += ch;
            }

            else if(array[byteList[i]] == false) {
                if(token1 != "") {
                    manTokList.add(token1);
                }

                token1 = "";
                char ch = (char) byteList[i];
                token2 += ch;

                if(token1 == "") {
                    manTokList.add(token2);
                }
                token2 = "";
            }
            else if (byteList[i] == 1) {
                char ch = (char) 1;
                token3 += ch;
                manTokList.add(token3);
                token3 = "";
            }
        }
        for(String i: manTokList) {
            System.out.println(i);
        }
	}

	public ArrayList tuple(ArrayList<String> tokens) {
		ArrayList<ArrayList> tup = new ArrayList<ArrayList>();
		ArrayList<String> indTup = new ArrayList<String>();
		int index = 0;

		for(int i = 0;i < tokens.size();i++) {
			indTup.add(Integer.toString(index));
		}


		return tup;
	}

	public static void readTokens2(String fileName) throws IOException {
		ArrayList<String> tokens = new ArrayList<>();
		boolean[] admissibleChars = new boolean[256];

		//set the numbers to true
		for(int i = 0;i < 10; i++) {
			admissibleChars[47 + i] = true;
		}
		//set the alphabet to true
		for(int i = 0;i < 26; i++) {
			admissibleChars[64 + i] = true;
			admissibleChars[96 + i] = true;
		}

		//change this to an array and implement the stack yourself if this is too slow
		Stack<Integer> buffer = new Stack<Integer>();
		FileReader iS = null;

		int TOKENSIZE = 50;
		char[] token1 = new char[TOKENSIZE];
		char[] token2 = new char[TOKENSIZE];

		try {
			iS = new FileReader(fileName);
			int c;
			int i = 0;
			int j = 0;
			int k = 0;
			while((c=iS.read()) != -1) {
				if(c != 64) {
					token1[j] = (char) c;
					j++;
				}

				if(c == 64) {
					token2[i] = (char) c;
					push_back(buffer, c);
					i++;
				}

				String b = new String(token1);

				//System.out.println(" " + b + " ");

				String d = new String(token2);

				System.out.println("\"" + d + "\"");

				tokens.add(b);
				i = 0;
				j = 0;
				token1 = new char[TOKENSIZE];
				System.out.println(tokens.get(k));
				k++;
			}

		} catch(Exception e) {
			System.out.println(e);
		} finally {
			iS.close();
		}
		System.out.println(tokens.size());
	}

	public static void main(String[] args) throws IOException {
		if(args.length != 1) {
			System.out.println("Invalid number of arguments. Program now terminating.");
			System.exit(0);
		}
		readTokens2(args[0]);

	}

}
