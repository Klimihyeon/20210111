package com.spring.module;

public class Summation {
	
	public int sum(int a, int b) {
		return a+b;
	}
	
	public int sum(int a, int b, int c) {
		return sum(a,b)+c;
	}
	
	public int sum(int a, int b, int c, int d) {
		return sum(a,b,c)+d;
	}
}
