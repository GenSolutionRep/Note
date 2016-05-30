<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.vertexid.utill.CommonUtil" %>
<%@ page import = "net.sf.json.JSONObject" %>
<%
JSONObject jsonObject = CommonUtil.getUpdateInfo(session);
String plist_url = jsonObject.getString("plist_url");
%>
<html>
<head>
<title>아이폰 앱 설치</title>
<link type="text/css" rel="stylesheet" href="/resources/css/style.css" />
<script type="text/javascript" src="/resources/js/slides.min.jquery.js"></script>
</head>
<body>
<div id="wrapper">
<br/>
<span style="font-size:40px">아이폰 앱 설치하세요.</span>
<br/><br/>
<a href="itms-services://?action=download-manifest&url=<%=plist_url%>"><font size="30px">설치정보 Download</font></a>
<br/>
</div>
</body>
</html>
