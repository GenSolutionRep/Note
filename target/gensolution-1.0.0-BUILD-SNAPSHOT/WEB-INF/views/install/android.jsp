<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.vertexid.utill.CommonUtil" %>
<%@ page import = "net.sf.json.JSONObject" %>
<%
JSONObject jsonObject = CommonUtil.getUpdateInfo(session);
String and_url = jsonObject.getString("and_url");
%>
<html>
<head>
<title>안드로이드 앱 설치</title>
<link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
<script type="text/javascript" src="/resources/js/slides.min.jquery.js"></script>
</head>
<body>
<div id="wrapper">
<br/>
<span style="font-size:40px">안드로이드 앱 설치하세요.</span>
<br/><br/>
<a href="<%=and_url%>"><font size="30px">설치파일 Download</font></a>
<br/>
</div>
</body>
</html>
