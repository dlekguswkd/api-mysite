package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.UserService;
import com.javaex.uti.JsonResult;
import com.javaex.uti.JwtUtil;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpServletRequest;
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
	
	
	// http://localhost:9000/api/users/login
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
	
	
	// -----------------------------------------------------------------------------

	/* 수정폼 */
	/* 회원정보수정폼 (~번 회원정보 가져오기) */
	// http://localhost:9000/api/users/me
	@GetMapping("/api/users/me")
	public JsonResult modifyform(HttpServletRequest request) {
		System.out.println("UserController.modifyform()");
		
		// 요청헤더에서 토큰을 꺼내서 유효성을 체크한후 정상이면 no값을 꺼내준다 -> -1이면 가짜
		int no = JwtUtil.getNoFromHeader(request);
		// System.out.println(no);
		
		if (no != -1) {		// 토큰정상
			UserVo userVo = userService.exeEditForm(no);	// no는 유저번호
			return JsonResult.success(userVo);
			
		} else {		// 토큰이 없거나(로그인상태아님), 변조된 경우
			return JsonResult.fail("토큰X, 비로그인, 변조");
			
		}

	}
	
	/* 회원정보수정 */
	@PutMapping("/api/users/me")
	public JsonResult modifyUser(HttpServletRequest request, @RequestBody UserVo userVo) {
		System.out.println("UserController.modifyUser()");
		
		// 화면에 있는 userVo 출력
		//System.out.println(userVo);	// password, name, gender
		
		// 토큰사용해서 no값 출력
		int no = JwtUtil.getNoFromHeader(request);
		
		if(no != -1) {		// 토큰이 정상일때
			// userVo에 no값도 넣어주기
			userVo.setNo(no);
			// System.out.println(userVo);
			int count = userService.exeModifyUser(userVo);	// no, password, name, gender
			// System.out.println(count);
			
			if(count == 1) {	// 정상적으로 수정되었을때
				userVo.setPassword(null);	// 로그인할때 토큰에 이름이랑 no만 저장하기때매
				userVo.setGender(null);		// 불필요한걸 지워서 저장시키기위해 두개만 보냄
				return JsonResult.success(userVo);
				
			}else {			// 비정상적으로 수정되었을때
				return JsonResult.fail("수정오류, 수정실패");
				
			}
			
		}else {		// 토큰이 비정상일때
			return JsonResult.fail("토큰X, 비로그인, 변조");
			
		}
	}
	
	
	
}
