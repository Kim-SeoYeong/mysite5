package com.javaex.vo;

public class GalleryVo {
	// 필드
	private int no; // 글번호
	private int userNo; // 회원식별변호
	private String name; // 작성자
	private String content; // 코멘트
	private String filePath; // 파일경로
	private String orgName; // 오리지널파일명
	private String saveName; // 저장파일명
	private long fileSize; // 파일사이즈
	
	// 생성자
	public GalleryVo(){}
	
	public GalleryVo(int no, int userNo, String name, String content, String filePath, String orgName, String saveName,
			long fileSize) {
		this.no = no;
		this.userNo = userNo;
		this.name = name;
		this.content = content;
		this.filePath = filePath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
	}

	//메소드 - g/s
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize2) {
		this.fileSize = fileSize2;
	}

	
	//일반 메소드
	
	
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", userNo=" + userNo + ", name=" + name + ", content=" + content + ", filePath="
				+ filePath + ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize=" + fileSize + "]";
	}
	
}
