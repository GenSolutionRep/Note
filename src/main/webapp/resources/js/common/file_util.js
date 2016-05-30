

Number.prototype.fileSize = function() {
	
	var size = 0;
	var unit = "Byte";
	if(this < 999){
		size = this;
	}else if(this < 999999){ 
		size = this/1024; //KB;
		if((size%1) != 0){
			size = size.toFixed(1);
		}
		unit = " KB";
	}else if(this < 999999999){
		size = this/(1024*1024); //MB;
		if((size%1) != 0 ){
			if( size < 100){
				size = size.toFixed(1);
			}else{
				size = size.toFixed(0);				
			};
		}
		unit = " MB";
	}else if(this < 999999999999){
		size = this/(1024*1024*1024);  //GB;		
		if((size%1) != 0){
			size = size.toFixed(2);
		}
		unit = " GB";
	}else if(this < 999999999999999){
		size = this/(1024*1024*1024*1024);		//TB;
		if((size%1) != 0){
			size = size.toFixed(2);
		}
		unit = " TB";
	}
    return size+unit;
};



var fileId = 1;
var op = {};
$.fn.extend({
	file: function (options) {
        var defaults = {
    		fileList : [],
            callback : null,
            id : "",
            browserCheck : false,
            maxcnt : 10,
            maxsize : 20,
            tooltip : "",
        };
        var html = "";
        $(this).addClass("tx-attach-div");
        var id = $(this).attr("id");
        html +='<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">';
        html +='<div class="tx-attach-box-inner">';
        html +='<div class="tx-attach-main">';
        html +='<ul class="tx-attach-top">';
        html +='<li id="'+id+'List" class="tx-attach-add file-zone">';
        html +='<input type="file" multiple  name="files"><a>파일등록</a>';
        html +='</li>';
        html +='<li style="display: none;" id="tx_attach_delete" class="tx-attach-delete"><a>전체삭제</a></li>';
        html +='<li id="tx_attach_size" class="tx-attach-size">';
        html +='</li>';
        html +='<li id="tx_attach_tools" class="tx-attach-tools">';
        html +='</li>';
        html +='</ul>';
        html +='<ul id="'+id+'ItemList" class="tx-attach-list">';
        html +='</ul>';
        html +='</div>';
        html +='</div>';
        html +='</div>';
        $(this).html(html);
        op =  $.extend(defaults, options);
        op.id = id;
        var el = jQuery(this);
        return this.each(function() {
            var files = [];
            var $this = $(this).find("#"+id+"ItemList");
            
            var input = $("#"+id+"List").children("input[type='file']");
            input.change(function() {
            	
            	var infiles = $(this)[0].files;
            	
            	if(el.checkMaxCnt($this, infiles.length)){
            		for (var i = 0; i < infiles.length; i++) {
//            			op.fileList.push(infiles[i]);
//            			op.fileList.push({
//            				"savefile":files[i],
//            				"id": fileId 
//            			});
            			el.fileAppend(infiles[i],$this);
            		}
            		el.viewTooltop($this);
            	}
            	input.val("");
            });
            $this.bind('dragover', function(event) {
            	$this.addClass("dnd");
            	event.stopPropagation();
            	event.preventDefault();
            });
            $this.bind('dragleave', function(event) {
            	$this.removeClass("dnd");
                event.stopPropagation();
                event.preventDefault();
            });
            
            $this.bind('drop', function(event) {
            	$this.removeClass("dnd");
                event.stopPropagation();
                event.preventDefault();
                files = event.originalEvent.target.files || event.originalEvent.dataTransfer.files;
                
                if(el.checkMaxCnt($this ,files.length)){
                	for (var i = 0; i < files.length; i++) {
//                		op.fileList.push(files[i]);
//                		op.fileList.push({
//                			"savefile":files[i],
//                			"id": fileId+"" 
//                		});
                		el.fileAppend(files[i],$this);
                		if(op.callback) {
                			op.callback(files[i]);
                		}
                	}
                	el.viewTooltop($this);
                }
                	
                
                return false;
            });
        });
        
        
    },
    checkMaxCnt:function(target ,addcnt){
    	var el = jQuery(this);
    	var curcnt = op.fileList.length ;
    	var maxcnt = op.maxcnt;
    	
    	op.tooltip = $("<span>파일 "+ (addcnt)+"개 등록</span>");
        
    	
        if(maxcnt < (curcnt + addcnt)){
        	op.tooltip = $("<span>"+ maxcnt+"개 이하파일만 등록가능합니다</span>");
        	el.viewTooltop(target);
        	return false;
        }else{
        	return true;
        }
    },
    viewTooltop:function(target){
    	
    	var divView = $("<div class='file-tip' />");
    	divView.append(op.tooltip);
    	divView.delay( 1000 ).fadeOut( "slow", function() {
    		divView.remove();
    	});
    	target.append(divView);
    	var left = (target.width() / 2) - (divView.width()/2)
    	divView.css("left",left+"px");
    	
    },
    checkingFileSize:function(file,target){
    	var el = jQuery(this);
    	var checkSize = 1024 * 1024 * (op.maxsize) ; //10MB
    	if(file.size  > checkSize){
//    		alert((op.maxsize)+"M 이하의 파일만 등록 가능합니다.");
    		op.tooltip = $("<span>"+ (op.maxsize)+"M 이하의 파일만 등록 가능합니다.</span>");
    		el.viewTooltop(target);
    		return false;
    	}else if(file.name.length  > 50){
    		op.tooltip = $("<span>파일명이 50자 이하의 파일만 등록 가능합니다. </span>");
    		el.viewTooltop(target);
    		return false;
    	}else{
    	    return true;
    	}
    },
    browserCheck:function(){
    	var rv = 10; // Return value assumes failure.    
    	var agt = navigator.userAgent.toLowerCase();
    	if (agt.indexOf("msie") != -1){
    	    if (navigator.appName == 'Microsoft Internet Explorer') {        
    	         var ua = navigator.userAgent;        
    	         var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");        
    	         if (re.exec(ua) != null)            
    	             rv = parseFloat(RegExp.$1);    
    	        }    
    		
    	}
//    	if(!browserCheck) rv= 10;
    	return rv <= 9 ? false : true;
    },
    fileAppend:function(addfile,target){
    	var el = jQuery(this);
    	var fileNm ="";
    	if(el.browserCheck()){
    		fileNm = addfile.name;
    		if(el.checkingFileSize(addfile ,target)){
    			op.fileList.push(addfile);
    			var fsize = addfile.size;
    			var html = '<li class="add-file-li type-image tx-existed file-group'+fileId+'" >';
    			html += '<dl>';
    			html += '<dt class="tx-name"><i class="fa ext" data-ext="'+fileNm+'" ></i>';
    			html += fileNm+' ('+(fsize.fileSize())+')';
    			html += '</dt><dd class="tx-button">';
    			html += '<a class="tx-delete" data-file-id="'+fileId+'"  >삭제</a>';
    			html += '</dd>';
    			html += '</dl>';
    			html += '</li>';
    			
    			target.append(html);
    			fileId++;
    		}
    		$(".tx-delete").off().click(function(){
    			var groupId = $(this).data("file-id");
    			var index =$("li.add-file-li").index($(".file-group"+groupId));
    			op.fileList.splice(index, 1);
    			$(".file-group"+groupId).remove();
    		});
    		
    	}
    }

});




//$.fn.extend({
//	filess: function (options) {
//      var defaults = {
//  		fileList : [],
//          callback : null,
//          maxcnt : 10
//      };
//      var html = "";
//      $(this).addClass("tx-attach-div");
//      var id = $(this).attr("id");
//      html +='<input type="hidden" id="'+id+'fSeq" value="0"  >';
//      html +='<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">';
//      html +='<div class="tx-attach-box-inner">';
//      html +='<div class="tx-attach-main">';
//      html +='<ul class="tx-attach-top">';
//      html +='<li id="'+id+'List" class="tx-attach-add file-zone">';
//      html +='<input type="file" multiple  name="files" onchange="fileAppendMulti(this);"><a>파일등록</a>';
//      html +='</li>';
//      html +='<li style="display: none;" id="tx_attach_delete" class="tx-attach-delete"><a>전체삭제</a></li>';
//      html +='<li id="tx_attach_size" class="tx-attach-size">';
//      html +='</li>';
//      html +='<li id="tx_attach_tools" class="tx-attach-tools">';
//      html +='</li>';
//      html +='</ul>';
//      html +='<ul id="'+id+'InfoList" class="tx-attach-list">';
//      html +='</ul>';
//      html +='</div>';
//      html +='</div>';
//      html +='</div>';
//      $(this).html(html);
//      options =  $.extend(defaults, options);
//      return this.each(function() {
//          var files = [];
//          var $this = $(this).find("#"+id+"InfoList");
//
//          $this.bind('dragover', function(event) {
//          	$this.addClass("dnd");
//          	event.stopPropagation();
//          	event.preventDefault();
//          });
//          $this.bind('dragleave', function(event) {
//          	$this.removeClass("dnd");
//              event.stopPropagation();
//              event.preventDefault();
//          });
//          var maxcnt = options.maxcnt;
//          $this.bind('drop', function(event) {
//          	$this.removeClass("dnd");
//              event.stopPropagation();
//              event.preventDefault();
//              files = event.originalEvent.target.files || event.originalEvent.dataTransfer.files;
//              var divView = $("<div class='file-tip' />");
//              var divViewText = $("<span>파일 "+ (files.length)+"개 등록</span>");
//              if(maxcnt < files.length){
//              	divViewText = $("<span>"+ maxcnt+"개 이하파일만 등록가능합니다</span>");
//              	
//              }else{
//              	options.fileList.push({
//              		"file":files[i],
//              		"id": fileId 
//              	});
//              	if(options.callback) {
//              		for (var i = 0; i < files.length; i++) {
//              			fileAppend(files[i],$this);
//              		}
//              	}
//              }
//      		divView.append(divViewText);
//      		divView.delay( 1000 ).fadeOut( "slow", function() {
//      			divView.remove();
//      		});
//      		$this.append(divView);
//              return false;
//          });
//      });
//      
//      
//  }
//});




//var fileArray = new Array();
//var fileId= 1;
//
//function fileAppend(addfile,target){
//	var fileNm ="";
//	if(browserCheck()){
//		fileNm = addfile.name;
//		if(checkingFileSize(addfile)){
//			var fsize = (addfile.size)/1024;
//			var html = '<li class="add-file-li type-image tx-existed file-group'+fileId+'"" >';
//			html += '<dl>';
//			html += '<dt class="tx-name"><i class="fa ext" data-ext="'+fileNm+'" ></i>';
//			html += fileNm+' ('+fsize.fileSize()+')';
//			html += '</dt><dd class="tx-button">';
//			html += '<a class="tx-delete" data-file-id="'+fileId+'"  >삭제</a>';
//			html += '</dd>';
//			html += '</dl>';
//			html += '</li>';
//			
//			target.append(html);
//			fileId++;
//		}
//		$(".tx-delete").click(function(){
//			var groupId = $(this).data("file-id");
//			for (var f = 0; f < fileArray.length; f++) {
//				if(fileArray[f].id == groupId){
//					fileArray.splice(f, 1);
//					$(".file-group"+groupId).remove();
//					break;
//				}
//			}
//		});
//		
//	}
//}

//function fileAppendMulti(addfile){
//	
//	var fileNm ="";
//	if(browserCheck()){
//		for (var i = 0; i < addfile.files.length; i++) {
//			fileNm = addfile.files[i].name;
//			if(checkingFileSize(addfile.files[i])){
//				fileArray.push({
//					"file":addfile.files[i],
//					"id": fileId 
//				});
//				var fsize = (addfile.files[i].size)/1024;
//				var html = '<li class="add-file-li type-image tx-existed insa-stud-file-group'+fileId+'"" >';
//				html += '<dl>';
//				html += '<dt class="tx-name"><i class="fa ext" data-ext="'+fileNm+'" ></i>';
//				html += fileNm+' ('+fsize.toFixed(1)+'KB)';
//				html += '</dt><dd class="tx-button">';
//				html += '<a class="tx-delete" data-file-id="'+fileId+'"  >삭제</a>';
//				html += '</dd>';
//				html += '</dl>';
//				html += '</li>';
//				
//				$("#insaStudfileInfoList").append(html);
//				fileId++;
//			}
//		}
//		$(".tx-delete").click(function(){
//			var groupId = $(this).data("file-id");
//			for (var f = 0; f < fileArray.length; f++) {
//				if(fileArray[f].id == groupId){
//					fileArray.splice(f, 1);
//					$(".insa-stud-file-group"+groupId).remove();
//					break;
//				}
//			}
//			
//		});
//	}
//}










/*
$.fn.extend({
	file: function (options) {
		var defaults = {
				callback : null,
				maxcnt : 10
		};
		var html = "";
		$(this)
		html +='<div class="tx-attach-div">';
		html +='<input type="hidden" id="insaStudfSeq" value="0"  >';
		html +='<div id="tx_attach_box" class="tx-attach-box" style="margin-left: 0px;">';
		html +='<div class="tx-attach-box-inner">';
		html +='<div class="tx-attach-main">';
		html +='<ul class="tx-attach-top">';
		html +='<li id="insaStudfileList" class="tx-attach-add file-zone">';
		html +='<input type="file" multiple class="insa-stud-file-group1"  name="files" onchange="insaStudAddFile(this);"><a>파일등록</a>';
		html +='</li>';
		html +='<li style="display: none;" id="tx_attach_delete" class="tx-attach-delete"><a>전체삭제</a></li>';
		html +='<li id="tx_attach_size" class="tx-attach-size">';
		html +='</li>';
		html +='<li id="tx_attach_tools" class="tx-attach-tools">';
		html +='</li>';
		html +='</ul>';
		html +='<ul id="insaStudfileInfoList" class="tx-attach-list">';
		html +='</ul>';
		html +='</div>';
		html +='</div>';
		html +='</div>';
		html +='</div>';
		options =  $.extend(defaults, options);
		return this.each(function() {
			var files = [];
			var $this = $(this);
			
			$this.bind('dragover', function(event) {
//          	$this.css("background-color","#ccc");
				$this.addClass("dnd");
				event.stopPropagation();
				event.preventDefault();
			});
			$this.bind('dragleave', function(event) {
				$this.removeClass("dnd");
				event.stopPropagation();
				event.preventDefault();
			});
			var maxcnt = options.maxcnt;
			$this.bind('drop', function(event) {
				$this.removeClass("dnd");
				event.stopPropagation();
				event.preventDefault();
				files = event.originalEvent.target.files || event.originalEvent.dataTransfer.files;
				var divView = $("<div class='file-tip' />");
				var divViewText = $("<span>파일 "+ (files.length)+"개 등록</span>");
				if(maxcnt < files.length){
					divViewText = $("<span>"+ maxcnt+"개 이하파일만 등록가능합니다</span>");
					
				}else{
					if(options.callback) {
						for (var i = 0; i < files.length; i++) {
							options.callback(files[i]);
						}
					}
				}
				
//      		divView.css({
//      			 "position": "absolute"
//      			,"height": "20px"
//      			,"background-color": "#fff"
//      			,"border": "1px solid #ccc"
//      			,"padding": "2px"
//      			,"left": "-60px"
//      			,"top": "6px"
//      			,"border-radius": "5px"
//      			,"z-index": "99999"
//      		});
				divView.append(divViewText);
				divView.delay( 1000 ).fadeOut( "slow", function() {
					divView.remove();
				});
				$this.append(divView);
				return false;
			});
		});
		
		
	}
});
*/
