package ksmart36.mybatis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ksmart36.mybatis.domain.Goods;

import ksmart36.mybatis.service.GoodsService;

@Controller
public class GoodsController {

// @Autowired를 통해 MemberService memberService = new MemberService(); 를 생략할 수 있다.
	@Autowired
	private GoodsService goodsService;
	//상품삭제처리 후 상품리스트로 이동
	@PostMapping
	public String deleteGoods() {
		return "redirect:/getGoodsList";
	}
	//상품삭제화면으로 이동
	@GetMapping("/deleteGoods")
	public String deleteGoods(Goods goods, Model model) {
		model.addAttribute("title", "상품삭제화면");

		model.addAttribute("goods", goods);

		return "goods/deleteGoods";
		
	}
	
	//상품수정처리 후 상품리스트로 이동
	@PostMapping("/updateGoods")
	public String updateGoods(Goods goods) {
		goodsService.updateGoods(goods);
		return "redirect:/getGoodsList";
	}
	
	//상품수정화면으로 이동
	@GetMapping("/updateGoods")
	public String updateGoods(Goods goods,
							Model model,
							@RequestParam(value="goodsCode",required = false)String goodsCode) {
		System.out.println("수정할상품코드:"+goodsCode);
		model.addAttribute("title", "상품수정화면");
		
		 goods = goodsService.getGoodsByCode(goodsCode);
		 model.addAttribute("goods", goods);
		return "goods/updateGoods";
	}
	
	//상품등록처리 후 상품리스트로 이동
	@PostMapping("/addGoods")
	public String addGoods(Goods goods, @RequestParam(value = "goodsName", required = false) String goodsName,
			@RequestParam(value = "goodsPrice", required = false) String goodsPrice,
			@RequestParam(value = "goodsSellerId", required = false) String goodsSellerId) {
		System.out.println(goodsName + "<--goodsName");
		System.out.println(goodsPrice + "<--goodsPrice");
		System.out.println(goodsSellerId + "<--goodsSellerId");
		goodsService.addGoods(goods);
		return "redirect:/getGoodsList";
	}
	
	//상품등록화면이동
	@GetMapping("/addGoods")
	public String addGoods(Model model) {
		model.addAttribute("title", "상품등록");
		return "goods/addGoods";

	}
	
	// 상품리스트 조회
	
	  @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET) 
	  public String getGoodsList(Model model) { 
		  List<Goods> goodsList = goodsService.getGoodsList();
		  System.out.println("goodsList ->" + goodsList);
	  
		  model.addAttribute("goodsList", goodsList);
		  model.addAttribute("title","전체상품목록" );
		  
		  return "goods/goodsList";
	  
	  }
	
}
