<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Boook</title>
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
					<div class="card-header">A Book</div>
					<div class="card-body">
							<div class="row" >
								<div class="col">
									<label>Angular</label>
									<span class="form-control">${book.category.name }</span>									
								</div>
								<div class="col">
									<label class="mb-1">Title</label>
									<span class="form-control">${book.title }</span>
								</div>
								<div class="col">
									<label class="mb-1">Author</label>
									<span class="form-control">${book.author }</span>
								</div>
							</div>
							<div class="row mt-2">
								<div class="col-4">
									<label class="mb-1">Price</label>
									<span class="form-control">${book.price }</span>
								</div>
								<div class="col">
									<label class="mb-1">Remarks</label>
									<span class="form-control">${book.remarks }</span>
								</div>

							</div>
							<div class="mt-3 text-end">
							<c:url value="/book/edit" var="editBook">
								<c:param name="id" value="${book.id }"></c:param>
							</c:url>
								<a href="${editBook }" class="btn btn-secondary">Edit Book</a>
							</div>
					</div>
				</div>
			</div>
			<div class="col">
				<c:import url="/views/imports/right-bar.jsp"></c:import>
			</div>
		</div>

	</div>
</body>
</html>