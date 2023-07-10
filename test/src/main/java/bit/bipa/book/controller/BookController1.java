package bit.bipa.book.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bit.bipa.book.dao.bookDao;
import bit.bipa.book.service.QuizService;
import bit.bipa.book.vo.BookCopy;

@Controller("BookController1")
@RequestMapping("/basic")
public class BookController1 {
	
	//DI에 해당되는 것 = Autowired
	@Autowired
	/* private QuizService quizService; */
	private bookDao dao;
	
	//value 가 form의 action method는 get혹은 post
	//ModelAndView : 저장과 앞으로 가야될 페이지를 지칭 -> 참고: ModelAndView는 스프링프레임워크에 있는 메서드? 이다.	

	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	public ModelAndView list() {
		System.out.println("basic");
		ModelAndView mav = new ModelAndView();
		List<BookCopy> list = dao.bookList();
		mav.addObject("list",list);
		mav.setViewName("./manager/book_list");
		
		return mav;
	}	
	
	
//	@RequestMapping(value="/view_regist.do", method=RequestMethod.GET)
//	public ModelAndView viewRegist() {
//		ModelAndView mav = new ModelAndView();
//		
//		mav.setViewName("./manager/book_regist");
//		return mav;
//	}
//		
//	
//	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
//	public ModelAndView regist(@ModelAttribute("book") BookCopy copy) {
//		ModelAndView mav = new ModelAndView();
//		System.out.println(copy);
//
//		boolean registSuccess = quizService.registBook(copy);
//		
//		System.out.print("registSuccess = ");
//		System.out.println(registSuccess);
//		mav.setViewName("redirect:list.do");
//		return mav;
//	}
//	
//	@RequestMapping(value="/view_detail.do", method=RequestMethod.GET)
//	public ModelAndView viewUpdate(@RequestParam String bookSeq) {
//		ModelAndView mav = new ModelAndView();
//
//		BookCopy copy = quizService.findBook(bookSeq);
//		
//		mav.addObject(copy);
//		mav.setViewName("./manager/book_detail");
//		return mav;
//	}
//	@RequestMapping(value="/update.do", method=RequestMethod.GET)
//	public ModelAndView viewRegist(@ModelAttribute("update") BookCopy copy) {
//		ModelAndView mav = new ModelAndView();
//		
//		
//		boolean flag = quizService.modifyBook(copy);
//		
//		System.out.print("update status : ");
//		System.out.println(flag);
//		mav.setViewName("./manager/book_list");
//		return mav;
//	}
//	
//	@RequestMapping(value="/remove.do", method=RequestMethod.GET)
//	public ModelAndView remove(@RequestParam String bookSeq) {
//		ModelAndView mav = new ModelAndView();
//		
//		
//		  boolean flag = quizService.removeBook(bookSeq);
//
//		  System.out.print("remove status : ");
//		 
//		 System.out.println(flag);
//
//	
//		mav.setViewName("redirect:list.do");
//		return mav;
//	}
//		
		
	
}
