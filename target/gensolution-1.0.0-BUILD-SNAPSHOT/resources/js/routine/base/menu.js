/**
 * @함수명: onLoad
 * @작성일: 2014. 09. 29.
 * @작성자: 
 * @설명: 로딩 및 Event
 * @param 
 */
$(document).ready(function(){
	
	fnMakeEventComponent();	// 버튼 이벤트 등록
//	fnGetTestList("파라메터 잘넘어갑니다...");
		
	$(".form_date").datetimepicker({
		language:  'ko',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$("#baseMenuList").sTree({
		url:"/menu/list",
		root:true,
		key:"m_no",
		val:"m_nm",
		className:"mtree",
		data:{"user_id":"123"},
		click:  function(btn){
			alert("1111111111");
			console.log("data3 : " + btn.code)
		}
    });
});

/**
 * @함수명: fnMake_EventComponent
 * @작성일: 2014. 09. 29.
 * @작성자: 김진호
 * @설명: 컴포넌트 이벤트 틍록
 * @param 
 */
function fnMakeEventComponent(){
	// 팝업창 닫기
	$("#closeBtn").click(function(){
	});
	
	// 저장 버튼
	$("#saveBtn").click(function(){
	});
}



