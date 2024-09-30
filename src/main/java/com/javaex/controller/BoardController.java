package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.BoardService;
import com.javaex.uti.JsonResult;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;


@RestController
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	/* 리스트 게시판 첫화면 */
	// http://localhost:9000/api/boards
	@GetMapping("/api/boards")
	public JsonResult boardList() {
		System.out.println("BoardController.boardlist()");
		
		List<BoardVo> boardList = boardService.exeGetBoardList();
		 System.out.println(boardList);
		return JsonResult.success(boardList);
	}
	
	
//	/* 게시판 읽기 */
//	// http://localhost:9000/api/boards 
//	@RequestMapping("/board/boardread")
//	public String boardRead(@RequestParam(value="no") int no, Model model) {
//		System.out.println("BoardController.boardRead()");
//		
//		BoardVo boardVo = boardService.exeGetReadOne(no);
//		
//		model.addAttribute("boardVo", boardVo);	
//		
//		return "board/read";
//	}
//	
//	
//	/* 게시판 쓰기폼 */
//	// http://localhost:9000/api/boards
//	@RequestMapping("/board/boardwriteform")
//	public String boardWriteform() {
//		System.out.println("BoardController.boardWriteform()");
//	
//		return "board/writeForm";
//	}
//	
//	
//	/* 게시판 쓰기(등록) */
//	// http://localhost:9000/api/boards
//	@RequestMapping("/board/boardwrite", )
//	public String boardWrite(@ModelAttribute BoardVo boardVo) {
//		System.out.println("BoardController.boardWrite()");
//		
//		BoardVo insertVo = boardVo;
//		
//		BoardVo returnVo = boardService.exeboardWrite(boardVo);
//	
//		return "redirect:/board/boardlist";
//	}
//	
//	
//	/* 게시판 수정폼  */
//	// http://localhost:9000/api/boards
//	@RequestMapping("/board/boardmodifyform")
//	public String boardModifyform(@RequestParam(value="no") int no, Model model) {
//		System.out.println("boardController.boardModifyform()");
//
//		BoardVo boardVo = boardService.exeGetReadOne(no);
//		
//		model.addAttribute("boardVo", boardVo);	
//		
//		return "board/modifyForm";
//	}
//	
//	
//	/* 게시판 수정  */
//	// http://localhost:9000/api/boards
//	@RequestMapping("/board/boardmodify")
//	public String boardModify(@ModelAttribute BoardVo boardVo) {
//		System.out.println("boardController.boardModify()");
//		
//		BoardVo updateVo = boardService.exeboardModify(boardVo);
//
//		return "redirect:/board/boardlist";
//	}
//	
//	
}
