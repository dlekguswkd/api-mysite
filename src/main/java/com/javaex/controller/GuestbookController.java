package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.GuestbookService;
import com.javaex.uti.JsonResult;
import com.javaex.vo.GuestbookVo;


@RestController
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	

	/* 방명록폼 (리스트도 보이기) */
	// http://localhost:9000/api/guestbooks
	@GetMapping("/api/guestbooks")
	public JsonResult guestbookForm() {
		System.out.println("GuestbookController.guestbookForm()");
		
		List<GuestbookVo> guestbookList = guestbookService.exeGetGuestList();
		
		return JsonResult.success(guestbookList);
		
	}
	
	
	/* 방명록 등록 */
	// http://localhost:9000/api/guestbooks
	@PostMapping("/api/guestbooks")
	public JsonResult guestbookWrite(@RequestBody GuestbookVo guestbookVo) {
		System.out.println("GuestbookController.guestbookWrite()");
		
		int count = guestbookService.exeWriteGuestbook(guestbookVo);
		
		if(count != 1) { //등록안됨
			return JsonResult.fail("등록에 실패했습니다.");
		}else { //등록됨
			return JsonResult.success(count);
		}
		
	}
	
	
	/* 방명록 삭제 */
	// http://localhost:9000/api/guestbooks/~
	@DeleteMapping("/api/guestbooks/{no}") 
	public JsonResult guestbookDelete(@RequestBody GuestbookVo guestbookVo,
								  @PathVariable(value="no") int no) {
		System.out.println("GuestbookController.guestbookDelete()");
		System.out.println(no);
		
		int count = guestbookService.exeDeleteGuestbook(no, guestbookVo.getPassword());
		
		if(count != 1) {	// 실패 (삭제안됨)
			return JsonResult.fail("해당번호가 없습니다.");
			
		}else {				// 성공 (삭제됨) 
			return JsonResult.success(count);
		}
		
	}


}
