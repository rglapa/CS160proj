package datascience;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Hello this is main");

		Tokenizer token =new Tokenizer();
		token.readTokens2(args[0]);

		
	}
}
