package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	/* 리스트 게시판 첫화면 */
	public List<BoardVo> getBoardList() {
		System.out.println("BoardDao.getBoardList()");
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList");
		
		return boardList;
	}
	
	
	/* 게시판 읽기 */
	public BoardVo getRead(int no) {
		System.out.println("boardDao.getRead()");
		
		BoardVo boardVo = sqlSession.selectOne("board.selectRead", no);
		
		return boardVo;
	}

	
	/* 게시판 쓰기(등록) */
	public BoardVo getInsert(BoardVo boardVo) {
		System.out.println("boardDao.getInsert()");
		
		int count = sqlSession.insert("board.insert", boardVo);		

		if (count == 1) {
			return boardVo;
		} else {
			return null;
		}
	}
	
	
	/* 게시판 수정폼 */
	public int increaseHitCount(int no) {
		System.out.println("boardDao.increaseHitCount()");
		
		int result = sqlSession.update("board.increaseHitCount", no);	
		
		if (result == 1) {
			return result;
		} else {
			return 0;
		}
		
	}
	

	/* 게시판 수정 */
	public BoardVo getUpdate(BoardVo boardVo) {
		System.out.println("BoardDao.getUpdate()");
		
		int count = sqlSession.update("board.update", boardVo);
	
		if (count == 1) {
			return boardVo;
		} else {
			return null;
		}
	}

	
}
