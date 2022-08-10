<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Shop</title>
<c:url value="/resources/css/bootstrap.min.css" var="bootStrapCss"></c:url>
<c:url value="/resources/js/bootstrap.bundle.min.js" var="bootBundleJs"></c:url>

<script type="text/javascript" src="${ bootBundleJs}"></script>
<link rel="stylesheet" href="${bootStrapCss }" />
</head>

<body>
	<div class="container mt-4">
		<h2>
			<sp:message code="app.title"></sp:message>
		</h2>

		<div class="row mt-4">

			<div class="col-9">
				<div class="card">
					<div class="card-header">Book List</div>
					<div class="card-body">
						<table class="table table-striped mt-4">
							<thead>
								<tr>
									<th>Id</th>
									<th>Title</th>
									<th>Category Name</th>
									<th>Author</th>
									<th>Price</th>
									<th>Remarks</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="book" items="${list }">
									<tr>
										<td>${book.id }</td>
										<c:url value="/book/detail" var="bookDetail">
											<c:param name="id" value="${book.id }"></c:param>
										</c:url>
										<td><a href="${bookDetail }"> ${book.title } </a></td>
										<td>${book.category.name }</td>
										<td>${book.author }</td>
										<td>${book.price }</td>
										<td>${book.remarks }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="col">
				<c:import url="/views/imports/right-bar.jsp"></c:import>
			</div>
		</div>
		
		<c:if test="${not empty uploadMessage }">
			<div id="uploadMessageModal" class="modal" tabindex="-1">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">Upload File</h5>
							<button class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<p>${uploadMessage}</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</c:if>

	</div>
</body>

</html>