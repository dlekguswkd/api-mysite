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
	
	
	/* 로그인 */
	public UserVo exeLogin(UserVo userVo) {
		System.out.println("UserService.exeLogin()");
		
		UserVo authUser =userDao.selectUser(userVo);
		
		return authUser;
	}
	
	/* 수정폼 */
	public UserVo exeGetUserOne(UserVo userVo) {
		System.out.println("UserService.exeGetUserOne()");
		
		UserVo authUser = userDao.getUserOne(userVo);
		
		return authUser;
		
	}
	
	/* 수정 */
	public UserVo exeModify(UserVo userVo) {
		System.out.println("UserService.exeModify()");
		
		UserVo authUser = userDao.updateUser(userVo);
		
		return authUser;
		
	}
	

	/* 아이디 중복체크 api */
	public boolean exeIdCheck(String id) {
		System.out.println("UserService.exeIdCheck()");
		//System.out.println(id);

		int count = userDao.selectUserById(id);
		
		if(count >= 1) {
			return false;		// 이미 가입된 아이디 가입불가능
		}else {
			return true;		// 없는 아이디 가입가능
		}
		
	}
	
	
	

}
