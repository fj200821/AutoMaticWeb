package cn.edu.hpu.autoweb.util;

import java.util.Random;

/**
 * 生成10位大写之母的字符串
 * @author Bertram
 *
 */
public class CharToString {
	
	public static final StringBuilder randomString(){
		Random random=new Random();
		final int startFlag='A';
		final int endFlag='Z';
		StringBuilder builder=new StringBuilder();
		while (builder.length()<10) {
			int number =random.nextInt(endFlag+1);
			if (number >= startFlag && number != 'I' && number != 'O') {
				builder.append((char)number);
			}
			
		}
		
		return builder;
	}
}
