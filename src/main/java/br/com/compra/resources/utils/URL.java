package br.com.compra.resources.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
