<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/aside.jsp"></c:import>
		<!-- //aside -->

		<div id="content">

			<div id="content-head">
				<h3>게시판</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>게시판</li>
						<li class="last">일반게시판</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->

			<div id="board">
				<div id="list">
					<form action="" method="">
						<div class="form-group text-right">
							<input type="text">
							<button type="submit" id=btn_search>검색</button>
						</div>
					</form>
					<table >
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
								<th>작성일</th>
								<th>group_no</th>
								<th>order_no</th>
								<th>depth</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${requestScope.rbList}" var="rboardList">
								<tr>
									<td>${rboardList.no}</td>
									<td class="text-left">
										<a href="${pageContext.request.contextPath}/rboard/read?no=${rboardList.no}">
											<c:forEach var="item" begin="1" end="${rboardList.depth}" step="1">
												<!-- &nbsp; 공백표시를 해줌. -->
												&nbsp;&nbsp;&nbsp;
											</c:forEach>
											${rboardList.title}
										</a>
									</td>
									<td>${rboardList.name}</td>
									<td>${rboardList.hit}</td>
									<td>${rboardList.regDate}</td>
									<td>${rboardList.groupNo}</td>
									<td>${rboardList.orderNo}</td>
									<td>${rboardList.depth}</td>
									<td>
										<c:if test="${authUser.no eq rboardList.userNo}">
											<a href="">[삭제]</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
		
					<div id="paging">
						<ul>
							<li><a href="">◀</a></li>
							<li><a href="">1</a></li>
							<li><a href="">2</a></li>
							<li><a href="">3</a></li>
							<li><a href="">4</a></li>
							<li class="active"><a href="">5</a></li>
							<li><a href="">6</a></li>
							<li><a href="">7</a></li>
							<li><a href="">8</a></li>
							<li><a href="">9</a></li>
							<li><a href="">10</a></li>
							<li><a href="">▶</a></li>
						</ul>
						
						
						<div class="clear"></div>
					</div>
					<c:if test="${authUser.no != null}">
						<a id="btn_write" href="${pageContext.request.contextPath}/rboard/writeForm">글쓰기</a>
					</c:if>
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

</body>

</html>
