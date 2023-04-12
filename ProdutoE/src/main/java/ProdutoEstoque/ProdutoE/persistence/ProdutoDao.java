package ProdutoEstoque.ProdutoE.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ProdutoEstoque.ProdutoE.model.Produto;

@Repository
public class ProdutoDao implements IProduto{

	@Autowired
	GenericDao gd;
	
	@Override
	public void insereP(Produto p) throws SQLException, ClassNotFoundException {
		Connection c;
		c = gd.getC();
		String sql = "INSERT INTO PRODUTO VALUES (?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, p.getCodigo());
		ps.setString(2, p.getNome());
		ps.setDouble(3, p.getValUni());
		ps.setInt(4, p.getQtd());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizaP(Produto p) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection c;
		c = gd.getC();
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE PRODUTO ");
		sb.append("SET NOME = ?, VALOR_UNITARIO = ?, QTD_ESTOQUE = ? ");
		sb.append("WHERE PRODUTO.CODIGO = ? ");
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setString(1,p.getNome());
		ps.setDouble(2, p.getValUni());
		ps.setInt(3, p.getQtd());
		ps.setInt(4, p.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void deleteP(int cod) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection c;
		c = gd.getC();
		String sql = "DELETE PRODUTO WHERE PRODUTO.CODIGO = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, cod);
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Produto buscaP(int cod) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection c;
		c = gd.getC();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT P.CODIGO,P.NOME,P.VALOR_UNITARIO,P.QTD_ESTOQUE ");
		sb.append("FROM PRODUTO P WHERE P.CODIGO = ? ");
		PreparedStatement ps = c.prepareStatement(sb.toString());
		ps.setInt(1, cod);
		ResultSet rs = ps.executeQuery();
		Produto p = new Produto();
		if(rs.next()) {
			p.setCodigo(rs.getInt("CODIGO"));
			p.setNome(rs.getString("NOME"));
			p.setQtd(rs.getInt("QTD_ESTOQUE"));
			p.setValUni(rs.getDouble("VALOR_UNITARIO"));
		}
		return p;
	}

	@Override
	public List<Produto> ListeP() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection c;
		c = gd.getC();
		String sql=("SELECT P.CODIGO,P.NOME,P.VALOR_UNITARIO,P.QTD_ESTOQUE FROM PRODUTO P");
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Produto> lp = new ArrayList<>();
		while(rs.next()) {
			Produto p = new Produto();
			p.setCodigo(rs.getInt("CODIGO"));
			p.setNome(rs.getString("NOME"));
			p.setQtd(rs.getInt("QTD_ESTOQUE"));
			p.setValUni(rs.getDouble("VALOR_UNITARIO"));
			lp.add(p);
		}
		rs.close();
		c.close();
		return lp;
	}

	@Override
	public int getQTDEstoque(int qtd) throws SQLException, ClassNotFoundException {
		Connection c;
		int val=0;
		c = gd.getC();
		String sql = "SELECT dbo.FN_BUSCP(?) AS FUNC";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, qtd);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			val = rs.getInt("FUNC");
		}
		return val;
	}

	@Override
	public List<Produto> buscaPEstoque(int qtd) throws SQLException, ClassNotFoundException {
		Connection c;
		c = gd.getC();
		String sql = "SELECT CODIGO, NOME, PQTD FROM FN_LISTP(?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, qtd);
		ResultSet rs = ps.executeQuery();
		List<Produto> lp = new ArrayList<>();
		while(rs.next()) {
			Produto p = new Produto();
			p.setCodigo(rs.getInt("CODIGO"));
			p.setNome(rs.getString("NOME"));
			p.setQtd(rs.getInt("PQTD"));
			lp.add(p);
		}
		return lp;
	}
	
}
