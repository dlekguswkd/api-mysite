package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 방명록폼 (리스트도 보이기) */
	public List<GuestbookVo> getGuestbookList() {
		System.out.println("GuestbookDao.getGuestbookList()");
		
		List<GuestbookVo> guestbookList =sqlSession.selectList("guestbook.selectList");
		
		//System.out.println(guestbookList);
		return guestbookList;	
	}
	
	
	/* 방명록 등록 */
	public int insertGuestbook(GuestbookVo guestbookVo){
		System.out.println("GuestbookDao.insertGuestbook()");
		
		int count = sqlSession.insert("guestbook.insert", guestbookVo);
		
		return count;		
	}
	
	
	/* 방명록 삭제 */
	public int deleteGuestbook(GuestbookVo guestbookVo){
		System.out.println("guestbookDao.deleteGuestbook()");
		
		int count = sqlSession.delete("guestbook.delete", guestbookVo);
		
		return count;
	}

	
	/* ajax 등록 저장 */
	// dao 에는 sqlSession은 한번만 다른다오로 또 해야함
	public int insertSelectKey(GuestbookVo guestbookVo){
		System.out.println("GuestbookDao.insertSelectKey()");
		
		//System.out.println(guestbookVo);		// 여기에선 no가 안찍히고
		int count = sqlSession.insert("guestbook.insertSelectKey", guestbookVo);		// 이걸로 no를 담아준다 vo에 넣어줌
		//System.out.println(guestbookVo);		// 여기에선 no가 생김
		//System.out.println(guestbookVo.getNo());
		
		//GuestbookVo gVo = sqlSession.selectOne("guestbook.selectOne", guestbookVo.getNo());
		
		return count;
		
	}
	
	
	/* ajax 데이터 1개 가져오기 no 1명 데이터 가져오기 */
	public GuestbookVo guestbookselectOne(int no){
		System.out.println("GuestbookDao.guestbookselectOne()");
		
		GuestbookVo guestbookVo = sqlSession.selectOne("guestbook.selectOne", no);
		
		return guestbookVo;
	}
	
	
	
}
