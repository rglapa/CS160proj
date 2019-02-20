import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
 
public class Buh{
   
    private boolean[] ASCII;
    private ArrayList<String> tokens = new ArrayList<String>();
   
    /**
     * ctor for Buh
     * no param
     * Creates ASCII array
     */
    public Buh() {
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
       
        for(int i = 0; i < line.length()-1; i++) {
            int asciiBit = (int) line.charAt(i); // turn current letter into ascii value
           
            // if a-z or A-Z
            if(ASCII[asciiBit] == true)
                temp = temp + line.substring(i, i + 1);
           
            else if(asciiBit == 64 || asciiBit == 46) {
                if(!temp.isEmpty()) {
                    buffer.push(temp); // push in letters before @
                    temp = ""; // wipe
                }
                buffer.push(line.substring(i, i + 1));
            }
           
            // not a-z or A-Z
            else {
                // store string as ready to be tokenized & clean up temp
                if(temp.length() > 0) {
                    buffer.push(temp);
                    temp = "";
                }
                if(buffer.search(".") < buffer.search("@") && buffer.search(".") != -1)
                {
                    ArrayList<String> emailAr = new ArrayList<String>();
                    String email = "";
                    while(!buffer.isEmpty())
                        emailAr.add(buffer.pop());
                    for(int j = emailAr.size()-1; j >= 0 ; j--)
                    {
                        email = email + emailAr.get(j);
                    }
                    tokens.add(email);
                }
                else
                {
                    while(!buffer.isEmpty())
                        tokens.add(buffer.pop());
                    tokens.add(line.substring(i, i + 1));
                }
            }
       
        }
    }
   
    public ArrayList<String> getTokens() {
        return tokens;
    }
   
    public static void main(String[] args) throws IOException {
        Buh test = new Buh();
       
        // file reading stuff
        //FileReader fr = new FileReader("Data1_07449817ascii.txt");
        //BufferedReader br = new BufferedReader(fr);
        //String line;
        //while((line = br.readLine()) != null)
            //test.tokenizeLine(line);
        //br.close();
        //fr.close();
       
        test.tokenizeLine("h.t@t.c !.!4");
       
        ArrayList<String> tokens = test.getTokens();
        for(int i = 0; i < tokens.size(); i++){
            int count = i + 1;
            System.out.println("Token" + count + " " + tokens.get(i));
        }
    }
}
   
    /*Using virtual input technique*/
    /*Read one file at a time*/
    /*Everything inside the file including words, blank spaces, special characters should be treated as a token
     *Multiple blanks continuously should be treated as a single blank .
     * eg: "Discovery       of an object"=> this sentence should be read as "Discovery of an object"   */