<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=0.7,minimum-scale=0.7,maximum-scale=0.7" />
<title>DASA</title>
<link type='text/css' rel="stylesheet" href="/resources/css/style.css" />
<link type='text/css' href="http://maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet" />
<script type='text/javascript' src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type='text/javascript' src="/resources/js/slides.min.jquery.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
	    $("#loginBtn").click(function(){
	    	location.href="/login";
	    });
    });
</script>
<style type="text/css">
html,body {
	height: 100%;
	margin: 0;
	padding: 0;
}

.login {
	min-height: 100%;
	height: 100%;
	padding-top: 12%;
}

.login-frame {
	position: relative;
	margin: auto;
	background: url('/resources/img/loginframe.png') no-repeat;
	width: 607px;
	height: 420px;
/* 	top: 25%; */
}

.login-frame div {
	position: absolute;
}

.login-frame span {
	width: 100px;
	display: inline-block;
	color: #fff;
	font-size: 17px;
}

.login-frame input[type='text'],.login-frame input[type='password'] {
	border: 0;
	width: 200px;
	border-radius: 4px;
	height: 25px;
	box-shadow: rgba(0, 0, 0, 0.6) 0px 1px 4px 0px inset;
	background-color: #E0E0E0;
	overflow: hidden;
	padding-left: 5px;
}

.user_id {
	right: 215px;
	bottom: 160px;
}

.user_pw {
	right: 215px;
	bottom: 125px;
}

.login_dv {
	right: 165px;
	bottom: 125px;
}

.footer {
	margin: auto;
	position: relative;
	width: 607px;
/* 	top: 25%; */
	text-align: center;
	font-weight: bold;
}

.login_btn {
	border-radius: 4px;
	background-color: #BFE6FF;
	padding: 3px 6px 3px 6px;
	/*     font-weight: bold; */
	font-size: 12px;
	box-shadow: rgb(19, 104, 148) -1px -1px 6px 0px inset;
	color: #5A5A5A;
	height: 64px;
}

.login_dv label {
	color: #fff;
	margin-right: 95px;
}

.login-frame>div.login-title {
	margin: auto;
	position: relative;
	width: 387px;
	top: 100px;
	font-size: 58px;;
}
</style>
</head>

<body>
	<div class="login">
		<div class="login-frame">
			<div class="login-title">DASA-LOGIN</div>
			<div class="user_id">
				<span>User ID</span> <input type="text" value="admin">
			</div>
			<div class="user_pw">
				<span>Password</span> <input type="password" value="admin">
			</div>
			<div class="login_dv">
				<button type="button" id="loginBtn" class="login_btn">Login</button>
			</div>
		</div>
	</div>
</body>
</html>