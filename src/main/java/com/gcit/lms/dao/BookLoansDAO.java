package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookLoans;

public class BookLoansDAO extends BaseDAO<BookLoans> implements ResultSetExtractor<List<BookLoans>> {

	long millis = System.currentTimeMillis();  
	Date curDate = new Date(millis);
	
	
	public void addLoans(BookLoans bookLoans) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		jdbcTemplate.update("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate, dateIn) values (?, ?, ?, ?, ?, ?)"
				,new Object[]{((Book) bookLoans).getBookId(), bookLoans.getBranch(), bookLoans.getCardNo(), bookLoans.getDateOut(), bookLoans.getDueDate(), bookLoans.getDateIn()} );
	}

	public void updateLoans(BookLoans bookLoans) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		jdbcTemplate.update("update tbl_book_loans set dateIn = CURDATE() where bookId = ? and branchId = ? and cardNo = ?", 
				new Object[]{ bookLoans.getBookId(), bookLoans.getBranch(), bookLoans.getCardNo()});
	}
	
	public void updateLoansDue(BookLoans bookLoans) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		jdbcTemplate.update("update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?", 
				new Object[]{bookLoans.getDueDate(), bookLoans.getBookId(), bookLoans.getBranch(), bookLoans.getCardNo()});
	}

	public List<BookLoans> readAllBookLoans(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return jdbcTemplate.query("select * from tbl_book_loans", this);

	}
	
	public List<BookLoans> readAllbooksWithCardNo(int pageNo, Integer cardNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return jdbcTemplate.query("select * from tbl_book_loans where cardNo = ? and dateIn is null", new Object[]{cardNo}, this);

	}
	
	public List<BookLoans> readAllOverrideLoans(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return jdbcTemplate.query("select * from tbl_book_loans where dateIn is null", this);
		
	}
	
	public BookLoans readBookLoansByPK(Date dateOut) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		//System.out.println(dueDate);
		//readAll("select * from tbl_book_loans where convert(VARCHAR, dateOut, 120) like ?%", new Object[]{dateOut});
		List<BookLoans> loans =  jdbcTemplate.query("select * from tbl_book_loans where dateOut = ?", new Object[]{dateOut}, this);
		if(!loans.isEmpty()){
			System.out.println(loans.get(0).getDueDate());
			return loans.get(0);
		}
		return null;
	}
	
	@Override
	public List<BookLoans> extractData(ResultSet rs)
			throws SQLException {
		List<BookLoans> bookLoans = new ArrayList<>();
		
		while(rs.next()){
			BookLoans bl = new BookLoans();
//			bl.setBookId(rs.getInt("bookId"));
//			bl.setBranchId(rs.getInt("branchId"));
//			bl.setCardNo(rs.getInt("cardNo"));
			bl.setDateOut(rs.getDate("dateOut"));
			bl.setDueDate(rs.getDate("dueDate"));
			bl.setDateIn(rs.getDate("dateIn"));
			bookLoans.add(bl);
		}
		return bookLoans;
	}

}
