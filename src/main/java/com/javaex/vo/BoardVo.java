package com.javaex.vo;

public class BoardVo {
	
	private int no;		//식별번호
	private String title;	//글제목
	private String content;		//글내용
	private String name;	//글쓴이
	private int hit;	//조회수
	private String regDate;	//등록일
	private int userNo;	//회원식별번호
	
	
	public BoardVo() {}

	public BoardVo(int no, String title, String content) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
	}

	public BoardVo(String title, String content, int userNo) {
		this.title = title;
		this.content = content;
		this.userNo = userNo;
	}

	public BoardVo(int no, String title, String content, String name, int hit, String regDate, int userNo) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.name = name;
		this.hit = hit;
		this.regDate = regDate;
		this.userNo = userNo;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	

	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", name=" + name + ", hit=" + hit
				+ ", regDate=" + regDate + ", userNo=" + userNo + "]";
	}
	
}
