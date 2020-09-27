package edu.book_tracking.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@NamedQueries({
	@NamedQuery(
	name = "BookModel.getByName",
	query = "from BookModel where bookName like :bookNamePlaceHolder"
	),
	@NamedQuery(
	name="BookModel.getAllBooks",
    query="select b.bookName from BookModel b"
    ),
	@NamedQuery(
	name="BookModel.getAllBookModelRecords",
    query="from BookModel"
    )
})
@Table(name="book")
public class BookModel implements Serializable{
	
	@Id
	@Column(name = "BookID")
	@GenericGenerator(name="maxIdIncrementer1" , strategy="increment")
	@GeneratedValue(generator="maxIdIncrementer1")
	private int bookId;
	
	@Column(name = "Book_Name")
	private String bookName;
	
	@Column(name = "ISBN_No")
	private String isbnNo;
	
	@Column(name = "Book_Price")
	private int bookPrice;
	
	@Column(name = "Book_Qty")
	private int bookQty;

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getIsbnNo() {
		return isbnNo;
	}

	public void setIsbnNo(String isbnNo) {
		this.isbnNo = isbnNo;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public int getBookQty() {
		return bookQty;
	}

	public void setBookQty(int bookQty) {
		this.bookQty = bookQty;
	}
	
	

}
