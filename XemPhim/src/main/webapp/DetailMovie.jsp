<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>${empty movie ? 'Không tìm thấy phim' : movie.title} - ActionMovie</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css?v=black-gold-1">
<link rel="stylesheet" href="css/Detail.css">
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>

<c:choose>
    <c:when test="${not empty movie}">
        <main class="container detail-shell">
            <section class="detail-hero" aria-labelledby="movie-title">
                <img class="detail-poster" src="img/${movie.poster}" alt="${movie.title}">
                <div class="detail-copy">
                    <div class="detail-meta">
                        <span class="badge-custom"><i class="bx bx-hd"></i> ${movie.quality}</span>
                        <span class="badge-custom"><i class="bx bx-time"></i> ${movie.times} phút</span>
                        <span class="badge-custom"><i class="bx bx-calendar"></i> ${movie.formattedDayUpload}</span>
                        <c:if test="${movie.vip}"><span class="badge-custom">VIP</span></c:if>
                    </div>
                    <h1 id="movie-title">${movie.title}</h1>
                    <p>${movie.descriptions}</p>
                    <div class="detail-actions">
                        <c:choose>
                            <c:when test="${not empty sessionScope.user}">
                                <button id="btnLike" class="btn-action btn-like ${isLiked ? 'liked' : ''}">
                                    <i class="bx ${isLiked ? 'bxs-heart' : 'bx-heart'}"></i>
                                    <span id="txtLike">${isLiked ? 'Đã Thích' : 'Yêu Thích'}</span>
                                </button>
                                <button id="btnShare" class="btn-action btn-share">
                                    <i class="bx bxs-share"></i> <span id="txtShare">Chia sẻ (<span id="shareCount">${movie.totalShares}</span>)</span>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn-action btn-like" onclick="alert('Vui lòng đăng nhập để thích phim!')">
                                    <i class="bx bx-heart"></i> Yêu thích
                                </button>
                                <button type="button" class="btn-action btn-share" onclick="alert('Vui lòng đăng nhập để chia sẻ phim!')">
                                    <i class="bx bxs-share"></i> Chia sẻ (<span id="shareCount">${movie.totalShares}</span>)
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </section>

            <section class="watch-section" aria-labelledby="watch-title">
                <h2 id="watch-title">Xem phim</h2>
                <div class="video-container">
                    <iframe src="https://www.youtube.com/embed/${movie.link}" title="${movie.title}" allow="autoplay; fullscreen" allowfullscreen></iframe>
                </div>
            </section>
            <input type="hidden" id="hiddenVideoId" value="${movie.id}">

            <section class="related-section" aria-labelledby="related-title">
                <h2 id="related-title">Phim liên quan</h2>
                <c:choose>
                    <c:when test="${not empty listVideo}">
                        <div class="related-grid">
                            <c:forEach items="${listVideo}" var="item">
                                <article class="card-clean">
                                    <a href="DetailMovie?id=${item.id}"><img src="img/${item.poster}" alt="${item.title}" loading="lazy"></a>
                                    <div class="card-body">
                                        <div class="card-title">${item.title}</div>
                                        <a href="DetailMovie?id=${item.id}" class="btn-watch-sm">Xem ngay</a>
                                    </div>
                                </article>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise><p class="text-muted">Không có phim liên quan.</p></c:otherwise>
                </c:choose>
            </section>
        </main>
    </c:when>
    <c:otherwise><main class="container not-found"><h1>Không tìm thấy phim</h1><p>Phim bạn tìm kiếm không còn tồn tại hoặc chưa được phát hành.</p><a class="hero-link" href="Home">Về trang chủ</a></main></c:otherwise>
</c:choose>

<jsp:include page="Footer.jsp"></jsp:include>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<c:if test="${not empty movie}">
<script>
$(function () {
    var videoId = $('#hiddenVideoId').val();
    $('#btnLike').on('click', function () {
        var button = $(this), icon = button.find('i'), text = $('#txtLike');
        $.ajax({
            url: 'DetailMovie', type: 'POST', data: { action: 'like', id: videoId },
            success: function (response) {
                if (response === 'liked') { text.text('Đã Thích'); icon.removeClass('bx-heart').addClass('bxs-heart'); button.addClass('liked'); }
                else if (response === 'unliked') { text.text('Yêu Thích'); icon.removeClass('bxs-heart').addClass('bx-heart'); button.removeClass('liked'); }
                else alert('Lỗi: ' + response);
            },
            error: function (xhr) { alert(xhr.status === 401 ? 'Vui lòng đăng nhập để thích phim!' : 'Lỗi kết nối!'); }
        });
    });
    $('#btnShare').on('click', function () {
        navigator.clipboard.writeText(window.location.href).then(function () {
            alert('Đã sao chép liên kết phim! Bạn có thể dán để chia sẻ.');
        }).catch(function () { alert('Không thể sao chép liên kết trên trình duyệt này.'); });
        $.ajax({
            url: 'DetailMovie', type: 'POST', data: { action: 'share', id: videoId },
            success: function (newCount) { if (newCount && !isNaN(newCount)) $('#shareCount').text(newCount); },
            error: function () { console.log('Lỗi khi cập nhật share count'); }
        });
    });
});
</script>
</c:if>
</body>
</html>
