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
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/ui-lightness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/style.css"/>
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/file-style.css"/>
<%-- <link rel="stylesheet" type="text/css" href="<%=cssPath%>/style_temp.css"/> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=cssPath%>/tree/foundation.css" > --%>
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/tree/mtree.css" >
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/font-awesome.min.css" >
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/tree/leftmenu.css" >
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/datetimepicker.css">
<link rel="stylesheet" type="text/css" href="<%=cssPath%>/autocomplete.css">

<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script> -->
<script type="text/javascript" src="<%=jsPath%>/common/html5shiv.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.placeholder.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.scrollabletab.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.init.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.form.js"></script> 
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.velocity.min.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/mtree.js"></script>
<script type="text/javascript" src="<%=jsPath%>/jquery/jquery.autocomplete.js"></script>
<script type="text/javascript" src="<%=jsPath%>/datetimepicker.js" charset="UTF-8"></script>
<script type="text/javascript" src="<%=jsPath%>/common/common_util.js"></script>
<script type="text/javascript" src="<%=jsPath%>/common/file_util.js"></script>

<link rel="stylesheet" type="text/css" href="<%=cssPath%>/post-search.css"/>
<script type="text/javascript" src="<%=jsPath%>/common/post-search.js"></script>

<!-- Include CSS for JQuery Frontier Calendar plugin (Required for calendar plugin) -->
<link rel="stylesheet" type="text/css"
	href="<%=cssPath%>/frontierCalendar/jquery-frontier-cal-1.3.2.css" />

<!-- Include CSS for color picker plugin (Not required for calendar plugin. Used for example.) -->
<link rel="stylesheet" type="text/css"
	href="<%=cssPath%>/colorpicker/colorpicker.css" />
<!-- Include JQuery UI (Required for calendar plugin.) -->
<script type="text/javascript"
	src="<%=jsPath%>/jquery-ui/smoothness/jquery-ui-1.8.1.custom.min.js"></script>

<!-- Include color picker plugin (Not required for calendar plugin. Used for example.) -->
<script type="text/javascript" src="<%=jsPath%>/colorpicker/colorpicker.js"></script>

<!-- Include jquery tooltip plugin (Not required for calendar plugin. Used for example.) -->
<script type="text/javascript"
	src="<%=jsPath%>/jquery-qtip-1.0.0-rc3140944/jquery.qtip-1.0.js"></script>
<!--
	(Required for plugin)
	Dependancies for JQuery Frontier Calendar plugin.
    ** THESE MUST BE INCLUDED BEFORE THE FRONTIER CALENDAR PLUGIN. **
-->
<script type="text/javascript" src="<%=jsPath%>/lib/jshashtable-2.1.js"></script>

<!-- Include JQuery Frontier Calendar plugin -->
<script type="text/javascript" src="<%=jsPath%>/frontierCalendar/jquery-frontier-cal-1.3.2.js"></script>

<script>

var auth_flag="${sessionScope.auth_flag}";
var login_no="${sessionScope.login_no}";
var login_cp_cd="${sessionScope.login_cp_cd}";
var login_bhf_cd="${sessionScope.login_bhf_cd}";
var login_cp_nm="${sessionScope.login_cp_nm}";
var login_dty_cd="${sessionScope.login_dty_cd}";

</script>
<title>PAMS</title>
