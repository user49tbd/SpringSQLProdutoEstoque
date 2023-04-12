package ProdutoEstoque.ProdutoE.persistence;

import java.sql.SQLException;
import java.util.List;

import ProdutoEstoque.ProdutoE.model.Produto;

public interface IProduto {
	public void insereP(Produto p) throws SQLException,ClassNotFoundException;
	public void atualizaP(Produto p) throws SQLException,ClassNotFoundException;
	public void deleteP(int cod) throws SQLException,ClassNotFoundException;
	public Produto buscaP(int cod) throws SQLException,ClassNotFoundException;
	public List<Produto> ListeP() throws SQLException,ClassNotFoundException;
	public int getQTDEstoque(int qtd) throws SQLException,ClassNotFoundException;
	public List<Produto> buscaPEstoque(int qtd) throws SQLException,ClassNotFoundException;
}
