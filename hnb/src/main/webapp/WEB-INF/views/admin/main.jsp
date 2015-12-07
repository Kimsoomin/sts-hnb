<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date"/>
<div id="header"></div>
<div class="list-group" id="main_left">
  <a href="#" class="list-group-item active">
    관리자 기능
  </a>
  <a href="#" class="list-group-item" id="admin_home">홈</a>
  <a href="#" class="list-group-item" id="mgmt_member">회원관리</a>
  <a href="#" class="list-group-item" id="mgmt_prod">제품관리</a>
  <a href="#" class="list-group-item" id="mgmt_emp">사원관리</a>
  <a href="#" class="list-group-item" id="mgmt_stat">통계보기</a>
</div>
<div id="main_right">
	<h1>관리자 홈입니다</h1>
</div>
<script>
$(function() {
	$('#header').load('${context}/admin.do?page=header');
	$('#main_left').css("float","left").css('width','300px').css('text-align','center');
	$('#main_right').css("float","left").css("margin-left","150px").css('width','50%');
	$('#tab_member').css('width','100%');
	$('#mgmt_member').click(function() {
		Admin.memberList();
	});
});	
 $('#btn_admin_table').click(function() {
    $('#btn_admin_table').submit();
  });
 var Admin = {
	memberList : function() {
		 $.getJSON('${context}/admin.do?page=member_list', function(data) {
			 var table = '<h1>회원목록</h1>'
					+'<table id="tab_member"><tr><th>아이디</th><th>이름</th><th>성별</th>'
					+'<th>생년원일</th><th>전화번호</th><th>이메일</th></tr>';
					$.each(data, function() {
						table +='<tr><td>'+this.userid+'</td>'
							+'<td>'+this.name+'</td><td>'+this.gender+'</td>'
							+'<td>'+this.birth+'</td><td>'+this.phone+'</td>'
							+'<td>'+this.email+'</td></tr>';
					});
					table += '</table>';
					
					$(table).appendTo($('#main_right').empty());
		});
	},
	memberNotExist : function() {
		var table ='<h1>회원목록</h1><table id="tab_member"><tr><th>아이디</th>';
			table += '<th>이름</th><th>성별</th><th>생년원일</th><th>전화번호</th><th>이메일</th></tr>';
			table += '<tr><td colspan="6"><h2>회원목록이 없습니다.</h2></td></tr></table>';
			$(table).appendTo($('#main_right').empty());
	}
 };
 
</script>