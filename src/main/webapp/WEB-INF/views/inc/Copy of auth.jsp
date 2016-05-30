<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page deferredSyntaxAllowedAsLiteral="true"%>
<%@ include file="../inc/common.jsp"%>
<!--  
/**
 * @파일명: header.jsp
 * @작성일: 2014. 9. 30.
 * @작성자: 김진호
 * @프로그램 설명:  헤더
 */
-->
<script type="text/javascript" src="<%=jsPath%>/common/auth.js"></script>
<script>
checkAuth(currMno);
</script>
