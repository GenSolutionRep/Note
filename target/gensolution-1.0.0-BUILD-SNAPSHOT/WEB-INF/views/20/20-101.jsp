<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../inc/auth.jsp" flush='true' />
<script type="text/javascript" src="<%=jsPath%>/routine/20/20-100.js"></script>
<script type="text/javascript">
		
$(document).ready(function(){
	
	//필수 (화면 로딩시 tab 밖으로 이동)
	$("#tempPop20100").instancePopUp();
	
	$("#tempOpen20100").click(function(){
		$("#tempPop20100").show();
	});
	$("#tempClose20100, #tempCloseX20100").click(function(){
		$("#tempPop20100").hide();
	});
});

</script>
</head>
<body>
<article class="content">
	<section>
		<div class="page_nav">
			<h1 id="mainTitle30100_0000000063">사원별 진열율</h1>
			<p>
				<i class="fa fa-home"></i><span class="clamp fa"> </span>분석통계<span class="clamp fa"> </span>사원별<span class="clamp fa"></span>진열률
			</p>
		</div>
	</section>
	<section>
		<div class="searchbox">
				<div class="input_group clear">
					<span><strong>지점명</strong></span>
					<span> 
						<select id="searchOmCode10400" style="width: 100px;"><option value="00001">동부지점</option></select>
					</span>
					
					<span style="margin-left: 15px;"><strong>고객그룹</strong></span>
					<span>
						<input id="searchSmName70100" type="text" maxlength="30">
						<label for="searchSmName70100"></label> 
					</span>
					
					<span style="margin-left: 15px;"><strong>관리업체</strong></span>
					<span>
						<input id="searchSmName70100" type="text" maxlength="30">
						<label for="searchSmName70100"></label> 
					</span>
					
					<span style="margin-left: 15px;"><strong>매장명</strong></span>
					<span>
						<input id="searchSmName70100" type="text" maxlength="30">
						<label for="searchSmName70100"></label> 
					</span>
					
					<span style="margin-left: 15px;"><strong>차수</strong></span>
					<span> 
						<select id="searchOmCode10400" style="width: 50px;">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						</select>
					</span>
				</div>
			<div class="btn_gup">
				<button class="red" id="cvsSearchBtn">조회</button>
				<button class="gray" id="cvsSearchResetBtn">초기화</button>
			</div>
		</div>
	</section>

	<section>
	<div style="overflow: auto;">
	<!-- UI Object -->
			<table summary="진열율 목록" class="tbl_col" style="border-bottom: 0;min-width:1518px !important ">
				<caption>
					진열율 목록
				</caption>
				<colgroup>
					<col width="50px">
					<col width="80px">
					<col width="80px">
					<col width="48px">
					<col width="48px">
					<col width="60px">
					<col width="60px">
					<col width="60px">
					<col width="48px">
					<col width="48px">
					<col width="60px">
					<col width="48px">
					<col width="60px">
					<col width="48px">
					<col width="60px">
					<col width="48px">
					<col width="48px">
					<col width="48px">
					<col width="60px">
					<col width="48px">
					<col width="48px">
					<col width="48px">
					<col width="60px">
					<col width="48px">
					<col width="48px">
					<col width="60px">
				</colgroup>
				<thead>
					<tr>
						<th scope="col" rowspan="2">No.</th>
						<th scope="col" rowspan="2">매장명</th>
						<th scope="col" rowspan="2">관리업체</th>
						<th scope="col" rowspan="2">차수</th>
						<th scope="col" colspan="3">I/C전체</th>
						<th scope="col" colspan="4">C/M전체</th>
						<th scope="col" colspan="4">카누진열율</th>
						<th scope="col" colspan="4">P/C전체</th>
						<th scope="col" colspan="4">HNT(메밀,둥글레)</th>
						<th scope="col" colspan="3">T.O.P</th>
					</tr>
					<tr>
						<th scope="col">D</th>
						<th scope="col">N+NY</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">Ny</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">Ny</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">K</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">A</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">L</th>
						<th scope="col">점유율</th>
					</tr>
				</thead>
				</table>
			<!-- UI Object -->
			<table summary="진열율 목록" class="tbl_row" style="border-top: 0;min-width:1518px !important ">
				<caption>
					진열율 목록
				</caption>
				<colgroup>
					<col width="50px">
					<col width="80px">
					<col width="80px">
					<col width="48px">
					<col width="48px">
					<col width="60px">
					<col width="60px" style="background-color:#FBF0F0">
					<col width="60px">
					<col width="48px">
					<col width="48px">
					<col width="60px" style="background-color:#FBF0F0">
					<col width="48px">
					<col width="60px">
					<col width="48px">
					<col width="60px" style="background-color:#FBF0F0">
					<col width="48px">
					<col width="48px">
					<col width="48px">
					<col width="60px" style="background-color:#FBF0F0">
					<col width="48px">
					<col width="48px">
					<col width="48px">
					<col width="60px" style="background-color:#FBF0F0">
					<col width="48px">
					<col width="48px">
					<col width="60px" style="background-color:#FBF0F0">
				</colgroup>
				<thead style="display:none;">
					<tr>
						<th scope="col">고객그룹</th>
						<th scope="col">관리업체</th>
						<th scope="col">매장명</th>
						<th scope="col">차수</th>
						<th scope="col">D</th>
						<th scope="col">N+NY</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">Ny</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">Ny</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">K</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">A</th>
						<th scope="col">기타</th>
						<th scope="col">점유율</th>
						<th scope="col">D</th>
						<th scope="col">L</th>
						<th scope="col">점유율</th>
					</tr>
				</thead>
				<tbody id="cvsTbody10400">
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr>
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
					<tr style="background-color:#F7F7F7;font-weight:bold; border-top:2px solid #ABABAB">
						<td colspan="4" style="font-weight:bold;text-align:center">합계</td>
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
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>

	<!-- //UI Object -->
	</div>
	<div id="" class="paginate">
		<ul class="pagination">
		  <li><a href="#"> <span><i class="fa fa-angle-double-left"></i></span> </a></li>
		     <li><a class="page_on">1</a></li>
		     <li><a href="#" onclick="javascript:fnGetCvsList(2)"> 2 </a></li> 
		     <li><a href="#" onclick="javascript:fnGetCvsList(3)"> 3 </a></li> 
		     <li><a href="#" onclick="javascript:fnGetCvsList(4)"> 4 </a></li> 
		     <li><a href="#" onclick="javascript:fnGetCvsList(5)"> 5 </a></li> 
		     <li><a href="#" onclick="javascript:fnGetCvsList(6)"> 6 </a></li> 
		   <li><a href="#"> <span><i class="fa fa-angle-double-right"></i></span> </a></li>
		</ul>
	</div>
	</section>
</article>
</body>
</html>
