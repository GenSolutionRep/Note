<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

	$(document).ready(function(){
		
		selectGroupMgrList();
		
		$("#test").click(function(){
			$.ajax({
				url : "/baseInfo/test",
			    data:{"param":$("#addr").val()},
			    type : "POST",
			    dataType : "json",
			    cache : false ,
			    success : function(result) {
					var html = "";
					
					if(result.length > 0){
						
					}else{
					}
					
			    }
			});
		});
		
	});
	
	//
	function selectStanMgrList(item_code){
		var html_text = "";
		
		$.ajax({
			url : "/baseInfo/selectItemMgrList",
		    data:{"param":group_code},
		    type : "POST",
		    dataType : "json",
		    cache : false ,
		    success : function(result) {
				var html = "";
				
				if(result.length > 0){
					for (var i=0; i <result.length; i++){
						html_text += '<tr onclick="selectItemMgrList('+result[i].ITEM_CODE+""+')">';
						html_text += '<td>' + result[i].ITEM_CODE + '</td>';
						html_text += '<td>' + result[i].ITEM_NM + '</td>';
						html_text += '<td>' + result[i].ITEM_SORT + '</td>';
						html_text += '<td>' + result[i].USE_YN + '</td>';
						html_text += '</tr>';
					}
				}else{
					
					html_text = '<tr><td colspan="4">조회된 데이터가 없습니다</td></tr>';
				}
				$("#dataList2").html(html_text);
				
		    }
		});
	}
	
	//품목조회함수
	function selectItemMgrList(group_code){
		
		var html_text = "";
		
		$.ajax({
			url : "/baseInfo/selectItemMgrList",
		    data:{"param":group_code},
		    type : "POST",
		    dataType : "json",
		    cache : false ,
		    success : function(result) {
				var html = "";
				
				if(result.length > 0){
					for (var i=0; i <result.length; i++){
						html_text += '<tr onclick="selectItemMgrList('+result[i].ITEM_CODE+""+')">';
						html_text += '<td>' + result[i].ITEM_CODE + '</td>';
						html_text += '<td>' + result[i].ITEM_NM + '</td>';
						html_text += '<td>' + result[i].ITEM_SORT + '</td>';
						html_text += '<td>' + result[i].USE_YN + '</td>';
						html_text += '</tr>';
					}
				}else{
					
					html_text = '<tr><td colspan="4">조회된 데이터가 없습니다</td></tr>';
				}
				$("#dataList2").html(html_text);
				
		    }
		});
	}
	
	//븐류항목조회함수
	function selectGroupMgrList(){
		
		var html_text = "";
		var param = "";
		$.ajax({
			url : "/baseInfo/selectGroupMgrList",
		    data:{"param":param},
		    type : "POST",
		    dataType : "json",
		    cache : false ,
		    success : function(result) {
				var html = "";
				
				if(result.length > 0){
					for (var i=0; i <result.length; i++){
						html_text += '<tr onclick="selectItemMgrList('+result[i].GROUP_CODE+""+')">';
						html_text += '<td>' + result[i].GROUP_CODE + '</td>';
						html_text += '<td>' + result[i].GROUP_NM + '</td>';
						html_text += '<td>' + result[i].GROUP_SORT + '</td>';
						html_text += '<td>' + result[i].USE_YN + '</td>';
						html_text += '</tr>';
					}
				}else{
					
					html_text = '<tr><td colspan="4">조회된 데이터가 없습니다</td></tr>';
				}
				$("#dataList1").html(html_text);
				
		    }
		});
	}

</script>
</head>
<body>
	<div style="float:left">
		<input type="text" id="addr" style="width:500px" value="대한민국 서울특별시 중구 세종대로 110"/>
		<div id="test">주소입력</div>
	</div>
	
	<div style="padding-top:40px">
		
		<div style="border:1px solid black;width:30%;float:left">
			<table>
				<thead>
					<tr>
						<th>코드</th>
						<th>분류</th>
						<th>순서</th>
						<th>사용</th>
					</tr>
				</thead>
				<tbody id="dataList1" >
				</tbody>
			</table>
		</div>
		<div style="width:30px;float:left"></div>
		<div style="border:1px solid black;width:30%;float:left;">
			<table>
				<thead>
					<tr>
						<th>코드</th>
						<th>품목명</th>
						<th>순서</th>
						<th>사용</th>
					</tr>
				</thead>
				<tbody id="dataList2" >
				</tbody>
			</table>
		</div>
		<div style="width:30px;float:left"></div>
		<div style="border:1px solid black;width:30%;float:left">
			<table>
				<thead>
					<tr>
						<th>코드</th>
						<th>규격</th>
						<th>순서</th>
						<th>사용</th>
						<th>관리</th>
					</tr>
				</thead>
				<tbody id="dataList2" >
				</tbody>
			</table>
		</div>
	</div>
	
</body>
</html>
