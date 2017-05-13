<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="${staticServer }/assets/css/bootstrap.css?versionNo=${versionNo}" />
<link rel="stylesheet" href="${staticServer }/assets/components/font-awesome/css/font-awesome.css?versionNo=${versionNo}" />
<!-- page specific plugin styles -->
<!-- text fonts -->

<link rel="stylesheet" href="${staticServer }/assets/css/common.css?versionNo=${versionNo}" />

<link rel="stylesheet" href="${staticServer }/assets/css/ace-fonts.css"?versionNo=${versionNo} />
<!-- ace styles -->
<link rel="stylesheet" href="${staticServer }/assets/css/ace.css?versionNo=${versionNo}" class="ace-main-stylesheet"
	id="main-ace-style" />

<!--[if lte IE 9]>
			<link rel="stylesheet" href="${staticServer }/assets/css/ace-part2.css?versionNo=${versionNo}" class="ace-main-stylesheet" />
		<![endif]-->
<!--[if lte IE 9]>
		  <link rel="stylesheet" href="${staticServer }/assets/css/ace-ie.css?versionNo=${versionNo}" />
		<![endif]-->
<!-- inline styles related to this page -->
<!-- ace settings handler -->
<script src="${staticServer }/assets/js/ace-extra.js?versionNo=${versionNo}"></script>

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
		<script src="${staticServer }/components/html5shiv/dist/html5shiv.min.js?versionNo=${versionNo}"></script>
		<script src="${staticServer }/components/respond/dest/respond.min.js?versionNo=${versionNo}"></script>
		<![endif]-->
<!--[if !IE]> -->
<script type="text/javascript">
	window.jQuery || document.write("<script src='${staticServer }/assets/components/jquery/dist/jquery.js?versionNo=${versionNo}'>" + "<"+"/script>");
</script>

<!-- <![endif]-->

<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='${staticServer }/assets/components/jquery.1x/dist/jquery.js?versionNo=${versionNo}'>"+"<"+"/script>");
</script>
<![endif]-->
<script type="text/javascript">
	if ('ontouchstart' in document.documentElement)
		document.write("<script src='${staticServer }/assets/components/_mod/jquery.mobile.custom/jquery.mobile.custom.js?versionNo=${versionNo}'>" + "<"+"/script>");
</script>

<link rel="shortcut icon" href="${staticServer }/assets/images/favicon.ico" mce_href="${staticServer }/assets/images/favicon.ico" type="image/x-icon" />
