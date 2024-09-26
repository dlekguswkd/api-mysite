package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository 
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;	//pom.xml에 mysql, mybatis 추가
									//application.properties에 datasource, mybatis 추가
									//resources에 mybatis, configuration, mappers, user.xml 추가

	//유저 회원가입
	public int insertUser(UserVo userVo) {
		System.out.println("UserDao.insertUser()");
		
						//     정해진이름insert (총 5가지있음)
						// user.xml에 user에  id이름
		int count = sqlSession.insert("user.insert", userVo);
		
		return count;
		
	}
	
	/* (아이디 중복체크 api) id로 데이터 가져오기-id 사용여부 체크할때 사용 */
	public int selectUserById(String id) {
		System.out.println("UserDao.selectUserById()");
		System.out.println(id);
		
		// 내가 요구해서 받는 count임 
		int count = sqlSession.selectOne("user.selectById", id);
		System.out.println(count);
		
		return count;
		
	}
	
	
	//로그인 
	public UserVo selectUser(UserVo userVo) {
		System.out.println("UserDao.selectUser()");
		
		//     인증된유저 (로그인에 성공한 사람들) 담아둔것
		UserVo authUser = sqlSession.selectOne("user.selectByIdPw", userVo);
		return authUser;
	}
	
	
	/* 수정폼 */
	// no로 한명데이터 가져오기(회원정보수정 폼)
	public UserVo userSelectOneByNo(int no) {
		System.out.println("UserDao.userSelectOneByNo()");

		UserVo userVo = sqlSession.selectOne("user.selectOneByNo", no);
		return userVo;
	}
	
	/* 수정 */
	// 수정(회원정보수정)
	public int userUpdate(UserVo userVo) {
		System.out.println("UserDao.userUpdate()");

		int count = sqlSession.update("user.update", userVo);
		return count;
	}
	
	
}
