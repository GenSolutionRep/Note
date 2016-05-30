var $tabs;
var addTab_function = null;
var currMno = 1; // 현텝 메뉴 아이디
var currTabNm = "Home"; // 현텝 메뉴 아이디
var currTabUrl = "/"; // 현텝 메뉴 아이디
var check = true; //로그아웃
var tabsOnIndex = {}; //로그아웃
//For debugging - shortcut
function d(m){console.log(m);}

$(function(){
	//To get the random tabs label with variable length for testing the calculations			
	
	$tabs = $("#tabs").tabs({
      
    });
	
	function addTab(title, url){
		var tabNameExists = false;
		$('#tabs > ul li a').each(function(i) {
		    if ($(this).text() == title) {
		        tabNameExists = true;
		        $tabs.tabs('select',i);
		    }
		});
		if(!tabNameExists){
			$tabs.tabs('add',url, title);
		}
		//d($tabs);
		
		return false;
	};
	
	addTab_function = addTab;
	
	$('#removeTab').click(function(){
		$tabs.tabs('select',$tabs.tabs('length')-1);
		$tabs.tabs('remove',$tabs.tabs('length')-1);
		return false;
	});
});

function tabAdds(title, url, m_no){
	tabsOnIndex[title]={"m_no" :m_no,"url" :url,"title" :title};
	currMno = m_no;
	//currTabNm= title;
	//currTabUrl= url;
	addTab_function(title, url);
}