package com.itheima.bigevent.util;

public class ThreadLocalUtil {
	
//	創建一個threadlocal物件
	private static final ThreadLocal THREAD_LOCAL = new ThreadLocal();
	
//	根據key取的value
	public static <T> T get(){return (T) THREAD_LOCAL.get();}
	
//	儲存key, value
	public static void set(Object value) {THREAD_LOCAL.set(value);}
	
//	清除threadlocal 防止內存泄露
	public static void remove() {THREAD_LOCAL.remove();}

}
