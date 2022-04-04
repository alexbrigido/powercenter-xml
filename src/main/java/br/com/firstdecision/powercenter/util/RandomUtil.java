package br.com.firstdecision.powercenter.util;

import java.util.Random;

public class RandomUtil {

	
	public static int random(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
	}

}
