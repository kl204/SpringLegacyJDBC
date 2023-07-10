package bit.bipa.book.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bit.bipa.book.service.QuizService;
import bit.bipa.book.vo.BookCopy;

@Controller("bookController2")
@RequestMapping("extend")
public class bookController2 {
	
	//DI에 해당되는 것 = Autowired
	@Autowired
	private QuizService quizService;
	
	//value 가 form의 action method는 get혹은 post
	@RequestMapping(value="/list.do", method=RequestMethod.GET)
	//ModelAndView : 저장과 앞으로 가야될 페이지를 지칭 -> 참고: ModelAndView는 스프링프레임워크에 있는 메서드? 이다.
	public ModelAndView list() {
		/*
		 * System.out.println("extends"); System.out.println("tsfdsdf");
		 */
		ModelAndView mav = new ModelAndView();
		ArrayList<BookCopy> list = quizService.searchBookAll();
		mav.addObject("list",list);
		mav.setViewName("./manager/book_list");
		
		return mav;
	}
	
	
	@RequestMapping(value="/view_regist.do", method=RequestMethod.GET)
	public ModelAndView viewRegist() {
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("./manager/book_regist");
		return mav;
	}
		
	
	@RequestMapping(value="/regist.do", method=RequestMethod.POST)
	public ModelAndView regist(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		BookCopy copy = new BookCopy();
		copy.setIsbn(request.getParameter("isbn"));
		copy.setTitle(request.getParameter("book_title"));
		copy.setAuthor(request.getParameter("author"));
		copy.setPublisher(request.getParameter("publisher"));
		String date = request.getParameter("publish_date");
		System.out.println(date);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date now = df.parse(date);
			copy.setPublishDate(new Timestamp(now.getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quizService.registBook(copy);
		
		System.out.println("regist");
		mav.setViewName("redirect:/list.do");
		return mav;
	}
	
	
}
