package com.friendship.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.friendship.util.MD5;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		
		System.out.println(MD5.toMD5("admin"));
	}

}
