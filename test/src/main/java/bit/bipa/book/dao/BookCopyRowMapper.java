package bit.bipa.book.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bit.bipa.book.vo.BookCopy;

public class BookCopyRowMapper implements RowMapper<BookCopy> {
    @Override
    public BookCopy mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookCopy bookCopy = new BookCopy();
        bookCopy.setBookSeq(rs.getInt("book_seq"));
        bookCopy.setIsbn(rs.getString("book_isbn"));
        bookCopy.setTitle(rs.getString("book_title"));
        bookCopy.setAuthor(rs.getString("book_author"));
        bookCopy.setPublishDate(rs.getTimestamp("book_published_date"));
        bookCopy.setBookPosition(rs.getString("book_position"));
        bookCopy.setBookStaus(rs.getString("book_status"));

        return bookCopy;
    }

}
