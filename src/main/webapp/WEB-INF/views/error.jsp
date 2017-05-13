<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-操作失败</title>
<%@ include file="common/header.jsp"%>
</head>

<body class="no-skin">
	<%@ include file="common/top.jsp"%>

	<div class="main-container" id="main-container">
		<%@ include file="common/menu.jsp"%>
		<div class="main-content">
			<div class="main-content-inner">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="${dynamicServer}/index.htm">首页</a></li>
						<li class="active">操作结果</li>
					</ul>
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="alert alert-danger" style="text-align: center;">
						<h4>
							<i class="fa fa-exclamation-circle"></i> ${msg }
						</h4>
						<a href="javascript:void(0)" onclick='history.back(-1);'>返回</a>
					</div>


					<!-- /.main-content -->
				</div>
				<!-- /.main-container -->
				<%@ include file="common/js.jsp"%>
</body>
</html>
