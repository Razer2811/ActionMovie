<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Trang chủ - ActionMovie</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css?v=black-gold-2" rel="stylesheet">
<link href="css/home.css?v=black-gold-2" rel="stylesheet">
<link href="css/card.css" rel="stylesheet">
<link href="css/chatbot.css" rel="stylesheet">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<main class="container home-shell">
    <section class="home-hero" aria-labelledby="home-title">
        <div class="hero-copy">
            <h1 id="home-title">Phim hay, xem ngay.</h1>
            <p>Phim chọn lọc, phát trực tuyến từ YouTube — mở một bộ phim và bắt đầu ngay.</p>
            <div class="hero-actions">
                <a class="hero-link" href="#new-releases">Xem phim mới <i class="fas fa-arrow-down"></i></a>
                <a class="hero-link hero-link-secondary" href="GenreVideo">Khám phá thư viện</a>
            </div>
        </div>
        <div class="hero-media">
            <iframe src="https://www.youtube.com/embed/UiMg566PREA?autoplay=1&amp;mute=1&amp;controls=0&amp;loop=1&amp;playlist=UiMg566PREA"
                    title="Trailer phim nổi bật" allow="autoplay; fullscreen" allowfullscreen></iframe>
        </div>
    </section>

    <section id="new-releases" aria-labelledby="new-releases-title">
        <div class="section-heading">
            <h2 id="new-releases-title">Phim mới cập nhật</h2>
            <p>Những tựa phim vừa được thêm vào kho ActionMovie. Chọn một poster để xem chi tiết.</p>
        </div>
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
            <c:otherwise><div class="empty-state">Hiện chưa có phim nào trong danh sách.</div></c:otherwise>
        </c:choose>

        <c:if test="${totalPages > 1}">
            <nav aria-label="Phân trang phim">
                <ul class="pagination">
                    <li class="page-item ${currentPage == 1 ? 'disabled' : ''}">
                        <a class="page-link" href="Home?page=${currentPage - 1}" aria-label="Trang trước"><i class="bx bx-chevron-left"></i></a>
                    </li>
                    <c:forEach begin="1" end="${totalPages}" var="page">
                        <li class="page-item ${page == currentPage ? 'active' : ''}">
                            <a class="page-link" href="Home?page=${page}">${page}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item ${currentPage == totalPages ? 'disabled' : ''}">
                        <a class="page-link" href="Home?page=${currentPage + 1}" aria-label="Trang sau"><i class="bx bx-chevron-right"></i></a>
                    </li>
                </ul>
            </nav>
        </c:if>
    </section>
</main>
<jsp:include page="chatbot.jsp"></jsp:include>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
