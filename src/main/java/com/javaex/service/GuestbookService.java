package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;
 
@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	/* 방명록폼 (리스트도 보이기) */
	public List<GuestbookVo> exeGetGuestList() {
		System.out.println("GuestbookService.exeGetGuestList()");
		
		List<GuestbookVo> guestbookList = guestbookDao.getGuestbookList();
		
		//System.out.println(guestbookList);
		
		return guestbookList;
	}
	
	
	/* 방명록 등록 */
	public int exeWriteGuestbook(GuestbookVo guestbookVo) {
		System.out.println("GuestbokService.exeWriteGuestbook()");
		
		int count = guestbookDao.insertGuestbook(guestbookVo);
		
		return count;
	}
	
	
	/* 방명록 삭제 */
	public int exeDeleteGuestbook(GuestbookVo guestbookVo){
		System.out.println("GuestbokService.exeDeleteGuestbook()");
		
		int count = guestbookDao.deleteGuestbook(guestbookVo);
		
		return count;
	}
	
	
	/* ajax 등록 저장 */
	public GuestbookVo exeAddandGuest(GuestbookVo guestbookVo){
		System.out.println("GuestbokService.exeAddandGuest()");
		//////////비지니스 로직
		//System.out.println(guestbookVo);
		// 저장
		int count = guestbookDao.insertSelectKey(guestbookVo);
		//System.out.println(guestbookVo);
		
		// 1명 데이터 가져오기
		GuestbookVo gVo = guestbookDao.guestbookselectOne(guestbookVo.getNo());	// guestbookVo에서 no를 가져와야함
		//////////////////////////////////////////////
		
		return gVo;
		
	}
	

}
