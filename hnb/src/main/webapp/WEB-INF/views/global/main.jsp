<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${context}/js/global.js"></script>
<script type="text/javascript">
	$(function() {
		Global.init();
		$('#header').load('${context}/main.do?page=header'); 
		$('#box').load('${context}/main.do?page=jumbotron');
		$('#footer').load('${context}/main.do?page=footer');
	});
</script>