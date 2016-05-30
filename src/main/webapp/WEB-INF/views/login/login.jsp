<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta name="viewport" content="width=device-width,initial-scale=0.7,minimum-scale=0.7,maximum-scale=0.7" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>PAMS</title>
<link type='text/css' rel="stylesheet" href="/resources/css/style.css" />
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="/resources/js/common/html5shiv.js"></script>
<script type='text/javascript' src="/resources/js/slides.min.jquery.js"></script>
<script type="text/javascript" src="/resources/js/routine/login/login.js"></script>
<link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/resources/css/font-awesome.min.css">
<style>
body { background: #e7e7e7 }
</style>
</head>
<script type="text/javascript">
if($("#tabs").is(":visible")){
	location.replace("/logout");
}
</script>
	<body>
		<div class="wrap">
			<article class="loginPanel">
				<div>
					<h1>Professional Activities<br>for Merchandising & Sales</h1>
					<ul>
						<li>
							<div class="login_txt">User ID</div>
							<div class="input_group clear">
								<span>
									<input type="text" id="em_id"  class="input-icon" value="" placeholder="아이디" >
									<!--
									<select id="em_id"  class="input-icon"  >
										<option value="mk.kim">고정MD</option>
										<option value="yuna">순회MD</option>
										<option value="user9">MD팀장</option>
										<option value="admin">관리자</option>
										<option value="superuser">슈퍼유저</option>
										<option value="dasa_admin">다사_관리자</option>
										<option value="sp_admin">동서SP_관리자</option>
									</select>
									-->
									<label for="datatime"><i class="fa fa-user"></i></label>
								</span>
							</div>
						</li>
						<li>
							<div class="login_txt">Password</div>
							<div class="input_group clear">
								<span>
									<input id="auth_flag_login" type="hidden" value="">
									<input id="em_password" class="input-icon" value="" type="password" placeholder="비밀번호">
									<label for="datatime"><i class="fa fa-lock"></i></label> 
								</span>
							</div>
						</li>
					</ul>
					<div class="controlPanel">
						<div class="inputCheck">
							<input id="checkSaveId" type="checkbox"/>
							<label for="checkSaveId"></label>
						</div>
						<span>아이디 저장</span>
						<button id="loginBtn" type="button" class="hotpink btn-login">
							Login
						</button>
					</div>
				</div>
				
			</article>
			<article class="login-copy">
				<footer>
					<small>Copyright © 2015 (주)다사마케팅. All rights reserved.</small>
				</footer>
			</article>
			<div id="loginPopSpace" >
				<div id="tempPopLogin" style="display:none  ;">
						<div class="popup"  style="width: 300px;" >
						
								<div class="phead">
									<span id="">회사선택</span>
									<a id="tempCloseXLogin" class="phead-closex" >닫기</a>
								</div>
								<div class="con">
									<div class="tbl_title" >
										<span>회사를 선택하세요.</span>
									</div>
									<div>
										<div id ="" >
											<select id="cm_code_login" name ="cm_code" >
											</select> 
										</div>
									</div>
								</div>
								<div class="pfooter">
					       			<button id="loginBtn2" type="button" class="hotpink btn-login">
										Login
									</button>
					       			<button type='button' class="white" id="tempCloseLogin">닫기</button>
								</div>
							</div>
							<div class="popup_overlay"></div>
						</div>
				</div>
		</div>
	</body>
</html>