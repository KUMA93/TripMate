package com.ssafy.trip.model.dto;

public class LikeDto {
	
	private int llistId;
	private String id;
	private int articleNo;
	
	public int getLlistId() {
		return llistId;
	}
	public void setLlistId(int llistId) {
		this.llistId = llistId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
	}
	@Override
	public String toString() {
		return "LikeDto [llistId=" + llistId + ", id=" + id + ", articleNo=" + articleNo + "]";
	}
}
