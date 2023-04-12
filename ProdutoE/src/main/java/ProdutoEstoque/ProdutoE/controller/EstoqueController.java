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
public class EstoqueController {
	@Autowired
	ProdutoDao pd;
	
	@RequestMapping(name = "estoque", value = "/estoque", method = RequestMethod.GET)
	public ModelAndView init(ModelMap model) {
		return new ModelAndView("estoque");
	}
	
	
	@RequestMapping(name = "estoque", value="/estoque", method = RequestMethod.POST)
	public ModelAndView init(ModelMap model, @RequestParam Map<String,String>
	allParam) {
		String bt = allParam.get("bt");
		String minval = allParam.get("minval");
		
		//double val=0;
		//List<FuncionarioDependente> lfd = new ArrayList<>();
		
		//FunDep fun = new FunDep();
		//Produto p = new Produto();
		int val=0;
		List<Produto> lp = new ArrayList<>();
		try {
			
			if(bt.equals("Quantidade")) {
				val= pd.getQTDEstoque(Integer.parseInt(minval));
			}
			else {
				lp = pd.buscaPEstoque(Integer.parseInt(minval));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			model.addAttribute("val",val);
			model.addAttribute("list",lp);
		}
		
		return new ModelAndView("estoque");
		
	}
}
