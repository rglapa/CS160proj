package datascience;

import java.util.*;
public class Token {

	private int stemid;
	private String stem;
	private String originalToken;
	private List<Integer> tokenposition;
	private List<Integer> sentenceLocation;
	private int tokenfreq;

	public void Token()
	{
		tokenposition= new ArrayList();
		sentenceLocation=new ArrayList();
	}


	public void setStemid(int stemid) {
		this.stemid=stemid;
	}


	public void setStem(String stem) {
		this.stem=stem;
	}


	public void setOriginalWord(String token) {
		this.originalToken=token;
	}

	public void setTokenFrequency(int frequency) {
		this.tokenfreq=frequency;
	}

	/*
	 * set the location of the token where it occurs in the file
	 * */
	public void setTokenPositon(int position) {
		this.tokenposition.add(position);
	}

	/*
	 * set the location of the sentence number where the token occurs
	 * */

	public void setSentenceLocation(int location) {
		this.sentenceLocation.add(location);
	}





	/***get token freq for each token and sort them in ascending order***/

	public int getFrequency()
	{
		return this.tokenfreq;
	}


	/*
	 * Select 1 concept using low frequency and high frequency ie alpha beta pairs in one document and find the same pair
	 *  in the next documents and store the document id and sentence number where these pair occurs in the documents.
	 *
	 *  Now take the 3rd postion to genrate a group of 3 keywords and find the group in the next documents  and
	 *  store the document id and sentence number where these pair occurs in the documents.
	 *
	 *  Select 2 concept to decide upper and lower bounds...and continue the same process till 4 concept
	 * */



	/*
	 * Programming for linear algebra
	 *
	 * given a set of x,y co ordinates on a x-y plane write a program to calculate the the new co ordinates when the
	 * plane is rotated 30 degrees clockwise
	 * */

	/***
	 * Given a set of x-y-z co ordinates, write a program to transform the coordinates to generate coordinates in a 2 dimension plane
	 *
	 *
	 *
	eg A(a,b,c) + B(c,b,d)= A(b,c) - A(a,c) + A(a,b) +B(b,d) -B(c,d) +B(c,b)
	 */






}
