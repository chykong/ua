<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>${webTitle }-模块管理</title>
<%@ include file="../common/header.jsp"%>
<link href="${staticServer }/assets/zTree/css/zTreeStyle/metro.css" rel="stylesheet" type="text/css" />
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
						<li class="active">模块管理</li>
					</ul>
				</div>

				<!-- /section:basics/content.breadcrumbs -->
				<div class="page-content">
					<div class="page-header">
						<h1>
							模块管理 <small> <i class="ace-icon fa fa-angle-double-right"></i> 新增模块
							</small>
						</h1>
					</div>
					<!-- /.page-header -->

					<div class="column">
						<div class="col-xs-12">
							<form id="moduleForm" name="moduleForm" class="form-horizontal" action="add.htm" method="post">
								<input type="hidden" name="backUrl" value="${backUrl }">
								<div class="form-group">
									<label class="col-sm-3 control-label">模块名称：</label>
									<div class="col-sm-9">
										<input id="name" name="name" type="text" class="col-xs-10 col-sm-5" placeholder="" value=""> <label id="nameTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">上级节点：</label>
									<div class="col-sm-9">
										<input id="parent_id" type="hidden" name="parent_id" readonly="readonly" class="col-xs-10 col-sm-5" placeholder="" value="">
										<input id="parent_name" type="text" name="parent_name" readonly="readonly" class="col-xs-10 col-sm-5" placeholder="" value="">
										<span class="input-group-btn">
											<button type="button" class="btn btn-primary btn-sm" onclick="javascript:showSelTree()">
												<span class="ace-icon fa fa-search icon-on-right bigger-110"></span> 选择
											</button> <label id="parent_idTip"></label>
										</span> </span>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">模块代码：</label>
									<div class="col-sm-9">
										<input id="code" type="text" name="code" class="col-xs-10 col-sm-5" placeholder="" value=""><label id="codeTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">模块链接：</label>
									<div class="col-sm-9">
										<input id="url" type="text" name="url" class="col-xs-10 col-sm-5" placeholder="" value=""><label id="urlTip"></label>
									</div>
								</div>
								<div class="form-group">

									<label class="col-sm-3 control-label">模块目标：</label>
									<div class="col-sm-9 ">
										<select name="target" class="col-xs-10 col-sm-5">
											<option value="main">main</option>
											<option value="blank">blank</option>
										</select> <label id="targetTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">图标：</label>
									<div class="col-sm-9">
										<input id="iconImg" type="text" name="iconImg" class="col-xs-10 col-sm-5" placeholder="" value=""><label id="iconImgTip"></label>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label">排序：</label>
									<div class="col-sm-9">
										<input id="display_order" type="text" name="display_order" class="col-xs-10 col-sm-5" placeholder="" value=""><label
											id="display_orderTip"></label>
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


				<div class="modal fade" id="basic" tabindex="-1" role="basic" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
								<h4 class="modal-title">选择上级节点</h4>
							</div>
							<div class="modal-body">
								<ul id="tree" class="ztree" style="width: 560px; overflow: auto;"></ul>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary" onclick="javascript:getSelected();">确认</button>
								<button type="button" class="btn " data-dismiss="modal">取消</button>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>
				<!-- /.main-container -->
				<%@ include file="../common/js.jsp"%>
				<script src="${staticServer }/assets/zTree/js/jquery.ztree.all-3.5.min.js" type="text/javascript"></script>

				<script type="text/javascript">
				
				 var zTree;
				    var setting = {
				        data: {
				            simpleData: {
				                enable: true,
				                idKey: "id",
				                pIdKey: "pId",
				                rootPId: ""
				            }
				        }
				    };
				    var zNodes = [${ztree}];
				    jQuery(document).ready(function () {
				        var t = $("#tree");
				        t = $.fn.zTree.init(t, setting, zNodes);
				        var zTree = $.fn.zTree.getZTreeObj("tree");
				    });

				    function getSelected() {
				        var treeObj = $.fn.zTree.getZTreeObj("tree");
				        var nodes = treeObj.getSelectedNodes();
				        if (nodes.length > 0) {
				            $("#parent_id").val(nodes[0].id);
				            $("#parent_name").val(nodes[0].name);
				            $('#basic').modal('hide');
				        }
				        else return;

				    }
				
				    function showSelTree() {
				        $('#basic').modal('show');
				    }
				    
					$(document).ready(function() {
						$("#moduleForm").validate({
							debug : true,
							errorElement : "label",
							errorClass : "valiError",
							errorPlacement : function(error, element) {
								error.appendTo($("#" + element.attr('id') + "Tip"));
							},
							rules : {
								 name: {
					                    required: true,
					                    maxlength: 40
					                },
					                code: {
					                    required: true,
					                    maxlength: 20
					                },
					                url: {
					                    required: true,
					                    maxlength: 100
					                },
					                display_order: {
					                    required: true,
					                    number: true,
					                    maxlength: 10
					                }
							},
							messages : {
							},
							submitHandler : function(form) {
								form.submit();
							}
						});
					});
				</script>
</body>
</html>
