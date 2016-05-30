<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-104.js"></script>
<style type="text/css">

.fixed-table td{
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}
.fixed-table{
border-bottom: 0; width:510px ;   max-width: 510px; position: absolute; background-color: #fff ;
}
.fixed-table tfoot > tr{
background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB
}
</style>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1 id="mainTitle30100_0000000063">관리업체별 진열율</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>분석통계<span class="clamp fa"> </span>관리업체별<span class="clamp fa"></span>진열율
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span><strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode20104" style="width: 100px;"><option value="">선택</option></select>
					</span>
					
					<span style="margin-left: 15px;"><strong>고객그룹</strong></span>
					<span>
						<select id="searchCgCode20104" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchCgName20104" type="text" maxlength="30"> -->
<!-- 						<label for="searchCgName20104"></label>  -->
					</span>
					
					<span style="margin-left: 15px;"><strong>관리업체</strong></span>
					<span>
						<select id="searchMeCode20104" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchEmName20104" type="text" maxlength="30"> -->
<!-- 						<label for="searchEmName20104"></label>  -->
					</span>
					
<!-- 					<span style="margin-left: 15px;"><strong>매장명</strong></span> -->
<!-- 					<span> -->
<!-- 						<input id="searchSmName20104" type="text" maxlength="30"> -->
<!-- 						<label for="searchSmName20104"></label>  -->
<!-- 					</span> -->
					
					<span style="margin-left: 15px;"><strong>차수</strong></span>
					<span> 
						<select id="searchSmOdr20104" style="width: 60px;">
						<option value="">전체</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
					
					<span style="margin-left: 15px;"><strong>사원명</strong></span>
					<span>
						<input id="searchEmName20104" type="text" maxlength="30">
						<label for="searchEmName20104"></label> 
					</span>
					<span style="margin-left: 15px;"><strong>시도</strong></span>
					<span> 
						<select id="searchAddrCode120104" >
						<option value="">선택</option>
						</select>
					</span>
					<span style="margin-left: 15px;"><strong>구군</strong></span>
					<span> 
						<select id="searchAddrCode220104">
						<option value="">선택</option>
						</select>
					</span>
<!-- 					<span style="margin-left: 15px;"><strong>면동</strong></span> -->
<!-- 					<span>  -->
<!-- 						<select id="searchAddrCode3" style="width: 50px;"> -->
<!-- 						<option value="">전체</option> -->
<!-- 						</select> -->
<!-- 					</span> -->
				</div>
			<div class="btn_gup">
				<button class="red" id="diplySearchBtn20104">조회</button>
				<button class="gray" id="diplySearchResetBtn20104">초기화</button>
				<button class="white" id="diplyBatchBtn20104">배치</button>
				<button class="skyblue" id="excelDownBtn20104"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
			</div>
		</div>
	</section>

	<section>
	
	<!-- UI Object -->
		<div style="overflow-y: auto;  ">
		
			<table summary="진열율 목록" class="tbl_col fixed-table">
				<thead id="fixedThead20104" >
				</thead>
				<tbody id="fixedTbody20104">
				</tbody>
				<tfoot>
				<tr>
					<td colspan="5" style="font-weight:bold;text-align:center">합계</td>
				</tr>
				</tfoot>
			</table>
			
			
			<table summary="진열율 목록" class="tbl_col" style="border-bottom: 0;min-width:1518px !important; margin-left: 509px " id="displayTable20104" >
				<colgroup id="arrColgroup20104" >
				</colgroup>
				<caption>
					진열율 목록
				</caption>
				
				<thead id="arrThead20104" >
				</thead>
				<tbody id="arrTbody20104">
				</tbody>
				<tfoot id="arrTfoot20104" >
					<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tfoot>
			</table>
		</div>
	<!-- //UI Object -->
	<div id="displayPageNavi20104" class="paginate" >
<!-- 		<ul class="pagination"> -->
<!-- 		  <li><a href="#"> <span><i class="fa fa-angle-double-left"></i></span> </a></li> -->
<!-- 		     <li><a class="page_on">1</a></li> -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(2)"> 2 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(3)"> 3 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(4)"> 4 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(5)"> 5 </a></li>  -->
<!-- 		     <li><a href="#" onclick="javascript:fnGetCvsList(6)"> 6 </a></li>  -->
<!-- 		   <li><a href="#"> <span><i class="fa fa-angle-double-right"></i></span> </a></li> -->
<!-- 		</ul> -->
	</div>
	</section>
</article>
</body>
</html>
