/**
 * @함수명: ready
 * @작성일: 2015. 09. 00
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	//팝업창등록(필수)
	$("#tempPop20100").instancePopUp();
	
	//input 달력
	$("#searchToDate20100").winCal(baseOptions);	
	$("#searchFromDate20100").winCal(baseOptions);
	
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
	
	
	
	$("#tempOpen20100").click(function(){
		$("#tempPop20100").show();
	});
	$("#tempClose20100, #tempCloseX20100").click(function(){
		$("#tempPop20100").hide();
	});
}

