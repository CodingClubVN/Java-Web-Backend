package com.se.codingclub.dto;

public class ImageDTO {
	private int id;
	private String url;
	private String type;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ImageDTO(int id, String url, String type) {
		super();
		this.id = id;
		this.url = url;
		this.type = type;
	}

	public ImageDTO() {
		// TODO Auto-generated constructor stub
	}
}
