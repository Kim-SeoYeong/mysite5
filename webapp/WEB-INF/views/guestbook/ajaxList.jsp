<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/bootstrap/js/bootstrap.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<div id="aside">
			<h2>방명록</h2>
			<ul>
				<li>일반방명록</li>
				<li>ajax방명록</li>
			</ul>
		</div>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>일반방명록</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>방명록</li>
						<li class="last">일반방명록</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="guestbook">
				<!-- <form action="" method="">  -->
					<table id="guestAdd">
						<colgroup>
							<col style="width: 70px;">
							<col>
							<col style="width: 70px;">
							<col>
						</colgroup>
						<tbody>
							<tr>
								<th><label class="form-text" for="input-uname">이름</label>
								</td>
								<td><input id="input-uname" type="text" name="name"></td>
								<th><label class="form-text" for="input-pass">패스워드</label>
								</td>
								<td><input id="input-pass" type="password" name="pass"></td>
							</tr>
							<tr>
								<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
							</tr>
							<tr class="button-area">
								<td colspan="4"><button id= "btnSubmit" type="submit">등록</button></td>
							</tr>
						</tbody>

					</table>
					<!-- //guestWrite -->

				<!-- </form>  -->
				
				<div id="guestbookListArea">
					<!-- 방명록 글 리스트 출력되는 영역 -->
				</div>
				<!-- //guestRead -->

			</div>
			<!-- //guestbook -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	<!-- 모달창 영역 -->
	<div class="modal fade" id="delModel">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">방명록 삭제</h4>
				</div>
				<div class="modal-body">
					<label>비밀번호</label>
					<input id="modalPassword" type="text" name="password" value=""><br>
					
					<!-- no 히든처리 -->
					<input id="modalNo" type="text" name="no" value="">
		
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
					<button id = "modalBtnDel" type="button" class="btn btn-primary">삭제</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

</body>

<script type="text/javascript">
	
	//브라우저 준비가 끝났을때(DOM이 생성되면)
	$("document").ready(function(){
		console.log("ready");
		
		//리스트 출력
		fetchList();
	});
	
	//모달창 삭제버튼 클릭할때 --> 삭제 프로세서
	$("#modalBtnDel").on("click", function(){
		console.log("모달창 삭제버튼 클릭");
		
		//모달창 비밀번호, no 수집
		var guestbookVo = {
			password : $("#modalPassword").val(),
			no :  $("#modalNo").val()
		};
		
		console.log(guestbookVo);
		
		//var password = $("#modalPassword").val();
		var no = $("#modalNo").val();
		
		//console.log(password + ", " + no);
		
		//ajax로 삭제요청
		$.ajax({
		
			url : "${pageContext.request.contextPath }/api/guestbook/remove",		
			type : "post",
			//contentType : "application/json",
			//data : {no : no, password : password},
			data : guestbookVo,
	
			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/
				console.log(count);
				
				//count == 1 ==>화면 삭제 작업
				if(count == 1) {
					//모달창을 닫고
					$("#delModel").modal("hide");
					//삭제한 no 테이블(글)이 화면에서 안보이도록 처리
					$("#t-" + no).remove();
					
				} else { //count == 0
					//alert 창 띄우기
					alert("비밀번호가 틀렸습니다.");
					$("#modalPassword").val("");
					//$("#delModel").modal("hide");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}	
		});
		
	});
	
	//삭제버튼 클릭할때 --> 비밀번호 입력창 호출
	//guestbookListArea 안에 있는 a태그를 클리했을때.
	$("#guestbookListArea").on("click", "a", function(){
		//클릭했을때 작동을 하지 않겠다.
		event.preventDefault();
		console.log("모달창 호출");

		//no 읽어오기
		//console.log($(this));
		var no = $(this).data("no");
		//console.log(no);
		
		//비밀번호 필드 초기화
		$("#modalPassword").val("");
		
		$("#modalNo").val(no);	
		
		//모달창 호출
		$("#delModel").modal();
	});

	
	//방명록 등록버튼 클릭할때
	//제이슨 형식으로 넘길때
	$("#btnSubmit").on("click", function(){
		console.log("방명록 등록 버튼 클릭");
		
		//객체로 만들어서 넘길경우
		var guestbookVo = {
			name : $("[name='name']").val(),
			password : $("[name='pass']").val(),
			content : $("[name='content']").val()
		};
		
		//{"name":1, "password": "1234", "content": "내용테스트"}
		
		//제이슨형식으로 넘길때
		//등록데이터 수집
		$.ajax({
			
			url : "${pageContext.request.contextPath}/api/guestbook/write2",		
			type : "post",
			contentType : "application/json",		//내가 보내는 데이터가 jason이야
			data : JSON.stringify(guestbookVo),		//guestbookVo를 jason형식으로 바꿔줌

			dataType : "json",
			success : function(guestVo){
				//성공시 처리해야될 코드 작성
				console.log(guestVo);
				//console.log(guestVo.no);
				//console.log(guestVo.name);
				//{no: 71, name: "제이슨", password: null, content: "제이슨 내용", regDate: "2021-02-09 16:09:17.0"}
				render(guestVo, "up");	//guestVo 정보 출력
				
				//입력폼 비우기
				$("[name='name']").val("");
				$("[name='pass']").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	
	
	
	//방명록 등록버튼 클릭할때
	//그냥 하나하나 파라미터 넘길경우
	/*
	$("#btnSubmit").on("click", function(){
		console.log("방명록 등록 버튼 클릭");
		
		//그냥 하나하나 넘길경우
		//등록 데이터 수집
		var bname = $("[name='name']").val();
		var bpassword = $("[name='pass']").val();
		var bcontent = $("[name='content']").val();
		
		console.log(bname);
		console.log(bpassword);
		console.log(bcontent);

		//등록데이터 수집
		$.ajax({
			
			url : "${pageContext.request.contextPath}/api/guestbook/write2",		
			type : "post",
			//contentType : "application/json",		
			data : {name : bname,
					password : bpassword,
					content : bcontent},

			dataType : "json",
			success : function(guestVo){
				//성공시 처리해야될 코드 작성
				console.log(guestVo);
				//console.log(guestVo.no);
				//console.log(guestVo.name);
				//{no: 71, name: "제이슨", password: null, content: "제이슨 내용", regDate: "2021-02-09 16:09:17.0"}
				render(guestVo, "up");	//guestVo 정보 출력
				
				//입력폼 비우기
				$("[name='name']").val("");
				$("[name='pass']").val("");
				$("[name='content']").val("");
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
	});
	*/
	
	
	//방명록 글 정보 + html 조합하여 화면에 출력
	function render(guestbookVo, updown) {
		
		var str = '';
		str += '<table id="t-'+ guestbookVo.no +'" class ="guestRead">';
		str += '	<colgroup>';
		str += '		<col style="width: 10%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 40%;">';
		str += '		<col style="width: 10%;">';
		str += '	</colgroup>';
		str += '	<tr>';
		str += '		<td>' + guestbookVo.no + '</td>';
		str += '		<td>' + guestbookVo.name + '</td>';
		str += '		<td>' + guestbookVo.regDate + '</td>';
		str += '		<td><a href="" data-no="' + guestbookVo.no + '" >[삭제]</a></td>';	//data-원하는값(data를 숨겨놓는것, data속성이라고함.)
		str += '	</tr>';
		str += '	<tr>';
		str += '		<td colspan=4 class="text-left">' + guestbookVo.content + '</td>';
		str += '	</tr>';
		str += '</table>';
	
		if(updown == "down") {
			$("#guestbookListArea").append(str);
		} else if(updown == "up") {
			//테이블 위에 테이블이 추가되야하니까 prepend
			$("#guestbookListArea").prepend(str);
		} else {
			console.log("테이블 순서 미지정");
		}
	
	}
	
	//전체리스트 출력
	function fetchList() {
		//ajax
		$.ajax({
			
			url : "${pageContext.request.contextPath}/api/guestbook/list",		
			type : "post",
			//contentType : "application/json",
			//data : {name : "홍길동"}

			dataType : "json",
			success : function(guestbookList){
				/*성공시 처리해야될 코드 작성*/
				console.log(guestbookList);
				
				for(var i = 0; i < guestbookList.length; i++) {
					render(guestbookList[i], "down");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});		
	}
	
</script>

</html>