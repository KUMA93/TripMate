package com.ssafy.trip.model.dto;

import java.io.Serializable;

public class AttractionInfo implements Serializable{
	private int contentId;
	private int contentTypeId;
	private String addr1;
	private String addr2;
	private String first_image; 
	private String first_image2;
	private int gugun_code;
	private double latitude;
	private double longitude;
	private String mlevel;
	private int readcount;
	private int sido_code;
	private String tel;
	private String title;
	private String zipcode;
	private String overview;
	
	
	
	public String getOverview() {
		return overview;
	}
	public void setOverview(String overview) {
		this.overview = overview;
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public int getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(int contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getFirst_image() {
		return first_image;
	}
	public void setFirst_image(String first_image) {
		this.first_image = first_image;
	}
	public String getFirst_image2() {
		return first_image2;
	}
	public void setFirst_image2(String first_image2) {
		this.first_image2 = first_image2;
	}
	public int getGugun_code() {
		return gugun_code;
	}
	public void setGugun_code(int gugun_code) {
		this.gugun_code = gugun_code;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getMlevel() {
		return mlevel;
	}
	public void setMlevel(String mlevel) {
		this.mlevel = mlevel;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getSido_code() {
		return sido_code;
	}
	public void setSido_code(int sido_code) {
		this.sido_code = sido_code;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	@Override
	public String toString() {
		return "AttractionInfo [contentId=" + contentId + ", contentTypeId=" + contentTypeId + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", first_image=" + first_image + ", first_image2=" + first_image2
				+ ", gugun_code=" + gugun_code + ", latitude=" + latitude + ", longitude=" + longitude + ", mlevel="
				+ mlevel + ", readcount=" + readcount + ", sido_code=" + sido_code + ", tel=" + tel + ", title=" + title
				+ ", zipcode=" + zipcode + ", overview=" + overview + "]";
	}
	
	
}
