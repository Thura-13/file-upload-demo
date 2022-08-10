<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="card">
	<div class="card-header">Book Search</div>
	<div class="card-body">
		<form action="/home">
			<div class="row">
				<div class="col">

					<label for="category">Category</label> <select name="categoryId"
						id="categoryId" class="form-select mt-1 ">
						<option value="">Search Category</option>
						<c:forEach var="c" items="${categoryList }">
							<c:choose>
								<c:when test="${c.id eq param.categoryId }">
									<option selected="selected" value="${c.id }">${c.name }</option>
								</c:when>
								<c:otherwise>
									<option value="${c.id }">${c.name }</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row mt-2">
				<div class="col">
					<label for="name">Keyword</label> <input name="keyword"
						value="${param.keyword }" type="text" class="form-control mt-1"
						placeholder="Search Keyword" />
				</div>
			</div>

			<div class="d-grid gap-2 mt-4">
				<button type="submit" class="btn btn-secondary">Search</button>
				<a href="#" id="uploadBtn" class="btn btn-primary">Upload</a>
				<c:url value="/book/edit" var="bookEdit"></c:url>
				<a href="${bookEdit }" class="btn btn-danger">Add New</a>

			</div>
		</form>
		
		<c:url value="/upload" var="upload"></c:url>
		<form action="${upload }" id="uploadForm" enctype="multipart/form-data" class="d-none" method="post">
			<input id="uploadInput" name="uploadInput" type="file" />
		</form>
		
		<c:url value="/resources/js/upload.js" var="uploadFile"></c:url>
		<script type="text/javascript" src="${uploadFile }"></script>
		
		
	</div>

</div>