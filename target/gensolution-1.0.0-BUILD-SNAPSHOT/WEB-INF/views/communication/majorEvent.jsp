<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<link href="<%=jsPath%>/scheduler/fullcalendar.css" rel="stylesheet" />
<link href="<%=jsPath%>/scheduler/fullcalendar.print.css" rel="stylesheet" media="print" />
<script src="<%=jsPath%>/scheduler/lib/moment.min.js"></script>
<%--<script src="<%=jsPath%>/scheduler/fullcalendar.min.js"></script>--%>
<script src="<%=jsPath%>/scheduler/fullcalendar.js"></script>
<script src="<%=jsPath%>/scheduler/lang-all.js"></script>
<script type="text/javascript" src="<%=jsPath%>/routine/communication/majorEvent.js"></script>
<style type="text/css">/* 일정관리 */
/* 일정관리 */
#spop{
	position: absolute;
/* 	height: 200px; */
	z-index: 99999;
}
/* #spop.mysch{ */
/* 	width: 700px; */
/* 	height: 400px; */
/* } */
#spop input[type='radio']{
	margin-left: 7px;
	margin-right: 3px;
}
#spop .arrow_box >h1 > a{color:#4f4f4f;display:inline-block;right:-25px;top:8px;position:absolute;text-decoration:none;text-indent:-9999px;background:url('../imgs/close_proj.png') no-repeat;background-size:15px 15px;width:50px;height:20px;;}
#spop .arrow_box h1{
	background-color: #67beb6;
	height: 30px;
}
#spop .arrow_box h1 > a{
	float: right;
}
.table_wrap.spop-table{
margin-bottom: 0px;
border: none;
}
.table_wrap.spop-table > table{
width: 500px;
}
.table_wrap.spop-table tbody tr > td{
text-align: left; padding-left:10px;
white-space: pre-line;
width: 300px;   
word-break:break-all;
}
.table_wrap.spop-table tbody tr:last-child > td{
height: 100px;
vertical-align: top;
white-space: pre-line;  
width: 300px;  
padding: 0px;
}
.table_wrap.spop-table tbody tr:last-child td > div{
	overflow-y:scroll; 
	height: 100px;
	padding: 10px;
}
.table_wrap.spop-table tbody tr:last-child td div.area{
	overflow:hidden;
	height: 100px;
	padding: 10px;
}
.table_wrap.spop-table tbody tr:last-child td div.area textarea{
	resize: none; wrap:hard;
}

.table_wrap.spop-table tbody td{
padding: 7px 15px;
font-size: 12px;
color: #444444;
border-bottom: 1px solid #e4e4e4;
height: 12px;
white-space: nowrap;
}


div#spop div.arrow_box h1 > span{
line-height:30px;padding-left:10px;
color: #fff;
}
.ta_center{text-align:center;margin:30px auto;}
.ta_center.spop-button{
margin-top:5px; margin-bottom:5px;
}
.ta_center.spop-button button:FIRST-CHILD{
margin-left: -5px;
}
.fc-content-skeleton table tbody > tr{
	background-color: transparent;
}
#calendar tr{
	background-color: transparent;
}
input.sch-date{
	width: 80px;
	margin-right: 5px;
}







.arrow_box {
	position: relative;
	background: #fff;
	border: 1px solid #4f4f4f;
	width: 500px;
}
.arrow_box:after, .arrow_box:before {
	top: 100%;
	left: 50%;
	border: solid transparent;
	content: " ";
	height: 0;
	width: 0;
	position: absolute;
	pointer-events: none;
}

.arrow_box:after {
	border-color: rgba(136, 183, 213, 0);
	border-top-color: #fff;
	border-width: 10px;
	margin-left: -10px;
}
.arrow_box:before {
	border-color: rgba(194, 225, 245, 0);
	border-top-color:#4f4f4f;
	border-width: 11px;
	margin-left: -11px;
}

</style>
</head>
<body>
	<div id="wrap">
		<div id="calendar"></div>
	</div>
</body>
</html>
