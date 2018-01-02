package com.gcit.lms.entity;

import java.util.ArrayList;

public class Genre {
	
	private Integer genreId;
	private String genreName;
	private ArrayList<Book> books;
	/**
	 * @return the genreId
	 */
	public Integer getGenreId() {
		return genreId;
	}
	/**
	 * @param genreId the genreId to set
	 */
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	/**
	 * @return the genreName
	 */
	public String getGenreName() {
		return genreName;
	}
	/**
	 * @param genreName the genreName to set
	 */
	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}
	/**
	 * @return the books
	 */
	public ArrayList<Book> getBooks() {
		return books;
	}
	/**
	 * @param books the books to set
	 */
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genreId == null) ? 0 : genreId.hashCode());
		result = prime * result + ((genreName == null) ? 0 : genreName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Genre other = (Genre) obj;
		if (genreId == null) {
			if (other.genreId != null)
				return false;
		} else if (!genreId.equals(other.genreId))
			return false;
		if (genreName == null) {
			if (other.genreName != null)
				return false;
		} else if (!genreName.equals(other.genreName))
			return false;
		return true;
	}
}
