<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html>
<html>
<head>

	<title>List Customers</title>

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

	<link type="text/css" rel="stylesheet"
		href="${pageContext.request.contextPath}/resources/css/suitUpMain.css">

</head>
<body>
	<nav>
		<a href="showAllCustomers" class="selected">Customers</a> 
		
		<security:authorize access="hasAnyRole('ADMIN','MANAGER')">
			<a href="showUsers">Users</a> 
		</security:authorize>
			
		<a href="#" id="userRole" class="selected">
			<security:authentication property="principal.authorities" />
		</a>	
	</nav>
	<main>
		<div id="userWrapper">
			<div id="bgrd">	
				<h2>		
					Hello ${pageContext.request.userPrincipal.name}
				</h2>							
			</div>
		</div>

		<div class="row">	
			<div id="left-button" class="button btn btn-secondary btn-sm float-left">	

				<security:authorize access="hasAnyRole('ADMIN','EMPLOYEE')">

					<input type="button" value="Add Customer"
							onclick="window.location.href='formToPocess'; return false;"
							class="btn btn-warning"/>
				</security:authorize>

			</div>						
									 
			<form:form action="search" method="GET"  class="mx-auto">			
					
				<div class="input-group">
						
					<input type="text" name="theSearchName" placeholder=" Search Customer" 
						   class="form-control py-2 border-right-0 border" />
					
					<span class="input-group-append">						                						
							<button type="submit" class="btn btn-warning border-left-0 border">
	        					<i class="fa fa-search"></i>
	      					</button>     						
					</span>	
				</div>				                    									                    
			
			</form:form>				                    					
					
			<div id="right-button" class="button btn btn-secondary btn-sm float-right">	
			
				<form:form action="${pageContext.request.contextPath}/logout" method="POST">
					<input type="submit" value="Logout" class="btn btn-warning" />

				</form:form>
			</div>	
		</div>

		<div id="container">
			<div id="content">

				<table>
					<tr id="bckgnd">
						<th><span class="theader">ID</span></th>
						<th><span class="theader">First Name</span></th>
						<th><span class="theader">Last Name</span></th>
						<th><span class="theader">Email</span></th>
						<th><span class="theader">Phone</span></th>
						<th><span class="theader">Registration Date</span></th>
						<th><span class="theader">
							 	<security:authorize access="hasRole('ADMIN')">Address</security:authorize>
							</span>
						</th>
						<th></th>
						<th></th>
					</tr>

					<c:forEach var="tempCustomer" items="${customersListModel}">

						<c:url var="addressLink" value="/showCustomerAddress">
							<c:param name="customerId" value="${tempCustomer.id}" />
						</c:url>

						<c:url var="updateLink" value="/update">
							<c:param name="customerId" value="${tempCustomer.id}" />
						</c:url>

						<c:url var="deleteLink" value="/delete">
							<c:param name="customerId" value="${tempCustomer.id}" />
						</c:url>

						<tr>
							<td>${tempCustomer.id}</td>
							<td>${tempCustomer.firstName}</td>
							<td>${tempCustomer.lastName}</td>
							<td>${tempCustomer.email}</td>
							<td>${tempCustomer.phone}</td>
							<td>${tempCustomer.registrationDate}</td>

							<td>
								<security:authorize access="hasAnyRole('ADMIN','MANAGER')">
									<input type="button" value="Address"
										   onclick="window.location.href='${addressLink}'; return false;"
										   class="btn btn-sm btn-outline-warning" 		
										   id="addressLink" />										
								</security:authorize>
							</td>
							<td>
								<security:authorize access="hasRole('ADMIN')">
									<input type="button" value="Update"
										   onclick="window.location.href='${updateLink}'; return false;"
										   class="btn btn-sm btn-outline-warning" 		
										   id="updateLink" />											
								</security:authorize>
							</td>
							<td>
								<security:authorize access="hasAnyRole('ADMIN')">
									<input type="button" value="Delete"
										   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) 	  			return false; 
														window.location.href='${deleteLink}'; 
														 return false;"
										   class="btn btn-sm btn-outline-danger" 		
										   id="deleteLink" />									
								</security:authorize>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</main>
</body>
</html>