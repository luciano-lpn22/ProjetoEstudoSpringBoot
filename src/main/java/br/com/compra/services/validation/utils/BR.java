package br.com.compra.services.validation.utils;

public class BR {

	private static final int[] cpf= {11,10,9,8,7,6,5,4,3,2};
	private static final int[] cnpj= {6,5,4,3,2,9,8,7,6,5,4,3,2};
	
	private static int calcule(final String codigo,final int[] weigth) {
		int sum =0;
		for (int i =codigo.length()-1,digit;i>=0;i--) {
			digit=Integer.parseInt(codigo.substring(i,i+1));
			sum+=digit* weigth[weigth.length -codigo.length()+i];
		}
		sum=11-sum%11;
		return sum >9 ?0 :sum;
	}
	
	public static boolean isValidCpf(final String cpf) {
		if ((cpf==null) || (cpf.length() !=11) || cpf.matches(cpf.charAt(0)+"{11}")) {return false;}
			final Integer digit1=calcule(cpf.substring(0,9), BR.cpf);
			final Integer digit2=calcule(cpf.substring(0,9)+digit1, BR.cpf);
			return cpf.equals(cpf.substring(0, 9)+digit1.toString()+digit2.toString()); 
	}
	public static boolean isValidCnpj(final String cnpj) {
		if ((cnpj==null) || (cnpj.length() !=14) || cnpj.matches(cnpj.charAt(0)+"{11}")) {return false;}
		final Integer digit1=calcule(cnpj.substring(0,9), BR.cnpj);
		final Integer digit2=calcule(cnpj.substring(0,9)+digit1, BR.cnpj);
		return cnpj.equals(cnpj.substring(0, 9)+digit1.toString()+digit2.toString());
	}
}
