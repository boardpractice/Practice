<%--
┌──────────────────────────────────────────────────────────────────┐
│ Copyright (c) 2023년 2월 20일 EdenDev All rights reserved.        │
└──────────────────────────────────────────────────────────────────┘
--%>

<%--
Date Created : 2023-02-22
Code Author : Eden
Creation Time : 오후 12:50
Purpose : plugin java script include  jsp file
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- REQUIRED JS SCRIPTS -->
<!-- jQuery 3 -->
<script src="/resources/bower_components/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap 3.3.7 -->
<script src="/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- iCheck -->
<script src="/resources/plugins/iCheck/icheck.min.js"></script>
<!-- AdminLTE App -->
<script src="/resources/dist/js/adminlte.min.js"></script>
<%--fileUpload--%>
<script src="/resources/bower_components/fileupload/js/jasny-bootstrap.min.js"></script>
<%--lightbox--%>
<script src="/resources/bower_components/lightbox/js/lightbox.js"></script>
<!-- DataTables -->
<script src="/resources/bower_components/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="/resources/bower_components/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<!-- related user java script -->
<script src="/resources/dist/js/user/relatedUser.js" type="text/javascript"></script>
<!-- related Bulletin -->
<script src="/resources/dist/js/board/relatedBulletin.js" type="text/javascript"></script>
<!-- related BookMark -->
<script src="/resources/dist/js/bookmark/relatedBookMark.js" type="text/javascript"></script>
<!-- related Comment -->
<script src="/resources/dist/js/comment/relateComment.js" type="text/javascript"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $(document).ajaxStart(function () {
            $('#loading').show(); // ajax 시작 -> 로딩바 표출
        });

        $(document).ajaxStop(function () {
            $('#loading').hide(); // ajax 끝 -> 로딩바 히든
        });

        $("#loading").hide();
    });
</script>