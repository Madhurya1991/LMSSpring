package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

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
	
	
	
// author
	
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
	
	
	public List<Author> readAuthors(Integer pageNo, String searchString)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		List<Author> authors = new ArrayList<>();
		if (searchString != null) {
			authors = adao.readAuthorsByName(searchString);
		}else{
			authors = adao.readAllAuthors(pageNo);
		}
		for(Author a: authors){
			a.setBooks(bdao.readAllBooksByAuthorID(a.getAuthorId()));
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
	
	
// book
	
	public List<Book> readBooks()
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		return bdao.readAllBooks();
	}
	
	@Transactional
	public void addBook(Book book)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		try {
			if(book.getBookId() != null){
				bdao.updateBook(book);
//				book.setBookId(bdao.addBookWithID(book));
//				bdao.addBookAuthors(book);
			}else{
				bdao.addBook(book);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		// do for genre
		// publisher
	}
	
	public Book deleteBook(Integer bookId) throws SQLException {
		Book book = new Book();
		try {
			book.setBookId(bookId);
			bdao.deleteBook(book);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return book;
	}
	
	public Book getBookByPK(Integer bookId) throws SQLException{
		 
		try {
			return bdao.readBookByPK(bookId);  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional
	public void saveBook(Book book) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		try {
			if (book.getBookId() != null) {
				bdao.updateBook(book);
			} else {
				//bdao.addBook(book);
				Integer bookId = bdao.addBookWithID(book);
				
				if(book.getAuthors()!=null && !book.getAuthors().isEmpty()){
					for(Author a: book.getAuthors()){
						bdao.addBookAuthors(bookId, a.getAuthorId());
					}
				}
				if(book.getGenres()!=null && !book.getGenres().isEmpty()){
					for(Genre a: book.getGenres()){
						bdao.addBookGenres(bookId, a.getGenreId());
					}
				}
				if(book.getPublisher()!=null) {
					//System.out.println("Pub Id: "+ book.getPublisher().getPublisherId());
					bdao.updateBookPublisher(bookId,book.getPublisher().getPublisherId());
				}
			}
			  
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public List<Book> getAllBooks(int pageNo) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException{ 
		try {
			List<Book> books =  bdao.readAllBooks(pageNo);
			for(Book b : books){
				b.setAuthors(adao.readAuthorsByBook(b.getBookId()));
				b.setGenres(gdao.readGenreByBook(b.getBookId()));
				b.setNoCopies(bcdao.getCopiesByBook(b.getBookId()));
			}
			return books;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<Book> getBooksByName(String bookName) throws SQLException{
		try {
			List<Book> books =  bdao.readBooksByName(bookName);
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
	public Integer getBooksCount() throws SQLException{
		try { 
			return bdao.getCountOfBooks(); 
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	// publisher
	
	public Publisher addPublisher(Integer[] list, String publisherName, String publisherAddress,String publisherPhone) throws SQLException{ 
		Publisher publisher = new Publisher();
		publisher.setPublisherName(publisherName);
		publisher.setPublisherAddress(publisherAddress);
		publisher.setPublisherPhone(publisherPhone);
		List<Book> books = new ArrayList<>();
		for(Integer id : list){
			Book b = new Book();
			b.setBookId(id);
			books.add(b);
		}
		publisher.setBooks(books);
		System.out.println("here");
		try {
			if(publisher.getPublisherId() != null){
				pdao.updatePublisher(publisher);
			}else{
				pdao.addPublisher(publisher);
			}
			  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return publisher;
	}
	
	public Publisher updatePublisher(String values){
		String[] p = values.split(",");
		Integer publisherId = Integer.parseInt(p[0]);
		Publisher publisher = new Publisher();
		try {
			publisher.setPublisherId(publisherId);
			publisher.setPublisherName(p[1].trim());
			publisher.setPublisherAddress(p[2].trim());
			publisher.setPublisherPhone(p[3].trim());
			if(publisher.getPublisherId() != null){
				pdao.updatePublisher(publisher);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return publisher;
	}
	
	
	public Publisher getPublisherById(Integer pubId) throws SQLException{
		try{
			return pdao.readPublisherByID(pubId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Publisher> getAllPublishers(int pageNo) throws SQLException{
		try {
			return pdao.readAllPublishers(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<Publisher> getPublishersByName(@PathVariable String publisherName) throws SQLException{
		try {
			return pdao.readPublishersByName(publisherName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public Integer getPublishersCount() throws SQLException{
		try {
			return pdao.getCountOfPublishers();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public Publisher deletePublisher(@PathVariable Integer publisherId) throws SQLException {
		Publisher pub = new Publisher();
		try {
			pub.setPublisherId(publisherId);
			pdao.deletePublisher(pub);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return pub;
	}
	
	public Publisher getPublisherByPK(Integer publisherId) throws SQLException{
		try {
			return pdao.readPublisherByPK(publisherId);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	// genre
	
	public Genre getGenreById(Integer genreId) throws SQLException{
		try{
			return gdao.readGenreByID(genreId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Genre> getAllGenres() throws SQLException{
		try {
			return gdao.readAllGenres();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	//borrower
	
	public Borrower addBorrower(String name,String address,String phone) throws SQLException{
		Borrower borrower = new Borrower();
		try {
			borrower.setName(name);
			borrower.setAddress(address);
			borrower.setPhone(phone);
			if(borrower.getCardNo() != null){
				borrowerdao.updateBorrower(borrower);
			}else{
				borrowerdao.addBorrower(borrower);
			}
			  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		return borrower;
	}
	
	public Borrower updateBorrower(String values) throws SQLException{
		String[] b = values.split(",");
		Integer cardNo = Integer.parseInt(b[0]);
		Borrower borrower = new Borrower();
		try {
			borrower.setCardNo(cardNo);
			borrower.setName(b[1].trim());
			borrower.setAddress(b[2].trim());
			borrower.setPhone(b[3].trim());
			if(borrower.getCardNo() != null){
				borrowerdao.updateBorrower(borrower);
			}else{
				borrowerdao.addBorrower(borrower);
			}
			  
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}	
		return borrower;
	}
	
	public Borrower getBorrowerByPK(Integer cardNo) throws SQLException{
		try {
			return borrowerdao.readBorrowerByPK(cardNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Borrower> getAllBorrowers(int pageNo) throws SQLException{
		 
		try {
			return borrowerdao.readAllborrowers(pageNo);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public List<Borrower> getBorrowersByName(String borrowersName) throws SQLException{ 
		try {
			return borrowerdao.readBorrowersByName(borrowersName);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public Integer getBorrowersCount() throws SQLException{
		 
		try {
			return borrowerdao.getCountOfBorrowers();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public Borrower deleteBorrower(Integer cardNo) throws SQLException {
		Borrower borrower = new Borrower();
		try {
			borrower.setCardNo(cardNo);
			borrowerdao.deleteBorrower(borrower);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return borrower;
	}
	
//  book loans

	public BookLoans overrideDate(Integer bookId,Integer branchId,Integer cardNo, Integer days) throws SQLException{
			BookLoans loans = new BookLoans();
			long millis = System.currentTimeMillis();  
		    Date dateOut = new Date(millis);
		    long ltime = dateOut.getTime() + days*24*60*60*1000;
		    Date dueDate = new Date(ltime);
		    loans.setBookId(bookId);
		    loans.setBranchId(branchId);
		    loans.setCardNo(cardNo);
		    loans.setDueDate(dueDate);
		try {
			if(loans.getDueDate() != null){
				bldao.updateLoansDue(loans);
			}
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return loans;
	}
	
	public List<BookLoans> getAllBookLoans(int pageNo) throws SQLException{
		try {
			List<BookLoans> loans = bldao.readAllOverrideLoans(pageNo);
			for(BookLoans l : loans){
				l.setBook(bookDao.readBookByPK(l.getBookId()));
			}
			return loans;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public BookLoans getBooKLoansByPK(DateTime dateOut) throws SQLException{
		
		try {
			return bldao.readBookLoansByPK(dateOut);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
	
}
