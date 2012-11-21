package test;

/**
 * GreatestCommonDivisor.java
 * Created by Stijn Strickx on April 17, 2006
 * Copyright 2006 Stijn Strickx, All rights reserved
 */

import java.io.*;
public class GreatestCommonDivisor {
public static void main(String[] arguments) throws java.io.IOException {
	
	long gcd;
	int numbersAmount = getNumber("From how many numbers would you like to know the greatest common divisor? ");
	long numbersArray[] = new long[numbersAmount];
	
	for(int x=0; x < numbersAmount; x++){
		numbersArray[x] = getNumber("Give in number " + (x+1) + ": ");
	}	
	
	gcd = greatestCommonDivisor(numbersArray[0], numbersArray[1]);
	
	if(numbersAmount > 2){
		for(int y=2; y < numbersAmount; y++){
			gcd = greatestCommonDivisor(gcd, numbersArray[y]);
		}
	}
	
	System.out.println("The greatest common divisor is " + gcd);
				
}
	
static long greatestCommonDivisor (long m, long n){
	long x;
	long y;
	while(m%n != 0){
		x = n;
		y = m%n;
		m = x;
		n = y;
	}
	return n;
}			
	
static int getNumber(String question) throws java.io.IOException {
	String theNumber;
	int number = 0;
	BufferedReader in = new BufferedReader (new InputStreamReader(System.in));
	System.out.print(question);
	theNumber = in.readLine();
	System.out.println();
	number = Integer.parseInt(theNumber);
	return number;
}
}