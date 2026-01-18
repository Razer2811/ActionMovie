<%@page import="modal.Users"%>
<%@page import="modal.Video"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Chi Tiết Phim</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<link rel="stylesheet" href="css/Detail.css">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>

	<%
	// 1. NHẬN DỮ LIỆU
	Video v = (Video) request.getAttribute("movie");
	ArrayList<Video> listRelated = (ArrayList<Video>) request.getAttribute("listVideo");
	Users user = (Users) session.getAttribute("user");

	if (v != null) {
		// Lấy trạng thái Like
		boolean isLiked = false;
		Boolean flagLike = (Boolean) request.getAttribute("isLiked");
		if (flagLike != null)
			isLiked = flagLike;
	%>

	<div class="container my-5">
		<div class="row g-5">
			<div class="col-md-4">
				<img src="img/<%=v.getPoster()%>" class="img-fluid"
					style="width: 100%; border-radius: 12px;" alt="<%=v.getTitle()%>">
			</div>

			<div class="col-md-8">
				<h1 class="display-5 fw-bold text-highlight mb-3"><%=v.getTitle()%></h1>

				<div class="d-flex flex-wrap gap-2 mb-4">
					<span class="badge-custom"><i class='bx bx-hd'></i> <%=v.getQuality()%></span>
					<span class="badge-custom"><i class='bx bx-time'></i> <%=v.getTimes()%>
						phút</span> <span class="badge-custom"><i class='bx bx-calendar'></i>
						<%=v.getFormattedDayUpload()%></span>
					<%
					if (v.getVip()) {
					%>
					<span class="badge bg-warning text-dark">VIP</span>
					<%
					}
					%>
				</div>

				<div class="mb-4">
					<h5 class="text-highlight">Nội dung:</h5>
					<p style="color: #c9d1d9;"><%=v.getDescriptions()%></p>
				</div>

				<div class="d-flex gap-3 mt-3">
					<%
					if (user != null) {
					%>
					<button id="btnLike"
						class="btn-action btn-like <%=isLiked ? "liked" : ""%>">
						<i class='bx <%=isLiked ? "bxs-heart" : "bx-heart"%>'></i> <span
							id="txtLike"><%=isLiked ? "Đã Thích" : "Yêu Thích"%></span>
					</button>
					<%
					} else {
					%>
					<button onclick="alert('Vui lòng đăng nhập để thích phim!')"
						class="btn-action btn-like">
						<i class='bx bx-heart'></i> Yêu Thích
					</button>
					<%
					}
					%>

					<%
					if (user != null) {
					%>
					<button id="btnShare" class="btn-action btn-share">
						<i class='bx bxs-share'></i> <span id="txtShare">Chia Sẻ (<span
							id="shareCount"><%=v.getTotalShares()%></span>)
						</span>
					</button>
					<%
					} else {
					%>
					<button onclick="alert('Vui lòng đăng nhập để chia sẻ phim!')"
						class="btn-action btn-share">
						<i class='bx bxs-share'></i> <span>Chia Sẻ (<span
							id="shareCount"><%=v.getTotalShares()%></span>)
						</span>
					</button>
					<%
					}
					%>
				</div>
			</div>
		</div>

		<div class="mt-5">
			<h3 class="text-highlight mb-3">
				<i class='bx bx-play-circle'></i> Xem Phim
			</h3>
			<div class="video-container">
				<iframe src="https://www.youtube.com/embed/<%=v.getLink()%>"
					title="Video player" frameborder="0" allowfullscreen></iframe>
			</div>
		</div>

		<input type="hidden" id="hiddenVideoId" value="<%=v.getId()%>">

		<div class="mt-5">
			<h3 class="text-highlight mb-4">Phim Liên Quan</h3>
			<div class="row row-cols-2 row-cols-md-4 g-4">
				<%
				if (listRelated != null && listRelated.size() > 0) {
					for (Video item : listRelated) {
				%>
				<div class="col">
					<div class="card-clean">
						<a href="DetailMovie?id=<%=item.getId()%>"> <img
							src="img/<%=item.getPoster()%>" alt="<%=item.getTitle()%>">
						</a>
						<div class="card-body">
							<div class="card-title"><%=item.getTitle()%></div>
							<a href="DetailMovie?id=<%=item.getId()%>"
								class="btn-watch-sm">Xem Ngay</a>
						</div>
					</div>
				</div>
				<%
				}
				} else {
				%>
				<div class="col-12">
					<p style="color: #8b949e;">Không có phim liên quan</p>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>

	<%
	} else {
	%>
	<div class="container text-center mt-5">
		<h3>Không tìm thấy phim!</h3>
	</div>
	<%
	}
	%>
	<jsp:include page="Footer.jsp"></jsp:include>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			var vId = $('#hiddenVideoId').val();

			// === XỬ LÝ LIKE ===
			$('#btnLike').click(function() {
				var btn = $(this);
				var icon = btn.find('i');
				var txt = $('#txtLike');

				$.ajax({
					url : 'DetailMovie',
					type : 'POST',
					data : {
						action : 'like',
						id : vId
					},
					success : function(response) {
						if (response === 'liked') {
							txt.text('Đã Thích');
							icon.removeClass('bx-heart').addClass('bxs-heart');
							btn.addClass('liked');
						} else if (response === 'unliked') {
							txt.text('Yêu Thích');
							icon.removeClass('bxs-heart').addClass('bx-heart');
							btn.removeClass('liked');
						} else {
							alert('Lỗi: ' + response);
						}
					},
					error : function(xhr) {
						if (xhr.status === 401) {
							alert('Vui lòng đăng nhập để thích phim!');
						} else {
							alert('Lỗi kết nối!');
						}
					}
				});
			});

			// === XỬ LÝ SHARE ===
			$('#btnShare').click(function() {
				// 1. Copy link phim vào bộ nhớ tạm
				var dummy = document.createElement('input');
				var text = window.location.href;
				document.body.appendChild(dummy);
				dummy.value = text;
				dummy.select();
				document.execCommand('copy');
				document.body.removeChild(dummy);

				// 2. Thông báo cho người dùng
				alert("Đã sao chép liên kết phim! Bạn có thể dán để chia sẻ.");

				// 3. Gửi AJAX để tăng đếm Share trong Database
				$.ajax({
					url : 'DetailMovie',
					type : 'POST',
					data : {
						action : 'share',
						id : vId
					},
					success : function(newCount) {
						if (newCount && !isNaN(newCount)) {
							$('#shareCount').text(newCount);
						}
					},
					error : function() {
						console.log('Lỗi khi cập nhật share count');
					}
				});
			});
		});
	</script>
</body>
</html>