package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

@Service
public class AdminService {

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

	@Transactional
	public void saveAuthor(Author author) {
		try {
			if (author.getAuthorId() != null) {
				adao.updateAuthor(author);
			} else {
				adao.addAuthor(author);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteAuthor(Author author)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		adao.deleteAuthor(author);
	}
	
	@Transactional
	public void addBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		book.setBookId(bdao.addBookWithID(book));
		bdao.addBookAuthors(book);
		// do for genre
		// publisher

	}

	public List<Author> readAuthors(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Author> authors = new ArrayList<>();
		if (searchString != null) {
			authors = adao.readAuthorsByName(searchString);
		}else{
			authors = adao.readAllAuthors(pageNo);
		}
		for(Author a: authors){
//			a.setBooks(bdao.readAllBooksByAuthorID(a.getAuthorId()));
		}
		return authors;
	}


	public Author readAuthorByPk(Integer authorId)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return adao.readAuthorByPK(authorId);
	}

	public Integer getAuthorsCount()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return adao.getAuthorsCount();
	}

	
	// books
	
	public void saveBook(Book book) {
		try {
			if (book.getBookId() != null) {
				bdao.updateBook(book);
			} else {
				bdao.addBook(book);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		bdao.deleteBook(book);
	}
	
	public List<Book> readBooks(Integer pageNo2, String searchString2) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		if(searchString2 != null){
			return bdao.readBooksByName(searchString2);
		}
		else
		{
		return bdao.readAllBooks(pageNo2);
		}
	}
	
	public Book readBookByPk(Integer bookId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return bdao.readBookByPK(bookId);
	}
	
	
	public Integer getBooksCount() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		return bdao.getBooksCount();
	}
	
	// Publisher
	
		public Publisher readPublisherByPk(Integer publisherId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return pdao.readPublisherByPK(publisherId);
		}
		
		public List<Publisher> readPublisher() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return pdao.readAllPublishers();
		}
		
		public void savePublisher(Publisher publisher) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		
				if(publisher.getPublisherId()!=null){
					pdao.updatePublisher(publisher);
				}else{
					pdao.addPublisher(publisher);
				}		
		}
		
		public void deletePublisher(Publisher publisher) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
				pdao.deletePublisher(publisher);
				}
		
		// book loans
		
		public List<BookLoans> readBookLoans() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return bldao.readAllBookLoans();
		}
	
		public BookLoans updateBookLoans(String dueDate, Integer dd, Integer bookId,Integer branchId, Integer cardNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return bldao.updateBookLoans(dueDate,dd, bookId, branchId, cardNo);
		}
		
		// borrower
		
		public Borrower readBorrowerByPk(Integer cardNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return borrowerdao.readBorrowerByPK(cardNo);
		}
		
		public List<Borrower> readBorrower() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return borrowerdao.readAllBorrowers();		
		}
		
		public void saveBorrower(Borrower borrower) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
				if(borrower.getCardNo()!=null){
					borrowerdao.updateBorrower(borrower);
				}else{
					borrowerdao.addBorrower(borrower);
				}
			
		}
		
		public void deleteBorrower(Borrower borrower) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			borrowerdao.deleteBorrower(borrower);
		}
		
		// library branch
		
		public Branch readBranchByPk(Integer branchId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return brdao.readBranchByPK(branchId);
		}
		
		public List<Branch> readBranch() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			return brdao.readAllBranches();
		}
		
		public void saveBranch(Branch branch) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
				if(branch.getBranchId()!=null){
					brdao.updateBranch(branch);
				}else{
					brdao.addBranch(branch);
				}
		}
		
		public void deleteBranch(Branch branch) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
			
				brdao.deleteBranch(branch);
		}
}
