package ksmart36.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ksmart36.mybatis.domain.Goods;




@Mapper
public interface GoodsMapper {
	
//상품삭제처리
	public int deleteGoods(Goods goods);
	
//상품수정
	public int updateGoods(Goods goods);
	
//상품수정 및 삭제 전 처리 - 상품코드로 상품검색
	public Goods getGoodsByCode(String goodsCode);
	
//상품리스트
	public List<Goods> getGoodsList();
//상품코드 조회
	public int goodsMaxCode();
//상품등록
	public int addGoods(Goods goods);
}
