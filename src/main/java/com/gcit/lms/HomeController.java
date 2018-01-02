package com.gcit.lms;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.gcit.lms.service.*;
import com.gcit.lms.dao.*;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdminService AdminService;
	
	@Autowired
	BorrowerService BorrowerService;
	
	@Autowired
	LibrarianService LibrarianService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {		
		return "welcome";
	}
	
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public String author(Locale locale, Model model) {		
		return "author";
	}
	
	@RequestMapping(value={"/addAuthor"}, method=RequestMethod.GET)
	  public String saveAuthor(Locale locale, Model model)
	  {
	    return "addAuthor";
	  }
	
	@RequestMapping(value={"/deleteAuthor"}, method=RequestMethod.GET)
	  public String deleteAuthor(Locale locale, Model model)
	    throws Exception
	  {
	    List<Author> authorlist = this.AdminService.readAuthors();
	    model.addAttribute("authorlist", authorlist);
	    return "deleteAuthor";
	  }
	
	@RequestMapping(value = "/viewauthors", method = RequestMethod.GET)
	public String viewAuthors(Locale locale, Model model) {		
		return "viewauthors";
	}
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String book(Locale locale, Model model) {		
		return "book";
	}
	
	@RequestMapping(value = "/viewbooks", method = RequestMethod.GET)
	public String viewBooks(Locale locale, Model model) {		
		return "viewBooks";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String addBooks(Locale locale, Model model) {		
		return "addBook";
	}
	
	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public String publisher(Locale locale, Model model) {		
		return "publisher";
	}
	
	@RequestMapping(value = "/addPublisher", method = RequestMethod.GET)
	public String addPublisher(Locale locale, Model model) {		
		return "addPublisher";
	}
	
	@RequestMapping(value = "/viewPublisher", method = RequestMethod.GET)
	public String viewPublisher(Locale locale, Model model) {		
		return "viewPublisher";
	}
	
	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String borrower(Locale locale, Model model) {		
		return "borrower";
	}
	
	@RequestMapping(value = "/addBorrower", method = RequestMethod.GET)
	public String addBorrower(Locale locale, Model model) {		
		return "addBorrower";
	}
	
	@RequestMapping(value = "/viewBorrower", method = RequestMethod.GET)
	public String viewBorrower(Locale locale, Model model) {		
		return "viewBorrower";
	}
	
	@RequestMapping(value = "/override", method = RequestMethod.GET)
	public String override(Locale locale, Model model) {		
		return "editBookLoans";
	}
	
	@RequestMapping(value={"/updateLibrary"}, method=RequestMethod.POST)
	  public String updateLibrary(Locale locale, Model model)
	  {
	    return "updateLibrary";
	  }
}
