package ksmart36.mybatis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ksmart36.mybatis.domain.Member;
import ksmart36.mybatis.mapper.MemberMapper;

@Service
@Transactional
public class MemberService {
	
	@Autowired 
	private MemberMapper memberMapper;
	
	//회원목록조건검
	public List<Member> MemberSearchList(String sk, String sv){
		//회원들의 정보가 담긴 List객체
		List<Member> memberList = memberMapper.MemberSearchList(sk,sv);
		
		//List객체가 null이 아닌 경우 = 조회 성공
		Member m = null;
		if(memberList != null) {
			for(int i=0; i<memberList.size();i++) {
				
				m = memberList.get(i);
				if(m.getMemberLevel()==1) {
					m.setMemberLevelName("관리자");
				}else if(m.getMemberLevel()==2) {
					m.setMemberLevelName("판매자");
				}else if(m.getMemberLevel()==3){
					m.setMemberLevelName("구매자");
				}else {
					m.setMemberLevelName("일반회원");
				}
			}
		}
		return memberList;
	}

	//회원정보삭제
	public int deleteMember(String memberId, String memberPw) {
		//delete성공결과 변수 초기화
		int result = 0;
		
		Member member = memberMapper.getMemberById(memberId);
		if(member != null && memberPw != null && !"".equals(memberPw)) {
			if(memberPw.equals(member.getMemberPw())) {
				//1. 로그인 테이블(login_id) 삭제
				result += memberMapper.deleteLogin(memberId);
				//2-1. select상품테이블(g_seller_id)통해 g_code조회 후
				List<Map<String,Object>> goodsList = memberMapper.getGoodsCodeById(memberId);
				//2-2. 주문 테이블 삭제 ()
				result += memberMapper.deleteOrder(goodsList);
				//3. 상품테이블 (g_seller_id)삭제
				result += memberMapper.deleteGoods(memberId);
				//4. 멤버테이블 삭제
				result += memberMapper.deleteMember(memberId);
			}
		}
		
		return result;
	}
	
	//회원정보 수정
	public int modifyMember(Member member) {
		int result = memberMapper.modifyMember(member);
		return result;
	}
	
	
	//id로 회원정보 조회
	public Member getMemberById(String memberId) {
		Member member = memberMapper.getMemberById(memberId);
		
		//레벨에 따라 memberlevelName의 값을 setter
		if(member != null) {
			int memberLevel = 0;
			memberLevel = member.getMemberLevel();
			if(memberLevel > 0) {
				if(memberLevel == 1) {
					member.setMemberLevelName("관리자");
				}else if(memberLevel == 2) {
					member.setMemberLevelName("판매자");
				}else if(memberLevel == 3) {
					member.setMemberLevelName("구매자");
				}else {
					member.setMemberLevelName("일반회원");
				}
			}
		}
		return member;
	}

	
	//회원정보등록
	public int addMember(Member member) {
		int result = memberMapper.addMember(member);
		return result;
	}
	
	
	
	public List<Member> getMemberList(){
		List<Member> memberList = memberMapper.getMemberList();
		
		//List객체가 null이 아닌 경우 = 조회 성공
		Member m = null;
		if(memberList != null) {
			for(int i=0; i<memberList.size();i++) {
				m = memberList.get(i);
				if(m.getMemberLevel()==1) {
					m.setMemberLevelName("관리자");
				}else if(m.getMemberLevel()==2) {
					m.setMemberLevelName("판매자");
				}else if(m.getMemberLevel()==3){
					m.setMemberLevelName("구매자");
				}else {
					m.setMemberLevelName("일반회원");
				}
			}
		}
		return memberList;
	}
	//회원정보리스트
	public List<Member> getAddMember(){
		List<Member> addMember = memberMapper.getMemberList();
		return addMember;
	}
}
