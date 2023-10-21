-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th10 21, 2023 lúc 09:41 AM
-- Phiên bản máy phục vụ: 10.6.5-MariaDB
-- Phiên bản PHP: 5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qlsach3`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctpm`
--

DROP TABLE IF EXISTS `ctpm`;
CREATE TABLE IF NOT EXISTS `ctpm` (
  `MaPM` int(11) NOT NULL,
  `MaSach` int(11) NOT NULL,
  `NgayTra` date DEFAULT NULL,
  `TinhTrangSach` int(11) NOT NULL,
  `TinhTrangTra` int(11) DEFAULT NULL,
  `User` varchar(10) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  `GhiChu` varchar(50) COLLATE utf8mb4_vietnamese_ci DEFAULT NULL,
  PRIMARY KEY (`MaPM`,`MaSach`),
  KEY `MaSach` (`MaSach`),
  KEY `User` (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `ctpm`
--

INSERT INTO `ctpm` (`MaPM`, `MaSach`, `NgayTra`, `TinhTrangSach`, `TinhTrangTra`, `User`, `GhiChu`) VALUES
(1, 2, '2020-06-03', 100, 0, 'admin', 'mất sách'),
(1, 78, '2020-06-03', 100, 100, 'admin', 'TT sách tốt'),
(2, 1, '2020-06-25', 100, 0, 'admin', 'Mất sách'),
(2, 2, '2020-06-25', 100, 70, 'admin', 'hư 30%, trễ hạn'),
(2, 3, '2020-06-25', 100, 0, 'admin', 'Mất sách'),
(3, 2, '2020-06-14', 100, 0, 'admin', 'mất sách'),
(3, 13, '2020-06-14', 100, 95, 'admin', 'sách hỏng 5%'),
(4, 2, '2020-07-05', 100, 90, 'admin', 'Hư 10%, trễ hạn'),
(4, 117, '2020-07-05', 100, 100, 'admin', 'sách tốt, trễ hạn'),
(4, 149, '2020-07-05', 100, 100, 'admin', 'sách tốt, trễ hạn'),
(5, 1, '2020-07-05', 100, 50, 'admin', 'sách hỏng 50%, trễ hạn'),
(5, 2, '2020-07-05', 90, 90, 'admin', 'TT sách tốt'),
(6, 1, NULL, 100, NULL, 'admin', NULL),
(6, 2, NULL, 100, NULL, 'admin', NULL),
(7, 3, '2020-07-04', 50, 45, 'admin', ''),
(8, 3, '2020-07-06', 100, 0, 'admin', 'Mất sách'),
(8, 4, '2020-07-06', 100, 0, 'admin', 'Mất sách'),
(9, 1, NULL, 100, NULL, 'admin', NULL),
(9, 114, NULL, 95, NULL, 'admin', NULL),
(10, 10, NULL, 100, NULL, 'admin', NULL),
(10, 85, NULL, 100, NULL, 'admin', NULL),
(11, 12, NULL, 100, NULL, 'admin', NULL),
(11, 13, NULL, 100, NULL, 'admin', NULL),
(11, 101, NULL, 100, NULL, 'admin', NULL),
(12, 37, '2023-10-14', 100, 100, 'admin', 'Tốt'),
(12, 87, NULL, 100, NULL, 'admin', NULL),
(13, 74, NULL, 100, NULL, 'admin', NULL),
(13, 77, NULL, 95, NULL, 'admin', NULL),
(14, 7, NULL, 100, NULL, 'admin', NULL),
(14, 83, '2023-10-14', 100, 99, 'admin', 'Tốt'),
(15, 32, NULL, 100, NULL, 'admin', NULL),
(15, 49, '2023-10-14', 100, 10, 'admin', 'Sách bị hỏng, trễ hạn'),
(15, 96, NULL, 100, NULL, 'admin', NULL),
(16, 83, NULL, 100, NULL, 'admin', NULL),
(17, 30, NULL, 100, NULL, 'admin', NULL),
(17, 34, NULL, 100, NULL, 'admin', NULL),
(17, 56, NULL, 100, NULL, 'admin', NULL),
(18, 30, NULL, 100, NULL, 'admin', NULL),
(18, 31, NULL, 100, NULL, 'admin', NULL),
(19, 63, NULL, 100, NULL, 'admin', NULL),
(19, 76, NULL, 100, NULL, 'admin', NULL),
(19, 79, NULL, 100, NULL, 'admin', NULL),
(20, 44, NULL, 100, NULL, 'admin', NULL),
(20, 48, NULL, 100, NULL, 'admin', NULL),
(21, 81, NULL, 100, NULL, 'admin', NULL),
(22, 93, NULL, 100, NULL, 'admin', NULL),
(23, 90, NULL, 100, NULL, 'admin', NULL),
(23, 92, NULL, 100, NULL, 'admin', NULL),
(24, 27, NULL, 100, NULL, 'admin', NULL),
(24, 29, NULL, 100, NULL, 'admin', NULL),
(24, 95, NULL, 100, NULL, 'admin', NULL),
(25, 43, NULL, 100, NULL, 'admin', NULL),
(26, 29, NULL, 100, NULL, 'admin', NULL),
(26, 62, NULL, 100, NULL, 'admin', NULL),
(26, 83, NULL, 100, NULL, 'admin', NULL),
(27, 56, NULL, 100, NULL, 'admin', NULL),
(27, 98, NULL, 100, NULL, 'admin', NULL),
(28, 96, NULL, 100, NULL, 'admin', NULL),
(29, 80, NULL, 100, NULL, 'admin', NULL),
(30, 71, NULL, 100, NULL, 'admin', NULL),
(30, 83, NULL, 100, NULL, 'admin', NULL),
(30, 92, NULL, 100, NULL, 'admin', NULL),
(31, 83, NULL, 100, NULL, 'admin', NULL),
(32, 91, NULL, 100, NULL, 'admin', NULL),
(33, 23, NULL, 100, NULL, 'admin', NULL),
(33, 72, NULL, 100, NULL, 'admin', NULL),
(34, 28, NULL, 100, NULL, 'admin', NULL),
(35, 26, NULL, 100, NULL, 'dinhvy', NULL),
(35, 39, NULL, 100, NULL, 'dinhvy', NULL),
(35, 65, NULL, 100, NULL, 'dinhvy', NULL),
(36, 23, NULL, 100, NULL, 'dinhvy', NULL),
(36, 34, NULL, 100, NULL, 'dinhvy', NULL),
(36, 67, NULL, 100, NULL, 'dinhvy', NULL),
(37, 43, NULL, 100, NULL, 'dinhvy', NULL),
(38, 20, NULL, 100, NULL, 'huudung', NULL),
(38, 153, NULL, 100, NULL, 'huudung', NULL),
(39, 54, NULL, 100, NULL, 'huudung', NULL),
(39, 158, NULL, 100, NULL, 'huudung', NULL),
(40, 67, NULL, 100, NULL, 'vanchien', NULL),
(40, 93, NULL, 100, NULL, 'vanchien', NULL),
(40, 139, NULL, 100, NULL, 'vanchien', NULL),
(41, 143, NULL, 100, NULL, 'vanchien', NULL),
(42, 129, NULL, 100, NULL, 'vanchien', NULL),
(43, 32, NULL, 100, NULL, 'huudung', NULL),
(43, 164, NULL, 100, NULL, 'huudung', NULL),
(43, 165, NULL, 100, NULL, 'huudung', NULL),
(45, 1, NULL, 100, NULL, 'dinhvy', NULL),
(46, 1, NULL, 1, NULL, 'dinhvy', NULL),
(47, 64, NULL, 100, NULL, 'dinhvy', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `docgia`
--

DROP TABLE IF EXISTS `docgia`;
CREATE TABLE IF NOT EXISTS `docgia` (
  `MaDG` int(11) NOT NULL AUTO_INCREMENT,
  `TenDG` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `SDT` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `DiaChi` varchar(50) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `GioiTinh` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `MatSach` int(11) NOT NULL,
  PRIMARY KEY (`MaDG`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `docgia`
--

INSERT INTO `docgia` (`MaDG`, `TenDG`, `SDT`, `DiaChi`, `GioiTinh`, `MatSach`) VALUES
(1, 'Đỗ Nhật Quang', '0342565857', 'Quận Thủ Đức', 'Nam', 0),
(2, 'Trần Quốc Phong', '0981249458', 'Quận Gò Vấp', 'Nam', 1),
(3, 'Lê Thị Thanh Tiến', '0983216950', 'Quận 2', 'Nữ', 1),
(4, 'Nguyễn Thái Hòa', '0356721980', 'Quận 9', 'Nam', 3),
(5, 'Phan Thanh Phú Thọ', '0914469424', 'Quận Gò Vấp', 'Nam', 2),
(6, 'Hồ Phương Ly', '0522004039', 'Quận Thủ Đức', 'Nữ', 0),
(7, 'Phan Ngọc Phương Nhi', '0682144611', 'Quận 5', 'Nữ', 0),
(8, 'Lê Thị Mỹ Diệu', '0347526510', 'Quận 1', 'Nữ', 0),
(9, 'Nguyễn Thị Mỹ Dung', '0714521035', 'Quận 10', 'Nữ', 0),
(10, 'Lữ Thị Ngọc Hiền', '0996215423', 'Quận 2', 'Nữ', 0),
(11, 'Nguyễn Phương Lan', '0888621540', 'Quận 1', 'Nữ', 0),
(12, 'Đỗ Thị Như Quỳnh', '0732561485', 'Huyện Cần Giờ', 'Nữ', 0),
(13, 'Nguyễn Thị Hà Thuyên', '0962532564', 'Quận 8', 'Nữ', 0),
(14, 'Lê Ngọc Tiến', '0632547125', 'Quận 1', 'Nam', 0),
(15, 'Nguyễn Lê Mỹ Hòa', '0856212356', 'Quận 10', 'Nữ', 0),
(16, 'Phan Phúc Khánh', '0571222356', 'Quận 5', 'Nam', 0),
(17, 'Trần Thanh Lâm', '0975263510', 'Quận 2', 'Nam', 0),
(18, 'Đỗ Nguyễn Vân Anh', '0974142514', 'Quận 1', 'Nữ', 0),
(19, 'Nguyễn Quốc Huy', '0814252012', 'Quận 9', 'Nam', 0),
(20, 'Nguyễn Thanh Hiển', '0345112456', 'Quận 9', 'Nam', 0),
(21, 'Trần Thanh Hiếu', '0845124785', 'Quận 10', 'Nam', 0),
(22, 'Đỗ Mẫn Nhi', '0641114507', 'Quận 2', 'Nữ', 0),
(23, 'Ngô Minh Thư', '0774150121', 'Quận 7', 'Nữ', 0),
(24, 'Ngô Khánh Hà', '0841112045', 'Quận 9', 'Nữ', 0),
(25, 'Trần Khôi Nguyên', '0888142012', 'Quận 5', 'Nam', 0),
(26, 'Tạ Hồng Đức', '0957154200', 'Quận Gò Vấp', 'Nam', 0),
(27, 'Ngô Thúy Hiền', '0314211212', 'Quận 12', 'Nữ', 0),
(28, 'Đỗ Ngọc Anh', '0314211212', 'Quận 3', 'Nữ', 0),
(29, 'Phan Thanh Kha', '0984145201', 'Quận 10', 'Nam', 0),
(30, 'Trần Xuân Lộc', '0654121450', 'Quận Tân Phú', 'Nam', 0),
(31, 'Hà Doãn Minh', '0714520124', 'Quận Tân Phú', 'Nam', 0),
(32, 'Đào Phạm Toàn', '0614017894', 'Quận Tân Phú', 'Nam', 0),
(33, 'Hồ Thị Trinh', '0341568547', 'Quận Tân Bình', 'Nữ', 0),
(34, 'Phan Hồ Anh Thư', '0341510412', 'Quận Tân Phú', 'Nữ', 0),
(35, 'Đỗ Ngọc Thiên', '0874114120', 'Quận 3', 'Nam', 0),
(36, 'Hồ Đăng Khoa', '0651425011', 'Quận 3', 'Nam', 0),
(37, 'Lê Chí Trung', '0874145145', 'Quận 1', 'Nam', 0),
(38, 'Bùi Thúy Kiều', '0645147887', 'Quận 12', 'Nữ', 0),
(39, 'Phan Ngọc Thư', '0741478947', 'Quận 10', 'Nữ', 0),
(40, 'Phan Thành Nhân', '0654178941', 'Quận 7', 'Nam', 0),
(41, 'Lê Khôi Thành', '0347845120', 'Quận Gò Vấp', 'Nam', 0),
(42, 'Mã Tiến Huy', '0999874520', 'Quận Gò Vấp', 'Nam', 0),
(43, 'Châu Ngọc Minh Thư', '0357125894', 'Quận Bình Thạnh', 'Nữ', 0),
(44, 'Nguyễn Trần Hà Đức', '0871421501', 'Quận 3', 'Nam', 0),
(45, 'Nguyễn Hữu Nhật Tân', '0614631549', 'Quận 1', 'Nam', 0),
(46, 'Phan Viết Đức', '0417521483', 'Quận 10', 'Nam', 0),
(47, 'Phan Vĩnh Khoa', '0971420156', 'Quận 9', 'Nam', 0),
(48, 'Võ Minh Khang', '0991478156', 'Quận 7', 'Nam', 0),
(49, 'Nguyễn Thu Huyền', '0624710586', 'Quận 5', 'Nữ', 0),
(50, 'Đào Thị Thu Hiền', '0714508041', 'Quận 3', 'Nữ', 0),
(51, 'Trần Nhật Linh', '0314540478', 'Quận 10', 'Nam', 0),
(52, 'Hồ Minh Huy', '0971542314', 'Quận Thủ Đức', 'Nam', 0),
(53, 'Bùi Xuân Hồng', '0982142357', 'Quận Tân Bình', 'Nữ', 0),
(54, 'Nguyễn Sơn Ca', '0315677152', 'Quận Tân Bình', 'Nữ', 0),
(55, 'Nguyễn Bảo Nghĩa', '0417622478', 'Quận 5', 'Nam', 0),
(56, 'Phan Thị Thùy Mi', '0687415245', 'Quận 8', 'Nữ', 0),
(57, 'Ngô Nguyễn Khôi', '0971423103', 'Quận 1', 'Nam', 0),
(58, 'Bùi Thành Hưng', '0571204612', 'Quận 9', 'Nam', 0),
(59, 'Lê Minh Thành', '09142113010', 'Quận 2', 'Nam', 0),
(60, 'Trương Yến Nhi', '04120703421', 'Quận 3', 'Nữ', 0),
(61, 'Nguyễn Hữu Hiếu', '0972163012', 'Quận 5', 'Nam', 0),
(62, 'Trần Tấn Công', '0782143015', 'Quận 2', 'Nam', 0),
(63, 'Đoàn Thúy Ngân', '0535344210', 'Quận 8', 'Nữ', 0),
(64, 'Võ Hoàng Tuấn', '0341258710', 'Quận Thủ Đức', 'Nam', 0),
(65, 'Mai Thanh Hiền', '0871534453', 'Quận Thủ Đức', 'Nữ', 0),
(66, 'Nguyễn Bảo Trân', '0789653100', 'Quận Tân Phú', 'Nữ', 0),
(67, 'Bạch Thị Ngọc', '0345410053', 'Quận Tân Bình', 'Nữ', 0),
(68, 'Bùi Thị Kim Thoa', '0542113033', 'Quận 9', 'Nữ', 0),
(69, 'Nguyễn Quốc Linh', '0333333333', 'Bắc Ninh', 'Nam', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phieumuon`
--

DROP TABLE IF EXISTS `phieumuon`;
CREATE TABLE IF NOT EXISTS `phieumuon` (
  `MaPM` int(11) NOT NULL AUTO_INCREMENT,
  `MaDG` int(11) NOT NULL,
  `NgayMuon` date NOT NULL,
  `NgayHenTra` date NOT NULL,
  `SoLuongMuon` int(11) NOT NULL,
  `User` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  PRIMARY KEY (`MaPM`),
  KEY `MaDG` (`MaDG`),
  KEY `User` (`User`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `phieumuon`
--

INSERT INTO `phieumuon` (`MaPM`, `MaDG`, `NgayMuon`, `NgayHenTra`, `SoLuongMuon`, `User`) VALUES
(1, 1, '2023-10-01', '2023-11-01', 2, 'admin'),
(2, 2, '2023-10-01', '2023-11-01', 3, 'admin'),
(3, 3, '2023-10-01', '2023-11-01', 2, 'admin'),
(4, 10, '2023-10-01', '2023-11-01', 3, 'admin'),
(5, 26, '2023-10-01', '2023-11-01', 2, 'admin'),
(6, 18, '2023-10-01', '2023-11-01', 2, 'admin'),
(7, 7, '2023-10-01', '2023-11-01', 1, 'admin'),
(8, 5, '2023-10-01', '2023-11-01', 2, 'admin'),
(9, 20, '2023-10-01', '2023-11-01', 2, 'admin'),
(10, 25, '2023-10-01', '2023-11-01', 2, 'admin'),
(11, 52, '2023-10-01', '2023-11-01', 3, 'admin'),
(12, 32, '2023-10-01', '2023-11-01', 2, 'admin'),
(13, 6, '2023-10-01', '2023-11-01', 2, 'admin'),
(14, 9, '2023-10-01', '2023-11-01', 2, 'admin'),
(15, 28, '2023-10-01', '2023-11-01', 3, 'admin'),
(16, 59, '2023-10-01', '2023-11-01', 1, 'admin'),
(17, 55, '2023-10-01', '2023-11-01', 3, 'admin'),
(18, 33, '2023-10-01', '2023-11-01', 2, 'admin'),
(19, 63, '2023-10-01', '2023-11-01', 3, 'admin'),
(20, 44, '2023-10-01', '2023-11-01', 2, 'admin'),
(21, 37, '2023-10-01', '2023-11-01', 1, 'admin'),
(22, 35, '2023-10-01', '2023-11-01', 1, 'admin'),
(23, 15, '2023-10-01', '2023-11-01', 2, 'admin'),
(24, 24, '2023-10-01', '2023-11-01', 3, 'admin'),
(25, 42, '2023-10-01', '2023-11-01', 1, 'admin'),
(26, 46, '2023-10-01', '2023-11-01', 3, 'admin'),
(27, 12, '2023-10-01', '2023-11-01', 2, 'admin'),
(28, 19, '2023-10-01', '2023-11-01', 1, 'admin'),
(29, 30, '2023-10-01', '2023-11-01', 1, 'admin'),
(30, 21, '2023-10-01', '2023-11-01', 3, 'admin'),
(31, 34, '2023-10-01', '2023-11-01', 1, 'admin'),
(32, 13, '2023-10-01', '2023-11-01', 1, 'admin'),
(33, 8, '2023-10-01', '2023-11-01', 2, 'admin'),
(34, 23, '2023-10-01', '2023-11-01', 1, 'admin'),
(35, 29, '2023-10-01', '2023-11-01', 3, 'dinhvy'),
(36, 11, '2023-10-01', '2023-11-01', 3, 'dinhvy'),
(37, 58, '2023-10-01', '2023-11-01', 1, 'dinhvy'),
(38, 45, '2023-10-01', '2023-11-01', 2, 'huudung'),
(39, 36, '2023-10-01', '2023-11-01', 2, 'huudung'),
(40, 31, '2023-10-01', '2023-11-01', 3, 'vanchien'),
(41, 51, '2023-10-01', '2023-11-01', 1, 'vanchien'),
(42, 17, '2023-10-01', '2023-11-01', 1, 'vanchien'),
(43, 14, '2023-10-01', '2023-11-01', 3, 'huudung'),
(45, 1, '2023-10-19', '2023-11-19', 1, 'dinhvy'),
(46, 69, '2023-10-19', '2023-11-19', 10, 'dinhvy'),
(47, 69, '2023-10-19', '2023-11-19', 1, 'dinhvy');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `sach`
--

DROP TABLE IF EXISTS `sach`;
CREATE TABLE IF NOT EXISTS `sach` (
  `MaSach` int(11) NOT NULL AUTO_INCREMENT,
  `TenSach` varchar(100) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TenTG` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `NhaXB` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `TheLoai` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `GiaTien` double NOT NULL,
  PRIMARY KEY (`MaSach`)
) ENGINE=InnoDB AUTO_INCREMENT=187 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `sach`
--

INSERT INTO `sach` (`MaSach`, `TenSach`, `TenTG`, `NhaXB`, `TheLoai`, `SoLuong`, `GiaTien`) VALUES
(1, 'Cơ sở dữ liệu', 'Vũ Minh Sang', 'NXB ĐHQG TP.HCM', 'Giáo trình', 8, 100000),
(2, 'Đại số tuyến tính', 'Đặng Lệ Thúy', 'NXB ĐHQG TP.HCM', 'Giáo trình', 10, 50000),
(3, 'Luật Im Lặng', 'Mario Puzo', 'ĐHQG Hà Nội', 'Văn học nước ngoài', 10, 71000),
(4, 'Tuổi trẻ hoang dại', 'Nguyễn Ngọc Thạch', 'NXB Văn Học', 'Kỹ năng sống', 9, 95000),
(5, '3 Phút Sơ Cứu', 'Ngô Đức Hùng', 'NXB Thế Giới', 'Chăm sóc sức khỏe', 11, 125000),
(6, 'Gửi tuổi trẻ yêu thương', 'Kim Wook', 'NXB Dân Trí', 'Kỹ năng sống', 10, 109000),
(7, 'Tâm lý học tích cực', 'Edward Hoffman', 'NXB Lao Động', 'Kỹ năng sống', 9, 129000),
(8, 'Quy tắc 5 giây', 'Mel Robbins', 'NXB Thế Giới', 'Kỹ năng sống', 10, 135000),
(9, 'Phương pháp tạo động lực trong công việc', 'Jon Sternfeld', 'NXB Lao Động', 'Kỹ năng sống', 10, 158000),
(10, 'Chạm', 'Werner Bartens', 'NXB Thanh Niên', 'Kỹ năng sống', 9, 122000),
(11, 'Chủ nghĩa tối giản', 'Fields Millburn', 'NXB Lao Động Xã Hội', 'Kỹ năng sống', 10, 139000),
(12, 'Văn phòng không nhạt nhẽo', 'Hương Lan', 'NXB Dân Trí', 'Kỹ năng sống', 9, 80000),
(13, 'Đời thay đổi khi ta thôi đẩy', 'Sarah Knight', 'NXB Thế Giới', 'Kỹ năng sống', 9, 92000),
(14, 'Điều vĩ đại đời thường', 'Robin Sharma', 'NXB Trẻ', 'Kỹ năng sống', 10, 73000),
(15, 'Đừng bao giờ đi ăn một mình', 'Keith Ferrazzi', 'NXB Trẻ', 'Kỹ năng sống', 10, 95000),
(16, 'Siêu trí nhớ', 'Dominic O’Brien', 'NXB Lao Động', 'Kỹ năng sống', 10, 99000),
(17, 'Mặc đẹp để thành công', 'Nishimura Yukiko', 'NXB Thế Giới', 'Kỹ năng sống', 10, 99000),
(18, 'Trở thành siêu nhân ghi nhớ', 'Oopsy', 'NXB Thế Giới', 'Kỹ năng sống', 10, 89000),
(19, 'Mỗi ngày một chút thôi', 'Nguyễn Thái Duy', 'NXB Tổng Hợp TP.HCM', 'Kỹ năng sống', 10, 109000),
(20, 'Sức mạnh của sự tử tế', 'Linda Kaplan Thaler', 'NXB Tri Thức', 'Kỹ năng sống', 9, 68000),
(21, 'Đọc sách siêu tốc', 'Christian Gruning', 'NXB Lao Động', 'Kỹ năng sống', 10, 69000),
(22, 'Bạn chính là thông điệp', 'Roger Ailes', 'NXB Thanh Hóa', 'Kỹ năng sống', 10, 79000),
(23, 'Bí quyết học nhanh nhớ lâu', 'Jonathan Hancock', 'NXB Trẻ', 'Kỹ năng sống', 18, 48000),
(24, 'Bài học của Thầy', 'Nhóm tác giả', 'NXB Hà Nội', 'Kỹ năng sống', 20, 35000),
(25, 'Đạo lý sống đẹp', 'Thế Anh', 'NXB VHTT', 'Kỹ năng sống', 20, 36000),
(26, 'Góc nhìn của người Do Thái', 'Robert Fulghum', 'NXB Tổng Hợp TP.HCM', 'Kỹ năng sống', 9, 80000),
(27, 'Ca Dao Giảng Luận', 'Thuần Phong', 'NXB Tri Thức', 'Văn học Việt Nam', 4, 117000),
(28, 'Kính thưa liền thị', 'Nguyễn Duy', 'NXB Phụ Nữ', 'Văn học Việt Nam', 9, 79000),
(29, 'Số Đỏ', 'Vũ Trọng Phụng', 'NXB Văn Học', 'Văn học Việt Nam', 13, 40000),
(30, 'Đời Thừa', 'Nam Cao', 'NXB Văn Học', 'Văn học Việt Nam', 8, 65000),
(31, 'Truyện Tây Bắc', 'Tô Hoài', 'NXB Kim Đồng', 'Văn học Việt Nam', 14, 50000),
(32, 'Truyện Kiều', 'Nguyễn Du', 'NXB Văn học', 'Văn học Việt Nam', 13, 45000),
(33, 'Thi nhân Việt Nam', 'Hoài Thanh', 'NXB Văn học', 'Văn học Việt Nam', 15, 89000),
(34, 'Cô ấy khiêu vũ một mình', 'Tịnh Bảo', 'NXB Trẻ', 'Văn học Việt Nam', 8, 66000),
(35, 'Ngoại ô thương nhớ', 'Phi Tân', 'NXB Trẻ', 'Văn học Việt Nam', 10, 97000),
(36, 'Thành ngữ và tục ngữ Việt Nam', 'Nhóm tác giả', 'NXB Hội Nhà Văn', 'Văn học Việt Nam', 20, 60000),
(37, 'Những tinh cầu trôi dạt', 'Đỗ Bích Ngọc', 'NXB Dân Trí', 'Văn học Việt Nam', 15, 59000),
(38, 'Áo trắng biển lặng', 'Nhiều tác giả', 'NXB Trẻ', 'Văn học Việt Nam', 20, 35000),
(39, 'Kim, Vân, Kiều truyện', 'Trương Vĩnh Ký', 'NXB Văn Học', 'Văn học Việt Nam', 9, 95000),
(40, 'Người canh giữ phù dung', 'Nguyệt Chu', 'NXB Văn Học', 'Văn học Việt Nam', 10, 68000),
(41, 'Thơ văn Hàn Mạc Tử', 'Trần Quang Chu', 'NXB Văn Học', 'Văn học Việt Nam', 10, 200000),
(42, 'Sài côn cố sự', 'Bằng Giang', 'NXB Tổng Hợp TP.HCM', 'Văn học Việt Nam', 10, 120000),
(43, 'Trúng số độc đắc', 'Vũ Trọng Phụng', 'NXB Văn Học', 'Văn học Việt Nam', 8, 12085),
(44, 'Lòng dạ đàn bà', 'Nguyễn Ngọc Thạch', 'NXB Hà Nội', 'Văn học Việt Nam', 9, 89000),
(45, 'Những ngày thơ ấu', 'Nguyên Hồng', 'NXB Văn Học', 'Văn học Việt Nam', 20, 40000),
(46, 'Trong cơn say níu sợi dây đứt', 'Hoàng Công Danh', 'NXB Trẻ', 'Văn học Việt Nam', 10, 79000),
(47, 'Người viết tình yêu', 'Nguyễn Ngọc Hà', 'NXB Trẻ', 'Văn học Việt Nam', 10, 82000),
(48, 'Một thời ngang dọc', 'Hoàng Ly', 'NXB Hội Nhà Văn', 'Văn học Việt Nam', 9, 190000),
(49, 'Mình và họ', 'Nguyễn Bình Phương', 'NXB Trẻ', 'Văn học Việt Nam', 10, 100000),
(50, 'Vũ trung tùy bút', 'Phạm Đình Hổ', 'NXB Văn Hóa Dân Tộc', 'Văn học Việt Nam', 10, 75000),
(51, 'Mắt biếc', 'Nguyễn Nhật Ánh', 'NXB Trẻ', 'Văn học Việt Nam', 15, 110000),
(52, 'Cỏ dại thênh thang', 'Bùi Tiểu Quyên', 'NXB Trẻ', 'Văn học Việt Nam', 10, 43000),
(53, 'Những đứa con cổ tích', 'Bạch Đằng', 'NXB Trẻ', 'Văn học Việt Nam', 10, 96000),
(54, 'Lời chào quá khứ', 'Trung Trung Đỉnh', 'NXB Trẻ', 'Văn học Việt Nam', 9, 105000),
(55, 'Lớn lên sẽ khác', 'Hai Mươi', 'NXB Văn Học', 'Văn học Việt Nam', 10, 89000),
(56, 'Gió lạnh đầu mùa', 'Thạch Lam', 'NXB Văn Học', 'Văn học Việt Nam', 8, 50000),
(57, 'Luận anh hùng', 'Dịch Trung Thiên', 'NXB Văn Học', 'Văn học nước ngoài', 10, 139000),
(58, 'Lũ trẻ đường ray', 'Edith Nesbit', 'NXB Văn Học', 'Văn học nước ngoài', 10, 90000),
(59, 'Một nụ cười nào đó', 'Francoise Sagan', 'NXB Văn Hóa', 'Văn học nước ngoài', 10, 86000),
(60, 'Cậu nhóc gặt gió', 'William Kamkwamba', 'NXB Thanh Niên', 'Văn học nước ngoài', 10, 115000),
(61, 'Quo Vadis', 'Henryk Sienkiewicz', 'NXB Văn Học', 'Văn học nước ngoài', 10, 350000),
(62, 'Giã từ vũ khí', 'Ernest Hemingway', 'NXB Văn Học', 'Văn học nước ngoài', 9, 149000),
(63, 'Thần thoại Bắc Âu', 'Quỳnh Liên', 'NXB Kim Đồng', 'Văn học nước ngoài', 9, 96000),
(64, 'Đệ tử quy', 'Lý Dục Tú', 'NXB Dân Trí', 'Văn học nước ngoài', 9, 85000),
(65, 'Hoa Tuylip đen', 'Alexandre Dumas', 'NXB Văn Học', 'Văn học nước ngoài', 9, 100000),
(66, 'Trò chuyện với ác quỷ', 'Mitsuro Sato', 'NXB Thế Giới', 'Văn học nước ngoài', 10, 159000),
(67, 'Thành cát tư hãn', 'René Grousset', 'NXB Văn Học', 'Văn học nước ngoài', 8, 150000),
(68, 'Sau ánh hào quang', 'Leslie Odom Jr', 'NXB Thế Giới', 'Văn học nước ngoài', 10, 81000),
(69, 'Hai mươi năm sau', 'Alexandre Dumas', 'NXB Văn Học', 'Văn học nước ngoài', 10, 270000),
(70, 'Nghìn lẻ một đêm', 'Antoine Galland', 'NXB Văn Học', 'Văn học nước ngoài', 10, 235000),
(71, 'Tội ác dưới ánh mặt trời', 'Agatha Christie', 'NXB Trẻ', 'Văn học nước ngoài', 9, 120000),
(72, 'Ngôi nhà nghìn hành lang', 'Diana Wynne Jones', 'NXB Hội Nhà Văn', 'Văn học nước ngoài', 9, 108000),
(73, 'Một chuyện đời', 'Sato Shogo', 'NXB Hà Nội', 'Văn học nước ngoài', 10, 145000),
(74, 'Nhà thờ Đức Bà Paris', 'Victor Hugo', 'NXB Văn Học', 'Văn học nước ngoài', 9, 165000),
(75, 'Hoa hồng xa mạc', 'Luis Sepulveda', 'NXB Hội Nhà Văn', 'Văn học nước ngoài', 15, 70000),
(76, 'Ông già và biển cá', 'Ernest Hemingway', 'NXB Tổng Hợp TP.HCM', 'Văn học nước ngoài', 14, 80000),
(77, 'Tiếng gọi nơi hoang dã', 'Jack London', 'NXB Văn Học', 'Văn học nước ngoài', 9, 85000),
(78, 'Bắt trẻ đồng xanh', 'David Salinger', 'NXB Hội Nhà Văn', 'Văn học nước ngoài', 10, 100000),
(79, 'Tâm hồn cao thượng', 'Edmondo De Amicis', 'NXB Hội Nhà Văn', 'Văn học nước ngoài', 19, 60000),
(80, 'Ác quỷ nam kinh', 'Mo Hayder', 'NXB Hội Nhà Văn', 'Văn học nước ngoài', 14, 116000),
(81, 'Quy Trình Bán Hàng Chuyên Nghiệp', 'Cory Bray', 'NXB Lao Động', 'Quản lý kinh doanh', 9, 119000),
(82, 'Big Data Cho Nhà Quản Lý', 'Mike Malmgren', 'NXB Công Thương', 'Quản lý kinh doanh', 10, 149000),
(83, 'Quản Trị Rủi Ro Pháp Lý Trong Kinh Doanh', 'Đỗ Đăng Khoa', 'NXB Tổng hợp TP.HCM', 'Quản lý kinh doanh', 6, 198000),
(84, 'Ngân Hàng Biết Tìm Khách Hàng Ở Đâu?', 'Trịnh Minh Thảo', 'NXB Hồng Đức', 'Quản lý kinh doanh', 10, 159000),
(85, 'Trải Nghiệm Khách Hàng', 'Blake Morgan', 'NXB Công Thương', 'Quản lý kinh doanh', 9, 139000),
(86, 'Tương Lai Thuộc Về Châu Á', 'Parag Khanna', 'NXB Trẻ', 'Quản lý kinh doanh', 8, 210000),
(87, 'Tư Duy Amazon - Think Like Amazon', 'John Rossman', 'NXB Lao Động', 'Quản lý kinh doanh', 9, 159000),
(88, 'TikTok Marketing', 'Markus Rach', 'NXB Thanh Niên', 'Quản lý kinh doanh', 10, 139000),
(89, '10 Loại Hình Đổi Mới Sáng Tạo', ' Larry Keeley', 'NXB Công Thương', 'Quản lý kinh doanh', 10, 249000),
(90, 'Siêu Năng Suất', 'Chris Bailey', 'NXB Công Thương', 'Quản lý kinh doanh', 9, 139000),
(91, 'Nâng Tầm Dịch Vụ', 'Ron Kaufman', 'NXB Trẻ', 'Quản lý kinh doanh', 9, 150000),
(92, 'Học Jack Ma Khởi Nghiệp', 'Trịnh Nhất Quần', 'NXB Dân Trí', 'Quản lý kinh doanh', 8, 150000),
(93, 'Quản Trị Theo Phong Cách Cờ Vây', 'Bất Thức Nguyệt', 'NXB Phụ Nữ', 'Quản lý kinh doanh', 8, 83000),
(95, 'Hùng Mạnh Hơn Sau Khủng Hoảng', 'Ian I Mitroff', 'NXB Công Thương', 'Quản lý kinh doanh', 9, 129000),
(96, 'Dẫn Dắt Sự Thay Đổi', 'John P Kotter', 'NXB Thế Giới', 'Quản lý kinh doanh', 8, 129000),
(97, 'Đi Ra Thế Giới Với Người Khổng Lồ', 'Phan Văn Phương', 'NXB Thế Giới', 'Quản lý kinh doanh', 10, 119000),
(98, 'Kiếm Tiền Thời Khủng Hoảng', 'Martin D Weiss', 'NXB Phụ Nữ', 'Quản lý kinh doanh', 9, 169000),
(99, 'Tư Duy Đơn Giản', 'Ken Segall', 'NXB Thế Giới', 'Quản lý kinh doanh', 10, 138000),
(100, 'Những Nhà Kinh Tế Tiên Phong', 'Nathan Schneider', 'NXB Công Thương', 'Quản lý kinh doanh', 10, 139000),
(101, 'Mưu Lược Trong Kinh Doanh', 'Hoàng Văn Tuấn', 'NXB Lao Động', 'Quản lý kinh doanh', 9, 130000),
(102, 'Quản Lý Chuyên Nghiệp', 'Dottie Schindlinger', 'NXB Lao Động', 'Quản lý kinh doanh', 15, 119000),
(104, 'Đế Chế Alibaba', 'Trần Vĩ', 'NXB Hồng Đức', 'Quản lý kinh doanh', 10, 172000),
(105, 'Muôn Kiếp Nhân Sinh', 'Nguyên Phong', 'NXB Tổng Hợp TP.HCM', 'Kiến thức bách khoa', 10, 228000),
(106, 'Câu Đố Việt Nam', 'Mai Chi', 'NXB Hồng Đức', 'Kiến thức bách khoa', 20, 58000),
(107, 'Giải Mã Siêu Trí Tuệ', 'Vishen Lakhian', 'NXB Công Thương', 'Kiến thức bách khoa', 10, 159000),
(108, 'Khoa Học Thật Là Vui', 'Triệu Từ Húc', 'NXB Thanh Niên', 'Kiến thức bách khoa', 30, 25000),
(109, 'Thời Cá Voi Biết Đi', 'Dougal Dixon', 'NXB ĐH Sư Phạm', 'Kiến thức bách khoa', 10, 98000),
(110, 'Nông Trại Nuôi Sống Chúng Ta', 'Nancy Castaldo', 'NXB ĐH Sư Phạm', 'Kiến thức bách khoa', 10, 120000),
(111, '30 Giây Y Học', 'Gabrielle M Finn', 'NXB Kim Đồng', 'Kiến thức bách khoa', 10, 130000),
(112, '30 Giây AI Và Robot Học', 'Luis de Miranda', 'NXB Kim Đồng', 'Kiến thức bách khoa', 10, 130000),
(113, 'Dấu Ấn Khơi Dòng Văn Hóa Việt', 'Lê Minh Quốc', 'NXB Tổng Hợp TP.HCM', 'Kiến thức bách khoa', 5, 260000),
(114, 'Kể Tiếp Chuyện Bác Hồ', 'Trần Quân Ngọc', 'NXB Tổng Hợp TP.HCM', 'Kiến thức bách khoa', 14, 70000),
(115, 'Thế Giới Sẽ Ra Sao?', 'Nhiều Tác Giả', 'NXB Dân Trí', 'Kiến thức bách khoa', 10, 129000),
(116, 'Giáo Trình Xã Hội Học Đại Cương', 'Trương Thị Hiền', 'NXB Tổng Hợp TP.HCM', 'Kiến thức bách khoa', 10, 90000),
(117, 'Đại Việt Sử Ký Tục Biên (1676 - 1789)', 'VNC Hàn Nôm', 'NXB Hồng Đức', 'Kiến thức bách khoa', 5, 300000),
(118, 'Vĩ Đại Một Con Người', 'Trần Bạch Đằng', 'NXB Trẻ', 'Kiến thức bách khoa', 10, 52000),
(119, '99 Thử Thách Từ Vựng', 'Sarah Khan', 'NXB Lao Động', 'Kiến thức bách khoa', 10, 75000),
(120, 'Về Trung Quốc', 'Henry Kissinger', 'NXB Hội Nhà Văn', 'Kiến thức bách khoa', 5, 299000),
(121, 'Lược Sử Kinh Tế Học', 'Niall Kishtainy', 'NXB Thế Giới', 'Kiến thức bách khoa', 10, 135000),
(122, 'Danh Nhân Quân Sự Việt Nam', 'Lê Minh Quốc', 'NXB Trẻ', 'Kiến thức bách khoa', 10, 125000),
(123, 'Tương Lai Nhân Loại', 'Michio Kaku', 'NXB Thế Giới', 'Kiến thức bách khoa', 10, 179000),
(124, 'Ngoại Giao Của Chính Quyền Sài Gòn', 'Trần Nam Tiến', 'NXB Dân Trí', 'Kiến thức bách khoa', 10, 159000),
(125, 'Thượng Kinh Ký Sự', 'Lê Hữu Trác', 'NXB Hà Nội', 'Kiến thức bách khoa', 10, 69000),
(126, 'Vừa A Dính', 'Tô Hoài', 'NXB Kim Đồng', 'Kiến thức bách khoa', 25, 12000),
(127, 'Kể Chuyện Bác Hồ', 'Khánh Linh', 'NXB Lao Động', 'Kiến thức bách khoa', 20, 50000),
(128, 'Vật Lý Của Tương Lai', 'Michio Kaku', 'NXB Thế Giới', 'Kiến thức bách khoa', 10, 199000),
(129, 'Cuộc Chiến Lỗ Đen', 'Leonard Susskind', 'NXB Trẻ', 'Kiến thức bách khoa', 9, 192000),
(130, 'Khoa Học Cực Ngầu', 'Daniel Tatarsky', 'NXB Thế Giới', 'Kiến thức bách khoa', 10, 79000),
(132, 'Không Hoảng Loạn Khi Con Kết Bạn', 'Michael Thompson', 'NXB Lao Động', 'Tâm lý giáo dục', 10, 99000),
(133, 'Hãy Nói Với Con Rằng Con Giỏi Lắm', 'Nguyệt Minh', 'NXB Lao Động', 'Tâm lý giáo dục', 15, 55000),
(134, 'Con Là Của Báu', 'Tadashi Tsujii', 'NXB Lao Động', 'Tâm lý giáo dục', 15, 43000),
(135, 'Cha Mẹ Pháp Không Đầu Hàng', 'Pamela Druckerman', 'NXB Lao Động', 'Tâm lý giáo dục', 15, 45000),
(136, 'Vì Chơi Là Học - Dành Cho Trẻ', 'Tadashi Tsujii', 'NXB Lao Động', 'Tâm lý giáo dục', 15, 43000),
(137, 'Trí Thông Minh Đa Dạng Của Trẻ', 'Yoon OK In', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 129000),
(138, '4 Món Quà Tặng Con', 'Barbara Coloroso', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 109000),
(139, 'Cùng Con Bước Qua Tiểu Học', 'Laura Numeroff', 'NXB Văn Học', 'Tâm lý giáo dục', 9, 89000),
(140, 'Trong Sách Có Gì Mà Vui Thế?', 'Mem Fox', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 89000),
(141, 'Cha Mẹ Vô Điều Kiện', 'Nguyễn Thị Thu Hiền', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 129000),
(142, 'Con Chúng Ta Không Sao Đâu', 'Cheonseok Suh', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 129000),
(143, 'Cùng Con Học Nói', 'Sally Ward', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 9, 129000),
(144, 'Học Làm Cha Mẹ Hiệu Quả', 'Thomas Gordon', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 109000),
(145, 'Dạy Con Tập Bơi', 'Usborne', 'NXB Lao Động', 'Tâm lý giáo dục', 10, 79000),
(146, 'Giúp Con Phát Triển Ngôn Ngữ', 'Kato Kumiko', 'NXB Văn Học', 'Tâm lý giáo dục', 10, 69000),
(147, 'Bí Quyết Giúp Con Giỏi Tiếng Anh', 'Claire Selby', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 49000),
(148, 'Cha Mẹ Thời Đại Kĩ Thuật Số', 'Shin Yee Jin', 'NXB Văn Học', 'Tâm lý giáo dục', 10, 109000),
(149, 'Chờ Đến Mẫu Giáo Thì Đã Muộn', 'Ibuka Masaru', 'NXB Văn Học', 'Tâm lý giáo dục', 10, 69000),
(150, 'Người Mẹ Tốt Hơn Là Người Thầy Tốt', 'Doãn Kiến Lợi', 'NXB Văn Học', 'Tâm lý giáo dục', 10, 139000),
(151, 'Nuôi Dạy Con Theo Phương Pháp Montessori', 'Quốc Tú Hoa', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 75000),
(152, 'Mẹ Dành Cả Thế Giới Cho Con', 'Lý Xuân Lợi', 'NXB Phụ Nữ', 'Tâm lý giáo dục', 10, 90000),
(153, 'Những Ngộ Nhận “Vì Sức Khỏe”', 'Toshio Akitsu', 'NXB Phụ Nữ', 'Chăm sóc sức khỏe', 9, 109000),
(154, 'Sống Mạnh Khỏe Nhờ Ăn Uống', 'Lê Duyên Hải', 'NXB Lao Động', 'Chăm sóc sức khỏe', 10, 80000),
(155, 'Thiền Chữa Lành Thân Và Tâm', 'Tulku Thondup', 'NXB Hà Nội', 'Chăm sóc sức khỏe', 10, 95000),
(156, 'Diện Chẩn Điều Khiển Liệu Pháp', 'Bùi Quốc Châu', 'NXB Đà Nẵng', 'Chăm sóc sức khỏe', 15, 38000),
(157, 'Định Ninh Tôi Học Mạch', 'Lê Đức Thiếp', 'NXB Dân Trí', 'Chăm sóc sức khỏe', 10, 150000),
(158, 'Nhịn Ăn Gián Đoạn', 'Gin Stephens', 'NXB Thế Giới', 'Chăm sóc sức khỏe', 9, 99000),
(159, 'Tranh Châm Cứu Giải Phẫu Thực Dụng', 'Lê Quý Ngưu', 'NXB Thế Giới', 'Chăm sóc sức khỏe', 10, 120000),
(160, 'Ám Ảnh Ăn Sạch', 'Renee Mc Gregor', 'NXB Công Thương', 'Chăm sóc sức khỏe', 10, 85000),
(161, 'Y Học Thực Chứng', 'Nguyễn Văn Tuấn', 'NXB Tổng Hợp TP.HCM', 'Chăm sóc sức khỏe', 10, 145000),
(162, 'Phế Đại Trường Trong Đông Y', 'Hoàng Duy Tân', 'NXB Dân Trí', 'Chăm sóc sức khỏe', 10, 180000),
(163, 'Thực Hành Thiền Định', 'Matthieu Ricard', 'NXB Hà Nội', 'Chăm sóc sức khỏe', 10, 65000),
(164, 'Dưỡng Sinh', 'Nguyễn Mạnh Trí', 'NXB Tổng Hợp TP.HCM', 'Chăm sóc sức khỏe', 14, 55000),
(165, 'Sức Khỏe Gia Đình', 'Đỗ Hồng Ngọc', 'NXB Tổng Hợp TP.HCM', 'Chăm sóc sức khỏe', 14, 90000),
(166, 'Giải tích 1', 'Lê Hoàng Tuấn', 'NXB ĐHQG TP.HCM', 'Giáo trình', 50, 75000),
(167, 'Giải tích 2', 'Lê Hoàng Tuấn', 'NXB ĐHQG TP.HCM', 'Giáo trình', 50, 80000),
(168, 'Lý Luận Nhà Nước Và Pháp Luật', 'Lê Vũ Nam', 'NXB ĐHQG TP.HCM', 'Giáo trình', 20, 96000),
(169, 'Tư Tưởng Hồ Chí Minh', 'Bộ Giáo Dục', 'NXB Chính Trị QG', 'Giáo trình', 100, 22000),
(170, 'Đường Lối Cách Mạng Của Đảng Cộng Sản VN', 'Bộ Giáo Dục', 'NXB Chính Trị QG', 'Giáo trình', 100, 30000),
(171, 'Phát Triển Ứng Dụng Web', 'Trần Khánh Lâm', 'NXB ĐHQG TP.HCM', 'Giáo trình', 50, 56000),
(172, 'Phân Tích Thiết Kế HTTT', 'Cao Thị Nhạn', 'NXB ĐHQG TP.HCM', 'Giáo trình', 25, 90000),
(173, 'Nhập Môn Mạng Máy Tính', 'Trần Thị Dung', 'NXB ĐHQG TP.HCM', 'Giáo trình', 25, 30000),
(174, 'Lập Trình JaVa', 'Phan Hoàng Khải', 'NXB ĐHQG TP.HCM', 'Giáo trình', 20, 99000),
(175, 'Hệ Điều Hành', 'Chung Quang Khánh', 'NXB ĐHQG TP.HCM', 'Giáo trình', 60, 40000),
(176, '600 Essential Words For The Toeic', 'Dr. Lin Lougheed', 'NXB Tổng Hợp TP.HCM', 'Ngoại ngữ', 10, 110000),
(177, 'Starter Toeic Third Edition', 'Anne Taylor', 'NXB Tổng Hợp TP.HCM', 'Ngoại ngữ', 15, 120000),
(178, 'Luyện Thi Toeic 850 Reading', 'Jo Gang - Soo', 'NXB Khoa Học Xã Hội', 'Ngoại ngữ', 10, 169000),
(179, 'Luyện Thi Toeic 750 Reading', 'Jo Gang - Soo', 'NXB Khoa Học Xã Hội', 'Ngoại ngữ', 10, 149000),
(180, 'Pass The Toeic Test Intermediate Course', 'Miles Craven', 'NXB Tổng Hợp TP.HCM', 'Ngoại ngữ', 10, 169000),
(181, 'Pass The Toeic Test - Advanced Course', 'Miles Craven', 'NXB Tổng Hợp TP.HCM', 'Ngoại ngữ', 10, 169000),
(182, 'Luyện Thi TOEIC Thật Là Đơn Giản (Kèm CD)', 'Yukihiro Tsukada', 'NXB Hồng Đức', 'Ngoại ngữ', 10, 150000),
(183, 'Very Easy Toeic - Kèm 2 CD', 'Yukihiro Tsukada', 'NXB Trẻ', 'Ngoại ngữ', 10, 134000),
(184, 'Toeic All In One', 'Research Institue', 'NXB Thế Giới', 'Ngoại ngữ', 10, 219000),
(185, 'Restart Your English - Traveling Abroad', 'Bae Jin Young', 'NXB Hồng Đức', 'Ngoại ngữ', 5, 250000),
(186, 'Toan cao cap', 'Trần Văn Hà', 'NXB Giáo Dục', 'Sách giáo khoa', 1000, 100000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
CREATE TABLE IF NOT EXISTS `taikhoan` (
  `User` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `Password` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `PhanQuyen` int(11) NOT NULL,
  `TenND` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `SDT` varchar(10) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  `CMND` varchar(20) COLLATE utf8mb4_vietnamese_ci NOT NULL,
  PRIMARY KEY (`User`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_vietnamese_ci;

--
-- Đang đổ dữ liệu cho bảng `taikhoan`
--

INSERT INTO `taikhoan` (`User`, `Password`, `PhanQuyen`, `TenND`, `SDT`, `CMND`) VALUES
('admin', 'admin', 1, 'Admin', '0312345678', '11111111111111'),
('dinhvy', 'admin', 2, 'Nguyễn Đình Vỹ', '0312345678', '11111111111111'),
('huudung', 'admin', 1, 'Hoàng Hữu Dũng', '0312345678', '11111111111111'),
('vanchien', 'admin', 1, 'Lê Văn Chiến', '0312345678', '11111111111111');

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ctpm`
--
ALTER TABLE `ctpm`
  ADD CONSTRAINT `ctpm_ibfk_1` FOREIGN KEY (`MaPM`) REFERENCES `phieumuon` (`MaPM`),
  ADD CONSTRAINT `ctpm_ibfk_2` FOREIGN KEY (`MaSach`) REFERENCES `sach` (`MaSach`),
  ADD CONSTRAINT `ctpm_ibfk_3` FOREIGN KEY (`User`) REFERENCES `taikhoan` (`User`);

--
-- Các ràng buộc cho bảng `phieumuon`
--
ALTER TABLE `phieumuon`
  ADD CONSTRAINT `phieumuon_ibfk_1` FOREIGN KEY (`MaDG`) REFERENCES `docgia` (`MaDG`),
  ADD CONSTRAINT `phieumuon_ibfk_2` FOREIGN KEY (`User`) REFERENCES `taikhoan` (`User`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
