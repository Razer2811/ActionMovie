<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<title>Đăng Ký Tài Khoản - Movie Entertainment</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css"
	rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/register.css" rel="stylesheet">
</head>
<body>
	<jsp:include page="Header.jsp"></jsp:include>
	<div class="main-content">
		<div class="auth-card">
			<h3 class="auth-title">Đăng Ký Thành Viên</h3>
			<form action="Register" method="post">
				<%
				String tb = (String) request.getAttribute("tb");
				if (tb != null) {
				%>
				<div class="alert alert-custom mb-4 text-center">
					<i class="fas fa-exclamation-circle me-2"></i>
					<%=tb%>
				</div>
				<%
				}
				%>
				<div class="mb-3">
					<label class="form-label">Tên đăng nhập</label> <input type="text"
						class="form-control" name="username"
						placeholder="Nhập tên đăng nhập..." equired>
				</div>
				<div class="mb-3">
					<label class="form-label">Họ và tên</label> <input type="text"
						class="form-control" name="fullname"
						placeholder="Nhập họ tên đầy đủ..." required>
				</div>
				<div class="mb-3">
					<label class="form-label">Email</label> <input type="email"
						class="form-control" name="email" placeholder="example@email.com"
						required>
				</div>
				<div class="row">
					<div class="col-md-6 mb-3">
						<label class="form-label">Mật khẩu</label> <input type="password"
							class="form-control" name="pass" placeholder="••••••" required>
					</div>
					<div class="col-md-6 mb-3">
						<label class="form-label">Nhập lại mật khẩu</label> <input
							type="password" class="form-control" name="repass"
							placeholder="••••••" required>
					</div>
				</div>
				<div class="mb-4">
					<label class="form-label">Ngày sinh</label> <input type="date"
						class="form-control" name="birth">
				</div>
				<button type="submit" class="btn btn-register w-100">
					<i class="fas fa-user-plus me-2"></i> ĐĂNG KÝ NGAY
				</button>
				<div class="auth-link">
					Bạn đã có tài khoản? <a href="Login">Đăng nhập tại đây</a>
				</div>
			</form>
		</div>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>