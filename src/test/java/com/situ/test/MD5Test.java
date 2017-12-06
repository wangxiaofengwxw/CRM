package com.situ.test;

import org.junit.Test;

import com.situ.crm.util.MD5Util;

public class MD5Test {

	//普通加密
	@Test
	public void test1() {
	String md5EncodeUtf8 = MD5Util.EncodeUtf8("123");
		System.out.println(md5EncodeUtf8);
		//202CB962AC59075B964B07152D234B70
	}
	//加盐加密
	@Test
	public void test2() {
		String md5EncodeUtf8 = MD5Util.EncodeUtf8AddSalt("123");
		System.out.println(md5EncodeUtf8);
		//DCDFE18FE9CB036E7D731D311B3EC188
	}
}
