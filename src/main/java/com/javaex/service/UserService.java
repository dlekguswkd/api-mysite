package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	/* 회원가입 */
	public int exeJoinUser(UserVo userVo) {	 
		System.out.println("UserService.exeJoinUser()");
		
		int count = userDao.insertUser(userVo);
		
		return count;
		
	}
	
	
	/* 아이디 중복체크  */
	public boolean exeIdCheck(String id) {
		System.out.println("UserService.exeIdCheck()");

		int count = userDao.selectUserById(id);
		
		if(count >= 1) {
			return false;		// 이미 가입된 아이디 가입불가능
		}else {
			return true;		// 없는 아이디 가입가능
		}
		
	}
	
	
	/* 로그인 */
	public UserVo exeLogin(UserVo userVo) {
		System.out.println("UserService.exeLogin()");
		
		UserVo authUser =userDao.selectUser(userVo);
		
		return authUser;
	}
	
	/* 수정폼 */
	// 회원정보수정폼(1명 데이터가져오기)
	public UserVo exeEditForm(int no) {
		System.out.println("UserService.exeEditForm()");

		UserVo userVo = userDao.userSelectOneByNo(no);
		return userVo;
	}

	/* 수정 */
	// 회원정보 수정
	public int exeModifyUser(UserVo userVo) {
		System.out.println("UserService.exeModifyUser()");

		int count = userDao.userUpdate(userVo);
		return count;
	}
	
	

}
