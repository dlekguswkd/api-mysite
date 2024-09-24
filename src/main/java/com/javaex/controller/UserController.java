package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.uti.JsonResult;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;


@RestController
public class UserController {

	@Autowired		
	private UserService userService;
	
	// http://localhost:9000/api/users
	// http://localhost:3000/joinform
	/* 회원가입 */
	@PostMapping("/api/users")
	public JsonResult join(@RequestBody UserVo userVo) {		//묶이면 @ModelAttribute
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
	@PostMapping(value="/api/users/{id}")
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
	@PostMapping(value="/api/user")
	public JsonResult login(@RequestBody UserVo userVo) {
		System.out.println("UserController.login()");
		System.out.println(userVo);
		UserVo authUser = userService.exeLogin(userVo);
		
		if(authUser == null ) { 		//로그인 안됨
			return JsonResult.fail("로그인에 실패했습니다.");
			
		}else { 				//로그인됨
			return JsonResult.success(authUser);
		}
	}
	
	
	
	/* 로그아웃 */
	//http://localhost:8888/mysite/user/logout
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("UserController.logout()");
		
		//얘는 공간자체를 없애버림 () 로그아웃됨 session이 사라짐
		session.invalidate();
		/* authUser을 없애버리는 동작 (특정데이터만 없애버림)
		session.removeAttribute("authUser");
		*/
		
		return "redirect:/main";
	}
	
	
	// -----------------------------------------------------------------------------
	
	/* 수정폼 */
	//http://localhost:8888/mysite/user/modifyform
	@RequestMapping(value="/user/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyform(HttpSession session, Model model) {
		System.out.println("UserController.modifyform()");
		
	    //로그인된 사용자 정보 가져오기
		//System.out.println("보내기용" + session.getAttribute("authUser"));
		
	    UserVo userVo = userService.exeGetUserOne((UserVo)session.getAttribute("authUser"));
	    
	    model.addAttribute(userVo);
	    //int no = authUser.getNo();
	    
//	    model.addAttribute("userVo", authUser);
	    //System.out.println(authUser);
		
		return "user/modifyform";
		
	}
	
	/* 수정 */
	//http://localhost:8888/mysite/user/modify?password=~&name=~&gender=~
	@RequestMapping(value="/user/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("UserController.modify()");
		
		UserVo authUser = userService.exeModify(userVo);
		
		session.setAttribute("authUser", authUser);
		
		//UserVo authUser = (UserVo)session.getAttribute("authUser") 
		//authUser.setName(userVo.getName());
		
		return "redirect:/main";
		
	}
	
	
	
}
