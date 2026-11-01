<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${pageTitle} - ActionMovie</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/genre.css" rel="stylesheet">
<link href="css/card.css" rel="stylesheet">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<main class="container main-content">
    <header class="genre-heading">
        <h1>${pageTitle}</h1>
        <p>Khám phá các lựa chọn phù hợp với tâm trạng của bạn hôm nay.</p>
    </header>
    <c:choose>
        <c:when test="${not empty dsvideo}">
            <div class="movie-grid">
                <c:forEach items="${dsvideo}" var="video">
                    <article class="movie-card">
                        <a class="movie-poster" href="DetailMovie?id=${video.id}">
                            <img src="img/${video.poster}" alt="${video.title}" loading="lazy">
                            <span class="poster-play" aria-hidden="true"><i class="bx bx-play"></i></span>
                        </a>
                        <div class="movie-info">
                            <a class="movie-title" href="DetailMovie?id=${video.id}" title="${video.title}">${video.title}</a>
                            <div class="movie-meta">
                                <span class="meta-item"><i class="bx bxs-show"></i>${video.views}</span>
                                <span class="meta-item"><i class="bx bxs-heart"></i>${video.totalFavorites}</span>
                                <span class="meta-item"><i class="bx bxs-share"></i>${video.totalShares}</span>
                            </div>
                            <a class="watch-link" href="DetailMovie?id=${video.id}">Xem ngay <i class="fas fa-arrow-right"></i></a>
                        </div>
                    </article>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise><div class="empty-state">Hiện chưa có phim nào thuộc lựa chọn này.</div></c:otherwise>
    </c:choose>
</main>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
