<%@page import="modal.Users"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đổi Mật Khẩu - Xem Phim Online</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div
		class="flex-grow-1 d-flex align-items-center justify-content-center py-5">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-5 col-lg-4">
					<div class="card-login">
						<div class="user-icon-wrapper">
							<i class='bx bxs-key'></i>
						</div>
						<h3 class="text-center mb-4 fw-bold login-title">Đổi Mật Khẩu
						</h3>
						<%
						Users user = (Users) session.getAttribute("user");
						if (user == null) {
						%>
						<div
							class="alert alert-danger text-center p-2 mb-3 alert-login-error">
							<i class='bx bxs-error-circle'></i> Bạn chưa đăng nhập vào hệ
							thống!
						</div>
						<div class="text-center">
							<a href="Login" class="btn btn-gold btn-sm">Quay lại Đăng
								nhập</a>
						</div>
						<%
						} else {
						String baoLoi = (String) request.getAttribute("baoLoi");
						if (baoLoi != null && !baoLoi.isEmpty()) {
						%>
						<div
							class="alert alert-danger text-center p-2 mb-3 alert-login-error"
							role="alert">
							<i class='bx bxs-error-circle'></i>
							<%=baoLoi%>
						</div>
						<%
						}
						%>
						<form action="ChangePass" method="POST">
							<div class="mb-3">
								<label class="form-label">Mật khẩu hiện tại</label>
								<div class="input-group">
									<span class="input-group-text input-icon-dark"> <i
										class='bx bxs-lock-open'></i>
									</span> <input type="password" class="form-control form-control-dark"
										name="matKhauHienTai" placeholder="Nhập mật khẩu cũ" required>
								</div>
							</div>
							<div class="mb-3">
								<label class="form-label">Mật khẩu mới</label>
								<div class="input-group">
									<span class="input-group-text input-icon-dark"> <i
										class='bx bxs-lock-alt'></i>
									</span> <input type="password" class="form-control form-control-dark"
										name="matKhauMoi" placeholder="Mật khẩu mới" required>
								</div>
							</div>
							<div class="mb-4">
								<label class="form-label">Xác nhận mật khẩu</label>
								<div class="input-group">
									<span class="input-group-text input-icon-dark"> <i
										class='bx bxs-check-shield'></i>
									</span> <input type="password" class="form-control form-control-dark"
										name="matKhauMoiNhapLai" placeholder="Nhập lại mật khẩu mới"
										required>
								</div>
							</div>
							<div class="d-grid mb-4">
								<button type="submit" class="btn btn-gold">
									Lưu mật khẩu <i class='bx bx-save ms-1'></i>
								</button>
							</div>
						</form>
						<%
						}
						%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>