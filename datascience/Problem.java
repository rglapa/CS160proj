package datascience;

import java.util.ArrayList;

public class Problem 
{
	public static void main(String[] args) 
	{
		Boolean[] ascii = new Boolean[256];
		for(int i = 0; i < 256; i++)
		{
			if(i >= 64 && i <=122)
				ascii[i] = true;
			else
				ascii[i] = false;
		}
		String input = "test1. I like cake@gmail.com234!@:hi!";
		String token = "";
		ArrayList<String> tokens = new ArrayList<String>();
		for(int i = 0; i < input.length(); i++)
		{
			if(ascii[(int) input.charAt(i)])
				token += input.substring(i, i+1);
			else if(input.charAt(i) == 46)
			{
				token += input.substring(i, i+1);
				i++;
			}
			else
			{
				if(!token.equals(""))
					tokens.add(token);
				if(input.charAt(i) == 32)
				{
					token = "";
					continue;
				}
				token = input.substring(i, i+1);
				tokens.add(token);
				token = "";
			}
		}
		for(int i = 0; i < tokens.size(); i++)
		{
			System.out.println("Token " + (i+1) + ": " + tokens.get(i));
		}
	}
}
