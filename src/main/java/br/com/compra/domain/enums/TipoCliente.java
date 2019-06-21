package br.com.compra.domain.enums;

public enum TipoCliente {

	PESSOAFISICA(1,"Pessoa Física"),
	PESSOAJURIDICA(2,"Pessoa Juridica");
	private int cod;
	private String descricao;
	private TipoCliente(int id,String descricao) {
		this.cod=id;
		this.descricao=descricao;
		
	}
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		
		if (cod==null) {
			return null;
		}
		
		for(TipoCliente x :TipoCliente.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id Inválido:"+ cod);
	}
	
}
