package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.*;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.*;
import com.gcit.lms.entity.Book;

@Service
public class LibrarianService {

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
	
	public List<Branch> getAllBranches(int pageNo) throws SQLException{
		try {
			return brdao.readAllBranches(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public BookCopies getBookCopiesById(Integer bookId, Integer branchId) throws SQLException{
		try {
			return bcdao.getAllCopiesbId(bookId,branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public String getBorrowerName(Integer cardNo) throws SQLException{
		try {
			Borrower borrower = borrowerdao.readBorrowerByPK(cardNo);
			if(borrower != null){
				return borrower.getName();
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public List<Book> getAllBooksWithBranch(Integer branchId) throws SQLException{
		System.out.println(branchId);
		try {
			List<Book> books = bdao.readAllbooksWithBranch(branchId);
			for(Book b : books){
				b.setAuthors(adao.readAuthorsByBook(b.getBookId()));
				b.setGenres(gdao.readGenreByBook(b.getBookId()));
				b.setNoCopies(bcdao.getCopiesByBook(b.getBookId()));
			}
			return books;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	
	public Branch getBranchPK(Integer branchId) throws SQLException{
		try {
			return brdao.readBranchByPK(branchId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	@Transactional
	public void addBranch(Branch branch) throws SQLException{
		try {
			if(branch.getBranchId()!=null){
				brdao.updateBranch(branch);
			}else{
				brdao.addBranch(branch);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Branch updateBranch(Integer branchId, String name, String address) throws SQLException{
		Branch branch = new Branch();
		try {
			branch.setBranchId(branchId);
			branch.setBranchAddress(address);
			branch.setBranchName(name);
			if(branch.getBranchId() != null){
				brdao.updateBranch(branch);
			}else{
				brdao.addBranch(branch);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return branch;
	}
	
	public BookCopies updateCopies(int branchId, int bookId, int noCopies) throws SQLException{
		try {
			BookCopies copies = new BookCopies();
			copies.setBookId(bookId);
			copies.setBranchId(branchId);
			copies.setNoOfCopies(noCopies);
			if(copies.getBookId() != null){
				bcdao.updateBookCopies(copies);
			}else{
				bcdao.addBookCopies(copies);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return new BookCopies();
	}	
}