<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-角色管理</title>
<%@ include file="../common/header.jsp"%>
</head>

<body class="no-skin">
	<%@ include file="../common/top.jsp"%>

	<div class="main-container" id="main-container">
		<%@ include file="../common/menu.jsp"%>
		<div class="main-content">
			<div class="main-content-inner">
				<!-- #section:basics/content.breadcrumbs -->
				<div class="breadcrumbs  breadcrumbs-fixed" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">首页</a></li>
						<li class="active">系统管理</li>
						<li class="active">角色管理</li>
					</ul>
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="page-header">
						<h1>
							角色管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> 新增角色
							</small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<form id="roleForm" name="roleForm" class="form-horizontal" action="update.htm" method="post">
								<input type="hidden" name="backUrl" value="${backUrl }"> <input type="hidden" name="id" value="${sysRole.id }"> <input
									type="hidden" id="moduleArr" name="moduleArr" value=""> <input type="hidden" name="functionArr" id="functionArr" value="">
								<div class="form-group">
									<label class="col-sm-3 control-label">角色名称：</label>
									<div class="col-sm-9">
										<input id="name" name="name" type="text" class="col-xs-10 col-sm-5" placeholder="" value="${sysRole.name}"> <label
											id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">角色说明：</label>
									<div class="col-sm-9">
										<input id="description" type="text" name="description" class="col-xs-10 col-sm-5" placeholder="" value="${sysRole.description }"><label
											id="descriptionTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">角色权限：</label>
									<div class="col-sm-9">
										<c:forEach items="${listModule }" var="sysModule" varStatus="st">
											<c:if test="${sysModule.parent_id eq 1 }">
												<div class="widget-box">
													<!-- #section:custom/widget-box.options -->
													<div class="widget-header">
														<h4 class="widget-title">
															<label> <input name="module" class="ace father" type="checkbox" id="mod_${sysModule.id }"
																value="${sysModule.id }" /> <span class="lbl">${sysModule.name }</span>
															</label>
														</h4>
														<div class="widget-toolbar">
															<a href="#" data-action="collapse"> <i class="ace-icon fa fa-chevron-up"></i>
															</a>
														</div>
													</div>
													<div class="widget-body">
														<div class="widget-main">
															<c:forEach items="${listModule }" var="secModule" varStatus="st">
																<c:if test="${sysModule.id eq secModule.parent_id }">
																	<label> <input name="module" id="mod_${secModule.id }" type="checkbox" value="${secModule.id }"
																		class="ace children" /> <span class="lbl"> ${secModule.name}</span>
																	</label>
																	<c:forEach items="${listFunction }" var="sysFunction" varStatus="st">
																			<c:if test="${sysFunction.module_id eq secModule.id }">
																				<label class="checkbox inline" style="color: blue"><input name="function" id="function_${sysFunction.id }"
																					type="checkbox" value="${sysFunction.id }" class="ace children" /> <span class="lbl"> ${sysFunction.name }</span> </label>
																			</c:if>
																		</c:forEach> 
																	<br />
																</c:if>
															</c:forEach>
														</div>
													</div>
												</div>
											</c:if>
										</c:forEach>
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
					$(document).ready(function() {
						$(".children").click(function() {
							$(this).parent().parent().parent().parent().find(".father").prop("checked", true);
						})
						$(".father").click(function() {
							if (this.checked) {
								$(this).parent().parent().parent().parent().find(".children").prop("checked", true);
							} else {
								$(this).parent().parent().parent().parent().find(".children").prop("checked", false);
							}
						})
						$("#roleForm").validate({
							debug : true,
							errorElement : "label",
							errorClass : "valiError",
							errorPlacement : function(error, element) {
								error.appendTo($("#" + element.attr('id') + "Tip"));
							},
							rules : {
								name : {
									required : true,
									maxlength : 40
								}
							},
							messages : {},
							submitHandler : function(form) {
								var moduleArr = "";
								$('input:checkbox[name=module]:checked').each(function(i) {
									moduleArr += $(this).val() + "@@";
								});
								$("#moduleArr").val(moduleArr);
								var functionArr = "";
								$('input:checkbox[name=function]:checked').each(function(i) {
									functionArr += $(this).val() + "@@";
								});
								$("#functionArr").val(functionArr);

								form.submit();
							}
						});
						///设置按钮选中
						${checkButton}
					});
				</script>
</body>
</html>
