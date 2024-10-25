package com.javaex.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.uti.JsonResult;

@RestController
public class AiController {

	@PostMapping("/api/ai/chats")
	public JsonResult chat(@RequestParam(value="question") String question) {
		System.out.println("AiController.chat()");
		System.out.println(question);
		
		String answer = "";
		
		// 메모리의 우리프로그램 밖의 파이썬을 실행해야한다
		try {
			// 있는이름 		내가정한이름			이미 있음			가상환경 실행위치, main.py실행(코드실행), question
			ProcessBuilder processBuilder = new ProcessBuilder(
					"C:\\javaStudy\\workspace-python\\Ex05\\ex05_venv\\Scripts\\python.exe", 
					"C:\\javaStudy\\workspace-python\\Ex05\\main.py", 
					question
			);
			
			// 파이썬에서 전달하는 메시지, 파이썬에서 발생하는 에러 메세지 따로 관리된다.
			// 이것을 아래코드로 1개로 관리할수 있다
			processBuilder.redirectErrorStream(true);
			
			// 파이썬 스트립트 실행
			Process process = processBuilder.start();
			
			///////////////////////////////////////////
			// 대답 받기
			InputStream is = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");		// 한글로 나오게하기 UTF-8
			BufferedReader br = new BufferedReader(isr);
			
			while(true) {
				String line = br.readLine();	
				if(line == null) {
					break;
					
				}else {
					answer += line+"<br/>";		
				}				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(answer);
		return JsonResult.success(answer);
	}
	
}
