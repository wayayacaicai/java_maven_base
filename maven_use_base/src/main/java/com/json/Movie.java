package com.json;

/**
 * @Desc:movie类
 * @author:zpp 
 * @time:2019年3月25日 下午9:04:18
 */
public class Movie {
	private String movie;
	private String rate;
	private String timeStamp;
	private String uid;
	
	
	public Movie() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getMovie() {
		return movie;
	}
	public void setMovie(String movie) {
		this.movie = movie;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Movie [movie=" + movie + ", rate=" + rate + ", timeStamp=" + timeStamp + ", uid=" + uid + "]";
	}
	
	
}
