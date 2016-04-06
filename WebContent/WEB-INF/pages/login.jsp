<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
	<link href='https://fonts.googleapis.com/css?family=Fjalla+One' rel='stylesheet' type='text/css'>
	<link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
	<title>Insert title here</title>
</head>

<body>
	<div class="container block_login">
	    <div class="row">
	        <div class="col-sm-6 col-md-4 col-md-offset-4">
	            <h1 class="text-center login-title">Air Traffic</h1>
	            <div class="account-wall">
	                <img class="profile-img" src="<c:url value="/resources/images/airplane.png" />"
	                    alt="">
	                <form class="form-signin" action="<c:url value="/login"/>" name="userPost" method="post">
	                <input name="name" type="text" class="form-control" placeholder="Email" required autofocus>
	                <input name="password" type="password" class="form-control" placeholder="Password" required>
	                <button class="btn btn-lg btn-primary btn-block" type="submit">
	                    Sign in
	                </button>
	                
	                </form>
	            </div>
	            <p class="error_found">${userNotFound}</p>
	        </div>
	    </div>
	</div>
</body>
</html>