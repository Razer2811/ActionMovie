<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đăng Nhập - Xem Phim Online</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@2.1.4/css/boxicons.min.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/login.css" rel="stylesheet">
</head>
<body>

	<jsp:include page="Header.jsp"></jsp:include>

	<div class="flex-grow-1 d-flex align-items-center justify-content-center">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-5 col-lg-4">
					<div class="card-login">

						<div class="user-icon-wrapper">
							<i class='bx bxs-user'></i>
						</div>

						<h3 class="text-center mb-4 fw-bold login-title">
							Đăng Nhập
						</h3>

						<%
						String tb = (String) request.getAttribute("tb");
						if (tb != null) {
						%>
						<div class="alert alert-danger text-center p-2 mb-3 alert-login-error" role="alert">
							<i class='bx bxs-error-circle'></i>
							<%=tb%>
						</div>
						<%
						}
						%>

						<form action="Login" method="post">

							<div class="mb-3">
								<label class="form-label">Tên đăng nhập</label>
								<div class="input-group">
									<span class="input-group-text input-icon-dark">
										<i class='bx bxs-user'></i>
									</span>
									<input type="text"
										   class="form-control form-control-dark"
										   name="username"
										   placeholder="Nhập tên tài khoản"
										   required>
								</div>
							</div>

							<div class="mb-4">
								<label class="form-label">Mật khẩu</label>
								<div class="input-group">
									<span class="input-group-text input-icon-dark">
										<i class='bx bxs-lock-alt'></i>
									</span>
									<input type="password"
										   class="form-control form-control-dark"
										   name="pass"
										   placeholder="Nhập mật khẩu"
										   required>
								</div>
							</div>

							<div class="d-grid mb-4">
								<button type="submit" class="btn btn-gold">
									Đăng Nhập <i class='bx bx-log-in-circle ms-1'></i>
								</button>
							</div>

							<div class="text-center login-footer-text">
								<a href="#" class="link-custom">Quên mật khẩu?</a>
								<div class="divider"></div>
								Chưa có tài khoản?
								<a href="Register" class="link-custom fw-bold">
									Đăng ký ngay
								</a>
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>

	<jsp:include page="Footer.jsp"></jsp:include>

	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
