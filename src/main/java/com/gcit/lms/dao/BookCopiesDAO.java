package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Branch;

public class BookCopiesDAO extends BaseDAO<BookCopies> implements ResultSetExtractor<List<BookCopies>> {


	
	public void addBookCopies(BookCopies bc)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		jdbcTemplate.update("INSERT INTO tbl_book_copies (bookId, branchId, noOfCopies) "
				+ "VALUES (?,?,?)", new Object[] {bc.getBookId(),bc.getBranchId(),bc.getNoOfCopies()});
	}

	public void updateBookCopies(BookCopies bc)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		jdbcTemplate.update("UPDATE tbl_book_copies SET noOfCopies = ? WHERE bookId = ? and branchId = ? ",
				new Object[] {bc.getNoOfCopies(),bc.getBookId(),bc.getBranchId()});
	}

	public void deleteBranch(BookCopies bc)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		jdbcTemplate.update("DELETE FROM tbl_book_copies WHERE branchId = ?", new Object[] { bc.getBranchId()});
	}

	public List<BookCopies> readAllBookCopies()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return jdbcTemplate.query("SELECT * FROM tbl_book_copies", this);
	}
	
	
	public BookCopies readBookCopiesByPK(Integer pk)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<BookCopies> bc = jdbcTemplate.query("SELECT * FROM tbl_book_copies WHERE branchId  = ?", new Object[] { pk }, this);
		if (bc != null) {
			return bc.get(0);
		} else {
			return null;
		}
	}
	
	
	
	@Override
	public List<BookCopies> extractData(ResultSet rs) throws SQLException {
		List<BookCopies> bcs = new ArrayList<>();
		while(rs.next()){
			BookCopies b = new BookCopies();
			b.setBookId(rs.getInt("bookId"));
			b.setBranchId(rs.getInt("branchId"));
			b.setBranchId(rs.getInt("noOfCopies"));
			bcs.add(b);
		}
		return bcs;
	}
	

}
