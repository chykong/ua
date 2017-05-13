<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle}-修改密码</title>
<%@ include file="../common/header.jsp"%>
</head>

<body class="no-skin">
	<%@ include file="../common/top.jsp"%>

	<div class="main-container" id="main-container">
		<%@ include file="../common/menu.jsp"%>
		<div class="main-content">
			<div class="main-content-inner">
				<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">修改密码</li>
					</ul>
				</div>

				<div class="page-content">
					<div class="page-header">
						<h1>
							修改密码 <small> <i class="ace-icon fa fa-angle-double-right"></i>
							</small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<form id="inputForm" name="inputForm" class="form-horizontal" action="updatePass.htm" method="post">
								<input type="hidden" name="backUrl" value="${backUrl }">
								<div class="form-group">
									<label class="col-sm-3 control-label">原密码：</label>
									<div class="col-sm-9">
										<input id="oldPass" name="oldPass" type="password" class="col-xs-10 col-sm-5" placeholder=""><label id="oldPassTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">新密码：</label>
									<div class="col-sm-9">
										<input id="newPass" type="password" name="newPass" class="col-xs-10 col-sm-5" placeholder=""><label id="newPassTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">确认新密码：</label>
									<div class="col-sm-9">
										<input id="renewPass" type="password" name="renewPass" class="col-xs-10 col-sm-5" placeholder=""><label id="renewPassTip"></label>
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-primary" type="submit">
											<i class="ace-icon fa fa-save bigger-110"></i> 保存
										</button>
										<button class="btn" type="button" onclick="history.back(-1)">
											<i class="ace-icon fa fa-undo bigger-110"></i> 取消
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<!-- /.main-content -->
				</div>
				<!-- /.main-container -->
				<%@ include file="../common/js.jsp"%>

				<script type="text/javascript">
					$(function() {
						var validate = $("#inputForm").validate({
							errorElement : "label",
							errorClass : "valiError",
							errorPlacement : function(error, element) {
								error.appendTo($("#" + element.attr('id') + "Tip"));
							},
							rules : {
								oldPass : {
									required : true,
									minlength : 6,
									maxlength : 20
								},
								newPass : {
									required : true,
									minlength : 6,
									maxlength : 20
								},
								renewPass : {
									required : true,
									minlength : 6,
									maxlength : 20,
									equalTo : "#newPass"
								},
							},
							messages : {
								renewPass : {
									equalTo : "确认新密码与新密码输入不一致"
								}
							}
						});
					});
				</script>
</body>
</html>
