<%--
  Created by IntelliJ IDEA.
  User: 高宇飞
  Date: 2016/7/9
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>系统日志</title>
    <%@ include file="../common/header.jsp"%>
</head>

<body class="no-skin">
<%@ include file="../common/top.jsp"%>

<div class="main-container" id="main-container">
    <%@ include file="../common/menu.jsp"%>
    <div class="main-content">
        <div class="main-content-inner">
            <!-- #section:basics/content.breadcrumbs -->
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">首页</a>
                    </li>

                    <li>
                        <a href="#">系统管理</a>
                    </li>
                    <li class="active">系统日志</li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">

                <div class="row">
                    <div class="col-xs-12">
                        <div id="timeline-1">
                            <div class="row">
                                <div class="col-xs-12 col-sm-10 col-sm-offset-1" style="margin-left: 6%;">
                                    <!-- #section:pages/timeline -->
                                    <div class="timeline-container" style="top: 20px">
                                        <div class="timeline-label">
                                            <!-- #section:pages/timeline.label -->
													<span class="label label-primary arrowed-in-right label-lg">
														<b>2016年5月1日</b>
													</span>
                                        </div>
                                        <div class="timeline-items">
                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <i class="timeline-indicator ace-icon fa fa-star btn btn-warning no-hover green"></i>
                                                </div>
                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main">
                                                           发布V1.0测试版本
                                                            <div class="pull-right">
                                                                <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                                               18:00
                                                            </div>

                                                        </div>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.实现基本的流程操作</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div>
                                    <div class="timeline-container" >
                                        <div class="timeline-label">
                                            <!-- #section:pages/timeline.label -->
													<span class="label label-primary arrowed-in-right label-lg">
														<b>2016年6月1日</b>
													</span>
                                        </div>
                                        <div class="timeline-items">
                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <i class="timeline-indicator ace-icon fa fa-star btn btn-warning no-hover green"></i>
                                                </div>
                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main">
                                                            发布V2.1版本
                                                            <div class="pull-right">
                                                                <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                                                12:00
                                                            </div>

                                                        </div>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.添加了部分功能，如数据的统计分析页面和微信设置</p>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.修复了商品信息显示不完全的BUG</p>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.修改了一些显示样式，微信端显示价格增加了价格浮动</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div>
                                    <div class="timeline-container" >
                                        <div class="timeline-label">
                                            <!-- #section:pages/timeline.label -->
													<span class="label label-primary arrowed-in-right label-lg">
														<b>2016年6月15日</b>
													</span>
                                        </div>
                                        <div class="timeline-items">
                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <i class="timeline-indicator ace-icon fa fa-star btn btn-warning no-hover green"></i>
                                                </div>
                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main">
                                                            发布V2.2版本
                                                            <div class="pull-right">
                                                                <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                                                18:00
                                                            </div>

                                                        </div>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.修复了商品统计错误的BUG，优化了个人中心显示的样式</p>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.优化了申请提现的样式</p>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.门店参数设置和微信设置做了一些改动，使之更加容易操作</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div>
                                    <div class="timeline-container" >
                                        <div class="timeline-label">
                                            <!-- #section:pages/timeline.label -->
													<span class="label label-primary arrowed-in-right label-lg">
														<b>2016年6月29日</b>
													</span>
                                        </div>
                                        <div class="timeline-items">
                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <i class="timeline-indicator ace-icon fa fa-star btn btn-warning no-hover green"></i>
                                                </div>
                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main">
                                                            发布V2.3版本
                                                            <div class="pull-right">
                                                                <i class="ace-icon fa fa-clock-o bigger-110"></i>
                                                                12:00
                                                            </div>

                                                        </div>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1.微信端修改了分类选择的样式，修复了之前选择分类时显示不完全的BUG</p>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2.优化了微信端商品详情页面的样式。</p>
                                                        <p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.添加了代言人的按地区统计，引入了快递接口，可以更加方便的查询快递信息</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="timeline-2" class="hide">
                            <div class="row">
                                <div class="col-xs-12 col-sm-10 col-sm-offset-1">
                                    <!-- #section:pages/timeline.style2 -->
                                    <div class="timeline-container timeline-style2">
												<span class="timeline-label">
													<b>Today</b>
												</span>

                                        <div class="timeline-items">
                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <span class="timeline-date">11:15 pm</span>

                                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                                </div>

                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main no-padding">
																	<span class="bigger-110">
																		<a href="#" class="purple bolder">Susan</a>
																		reviewed a product
																	</span>

                                                            <br>
                                                            <i class="ace-icon fa fa-hand-o-right grey bigger-125"></i>
                                                            <a href="#">Click to read …</a>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <span class="timeline-date">12:30 pm</span>

                                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                                </div>

                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main no-padding">
                                                            Going to
                                                            <span class="green bolder">veg cafe</span>
                                                            for lunch
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <span class="timeline-date">11:15 pm</span>

                                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                                </div>

                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main no-padding">
                                                            Designed a new logo for our website. Would appreciate feedback.
                                                            <a href="#">
                                                                Click to see
                                                                <i class="ace-icon fa fa-search-plus blue bigger-110"></i>
                                                            </a>

                                                            <div class="space-2"></div>

                                                            <div class="action-buttons">
                                                                <a href="#">
                                                                    <i class="ace-icon fa fa-heart red bigger-125"></i>
                                                                </a>

                                                                <a href="#">
                                                                    <i class="ace-icon fa fa-facebook blue bigger-125"></i>
                                                                </a>

                                                                <a href="#">
                                                                    <i class="ace-icon fa fa-reply light-green bigger-130"></i>
                                                                </a>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>

                                            <div class="timeline-item clearfix">
                                                <div class="timeline-info">
                                                    <span class="timeline-date">9:00 am</span>

                                                    <i class="timeline-indicator btn btn-info no-hover"></i>
                                                </div>

                                                <div class="widget-box transparent">
                                                    <div class="widget-body">
                                                        <div class="widget-main no-padding"> Took the final exam. Phew! </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div><!-- /.timeline-items -->
                                    </div><!-- /.timeline-container -->
                                </div>
                            </div>
                        </div>

                        <!-- PAGE CONTENT ENDS -->
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.page-content -->
        </div>
    </div>
</div>
<!-- /.main-container -->
<%@ include file="../common/js.jsp"%>
</body>
</html>

