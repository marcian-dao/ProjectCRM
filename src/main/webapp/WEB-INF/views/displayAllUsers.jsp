<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE Html>
<html>
<head>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
	  integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" 
	  crossorigin="anonymous">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">	

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/suitUpMain.css">

</head>
<body>
<nav>
	<a href="showAllCustomers">Customers</a>
	<a href="showUsers" class="selected">Users</a>
	<a href="#" id="userRole" class="selected">
			<security:authentication property="principal.username" /> 
			<security:authentication property="principal.authorities" />
	</a>
</nav>
	<main>
		<div id="container">
			<div id="content">

				<table>
					<tr id="bckgnd">
						<th>ID</th>
						<th>User Name</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Email</th>
						<th>Roles</th>	
						<th></th>	

					</tr>

					<c:forEach var="tempUser" items="${allUsersModel}">
		
						<c:url var="deleteLink" value="/deleteUser">
							<c:param name="userId" value="${tempUser.id}" />
						</c:url>

						<tr>
							<td>${tempUser.id}</td>
							<td>${tempUser.userName}</td>
							<td>${tempUser.firstName}</td>
							<td>${tempUser.lastName}</td>
							<td>${tempUser.email}</td>
							<td>
							<c:forEach var="tempRole" items="${tempUser.roles}">
							
								${tempRole.name}
								
							</c:forEach>		
							</td>
							<td>
								<security:authorize access="hasRole('ADMIN')">

									<input type="button" value="Delete"
													onclick="if (!(confirm('Are you sure you want to delete this customer?'))) 	  		return false; 
																  window.location.href='${deleteLink}'; return false;"
													class="btn btn-sm btn-outline-danger" 		
											        id="deleteLink" />	
								</security:authorize>
							</td>								
						</tr>	
					</c:forEach>								
				</table>
			</div>				
			<div id="add-user">
				<security:authorize access="hasAnyRole('ADMIN')">

					<input type="button" value="Add User"
							onclick="window.location.href='showRegistrationForm'; return false;"
							class="button" />
				</security:authorize>				
			</div>								
		</div>
	</main>	
</body>
</html>