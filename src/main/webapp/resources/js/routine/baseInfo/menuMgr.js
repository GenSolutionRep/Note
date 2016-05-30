var selected_tree = '';
var selected_treeTitle = '';
var selected_depth = 1;
var selected_code = 0;
var treeView = null;

/**
 * @메서드명: fnGetFirstCode
 * @작성일: 2015.09.15.
 * @설명: 첫 번째 메뉴 일련번호 가져오기
 * @param fnCallbackFirstCode
 */
function fnGetFirstCode(fnCallbackFirstCode) {
	fnChkSession();

	$.ajax({
		url : "/MenuMgr/getFirstCode",
		type : "POST",
		dataType : "json",
		success : function(response) {
			fnCallbackFirstCode(response);
		}
	});
}

/**
 * @메서드명: fnCallbackFirstCode
 * @작성일: 2015.09.15.
 * @설명: fnGetFirstCode 함수에 넘길 콜백 함수
 * @param strResult
 */
function fnCallbackFirstCode(strResult) {
	loadMenuList(objFunc.trim(strResult));
}

$(document)
		.ready(
				function() {
					fnChkSession();

					fnGetFirstCode(fnCallbackFirstCode);

					$(".QTPopup").css('display', 'none'); // Set the popup
					// display none
					// default

					$("#btn_add").click(function() { // Launch the popup
						$('#popup_title').text('메뉴 등록');
						$(".QTPopup").animate({
							width : 'show'
						}, 'slow');
						$("#inputHiddenInputType").val('INSERT');
					});

					$(".btn-default").click(function() { // Close the popup
						$(".QTPopup").css('display', 'none');

						clearValue();
					});

					$("#inputHiddenInputType").val('');

					make_depth_select(1, 0, 0);
					setFormDisabled();

					$("#cc_auto").click(function() {
						setFormDisabled();
					});

					$('#m_seq')	.bind('keypress',
									function(e) {
										if (!(e.which != 8
												&& e.which != 0
												&& (e.which < 48 || e.which > 57) && e.which != 46))
											return true;
										else {
											alert('숫자만 입력하세요.');

											$('#m_seq').focus();

											return false;
										}
									});

					$('#m_order').bind('keypress',
									function(e) {
										if (!(e.which != 8
												&& e.which != 0
												&& (e.which < 48 || e.which > 57) && e.which != 46))
											return true;
										else {
											alert('숫자만 입력하세요.');

											$('#m_order').focus();

											return false;
										}
									});

					$("#btn_submit").click(
									function() {
										fnChkSession();

										var auto = $("#cc_auto")
												.attr("checked");

										if (auto != 'checked'
												&& $("#m_seq").val() == '') {
											alert('자동입력 체크를 하거나 일련번호를 입력하세요.');

											$("#m_seq").focus();

											return;
										}

										if (auto != 'checked'
												&& $("#m_seq").val().length > 11) {
											alert('11자리 이내 숫자만 입력하세요.');

											$("#m_seq").focus();

											return;
										}

										if ($.trim($("#m_name").val()) == '') {
											alert('메뉴명을 입력하세요.');

											$("#m_name").focus();

											return;
										}

										if ($.trim($("#m_url").val()) == '') {
											alert('메뉴URL을 입력하세요.');

											$("#m_url").focus();

											return;
										}

										if (auto == 'checked')
											$("#p_cc_auto").val('1');
										else
											$("#p_cc_auto").val('0');

										$(".QTPopup").css('display', 'none');

										var params = "&m_seq="
												+ $("#m_seq").val()
												+ "&m_name="
												+ encodeURI($("#m_name").val())
												+ "&p_cc_auto="
												+ $("#p_cc_auto").val()
												+ "&inputHiddenInputType="
												+ $("#inputHiddenInputType")
														.val()
												+ "&m_url="
												+ encodeURI($("#m_url").val())
												+ "&m_note="
												+ encodeURI($("#m_note").val())
												+ "&m_parent_seq1="
												+ $("#m_parent_seq1").val()
												+ "&m_depth="
												+ $("#m_depth").val()
												+ "&m_is_use="
												+ $(
														"input[name=m_is_use]:radio:checked")
														.val() + "&m_order="
												+ $("#m_order").val();

										$.ajax({
													url : "/Menu?cmd=update"
															+ params,
													type : "POST",
													dataType : "text",
													cache : false,
													beforeSend : function(xhr) {
														xhr
																.overrideMimeType("text/plain; charset=utf-8");
													},
													success : function(response) {
														if (response == 1) {
															alert('입력하신 메뉴가 저장되었습니다.');

															loadMenuList(selected_tree);
															treeReload();
														} else
															alert('메뉴 저장 실패!!');

														clearValue();
													}
												});
									});

					$("#tree").fancytree(
							{
								source : {
									url : "/MenuMgr?cmd=select_tree&m_seq="
								},
								lazyLoad : function(event, data) {
									data.result = $.ajax({
										url : "/MenuMgr?cmd=select_tree&m_seq="+ data.node.key,
										dataType : "json"
									});
								},
								click : function(event, data) {

									selected_treeTitle = data.node.title;
									selected_tree = data.node.key;

									loadMenuList(selected_tree);

									selectde_depth = data.node.getLevel();
									selected_code = data.node.key;
									selectde_depth = selectde_depth > 2 ? 2
											: selectde_depth;

									make_depth_select(selectde_depth - 1,
											selected_code, 0);
								}
							// ,checkbox:true
							// ,selectMode: 3

							});

				});

/**
 * @함수명: treeReload
 * @작성일: 2015.09.15.
 * @설명: 트리뷰 리로드
 */
function treeReload() {
	treeView = $("#tree").fancytree("getTree");

	treeView.reload();
}

/**
 * @함수명: loadMenuList
 * @작성일: 2015.09.15.
 * @설명: 메뉴 목록
 * @param seq
 */
function loadMenuList(seq) {
	fnChkSession();

	var progress = '<tr>'
			+ '<td class="clsTextAlignCenter" colspan="7" style="height: 300px;">'
			+ '<img src="/images/common/loader.gif" alt="로딩중" style="margin-top: 60px;"/>'
			+ '</td>' + '</tr>';

	$("#tbodyList").html(progress);

	var listHtml ="";
	$.ajax({
		//url : "/Menu?cmd=select_list&m_seq=" + seq + "&page=&listRow=",
		url : "/MenuMgr/select_list",
		data:{"m_seq":seq},
		type : "POST",
		dataType : "json",
		success : function(data) {
			if( data.result.length==0 ){
				listHtml += "<tr>";
	            listHtml += "  <td class='clsTextAlignCenter' colspan='7' style='height: 426px;'>";
	            listHtml += "    <div><img src='/images/common/exclamation.png' alt='' ></div>";
	            listHtml += "    <h2>내용이 없습니다.</h2>";
	            listHtml += "  </td>";
	            listHtml += "</tr>";
			}else{
				for(var i=0; i<data.result.length; i++){
					var res = data.result[i];
	        		if(result.m_depth==1)
	        			listHtml += "<input type='hidden' id='firstCode' value='" + res.m_no + "'/>";
	        		else if (result.m_depth=="2")
	        			listHtml += "<input type='hidden' id='firstCode' value='" + res.m_parent_no + "'/>";
	        		else if (result.m_depth=="3")
	        			listHtml += "<input type='hidden' id='firstCode' value='" + res.m_parent_no + "'/>";
	        		
	        		listHtml += "<td>" + m_no + "</td>";
	    			listHtml += "<td>" + m_nm + "</td>";
	    			listHtml += "<td>" + m_url + "</td>";
	    			listHtml += "<td>" + m_order + "</td>";
	    			listHtml += "<td>" + m_use_yn + "</td>";
	    			listHtml += "<td>";
	    			listHtml += "<button type='button' onclick=\"codeUpdate('"+res.m_no+"','" + res.m_parent_no+ "','" + cc_child_cnt + "')\"; <span class='glyphicon glyphicon-edit'></span> 수정</button>)&nbsp;&nbsp;";
	                listHtml += "<button type='button' onclick=\"deleteMenu('"+res.m_no+"','"+ res.m_nm + "')\"; id='btn_del' class='btn btn-default btn-xs'><span class='glyphicon glyphicon-remove'></span> 삭제</button> ";
	    			listHtml += "</td>";
	        	}
			}
			$("#tbodyList").html(listHtml);
		}
	});
}

/**
 * @함수명: clearValue
 * @작성일: 2015.09.15.
 * @설명: 메뉴 수정 팝업 리셋
 */
function clearValue() {
	$("#m_seq").val('');
	$("#m_name").val('');
	$("#m_note").val('');
	$("#m_url").val('');
	$("#m_order").val('');

	make_depth_select(1, '0');
}

/**
 * @함수명: setFormDisabled
 * @작성일: 2015.09.15.
 * @설명: 메뉴 수정 시 일련번호 수정 못하게 input disabled
 * @param
 * @param
 */
function setFormDisabled() {
	if ($("#cc_auto").attr("checked"))
		$("#m_seq").attr('disabled', 'disabled');
	else
		$("#m_seq").removeAttr('disabled');
}

/**
 * @함수명: make_depth_select
 * @작성일: 2015.09.15.
 * @설명: 메뉴 팝업 부모 일련번호 select 만들기
 * @param depth
 * @param seq
 * @param parent_seq
 * @param child_cnt
 */
function make_depth_select(depth, seq, parent_seq, child_cnt) {
	fnChkSession();

	parent_seq = typeof parent_seq !== 'undefined' ? parent_seq : '0';
	child_cnt = typeof child_cnt !== 'undefined' ? child_cnt : '0';

	if (depth > 1 && seq == 0) {
		$("#m_depth").val((depth + 1) + '');
		// $("#cc_parent_code3").val('0');

		return;
	}

	if (seq == 0) {/*
					 * var div = $("#code_select2");
					 * 
					 * div.html('');
					 * 
					 * div = $("#code_select3");
					 * 
					 * div.html('');
					 */

		depth = 1;
	}

	$("#m_depth").val((depth + 1) + '');

	var content = null;

	try {
		$.ajax({
			/*url : "/Menu?cmd=select_depth&m_depth_level=" + depth + "&m_seq="
					+ seq + "&cc_child_cnt=" + child_cnt,*/
			url : "/MenuMgr/select_depth",
			data:{"m_depth_level":depth, "m_seq":seq, "cc_child_cnt":child_cnt},
			type : "POST",
			dataType : "json",
			success : function(response) {
				if (seq == 0) {
					content = $("#code_select1");

					content.html(response);
				}
				/*
				 * else if (depth == 1 && code > 0) { content =
				 * $("#code_select2");
				 * 
				 * content.html(response); } else if (depth == 2 && code > 0) {
				 * content = $("#code_select3");
				 * 
				 * content.html(response); }
				 */

				if (child_cnt > 0) {
					$("#m_parent_seq").attr("disabled", true);
					$("#cc_parent_seq2").attr("disabled", true);
				} else {
					$("#m_parent_seq").attr("disabled", false);
					$("#cc_parent_seq2").attr("disabled", false);
				}
			}
		});
	} catch (e) {
		// alert(e.message);
	}
}

/**
 * @함수명: updateMenu
 * @작성일: 2015.09.15.
 * @설명: 메뉴 등록/수정
 * @param seq
 * @param parent_seq
 * @param child_cnt
 */
function updateMenu(seq, parent_seq, child_cnt) {
	fnChkSession();

	$("#inputHiddenInputType").val('UPDATE');

	updateSeq = seq;

	$('#popup_title').text('공통 일련번호 수정');

	var firstCode = $("#firstCode").val();

	$.ajax({
		//url : "/MenuMgr?cmd=select_row&m_seq=" + seq,
		url : "/MenuMgr/select_row",
		data:{"m_seq":seq},
		type : "POST",
		dataType : "json",
		
		success : function(response) {
			$("#m_seq").val(response.m_seq);
			$("#m_name").val(response.m_name);
			$("#m_url").val(response.m_url);
			$("#m_note").val(response.m_note);
			$("#m_order").val(response.m_order);
			$(
					"input:radio[name=m_is_use]:input[value="
							+ response.m_is_use + "]").attr("checked", true);

			var depth = response.m_depth;

			if (depth == 2) {
				$("#m_parent_seq").val(firstCode);

				make_depth_select(1, firstCode, 0, child_cnt);
			} else if (depth == 3) {
				$("#m_parent_seq").val(firstCode);

				make_depth_select(1, firstCode, parent_seq, child_cnt);
			}

			if (child_cnt > 0)
				$("#m_parent_seq").attr("disabled", true);
			else
				$("#m_parent_seq").attr("disabled", false);

			$(".QTPopup").animate({
				width : 'show'
			}, 'slow');
		},
	});
}

/**
 * @함수명: deleteMenu
 * @작성일: 2015.09.15.
 * @설명: 메뉴 삭제 팝업
 * @param seq
 * @param mame
 */
function deleteMenu(seq, mame) {
	fnChkSession();

	$("#inputHiddenInputType").val('DELETE');
	$('#del_seq').html(seq);
	$('#del_name').html(mame);

	$.ajax({
		//url : "/MenuMgr/checkDelete&m_seq=" + seq,
		url : "/MenuMgr/checkDelete",
		data:{"m_seq":seq},
		type : "POST",
		dataType : "json",
		success : function(response) {
			if (response.resultCode == -1)
				alert(response.message);
			else if (response.resultCode == 0)
				$('#btn_delete_popup').click();
			else if (response.resultCode > 0)
				alert(response.message);
		}
	});
}

/**
 * @함수명: deleteMenuComplete
 * @작성일: 2015.09.15.
 * @설명: 메뉴 삭제
 */
function deleteMenuComplete() {
	fnChkSession();

	var seq = $('#del_seq').text();

	$.ajax({
				/*url : "/MenuMgr?cmd=update&m_seq="
						+ seq
						+ "&inputHiddenInputType=DELETE&p_cc_auto=0&m_parent_seq1=0&m_depth=0",*/
				url : "/MenuMgr/update",
				data : {"m_seq": seq, 
						"inputHiddenInputType": "DELETE", 
						"p_cc_auto": "0", 
						"m_parent_seq1": "0", 
						"m_depth": "0"},
				type : "POST",
				dataType : "text",
				success : function(response) {
					if (response == 1) {
						alert('메뉴 삭제 성공!!');

						loadMenuList(selected_tree);
						treeReload();
					} else
						alert('메뉴 삭제 실패!!');
				}
			});
}

/**
 * @함수명: btnEnter
 * @작성일: 2015.09.15.
 * @설명: 메뉴 저장 버튼 Enter
 * @param element
 */
function btnEnter(element) {
	element.classList.add('ui-state-hover');
}

/**
 * @함수명: btnLeave
 * @작성일: 2015.09.15.
 * @설명: 메뉴 등록 버튼 Leave
 * @param element
 */
function btnLeave(element) {
	element.classList.remove('ui-state-hover');
}

/**
 * @함수명: codeUpdate
 * @작성일: 2015.09.15.
 * @설명: 메뉴 등록 또는 수정 Submit
 * @param seq
 * @param parent_seq
 */
function codeUpdate(seq, parent_seq, child_cnt) {
	fnChkSession();

	$("#inputHiddenInputType").val('UPDATE');

	updateSeq = seq;

	$('#popup_title').text('공통 일련번호 수정');

	var firstCode = $("#firstCode").val();

	$.ajax({
		//url : "/MenuMgr?cmd=select_row&m_seq=" + seq,
		url : "/MenuMgr/select_row",
		data : {"m_seq" : seq},
		type : "POST",
		dataType : "json",
		success : function(response) {

			$("#m_seq").val(response.m_seq);
			$("#m_name").val(response.m_name);
			$("#m_url").val(response.m_url);
			$("#m_note").val(response.m_note);
			$("#m_order").val(response.m_order);
			$(
					"input:radio[name=m_is_use]:input[value="
							+ response.m_is_use + "]").attr("checked", true);

			var depth = response.m_depth;

			if (depth == 2) {
				$("#m_parent_seq1").val(firstCode);

				make_depth_select(1, firstCode, 0, child_cnt);
			} else if (depth == 3) {
				$("#m_parent_seq1").val(firstCode);

				make_depth_select(1, firstCode, parent_seq, child_cnt);
			}

			if (child_cnt > 0) {
				$("#m_parent_seq1").attr("disabled", true);
			} else {
				$("#m_parent_seq1").attr("disabled", false);
			}

			$(".QTPopup").animate({
				width : 'show'
			}, 'slow');
		}
	});
}