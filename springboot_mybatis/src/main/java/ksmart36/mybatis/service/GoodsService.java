package ksmart36.mybatis.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart36.mybatis.domain.Goods;

import ksmart36.mybatis.mapper.GoodsMapper;



@Service
@Transactional
public class GoodsService {	
	@Autowired 
	private GoodsMapper goodsMapper;
	
//상품삭제전 처리 - 상품코드로 상품조회

	
//상품수정
	public int updateGoods(Goods goods) {
		int result = goodsMapper.updateGoods(goods);
		return result;
	}
//상품수정전처리- 상품코드로 상품조회
	public Goods getGoodsByCode(String goodsCode) {
		Goods goods = goodsMapper.getGoodsByCode(goodsCode);
	
		return goods;
	}

//상품등록	
	public int addGoods(Goods goods){	
		String gCode = "g";
		int goodsCode = goodsMapper.goodsMaxCode();
		String newGoodsCode = gCode + goodsCode;
		System.out.println(newGoodsCode + "<-- newGoodsCode");
		goods.setGoodsCode(newGoodsCode);
		int result = goodsMapper.addGoods(goods);
		System.out.println(result + "등록여부");
		return result;
	}

//상품리스트	
	 public List<Goods> getGoodsList(){
		 List<Goods> addGoods = goodsMapper.getGoodsList(); 
		 
		 return addGoods; 
	}
	
}