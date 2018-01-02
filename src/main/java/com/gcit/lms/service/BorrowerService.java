package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;
import com.gcit.lms.entity.Book;

@Service
public class BorrowerService {

	@Autowired
	AuthorDAO adao;
	
	@Autowired
	BookDAO bdao;
	
	@Autowired
	PublisherDAO pdao;
	
	@Autowired
	GenreDAO gdao;
	
	@Autowired
	BookLoansDAO bldao;
	
	@Autowired
	BookCopiesDAO bcdao;
	
	@Autowired
	BranchDAO brdao;
	
	@Autowired
	BorrowerDAO borrowerdao;
	

public boolean validateCardNo(Integer cardNo){
		try{
			Borrower b = borrowerdao.readBorrowerByPK(cardNo);
			if(b.getCardNo() != null){
				return true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public String getBorrowerName(Integer cardNo) throws SQLException{
		try {
			Borrower borrower = borrowerdao.readBorrowerByPK(cardNo);
			if(borrower.getName() != null){
				return borrower.getName();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<BookLoans> getAllBookWithLoans(Integer pageNo,Integer cardNo) throws SQLException{
		try {
			List<BookLoans> loans = bldao.readAllbooksWithCardNo(pageNo, cardNo);
			for(BookLoans l : loans){
				l.setBook(bdao.readBookByPK(l.getBookId()));
			}
			return loans;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public BookLoans checkOut(Integer cardNo, @PathVariable Integer bookId, @PathVariable Integer branchId) throws SQLException{
		 BookLoans bookLoans = new BookLoans();
		try {
			long millis = System.currentTimeMillis();  
		    Date dateOut = new Date(millis);
		    long ltime = dateOut.getTime()+5*24*60*60*1000;
		    Date dueDate = new Date(ltime);		   
		    bookLoans.setBookId(bookId);
		    bookLoans.setBranchId(branchId);
		    bookLoans.setCardNo(cardNo);
		    bookLoans.setDateOut(dateOut);
		    bookLoans.setDueDate(dueDate);
		    bookLoans.setDateIn(null);
		    bldao.addLoans(bookLoans);
		  
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(bookId);
			bookCopies.setBranchId(branchId);
			bcdao.updateBookCopiesOut(bookCopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return bookLoans;
	}
	
	public BookLoans checkIn(Integer bookId, Integer branchId, Integer cardNo) throws SQLException{
		 BookLoans bookLoans = new BookLoans();
		try {
		    bookLoans.setBookId(bookId);
		    bookLoans.setBranchId(branchId);
		    bookLoans.setCardNo(cardNo);
		    bldao.updateLoans(bookLoans);
		  
			BookCopies bookCopies = new BookCopies();
			bookCopies.setBookId(bookId);
			bookCopies.setBranchId(branchId);
			bcdao.updateBookCopiesIn(bookCopies);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return bookLoans;
	}
	
}