<%--
  ┌───────────────────────────────────────────────────────────────────┐
  │ Copyright (c) 2023년 3월 7일 EdenDEV All rights reserved.          │
  └───────────────────────────────────────────────────────────────────┘
  --%>

<%--
Date Created : 2023-03-07
Code Author : EdenDev
Creation Time : 오후 3:12
Purpose : Web Posting List View Page
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="../include/head.jsp" %>
<%@ include file="../include/user_menu.jsp" %>

<html>
<body class="hold-transition skin-green-light sidebar-mini" oncopy="return false" oncut="return false"
      onpaste="return false">
<div class="wrapper">

    <%@ include file="../include/top_menu.jsp" %>

    <%@ include file="../include/left_menu.jsp" %>

    <div class="content-wrapper">
        <section class="content container-fluid">

            <form name="detailsForm" role="form" method="post">
                <input type="hidden" id="boardNo" name="board_no" value="">
            </form>

            <form name="writeForm" role="form" method="post">
                <input type="hidden" id="category" name="category_no" value="">
            </form>

            <form name="listForm" role="form" method="post">
                <input type="hidden" id="BOARD_NO" name="board_no" value="">
                <input type="hidden" id="CATEGORY_NO" name="category_no" value="">
                <input type="hidden" id="pageNum" name="pageNum" value="">
            </form>

            <div class="box-header with-border">

            </div>
            <div class="box-body">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th class="col-xs-1">글 번호</th>
                        <th class="col-xs-1">카테고리</th>
                        <th class="col-xs-2">제목</th>
                        <th class="col-xs-2">작성자</th>
                        <th class="col-xs-2">작성일</th>
                        <th class="col-xs-1">조회수</th>
                        <th class="col-xs-1">좋아요</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${dataList }" var="data">
                        <tr>
                            <td>${data.boardVo.board_no}</td>
                            <td>${data.categoryVo.category_name}</td>
                            <td><a
                                    href="javascript:goPage(${data.boardVo.board_no});">${data.boardVo.board_title }</a>
                                <span class="badge bg-teal"><i class="fa fa-comment-o"></i> + ${data.totalCommentCount}</span>
                            </td>
                            <td>${data.userVo.user_nickname}</td>
                            <td><fmt:formatDate value="${data.boardVo.board_write_date }"
                                                pattern="yyyy:MM:dd: HH:mm:ss"/></td>
                            <td><span class="badge bg-red">${data.boardVo.board_view_count}</span></td>
                            <td><span class="badge bg-teal">${data.totalLikeCount}</span></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div class="box-footer">
                <form id="searchForm" action="../board/postingList" method="post">
                    <input type="hidden" name="category_no" value="${category_no}">
                    <div class="form-group col-sm-2">
                        <select name="search_category_no" class="form-control" id="categoryList">
                            <c:forEach items="${list}" var="search">
                                <c:choose>
                                    <c:when test="${search_category_no == search.search_category_no}">
                                        <option value="${search.search_category_no}" selected>
                                                ${search.search_type}
                                        </option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${search.search_category_no}">
                                                ${search.search_type}
                                        </option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group col-sm-10">
                        <div class="input-group">
                            <input type="text" class="form-control" name="keyword" id="keywordInput"
                                   value="${keyword}" placeholder="검색어">
                            <span class="input-group-btn">
                                    <button type="submit" class="btn btn-primary btn-flat" id="searchBtn">
                                        <i class="fa fa-search"></i> 검색
                                    </button>
                                </span>
                        </div>
                    </div>
                </form>
            </div>

            <div class="box-footer">
                <div class="col" align="center"><!-- 페이징...UI -->
                    <nav aria-label="Page navigation example">
                        <ul class="pagination">
                            <c:choose>
                                <c:when test="${startPage <= 1}">
                                    <li class="page-item disabled"><a class="page-link" href="#">&lt;</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link"
                                                             href="javascript:paging('${startPage - 1}', '${category_no}');">&lt;</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>


                            <c:forEach begin="${startPage }" end="${endPage }" var="i">
                                <c:choose>
                                    <c:when test="${i == currentPageNum }">
                                        <li class="page-item active"><a class="page-link"
                                                                        href="javascript:paging('${i}', '${category_no}');">${i}</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                                                 href="javascript:paging('${i}', ${category_no});">${i}</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <c:choose>
                                <c:when test="${endPage >= totalPageCount}">
                                    <li class="page-item disabled"><a class="page-link">&gt;</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link"
                                                             href="javascript:paging('${endPage + 1}', '${category_no}');">&gt;</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                </div>
            </div>

            <div class="box-footer">
                <div class="pull-right">
                    <a class="btn btn-success btn-flat" href="javascript:writePosting(${category_no});">
                        <i class="fa fa-pencil"></i> 글쓰기
                    </a>
                </div>
            </div>
        </section>
    </div>

    <%@ include file="../include/footer.jsp" %>

</div>

<%@ include file="../include/plugin_js.jsp" %>
</body>
</html>