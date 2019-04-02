package com.heima.study;

import java.util.List;
import java.util.ListIterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListIterator_demo {

	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	public static void main(String[] args) throws ParseException {
		List al = new ArrayList();
		al.add("a");
		al.add("b");
		al.add("c");
		al.add("d");

		ListIterator it = al.listIterator();
		while (it.hasNext()) {
			String s = (String) it.next();
			if ("a".equals(s)) {
				it.add("hehe");
			}
		}
		System.out.println(al);

		List a2 = new ArrayList();
		while (it.hasPrevious()) {
			String ss = (String) it.previous();
			a2.add(ss);
		}
		System.out.println(a2);
	}

}
