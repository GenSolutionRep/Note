<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="../inc/common.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%-- <jsp:include page="../inc/header.jsp" flush='true' /> --%>
<script type="text/javascript" src="<%=jsPath%>/routine/main/main.js"></script>
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
		<div id="menuList" class="mtree-wrap" >
		  <ul  class="mtree transit">
		    <li><a href="#">근태관리</a>
		      <ul>
		        <li><a href="#">Algeria</a></li>
		        <li><a href="#">Marocco</a></li>
		        <li><a href="#">Libya</a></li>
		        <li><a href="#">Somalia</a></li>
		        <li><a href="#">Kenya</a></li>
		        <li><a href="#">Mauritania</a></li>
		        <li><a href="#">South Africa</a></li>
		      </ul>
		    </li>
		    <li><a href="#">진열현황</a>
		      <ul>
		        <li><a href="#">North-America</a>
		          <ul>
		            <li><a href="#">Canada</a></li>
		            <li><a href="#">USA</a>
		              <ul>
		                <li><a href="#">New York</a></li>
		                <li><a href="#">California</a>
		                  <ul>
		                    <li><a href="#">Los Angeles</a></li>
		                    <li><a href="#">San Diego</a></li>
		                    <li><a href="#">Sacramento</a></li>
		                    <li><a href="#">San Francisco</a></li>
		                    <li><a href="#">Bakersville</a></li>
		                  </ul>
		                </li>
		                <li><a href="#">Lousiana</a></li>
		                <li><a href="#">Texas</a></li>
		                <li><a href="#">Nevada</a></li>
		                <li><a href="#">Montana</a></li>
		                <li><a href="#">Virginia</a></li>
		              </ul>
		            </li>
		          </ul>
		        </li>
		        <li><a href="#">Middle-America</a>
		          <ul>
		            <li><a href="#">Mexico</a></li>
		            <li><a href="#">Honduras</a></li>
		            <li><a href="#">Guatemala</a></li>
		          </ul>
		        </li>
		        <li><a href="#">South-America</a>
		          <ul>
		            <li><a href="#">Brazil</a></li>
		            <li><a href="#">Argentina</a></li>
		            <li><a href="#">Uruguay</a></li>
		            <li><a href="#">Chile</a></li>
		          </ul>
		        </li>
		      </ul>
		    </li>
		    <li><a href="#">전자결재</a>
		      <ul>
		        <li><a href="#">China</a></li>
		        <li><a href="#">India</a></li>
		        <li><a href="#">Malaysia</a></li>
		        <li><a href="#">Thailand</a></li>
		        <li><a href="#">Vietnam</a></li>
		        <li><a href="#">Singapore</a></li>
		        <li><a href="#">Indonesia</a></li>
		        <li><a href="#">Mongolia</a></li>
		      </ul>
		    </li>
		    <li><a href="#">거래처관리</a>
		      <ul>
		        <li><a href="#">North</a></li>
		        <li><a href="#">East</a></li>
		        <li><a href="#">South</a></li>
		        <li><a href="#">West</a></li>
		      </ul>
		    </li>
		    <li><a href="#">사원관리</a>
		      <ul>
		        <li><a href="#">Australia</a></li>
		        <li><a href="#">New Zealand</a></li>
		      </ul>
		    </li>
		</ul>
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
