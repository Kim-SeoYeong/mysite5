<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
					<c:if test="${sessionScope.authUser.no != null}">
						<button id="btnImgUpload">이미지올리기</button>
					</c:if>
					<div class="clear"></div>

					<ul id="viewArea">
						<!-- 이미지반복영역 -->
						<c:forEach items="${galList}" var="galleryList">
							<li id="galLi" data-no="${galleryList.no}" data-chkdelete="${galleryList.userNo == authUser.no}" data-userno="${galleryList.userNo}">
								<!-- li태그를 클릭했을때 no값을 가져오게 하고싶어서 data-no를 이용..data 어렵다ㅠ 엄청 헤맴 ㅠㅠ--> <!-- data-어쩌고 ==> 여기 어쩌고부분에 대문자가 들어가면 인식이안됨.. 이유는 모르겠음 ㅠㅠ  -->
								<div class="view">
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${galleryList.saveName}">
									<div class="imgWriter">
										작성자: <strong>${galleryList.name}</strong>
									</div>
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->



	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지등록</h4>
				</div>

				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->



	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">

					<div class="formgroup">
						<img id="viewModelImg" src="">
						<!-- ajax로 처리 : 이미지출력 위치-->
					</div>

					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>

					<!-- no 히든처리 -->
					<input id="modalNo" type="hidden" name="gno" value="">

				</div>

				<div class="modal-footer"></div>

			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->


</body>

<script type="text/javascript">
	//이미지등록 버튼 클릭시
	$("#btnImgUpload").on("click", function() {
		console.log("모달창 호출");

		//모달창 내용 비우기(초기화)
		$("#addModalContent").val("");
		$("#file").val("");

		//모달창 띄우기
		$("#addModal").modal();
	});

	//모달창에서 등록버튼 클릭시
	$("#btnUpload").on("click", function() {
		console.log("모달창 닫기");

		//모달창 닫기
		$("#addModal").modal("hide");
	});

	//이미지화면 클릭시 
	$("#viewArea")
			.on(
					"click",
					"li",
					function() {
						console.log("이미지 클릭");

						//no값 data-no을 이용해서 가져오기.
						var no = $(this).data("no");
						//console.log(no);

						//이미지 출력을 ajax로 이용
						$
								.ajax({

									url : "${pageContext.request.contextPath}/gallery/read",
									type : "post",
									//contentType : "application/json",
									data : {
										no : no
									},

									dataType : "json",
									success : function(galleryVo) {
										/*성공시 처리해야될 코드 작성*/
										//잘넘어오는지 확인해보자!
										//console.log(galleryVo.no);
										//console.log(galleryVo.name);
										//console.log(galleryVo.saveName);
										//이미지 보기 팝업창 img 경로에 가져온 데이터로 넣어주기
										$("#viewModelImg").attr(
												"src",
												"${pageContext.request.contextPath}/upload/"
														+ galleryVo.saveName);

										//모달창에 content 넣어주기
										$("#viewModelContent").html(
												galleryVo.content);

										//no값 넣어주기
										$("#modalNo").attr("value",
												galleryVo.no);

									},
									error : function(XHR, status, error) {
										console.error(status + " : " + error);
									}
								});

						//첫번쨰 방법은 userno값만 가져와서 비교하는 방법
						var userNo = $(this).data("userno");
						//console.log("userNo : " + userNo);

						var authUser = "${authUser.no}";
						//console.log("authUser : " + authUser);

						if (userNo == authUser) {
							$(".modal-footer")
									.html(
											"<button type='button' class='btn btn-default' data-dismiss='modal'>닫기</button>"
													+ "<button type='button' class='btn btn-danger' id='btnDel'>삭제</button>");
						} else {
							$(".modal-footer")
									.html(
											"<button type='button' class='btn btn-default' data-dismiss='modal'>닫기</button>");
						}

						//2번쨰 방법은 아예 data에서부터 비교해서 가져오는 방법
						/*
						var chkdelete = $(this).data("chkdelete");
						//console.log(chkdelete);
						
						if (chkdelete == true) {
							$(".modal-footer").html("<button type='button' class='btn btn-default' data-dismiss='modal'>닫기</button>" + 
												    "<button type='button' class='btn btn-danger' id='btnDel'>삭제</button>");
						} else {
							$(".modal-footer").html("<button type='button' class='btn btn-default' data-dismiss='modal'>닫기</button>");
						}
						 */

						//이미지보기 팝업 모달창 띄우기
						$("#viewModal").modal();
					});

	//이미지 삭제 버튼 클릭시
	$(".modal-footer").on("click", "#btnDel", function() {
		console.log("삭제버튼 클릭!");

		var no = $("#modalNo").val();
		//console.log(no);

		//이미지 삭제
		$.ajax({

			url : "${pageContext.request.contextPath}/gallery/delete",
			type : "post",
			//contentType : "application/json",
			data : {
				no : no
			},

			dataType : "json",
			success : function(count) {
				/*성공시 처리해야될 코드 작성*/
				if (count == 1) {
					//모달창 닫기
					$("#viewModal").hide();

					//갤러리 리스트에서 삭제해주자
					$("#galLi").remove();
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

	});
</script>




</html>

