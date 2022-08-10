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
					<div class="card-header">${book.id eq 0 ? 'Add New' : 'Edit' } Book</div>
					<div class="card-body">
						<form:form action="/home" modelAttribute="book">
							<form:hidden path="id" />
							<div class="row">
								<div class="col">
									<form:label path="category" class="mb-1">Select Category</form:label>
									<form:select path="category" cssClass="form-select">
										<form:option value="">Select Category</form:option>
										<form:options items="${categoryList }" itemValue="id" />
									</form:select>
									<form:errors path="category" cssClass="text-danger"></form:errors>

								</div>
								<div class="col">
									<label class="mb-1">Title</label>
									<form:input path="title" cssClass="form-control"
										placeholder="Enter Title" />
									<form:errors path="title" cssClass="text-danger"></form:errors>
								</div>
								<div class="col">
									<label class="mb-1">Author</label>
									<form:input path="author" cssClass="form-control"
										placeholder="Enter Author" />
									<form:errors path="author" cssClass="text-danger"></form:errors>
								</div>
							</div>
							<div class="row mt-2">
								<div class="col-4">
									<label class="mb-1">Price</label>
									<form:input path="price" cssClass="form-control"
										placeholder="Enter Price" />
									<form:errors path="price" cssClass="text-danger"></form:errors>
								</div>
								<div class="col">
									<label class="mb-1">Remarks</label>
									<form:input type="textarea" path="remarks"
										cssClass="form-control" placeholder="Enter Rremarks" />

								</div>

							</div>
							<div class="mt-3 text-end">
								<button class="btn btn-primary">Save Book</button>
							</div>
						</form:form>
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