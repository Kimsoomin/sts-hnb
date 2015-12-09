<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="${js}/global.js"></script>
<script type="text/javascript">
	$(function() {
 		Global.init();
		$('#header').load('${context}/global/header'); 
		$('#box').load('${context}/global/jumbotron');
		$('#footer').load('${context}/global/footer'); 
	});
</script>