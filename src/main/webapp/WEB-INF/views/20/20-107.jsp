<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-107.js"></script>
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
			<h1>관리업체별 PD매대현황</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>분석통계<span class="clamp fa"> </span>관리업체별<span class="clamp fa"></span>PD매대현황
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span><strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode20107" style="width: 100px;"><option value="">선택</option></select>
					</span>
					
					<span style="margin-left: 15px;"><strong>고객그룹</strong></span>
					<span>
						<select id="searchCgCode20107" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchCgName20107" type="text" maxlength="30"> -->
<!-- 						<label for="searchCgName20107"></label>  -->
					</span>
					
					<span style="margin-left: 15px;"><strong>관리업체</strong></span>
					<span>
						<select id="searchMeCode20107" style="width: 100px;"><option value="">선택</option></select>
<!-- 						<input id="searchEmName20107" type="text" maxlength="30"> -->
<!-- 						<label for="searchEmName20107"></label>  -->
					</span>
					
<!-- 					<span style="margin-left: 15px;"><strong>매장명</strong></span> -->
<!-- 					<span> -->
<!-- 						<input id="searchSmName20107" type="text" maxlength="30"> -->
<!-- 						<label for="searchSmName20107"></label>  -->
<!-- 					</span> -->
					
					<span style="margin-left: 15px;"><strong>차수</strong></span>
					<span> 
						<select id="searchSmOdr20107" style="width: 60px;">
						<option value="">전체</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
					<span style="margin-left: 15px;"><strong>사원명</strong></span>
					<span>
						<input id="searchEmName20107" type="text" maxlength="30">
						<label for="searchEmName20107"></label> 
					</span>
					
					<span style="margin-left: 15px;"><strong>시도</strong></span>
					<span> 
						<select id="searchAddrCode120107" >
						<option value="">선택</option>
						</select>
					</span>
					<span style="margin-left: 15px;"><strong>구군</strong></span>
					<span> 
						<select id="searchAddrCode220107">
						<option value="">선택</option>
						</select>
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="diplySearchBtn20107">조회</button>
				<button class="gray" id="diplySearchResetBtn20107">초기화</button>
				<button class="white" id="diplyBatchBtn20107">배치</button>
				<button class="skyblue" id="excelDownBtn20107"><i class="fa fa-file-excel-o fa-lg"></i> 엑셀</button>
			</div>
		</div>
	</section>

	<section>
	<div style="overflow: auto;">
	<!-- UI Object -->
			<table class="tbl_col fixed-table">
				<thead id="fixedThead20107" >
				</thead>
				<tbody id="fixedTbody20107">
				</tbody>
				<tfoot>
				<tr>
					<td colspan="5" style="font-weight:bold;text-align:center">합계</td>
				</tr>
				</tfoot>
			</table>
			<table summary="PD매대 목록" class="tbl_col" style="border-bottom: 0;min-width:1518px !important ;margin-left: 509px;" id="displayTable20107" >
				<colgroup id="pdColgroup20107" >
				</colgroup>
				<caption>
					보조진열현황 목록
				</caption>
				<thead id="pdThead20107" >
				</thead>
				<tbody id="pdTbody20107">
				</tbody>
				<tfoot id="pdTfoot20107" >
					<tr><td></td></tr>
				</tfoot>
			</table>

	<!-- //UI Object -->
	</div>
	<div id="displayPageNavi20107" class="paginate" >
	</div>
	</section>
</article>
</body>
</html>
