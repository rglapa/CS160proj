package datascience;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class RevTokenizer {

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

    public static void main(String[] args) {

    }
}
