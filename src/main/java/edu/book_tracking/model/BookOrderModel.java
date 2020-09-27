package edu.book_tracking.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;


@NamedNativeQueries({
	@NamedNativeQuery(
			name="BookOrderModel.getByStudentID",
			query="select BookOrderID from book_order where StudentID = :studentIDPlaceholder "
			),
	@NamedNativeQuery(
			name="BookOrderModel.getStudentByBookId",

			query="select StudentID from book_order where BookID = :bookID and ReturnDate=null"
			),
	@NamedNativeQuery(
			name="BookOrderModel.getBookByReturnDate",
			query="select BookID from book_order where BookID = :bookID and StudentID=:studId and ReturnDate is null"
			),
	@NamedNativeQuery(
			name = "BookOrderModel.getBookOrderDetailsByBookid",
			query = "select BookOrderID from book_order where BookID = :bookorderdetailsbookid"
			),
	@NamedNativeQuery(
			name = "BookOrderModel.getAllBookidfromBookOrder",
			query = "select DISTINCT BookID from book_order"
			)
})
@Entity
@NamedQueries({
	@NamedQuery(
	name = "BookOrderModel.getOrderType",
	query = "select distinct bom.orderType from BookOrderModel bom"
	),
	@NamedQuery(
	name = "BookOrderModel.getBookOrderDetails",
	query = "from BookOrderModel where orderType like :orderTypePlaceHolder"
	),
	@NamedQuery(
	name = "BookOrderModel.getStudentBookOrderDetails",
	query = "from BookOrderModel"
	)
})

@Table(name="book_order")
public class BookOrderModel implements Serializable{

	@Id
	@Column(name = "BookOrderID")
	@GenericGenerator(name="bookOrderIdGenerator" , strategy="increment")
	@GeneratedValue(generator="bookOrderIdGenerator")
	private int bookOrderId;
	
	
	@Column(name = "DueDate")
	private Date dueDate;
	
	@Column(name = "CheckoutDate")
	private Date checkoutDate;
	
	@Column(name = "ReturnDate")
	private Date returnDate;
	
	@Column(name = "Price")
	private int price;
	
	@Column(name = "Order_Type")
	private String orderType;
	
	
	@OneToOne
	@JoinColumn(name="BookID")
	private BookModel bookModelObj;
	
	@OneToOne
	@JoinColumn(name="StudentID")
	private StudentModel studentModelObj;
	
	public Date getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(Date checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public int getBookOrderId() {
		return bookOrderId;
	}

	public void setBookOrderId(int bookOrderId) {
		this.bookOrderId = bookOrderId;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public BookModel getBookModelObj() {
		return bookModelObj;
	}

	public void setBookModelObj(BookModel bookModelObj) {
		this.bookModelObj = bookModelObj;
	}

	public StudentModel getStudentModelObj() {
		return studentModelObj;
	}

	public void setStudentModelObj(StudentModel studentModelObj) {
		this.studentModelObj = studentModelObj;
	}
}
