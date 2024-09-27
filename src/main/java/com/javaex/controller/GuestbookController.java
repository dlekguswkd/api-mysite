package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	
//	/* 방명록 삭제폼 */
//	//http://localhost:8888/mysite/guestbook/deleteform
//	@RequestMapping(value="/guestbook/deleteform",method= {RequestMethod.GET, RequestMethod.POST}) 
//	public String deleteForm() {
//		System.out.println("GuestbookController.deleteForm()");
//		
//		//no값을 숨겨놔야한다  addList.jsp에서 줌
//		
//		return "guestbook/deleteForm";
//	}
//	
//	
//	/* 방명록 삭제 */
//	//http://localhost:8888/mysite/guestbook/guestbookdelete?no=~&password=~
//	@RequestMapping(value="/guestbook/guestbookdelete",method= {RequestMethod.GET, RequestMethod.POST}) 
//	public String guestbookDelete(@ModelAttribute GuestbookVo guestbookVo) {
//		
//		System.out.println("GuestbookController.guestbookDelete()");
//		
//		guestbookService.exeDeleteGuestbook(guestbookVo);
//		
//		return "redirect:/guestbook/guestbookform";
//	}
//
//	
//	/* ---------------------------------------------------------------------- */
//	///////////////////////////////////////////////////////////////////////////
//	
//	/* ajaxindex  화면 뿌리기 */
//	//http://localhost:8888/mysite/guestbook/ajaxindex
//	@RequestMapping(value="/guestbook/ajaxindex", method= {RequestMethod.GET, RequestMethod.POST})
//	public String ajaxindex() {
//		System.out.println("GuestbookController.ajaxindex");
//		
//		// 방명록 데이터 리스트 가져오지 않는다
//		
//		return "guestbook/ajaxindex";
//	}
//	
//	
	
}
