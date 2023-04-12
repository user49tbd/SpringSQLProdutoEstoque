package ProdutoEstoque.ProdutoE.model;


public class Produto {
	private int codigo;
	private String nome;
	private double valUni;
	private int qtd;
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQtd() {
		return qtd;
	}
	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	public double getValUni() {
		return valUni;
	}
	public void setValUni(double valUni) {
		this.valUni = valUni;
	}
	
}
