package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.*;;

public class BranchDAO extends BaseDAO<Branch> implements ResultSetExtractor<List<Branch>>{

	@Autowired
	BookCopiesDAO bCopiesDao;
	
	public void addBranch(Branch branch) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		jdbcTemplate.update("insert into tbl_library_branch (branchName, branchAddress) values (?, ?)", 
				new Object[]{branch.getBranchName(), branch.getBranchAddress()});
	}
	
	public void updateBranch(Branch branch) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		jdbcTemplate.update("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?", 
				new Object[]{branch.getBranchName(), branch.getBranchAddress(), branch.getBranchId()});
	}
	
	public void deleteBranch(Branch branch) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		jdbcTemplate.update("delete from tbl_library_branch where branchId = ?", new Object[]{branch.getBranchId()});
	}

	public List<Branch> readAllBranches(int pageNo) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		setPageNo(pageNo);
		return jdbcTemplate.query("select * from tbl_library_branch join tbl_book_copies on tbl_library_branch.branchId = tbl_book_copies.branchId where tbl_book_copies.noOfCopies > 0", this);
	}

	public Branch readBranchByPK(Integer branchId) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		List<Branch> branches =  jdbcTemplate.query("select * from tbl_library_branch where branchId = ?", new Object[]{branchId}, this);
		if(!branches.isEmpty()){
			return branches.get(0);
		}
		return null;
	}
	
	@Override
	public List<Branch> extractData(ResultSet rs)
			throws SQLException {
		List<Branch> branches = new ArrayList<>();
		while(rs.next()){
			Branch branch = new Branch();
			branch.setBranchId(rs.getInt("branchId"));
			branch.setBranchName(rs.getString("branchName"));
			branch.setBranchAddress(rs.getString("branchAddress"));
			Integer copies = bCopiesDao.getCopiesByBranch(branch.getBranchId());
//			branch.setBookCopies(copies);
			branches.add(branch);
		}
		return branches;
	}

}

