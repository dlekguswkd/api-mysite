package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;
import com.javaex.uti.JsonResult;
import com.javaex.vo.AttachVo2;

@RestController
public class AttachController {
	
	@Autowired
	private AttachService attachService;
	
	// http://localhost:3000/attach/form
	// 첨부파일보내기
	@PostMapping("/api/attachs")		// 들어간이름 		 자료형이 이거임..
	public JsonResult form(@RequestParam("profileImg") MultipartFile profileImg) {
		System.out.println("AttachController.form()");
		// System.out.println(profileImg.getOriginalFilename());	// 파일은 이름 이렇게 써줘야함
		
		// 유효이름붙은 긴이름  -> 화면에 보낼거임
		String saveName = attachService.exeUpload(profileImg);
		
		return JsonResult.success(saveName);
	}

	
	// http://localhost:3000/attach/form2
	// 첨부파일보내기 + 글씨같이 보내기
	@PostMapping("/api/attachs2")	// 묶어줬음 vo2만들어서 -> 한방에 해줌 여러개여도	
	public JsonResult form2(@ModelAttribute AttachVo2 attachVo2) {
						// @RequestParam("profileImg") MultipartFile profileImg,
						// @RequestParam("content") String content) { --> 묶어줘놓고 따로 또 해도됨
		System.out.println("AttachController.form2()");
//		System.out.println(profileImg.getOriginalFilename());
//		System.out.println(content);	// 글씨는 그냥 String임 
		System.out.println(attachVo2);
		
		String saveName = attachService.exeUpload2(attachVo2);
		
		return JsonResult.success(saveName);
	}
	
	
}
