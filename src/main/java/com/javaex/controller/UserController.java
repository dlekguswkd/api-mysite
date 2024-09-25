package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.uti.JsonResult;
import com.javaex.uti.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletResponse;

@RestController			// controller + responsebody
public class UserController {

	@Autowired		
	private UserService userService;
	
	// http://localhost:9000/api/users
	// http://localhost:3000/joinform
	/* 회원가입 */
	@PostMapping("/api/users")
	public JsonResult join(@RequestBody UserVo userVo) {	
		System.out.println("UserController.join()");
		
		int count = userService.exeJoinUser(userVo);
		
		if(count != 1) { 		//등록안됨
			return JsonResult.fail("등록에 실패했습니다.");
		}else { 				//등록됨
			return JsonResult.success(count);
		}
		
	}
	
	
	// http://localhost:9000/api/users/~
	// http://localhost:3000/joinform
	/* 아이디 중복체크 */
	@PostMapping("/api/users/{id}")
	public JsonResult idCheck(@PathVariable(value="id") String id) {
		System.out.println("UserController.idCheck()");
		//System.out.println(id);
		
		boolean can = userService.exeIdCheck(id);
		
		if(can == false) { 		//등록안됨
			return JsonResult.fail("등록에 실패했습니다.");
		}else { 				//등록됨
			return JsonResult.success(can);
		}
		
	}
	
	
	// http://localhost:9000/api/user
	// http://localhost:3000/loginform
	/* 로그인 */
	@PostMapping("/api/users/login")
	public JsonResult login(@RequestBody UserVo userVo, HttpServletResponse response) {
		System.out.println("UserController.login()");
		System.out.println(userVo);
		UserVo authUser = userService.exeLogin(userVo);	// id, password만 온다
		
		if(authUser != null ) { 	//로그인됨	
			// 토큰을 만들고 "응답문서의 헤더"에 토큰을 붙여서 보낸다
			JwtUtil.createTokenAndSetHeader(response, ""+authUser.getNo());
			return JsonResult.success(authUser);	// no, name만 온다
			
		}else { 				//로그인 안됨
			return JsonResult.fail("로그인 실패");	// no, name만 온다
		}
		
		
	}
	
	
	
	
//	// -----------------------------------------------------------------------------
//	
//	/* 수정폼 */
//	//http://localhost:8888/mysite/user/modifyform
//	@RequestMapping(value="/user/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
//	public String modifyform(HttpSession session, Model model) {
//		System.out.println("UserController.modifyform()");
//		
//	    //로그인된 사용자 정보 가져오기
//		//System.out.println("보내기용" + session.getAttribute("authUser"));
//		
//	    UserVo userVo = userService.exeGetUserOne((UserVo)session.getAttribute("authUser"));
//	    
//	    model.addAttribute(userVo);
//	    //int no = authUser.getNo();
//	    
////	    model.addAttribute("userVo", authUser);
//	    //System.out.println(authUser);
//		
//		return "user/modifyform";
//		
//	}
//	
//	/* 수정 */
//	//http://localhost:8888/mysite/user/modify?password=~&name=~&gender=~
//	@RequestMapping(value="/user/modify", method = {RequestMethod.GET, RequestMethod.POST})
//	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
//		System.out.println("UserController.modify()");
//		
//		UserVo authUser = userService.exeModify(userVo);
//		
//		session.setAttribute("authUser", authUser);
//		
//		//UserVo authUser = (UserVo)session.getAttribute("authUser") 
//		//authUser.setName(userVo.getName());
//		
//		return "redirect:/main";
//		
//	}
//	
	
	
}
