<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<script type="text/javascript" src="<%=jsPath%>/routine/base/menu.js"></script>
<style type="text/css">
.mtree-wrap {
/*   background: #EEE; */
  border-radius: 3px;
  height: 700px;
  overflow-y: scroll;
  width: 100%;
  padding-top: 0;
  
}
td{
text-align: left;
vertical-align: top;
  padding: 10px;

}
</style>
</head>
<body>

<table style="width: 100%;" >
<colgroup>
	<col width="30%" >
	<col width="70%" >
</colgroup>
<tr>
	<td>
		<div id="baseMenuList" class="mtree-wrap" >
		</div>
	</td>
	<td>
		<div>
			<input class="form_date" type="text" data-date-format="yyyy-mm-dd" readonly placeholder="날짜입력" >
		</div>
		<table>
			<thead>
				<tr>
					<th>필드1</th>
					<th>필드2</th>
					<th>필드3</th>
					<th>필드4</th>
					<th>필드5</th>
					<th>필드6</th>
					<th>필드7</th>
					<th>필드8</th>
				</tr>
			</thead>
			<tbody id="testTbody" >
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
				<tr>
					<td>1</td>
					<td>2</td>
					<td>3</td>
					<td>4</td>
					<td>5</td>
					<td>6</td>
					<td>7</td>
					<td>8</td>
				</tr>
			</tbody>
		</table>
	</td>
</tr>
</table>
</body>
</html>
