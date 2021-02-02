package com.javaex.vo;

public class RboardVo {
	// 필드
	private int no; // 댓글번호
	private int userNo; // 회원식별번호
	private String title; // 댓글제목
	private String name;	//작성자
	private String content; // 댓글내용
	private int hit; // 댓글 조회수
	private String regDate; // 등록일
	private int groupNo; // 그룹번호(부모번호)
	private int orderNo; // 그룹내 글 순서
	private int depth; // 깊이
	private int flag;	//삭제처리를 위한 플래그

	//생성자
	public RboardVo() {}

	public RboardVo(int no, int userNo, String title, String name, String content, int hit, String regDate, int groupNo,
			int orderNo, int depth, int flag) {
		this.no = no;
		this.userNo = userNo;
		this.title = title;
		this.name = name;
		this.content = content;
		this.hit = hit;
		this.regDate = regDate;
		this.groupNo = groupNo;
		this.orderNo = orderNo;
		this.depth = depth;
		this.flag = flag;
	}


	//메소드-g/s
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}
	//일반메소드

	@Override
	public String toString() {
		return "RboardVo [no=" + no + ", userNo=" + userNo + ", title=" + title + ", name=" + name + ", content="
				+ content + ", hit=" + hit + ", regDate=" + regDate + ", groupNo=" + groupNo + ", orderNo=" + orderNo
				+ ", depth=" + depth + ", flag=" + flag + "]";
	}
	
}
