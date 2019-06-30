package br.com.compra.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	
	public static List<Integer> decodeToList(String decode){
		String[] v = decode.split(",");
		List<Integer> list= new ArrayList<Integer>();
		for(int i=0; i< v.length; i++) {
			list.add(Integer.parseInt(v[i]));
		}
		return list;
		//lambida
		//return Arrays.asList(decode.split(",")).stream().map( x->Integer.parseInt(x)).collect(Collectors.toList());
	}

	public static String decodeParam(String o) {
		
		try {
			return URLDecoder.decode(o,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
}
