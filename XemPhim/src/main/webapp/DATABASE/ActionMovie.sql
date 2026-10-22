/*
    ActionMovie - SQL Server schema and sample data
    Run this file in SQL Server Management Studio as a login that can create
    databases. The Java application expects the database name ActionMovie.

    Sample accounts:
      admin / 123456
      user  / 123456
    Passwords use the same salted SHA-1/Base64 algorithm as util.MaHoa.
*/

USE [master];
GO

IF DB_ID(N'ActionMovie') IS NULL
BEGIN
    CREATE DATABASE [ActionMovie];
END;
GO

USE [ActionMovie];
GO

IF OBJECT_ID(N'dbo.SHARE', N'U') IS NOT NULL DROP TABLE dbo.SHARE;
IF OBJECT_ID(N'dbo.FAVORITE', N'U') IS NOT NULL DROP TABLE dbo.FAVORITE;
IF OBJECT_ID(N'dbo.VIDEO', N'U') IS NOT NULL DROP TABLE dbo.VIDEO;
IF OBJECT_ID(N'dbo.USERS', N'U') IS NOT NULL DROP TABLE dbo.USERS;
IF OBJECT_ID(N'dbo.GENREVIDEO', N'U') IS NOT NULL DROP TABLE dbo.GENREVIDEO;
GO

CREATE TABLE dbo.GENREVIDEO
(
    id INT NOT NULL CONSTRAINT PK_GENREVIDEO PRIMARY KEY,
    title NVARCHAR(100) NOT NULL
);
GO

CREATE TABLE dbo.USERS
(
    id INT IDENTITY(1, 1) NOT NULL CONSTRAINT PK_USERS PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    pass VARCHAR(128) NOT NULL,
    email VARCHAR(255) NOT NULL,
    fullname NVARCHAR(100) NOT NULL,
    birth DATE NULL,
    avatar VARCHAR(255) NULL,
    starday DATE NOT NULL CONSTRAINT DF_USERS_starday DEFAULT CONVERT(date, GETDATE()),
    vip BIT NOT NULL CONSTRAINT DF_USERS_vip DEFAULT 0,
    admin BIT NOT NULL CONSTRAINT DF_USERS_admin DEFAULT 0,
    active BIT NOT NULL CONSTRAINT DF_USERS_active DEFAULT 1,
    CONSTRAINT UQ_USERS_username UNIQUE (username),
    CONSTRAINT UQ_USERS_email UNIQUE (email)
);
GO

CREATE TABLE dbo.VIDEO
(
    id INT IDENTITY(1, 1) NOT NULL CONSTRAINT PK_VIDEO PRIMARY KEY,
    title NVARCHAR(255) NOT NULL,
    poster VARCHAR(255) NOT NULL CONSTRAINT DF_VIDEO_poster DEFAULT 'default.jpg',
    views INT NOT NULL CONSTRAINT DF_VIDEO_views DEFAULT 0,
    link VARCHAR(500) NOT NULL,
    descriptions NVARCHAR(MAX) NULL,
    active BIT NOT NULL CONSTRAINT DF_VIDEO_active DEFAULT 1,
    vip BIT NOT NULL CONSTRAINT DF_VIDEO_vip DEFAULT 0,
    quality VARCHAR(20) NOT NULL CONSTRAINT DF_VIDEO_quality DEFAULT 'HD',
    dayupload DATE NOT NULL CONSTRAINT DF_VIDEO_dayupload DEFAULT CONVERT(date, GETDATE()),
    times INT NOT NULL CONSTRAINT DF_VIDEO_times DEFAULT 0,
    idgenre INT NOT NULL,
    CONSTRAINT CK_VIDEO_views CHECK (views >= 0),
    CONSTRAINT CK_VIDEO_times CHECK (times >= 0),
    CONSTRAINT FK_VIDEO_GENRE FOREIGN KEY (idgenre)
        REFERENCES dbo.GENREVIDEO(id)
);
GO

CREATE TABLE dbo.FAVORITE
(
    id INT IDENTITY(1, 1) NOT NULL CONSTRAINT PK_FAVORITE PRIMARY KEY,
    userid INT NOT NULL,
    videoid INT NOT NULL,
    likedate DATETIME2(0) NOT NULL CONSTRAINT DF_FAVORITE_likedate DEFAULT SYSDATETIME(),
    active BIT NOT NULL CONSTRAINT DF_FAVORITE_active DEFAULT 1,
    CONSTRAINT FK_FAVORITE_USER FOREIGN KEY (userid)
        REFERENCES dbo.USERS(id) ON DELETE CASCADE,
    CONSTRAINT FK_FAVORITE_VIDEO FOREIGN KEY (videoid)
        REFERENCES dbo.VIDEO(id) ON DELETE CASCADE,
    CONSTRAINT UQ_FAVORITE_user_video UNIQUE (userid, videoid)
);
GO

CREATE TABLE dbo.SHARE
(
    id INT IDENTITY(1, 1) NOT NULL CONSTRAINT PK_SHARE PRIMARY KEY,
    userid INT NOT NULL,
    videoid INT NOT NULL,
    emails VARCHAR(1000) NOT NULL,
    sharedate DATETIME2(0) NOT NULL CONSTRAINT DF_SHARE_sharedate DEFAULT SYSDATETIME(),
    number INT NOT NULL CONSTRAINT DF_SHARE_number DEFAULT 1,
    CONSTRAINT CK_SHARE_number CHECK (number > 0),
    CONSTRAINT FK_SHARE_USER FOREIGN KEY (userid)
        REFERENCES dbo.USERS(id) ON DELETE CASCADE,
    CONSTRAINT FK_SHARE_VIDEO FOREIGN KEY (videoid)
        REFERENCES dbo.VIDEO(id) ON DELETE CASCADE
);
GO

CREATE INDEX IX_VIDEO_genre_active ON dbo.VIDEO(idgenre, active, id DESC);
CREATE INDEX IX_FAVORITE_user_active ON dbo.FAVORITE(userid, active, likedate DESC);
CREATE INDEX IX_SHARE_user_date ON dbo.SHARE(userid, sharedate DESC);
GO

INSERT INTO dbo.GENREVIDEO (id, title)
VALUES
    (1, N'Hành động'),
    (2, N'Tình cảm'),
    (3, N'Hài hước'),
    (4, N'Kinh dị'),
    (5, N'Hoạt hình'),
    (6, N'Viễn tưởng');
GO

-- 123456 -> Lxo0HdjlzbFSxRJApnDHFnpjm6k= with util.MaHoa
INSERT INTO dbo.USERS
    (username, pass, email, fullname, birth, avatar, starday, vip, admin, active)
VALUES
    ('admin', 'Lxo0HdjlzbFSxRJApnDHFnpjm6k=', 'admin@actionmovie.local',
     N'Quản trị viên', '1999-01-01', 'logo.png', CONVERT(date, GETDATE()), 0, 1, 1),
    ('user', 'Lxo0HdjlzbFSxRJApnDHFnpjm6k=', 'user@actionmovie.local',
     N'Người xem thử nghiệm', '2000-01-01', 'logo.png', CONVERT(date, GETDATE()), 0, 0, 1);
GO

INSERT INTO dbo.VIDEO
    (title, poster, views, link, descriptions, active, vip, quality, dayupload, times, idgenre)
VALUES
    (N'Rừng phương Nam', 'datrungphuongnam.webp', 120, 'UiMg566PREA',
     N'Một bộ phim mẫu để kiểm tra chức năng xem phim từ YouTube.', 1, 0, 'HD',
     CONVERT(date, GETDATE()), 120, 1),
    (N'Bệnh viện ma', 'benhvienma.jpg', 85, 'dQw4w9WgXcQ',
     N'Phim mẫu thể loại kinh dị.', 1, 0, 'FULLHD',
     CONVERT(date, GETDATE()), 95, 4),
    (N'Fairy Tail', 'fairytail.jpg', 210, 'dQw4w9WgXcQ',
     N'Video mẫu thể loại hoạt hình.', 1, 0, 'HD',
     CONVERT(date, GETDATE()), 80, 5),
    (N'Cuộc xâm lăng', 'cuocxamlang.jpg', 42, 'dQw4w9WgXcQ',
     N'Video mẫu thể loại viễn tưởng.', 1, 1, '4K',
     CONVERT(date, GETDATE()), 110, 6);
GO

-- Dữ liệu tương tác mẫu để các bộ đếm trên giao diện có thể kiểm tra.
INSERT INTO dbo.FAVORITE (userid, videoid, likedate, active)
VALUES (2, 1, SYSDATETIME(), 1), (2, 3, SYSDATETIME(), 1);

INSERT INTO dbo.SHARE (userid, videoid, emails, sharedate, number)
VALUES (2, 1, 'demo@example.com', SYSDATETIME(), 1);
GO
