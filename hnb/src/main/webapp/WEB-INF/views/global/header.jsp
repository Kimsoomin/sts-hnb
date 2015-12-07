<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css" >
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="${context}/css/common.css" /> 
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${context}/main.do">
      	<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
      </a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Link <span class="sr-only">(current)</span></a></li>
        <li><a href="#">Link</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Javascript <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${context}/js/main.do?page=hello">Javascript 소개</a></li>
			<li><a href="${context}/js/main.do?page=bom">BOM</a></li>
			<li><a href="${context}/js/main.do?page=dom">DOM</a></li>
			<li><a href="${context}/js/main.do?page=form_tag">폼태그</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">jQuery <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="${context}/jquery/main.do?page=hello">JQuery 소개</a></li>
			<li><a href="${context}/jquery/main.do?page=form_tag">폼태그</a></li>
			<li><a href="${context}/jquery/main.do?page=selector">셀렉터</a></li>
			<li><a href="${context}/jquery/main.do?page=traversing">조회(트래버싱)</a></li>
			<li><a href="${context}/jquery/main.do?page=attr">어트리뷰트</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="${context}/jquery/main.do?page=core">코어</a></li>
			<li><a href="${context}/jquery/main.do?page=css">CSS</a></li>
			<li><a href="${context}/jquery/main.do?page=effect">이펙트</a></li>
			<li><a href="${context}/jquery/main.do?page=data">Data</a></li>
			<li><a href="${context}/jquery/main.do?page=event">이벤트</a></li>
			<li role="separator" class="divider"></li>
			<li><a href="${context}/jquery/main.do?page=utility">유틸리티</a></li>
			<li><a href="${context}/jquery/main.do?page=ajax">AJAX</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Search">
        </div>
        <button type="submit" class="btn btn-default">검 색</button>
      </form>
<c:if test="${empty sessionScope.member}">
    <!-- ..로그아웃 상태 시 표시할 태그 -->
    <ul class="nav navbar-nav navbar-right">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
          role="button" aria-haspopup="true" aria-expanded="false">회 원 <span class="caret">
          </span></a>
          <ul class="dropdown-menu">
            <li><a href="#" id="login">로그인</a></li>
            <li><a href="${context}/member.do?page=join">회원가입</a></li>
            <li><a href="#" id="admin">관리자</a></li>
          </ul>
        </li>
      </ul>
</c:if>
 
<c:if test="${not empty sessionScope.member}">
<!-- ..로그인 상태 시 표시할 태그 -->
   <ul class="nav navbar-nav navbar-right" id="mypage">
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" 
          role="button" aria-haspopup="true" aria-expanded="false"><c:out value="${member.name}"/> <span class="caret">
          </span></a>
          <ul class="dropdown-menu">
            <li><a href="${context}/member.do?page=logout" id="logout">로그아웃</a></li>
            <li><a href="${context}/member.do?page=mypage&userid=${member.userid}" id="mypage">마이페이지</a></li>
          </ul>
        </li>
      </ul>
</c:if>
      
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<script type="text/javascript">
	$(function() {
		$('#login').click(function() {
			bom.popup();
		});
		$('#admin').click(function() {
			admin.checkAdmin();
		});
	});
	var bom = {};
	bom.popup = function() {
		var url     = "${context}/member.do?";
        var name    = "로그인";
        var style   = "toolbar=no,status=no,directories=no,scrollbars=yes,location=no,resizable=no,border=0,menubar=no";
        var param   = "page=login_form";      //없으면 지워도 됨.
        var width   = 600;   //가로 사이즈 조절
        var height  = 400;    //세로 사이즈 조절
        var xpos    = (screen.availWidth - width  ) / 2;
        var ypos    = (screen.availHeight- height ) / 2;
        style       = style+',top='+ypos+',left='+ xpos +',width=' + width + ',height=' + height;
        url         = url+param;
        window.open(url,'',style);
	        
	}
	var admin = {
		checkAdmin : function() {
			// confirm() 은 예 와 아니오 버튼이 존재하는 팝업창
			// 예 버튼을 클릭하면 true 리턴
			// 아니오 버튼을 클릭하면 false 리턴
			var isAdmin = confirm('관리자입니까?');
			if (!isAdmin) {
				alert('관리자만 접근허용합니다.');
			} else {
				var password = prompt('관리자 비번을 입력하세요.');
				// 관리자는 이미 회사로부터 접근번호를 부여받았으므로
				// 이 예제에서는 1 로 미리 정의해둠
				if (password == 1) {
					location.href = "${context}/admin.do?page=main";
				} else {
					alert('관리자 비번이 틀립니다.');
				}
			}
		}	
	};
</script>