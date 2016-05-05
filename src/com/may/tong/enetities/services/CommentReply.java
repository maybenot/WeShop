package com.may.tong.enetities.services;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="S_CommentReply")
//评价回复
public class CommentReply {
	
	private Integer commentReplyNo;
	//评论回复的内容
	private String commentReplyDetail;
	//评论回复的时间
	private Date replyTime;
	
	//多个评价对应一个卖家
	private Seller seller;
  
	@Id
	@GeneratedValue
	public Integer getCommentReplyNo() {
		return commentReplyNo;
	}

	public void setCommentReplyNo(Integer commentReplyNo) {
		this.commentReplyNo = commentReplyNo;
	}

	public String getCommentReplyDetail() {
		return commentReplyDetail;
	}

	public void setCommentReplyDetail(String commentReplyDetail) {
		this.commentReplyDetail = commentReplyDetail;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	@JoinColumn(name="Seller_No")
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REFRESH)
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	

}
