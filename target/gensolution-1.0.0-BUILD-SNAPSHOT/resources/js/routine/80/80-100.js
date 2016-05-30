/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#tempPop80100").instancePopUp();
	
	//input 달력
	$("#searchToDate80100").winCal();	
	$("#searchFromDate80100").winCal();
	
	// 이벤트 등록
	fnSetEventComponent();	
});




/**
 * @함수명: fnSetEventComponent
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnSetEventComponent(){
	
	
	
	$("#tempOpen80100").click(function(){
		$("#tempPop80100").show();
	});
	$("#tempClose80100, #tempCloseX80100").click(function(){
		$("#tempPop80100").hide();
	});
}

