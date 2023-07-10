package bit.bipa.book.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import bit.bipa.book.dao.bookDao;
import bit.bipa.book.vo.BookCopy;

@Service("quizService")
public class QuizService {
	
	private ArrayList<BookCopy> list;
	private bookDao dao = new bookDao();
	
	public QuizService() {
		/*
		 * list = new ArrayList<BookCopy>(); list.add(new
		 * BookCopy(1,"PM0000037903","���� ������ ��� �� : ����� ��� �ְ� �ϴ� ���� �����Դϱ�","������ ����",new
		 * Timestamp(new Date(2021-1900,12,23).getTime()),"BS-0001","BM-0001"));
		 * list.add(new
		 * BookCopy(2,"PM0000037904","��â�� ����ȸȭ�� �� ���ϴ� ��. 1 Fluent English","������ ����",new
		 * Timestamp(new Date(2023-1900,1,28).getTime()),"BS-0001","BM-0001"));
		 * list.add(new
		 * BookCopy(3,"PM0000037905","���ϸ��� �� : �������� �߰��� ����� 7���� ����","��Ͼ� ���� ���� ; ȫ���� �ű�"
		 * ,new Timestamp(new Date(2023-1900,9,8).getTime()),"BS-0001","BM-0001"));
		 */
		
		
	}
	
	public boolean checkId(String id) {
		boolean flag = false;
		if(id.equals("admin")) {
			flag = true;
		}
		return flag;
	}
	
	public boolean registBook(BookCopy copy) {
		boolean flag = false;
		copy.setBookSeq(list.size()+1);
		copy.setBookPosition("BS-0001");
		copy.setBookStaus("BM-0001");
		list.add(copy);
		return flag;
	}
	
	public List<BookCopy> searchBookAll(){
				
		List<BookCopy> list = dao.bookList();
		
		return list;
	}
	public boolean removeBook(String bookSeq) {
		// TODO Auto-generated method stub
		boolean flag = false;
		int index = -1;
		for(int i=0;i<list.size();i++) {
			BookCopy copy = list.get(i);
			if(copy.getBookSeq()==Integer.parseInt(bookSeq)) {
				index = i;
				flag = true;
				break;
			}
		}
		list.remove(index);
		return flag;
	}
	public BookCopy findBook(String bookSeq) {
		BookCopy copy = null;
		for(int i=0;i<list.size();i++) {
			copy = list.get(i);
			if(copy.getBookSeq()==Integer.parseInt(bookSeq)) {
				break;
			} else {
				copy = null;
			}
		}
		System.out.println(copy);
		return copy;
	}
	public boolean modifyBook(BookCopy copy) {
		// TODO Auto-generated method stub
		boolean flag = false;
		BookCopy book = null;
		for(int i=0;i<list.size();i++) {
			book = list.get(i);
			if(book.getBookSeq()==copy.getBookSeq()) {
				list.set(i, copy);
				flag = true;
			} 
		}
		return flag;
	}
}
