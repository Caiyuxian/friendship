package com.friendship.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatTest {

	public static void main(String[] args) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sf.format(new Date()));
	}

}
