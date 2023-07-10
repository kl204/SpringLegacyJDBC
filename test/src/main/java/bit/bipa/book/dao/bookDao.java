package bit.bipa.book.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import bit.bipa.book.vo.BookCopy;


public class bookDao {
	
	private JdbcTemplate jdbcTemplate;
	

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	
	
	//전체 도서 출력
    public List<BookCopy> bookList() {

        String sql = "select a.book_seq,b.book_isbn, b.book_title, b.book_author, b.book_published_date, a.book_position, a.book_status from book_copy a join book_info b on a.book_isbn = b.book_isbn order by a.book_seq asc;";                
        
        List<BookCopy> bookList = null;
		try {
			System.out.println("before query");
			System.out.println(jdbcTemplate);
			bookList = jdbcTemplate.query(sql, new BookCopyRowMapper());
			System.out.println("after query");

		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
 

        return bookList;
    }


    // 도서 등록
    public int bookRegist(BookCopy copy) throws SQLException {

        int flagBookCopy = 0;
        int flagBookInfo = 0;

        String bookInfoSql = "insert into book_info( book_isbn, book_title, book_author, book_published_date) values(?,?,?,?); ";
        String bookCopySql = "insert into book_copy (book_isbn) values (?);";
        
        flagBookInfo = jdbcTemplate.update(bookInfoSql, copy.getIsbn(), copy.getTitle(), copy.getAuthor(), copy.getPublishDate());
        
        flagBookCopy = jdbcTemplate.update(bookCopySql, copy.getIsbn());
        

        if(flagBookCopy!=0 && flagBookInfo!=0){
            System.out.println("sql regist success");
            return 1;
        }
        return 0;
    }

    
    public int bookDelete(String book_seq) throws SQLException{
        int flagBookCopy = 0;
        int flagBookInfo = 0;

/*        String bookInfoDeleteSql = "delete from book_info where book_isbn = ?;";
*/        String bookCopyDeleteSql = "delete from book_copy where book_seq = ?;";

/*
 * flagBookInfo = jdbcTemplate.update(bookInfoDeleteSql, );
 */        		
        		 
        flagBookCopy = jdbcTemplate.update(bookCopyDeleteSql, Integer.parseInt(book_seq));
        

        if(flagBookCopy!=0 && flagBookInfo!=0){
            System.out.println("sql regist success");
            return 1;
        }
        return 0;

    }

    
    
   
    
    public BookCopy bookUpdatePage (String book_seq) {
    	
    	BookCopy findBook = new BookCopy();

        String sql = " select a.book_seq, b.book_isbn, b.book_title, b.book_author, b.book_publisher, b.book_published_date, a.book_position, a.book_status "
        		+ "from book_copy a "
        		+ "join book_info b "
        		+ "on a.book_isbn = b.book_isbn "
        		+ "where a.book_seq=?;";
              
        findBook = jdbcTemplate.query(sql, new Object[]{book_seq}, new BookCopyExtractor());

        return findBook;

    }
 
    // BookCopy 단일 객체만 넘기는 메소드, 위의 bookUpdatePage에서 사용함
    private static class BookCopyExtractor implements ResultSetExtractor<BookCopy> {
        @Override
        public BookCopy extractData(ResultSet rs) throws SQLException, DataAccessException {
            if (rs.next()) {
                BookCopy bookCopy = new BookCopy();
                bookCopy.setBookSeq(Integer.parseInt(rs.getString("book_seq")));
                bookCopy.setIsbn(rs.getString("book_isbn"));
                bookCopy.setTitle(rs.getString("book_title"));
                bookCopy.setAuthor(rs.getString("book_author"));
                bookCopy.setPublisher(rs.getString("book_publisher"));
                bookCopy.setPublishDate(rs.getTimestamp("book_published_date"));
                bookCopy.setBookPosition(rs.getString("book_position"));
                bookCopy.setBookStaus(rs.getString("book_status"));
                // Set other properties of the book copy
                return bookCopy;
            }
            return null;
        }
    }

    //도서 수정
    public int bookUpdate (BookCopy copy) throws SQLException {
        int flagBookCopy = 0;
        int flagBookInfo = 0;

        String bookInfoUpdateSql = "update book_info set book_isbn=?, book_title=?, book_author=?, book_publisher=?, book_published_date=? where book_isbn = ?";
        String bookCopyUpdateSql = "update book_copy set book_seq= ?, book_position= ?, book_status=? where book_seq = ?";

        
        flagBookInfo = jdbcTemplate.update(bookInfoUpdateSql, copy.getIsbn(), copy.getTitle(), copy.getAuthor(), copy.getPublisher() , copy.getPublishDate(), copy.getIsbn());
        
        flagBookCopy = jdbcTemplate.update(bookCopyUpdateSql, copy.getBookSeq(), copy.getBookPosition(), copy.getBookStaus(), copy.getBookSeq());
       


        if(flagBookCopy!=0 && flagBookInfo!=0){
            System.out.println("sql regist success");
            return 1;
        }
        return 0;
    }



}
