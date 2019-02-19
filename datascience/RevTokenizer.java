package datascience;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RevTokenizer {

    public static boolean isPureAscii(String v) {
        byte byteArray[] = v.getBytes();
        CharsetDecoder d = Charset.forName("US-ASCII").newDecoder();
        try {
            CharBuffer r = d.decode(ByteBuffer.wrap(byteArray));
            r.toString();
        } catch(CharacterCodingException e) {
            return false;
        }
        return true;
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
        buffer.pop();
    }

    public static void readTokens2(String fileName) throws IOException {
        ArrayList<String> tokens = new ArrayList<String>();
        boolean[] admissibleChars = new boolean[256];

        //set the numbers to true
        for(int i = 0;i < 10; i++) {
            admissibleChars[47 + i] = true;
        }
        //set the alphabet to true
        for(int i = 0; i < 26; i++) {
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
            int e = iS.read();
            int i = 0, j = 0, k = 0;

            if(isPureAscii(Integer.toString(e)) == false) {
                System.out.println("Character is not ASCII");
                System.exit(0);
            }

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
        System.out.println("Hello");
    }
    public static void main(String[] args) throws IOException {
        if(args.length != 1) {
            System.out.println("Invalid number of arguments. Program now terminating.");
            System.exit(0);
        }
        readTokens2(args[0]);
    }
}
