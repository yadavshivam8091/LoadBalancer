package io.ys.test;

import java.util.Random;

public class Test {
	public static void main(String[] args) {
		Random random = new Random();
		int position = random.nextInt(2-1)+1;
		System.out.println(position);
	}

}
