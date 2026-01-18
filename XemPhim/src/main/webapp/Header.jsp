<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<header class="site-header">
    <div class="header-container">

        <div class="header-left">
            <a href="Home" class="header-logo">
                <img src="img/logo2.png" alt="MOVIE ENTERTAINMENT">
            </a>

            <form action="GenreVideo" class="search-form">
                <button type="submit" class="search-btn">
                    <i class="fas fa-search"></i>
                </button>
                <input type="text" name="timkiem"
                       placeholder="Tìm kiếm phim, diễn viên..."
                       class="search-input">
            </form>
        </div>

        <div class="header-right">
            <nav class="main-menu">
                <ul>
                    <li><a href="Home">Phim Truyện</a></li>
                    <li><a href="LikeMovie">Phim yêu thích</a></li>
                    <li><a href="ShareMovie">Phim chia sẻ</a></li>

                    <!-- THỂ LOẠI -->
                    <li>
                        <a href="#">Thể loại <i class="fas fa-caret-down"></i></a>
                        <ul class="sub-menu">
                            <c:forEach items="${dsGenre}" var="g">
                                <li>
                                    <a href="GenreVideo?genres=${g.id}">
                                        ${g.title}
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>

                    <!-- CHƯA ĐĂNG NHẬP -->
                    <c:if test="${empty sessionScope.user}">
                        <li><a href="Login">Đăng nhập</a></li>
                        <li><a href="Register">Đăng ký</a></li>
                    </c:if>

                    <!-- ĐÃ ĐĂNG NHẬP -->
                    <c:if test="${not empty sessionScope.user}">
                        <li>
                            <a href="#" class="user-greeting">
                                <img
                                    src="avatar/${empty sessionScope.user.avatar ? 'logo2.png' : sessionScope.user.avatar}"
                                    alt="Avatar"
                                    class="my-avatar-img">

                                <span>${sessionScope.user.fullname}</span>
                                <i class="fas fa-caret-down"></i>
                            </a>

                            <ul class="sub-menu">
                                <li><a href="ChangePass">Đổi mật khẩu</a></li>
                                <li><a href="Logout">Đăng xuất</a></li>
                            </ul>
                        </li>
                    </c:if>

                </ul>
            </nav>
        </div>

    </div>
</header>
