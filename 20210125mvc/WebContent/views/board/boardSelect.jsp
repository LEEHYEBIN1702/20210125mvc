<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div align="center">
<jsp:include page="../common/menu.jsp" />
		<div>
			<h1>게시글 상세보기</h1>
		</div>
		<div>
			<table border="1">
				<tr>
					<th width="70">글번호</th>
					<td align="center" width="50">${vo.boardNo }</td>
					<th width="70">작성자</th>
					<td align="center" width="150">${vo.writer }</td>
					<th width="80">작성일자</th>
					<td align="center" width="150">${vo.creationDate }</td>
				</tr>
				<tr>
					<th width="70">제 목</th>
					<td colspan="5" >${vo.title }</td>
				</tr>
				<tr>
					<th width="70">내용</th>
					<td colspan="5"><textarea rows="7" cols="95">${vo.content }</textarea></td>
				</tr>
			</table>
		</div><br />
		<div>
		<button type="button" onclick="location.href='/20210125mvc/BoardList.do'">목록보기</button>
		&nbsp;&nbsp;&nbsp;
		<button type="button" onclick="location.href='/20210125mvc/BoardDelete.do?row='+${vo.boardNo }">글 삭제</button>
		&nbsp;&nbsp;&nbsp;
		<button type="button" onclick="location.href='/20210125mvc/BoardUpdate.do?row='+${vo.boardNo }">글 수정</button>
		</div>
	</div>
</body>
</html>