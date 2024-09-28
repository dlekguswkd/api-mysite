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
	public int exeDeleteGuestbook(int no, String password){
		System.out.println("GuestbokService.exeDeleteGuestbook()");
		
	    // GuestbookVo 객체 생성
	    GuestbookVo guestbookVo = new GuestbookVo();
	    guestbookVo.setNo(no); // no 설정
	    guestbookVo.setPassword(password); // password 설정
		
		int count = guestbookDao.deleteGuestbook(guestbookVo);
		
		return count;
	}
	
}
