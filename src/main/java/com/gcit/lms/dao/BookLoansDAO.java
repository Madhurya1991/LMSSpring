package com.gcit.lms.dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.*;

public class BookLoansDAO extends BaseDAO<BookLoans> implements ResultSetExtractor<List<BookLoans>>{

	
//	public BookLoans read(Integer[] pk) throws SQLException {
//		return read(
//				"SELECT * FROM tbl_book_loans WHERE bookid=? AND branchid=? AND cardno=?",
//				pk);
//	}
	public BookLoans read(Integer[] pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookLoans> bc = jdbcTemplate.query("SELECT * FROM tbl_book_loans WHERE bookid=? AND branchid=? AND cardno=?", new Object[] { pk[0],pk[1],pk[2] }, this);
			return bc.get(0);
		
	}
//	public void addBookLoans(BookLoans bl) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		save("INSERT INTO tbl_book_loans (title) VALUES (?)", new Object[] {book.getTitle()});
//	}
//	
//	public Integer addBookWithID(Book book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		return saveWithID("INSERT INTO tbl_book (title) VALUES (?)", new Object[] {book.getTitle()});
//	}
//	
//	public void addBookAuthors(Book book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		for(Author a: book.getAuthors()){
//			save("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] {book.getBookId(), a.getAuthorId()});
//		}
//	}
	
	public BookLoans updateBookLoans(String dueDate, Integer dd, Integer bookId,Integer branchId, Integer cardNo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		System.out.println("bo Id1 = "+bookId+" branch id1 ="+branchId+" cn1 ="+cardNo+"dueDate ="+dueDate);
		jdbcTemplate.update("Update tbl_book_loans SET dueDate = DATE_ADD(dueDate, INTERVAL ? DAY) where bookId = ? and branchId = ? and cardNo = ?", new Object[] {dd,bookId,branchId,cardNo});
//	PreparedStatement pst = conn.prepareStatement("UPDATE tbl_book_loans SET dueDate = DATE_ADD(?, INTERVAL ? DAY)");
		return null;
	}
	
	public void updateDuedat(BookLoans be) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		jdbcTemplate.update("update tbl_book_loans set duedate=? where bookid=? AND branchid=? AND cardno=?",
				new Object[] { be.getDueDate(),
						be.getBook().get(0),be.getBranch().get(0),be.getBorrower().get(0)});

	}

//	public void deleteBook(Book book) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
//		save("DELETE FROM tbl_book WHERE bookId = ?", new Object[]{book.getBookId()});
//	}
	
//	public List<Book> readAllBooks() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
//		return read("SELECT * FROM tbl_book", null);
//	}

	public BookLoans readBookLoansByPK(Integer bookId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookLoans> bo = jdbcTemplate.query("SELECT * FROM tbl_book_loans WHERE bookId  = ?", new Object[] { bookId },this);
		if (bo != null) {
			return bo.get(0);
		} else {
			return null;
		}
	}
	
//	public BookLoans updateBookLoansByPK(Date dueDate,Integer bookId)
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		save("UPDATE tbl_book SET dueDate = ?", new Object[] {dueDate});
//		return null;
//	}
//	public BookLoans updateBookLoans(Integer d)
//			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
//		save("UPDATE tbl_book_loans SET dueDate = DATE_ADD(dueDate, INTERVAL ? DAY)", new Object[] {d});
//		return null;
//	}
	
	public List<BookLoans> readAllBookLoans()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return jdbcTemplate.query("SELECT * FROM tbl_book_loans", this);
	}
	
	@Override
	public List<BookLoans> extractData(ResultSet rs) throws SQLException{
		List<BookLoans> bl = new ArrayList<>();
		while(rs.next()){
			BookLoans b = new BookLoans();
			b.setDateIn(rs.getDate("dateOut"));
			b.setDueDate(rs.getDate("dueDate"));
			b.setDateOut(rs.getDate("dateIn"));
			bl.add(b);
		}
		return bl;
	}
	

}
