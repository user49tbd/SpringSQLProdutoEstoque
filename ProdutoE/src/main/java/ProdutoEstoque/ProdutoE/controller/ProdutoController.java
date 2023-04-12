package ProdutoEstoque.ProdutoE.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ProdutoEstoque.ProdutoE.model.Produto;
import ProdutoEstoque.ProdutoE.persistence.ProdutoDao;

@Controller
public class ProdutoController {
	
	@Autowired
	ProdutoDao pd;
	
	@RequestMapping(name = "produto", value = "/produto", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("produto");
	}
	
	
	@RequestMapping(name = "produto", value="/produto", method = RequestMethod.POST)
	public ModelAndView init(ModelMap model, @RequestParam Map<String,String>
	allParam) {
		String bt = allParam.get("bt");
		String btcodigo = allParam.get("btcodigo");
		String btnome = allParam.get("btnome");
		String btvu = allParam.get("btvu");
		String btqtd = allParam.get("btqtd");
		String err ="";
		
		//double val=0;
		//List<FuncionarioDependente> lfd = new ArrayList<>();
		
		//FunDep fun = new FunDep();
		Produto p = new Produto();
		List<Produto> lp = new ArrayList<>();
		try {
			if((bt.equals("Inserir") || bt.equals("Atualizar"))&&
					(!btcodigo.equals("")&&!btnome.equals("")&&
					 !btvu.equals("")&&!btqtd.equals("")
							)) {
				//Produto p = new Produto();
				p.setCodigo(Integer.parseInt(btcodigo));
				p.setNome(btnome);
				p.setQtd(Integer.parseInt(btqtd));
				p.setValUni(Double.parseDouble(btvu));
				if(bt.equals("Inserir")) {
					pd.insereP(p);
				}else {
					pd.atualizaP(p);
				}
			}
			if((bt.equals("Deletar")||bt.equals("Buscar"))&&
					!btcodigo.equals("")) {
				if(bt.equals("Deletar")) {
					pd.deleteP(Integer.parseInt(btcodigo));
				}else {
					p = pd.buscaP(Integer.parseInt(btcodigo));
				}
			}
			if(bt.equals("Listar")) {
				lp = pd.ListeP();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			err= e.getMessage();
		}finally {
			model.addAttribute("prod",p);
			model.addAttribute("lisprod",lp);
			model.addAttribute("err",err);
		}
		
		return new ModelAndView("produto");
		
	}
}
