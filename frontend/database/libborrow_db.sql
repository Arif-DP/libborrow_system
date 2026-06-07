-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 07, 2026 at 01:11 PM
-- Server version: 8.0.30
-- PHP Version: 8.4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `libborrow_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `buku`
--

CREATE TABLE `buku` (
  `id` bigint NOT NULL,
  `judul` varchar(255) NOT NULL,
  `penulis` varchar(255) DEFAULT NULL,
  `kategori` varchar(255) DEFAULT NULL,
  `stok` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `buku`
--

INSERT INTO `buku` (`id`, `judul`, `penulis`, `kategori`, `stok`) VALUES
(1, 'Pemrograman Java', 'Budi Santoso', 'Teknologi', 2),
(2, 'Belajar C++', 'Rian', 'Teknologi', 3),
(3, 'Laskar Pelangi', 'Andrea Hirata', 'Fiksi', 3),
(4, 'Bumi Manusia', 'Pramoedya Ananta Toer', 'Sastra', 0),
(5, 'Filosofi Teras', 'Henry Manampiring', 'Pengembangan Diri', 1),
(6, 'Atomic Habits', 'James Clear', 'Pengembangan Diri', 2),
(7, 'Pengantar Teknologi Informasi', 'Edy Irwansyah', 'Komputer', 2),
(8, 'Sejarah Dunia yang Disembunyikan', 'Jonathan Black', 'Sejarah', 1),
(9, 'Cosmos', 'Carl Sagan', 'Sains', 2),
(10, 'Sebuah Seni untuk Bersikap Bodo Amat', 'Mark Manson', 'Pengembangan Diri', 2),
(11, 'Negeri 5 Menara', 'Ahmad Fuadi', 'Fiksi', 1),
(12, 'Pulang', 'Leila S. Chudori', 'Sastra', 2),
(13, 'Sapiens', 'Yuval Noah Harari', 'Sejarah', 2),
(14, 'Belajar Jaringan Komputer', 'Iwan Sofana', 'Komputer', 2),
(15, 'Ksatria, Putri, dan Bintang Jatuh', 'Dee Lestari', 'Fiksi', 2),
(16, 'Mantappu Jiwa', 'Jerome Polin', 'Biografi', 2),
(17, 'The Selfish Gene', 'Richard Dawkins', 'Sains', 2),
(18, 'Dasar-dasar Alam Semesta', 'Ahmad Rahman', 'Sejarah', 1),
(19, 'Seni Manusia', 'Dian Kusuma', 'Pendidikan', 1),
(20, 'Analisis Pemrograman', 'Budi Kusuma', 'Sejarah', 2),
(21, 'Kisah Digital', 'Maya Santoso', 'Sastra', 2),
(22, 'Mengenal Kesehatan', 'Dian Garcia', 'Sejarah', 1),
(23, 'Filosofi Klasik', 'Ahmad Santoso', 'Pengembangan Diri', 2),
(24, 'Analisis Jaringan', 'Ahmad Setiawan', 'Sejarah', 2),
(25, 'Teori Manusia', 'Andi Jones', 'Komputer', 1),
(26, 'Penerapan Data', 'Maya Hidayat', 'Fiksi', 2),
(27, 'Filosofi Modern', 'Dewi Jones', 'Komputer', 2),
(28, 'Dinamika Kepemimpinan', 'Maya Brown', 'Pendidikan', 1),
(29, 'Mengenal Keuangan', 'Siti Rahman', 'Sains', 2),
(30, 'Sejarah Masa Lalu', 'Dewi Jones', 'Sains', 2),
(31, 'Filosofi Kesehatan', 'Sarah Doe', 'Biografi', 2),
(32, 'Penerapan Masa Depan', 'Ahmad Kusuma', 'Pendidikan', 2),
(33, 'Rahasia Modern', 'Jane Doe', 'Agama', 2),
(34, 'Pengantar Kesuksesan', 'Dian Rahman', 'Biografi', 1),
(35, 'Panduan Masa Lalu', 'Siti Williams', 'Bisnis', 2),
(36, 'Jejak Keuangan', 'John Sari', 'Biografi', 1),
(37, 'Strategi Kecerdasan Buatan', 'Kevin Setiawan', 'Sains', 1),
(38, 'Seni Masa Lalu', 'Dian Pratama', 'Sains', 2),
(39, 'Konsep Cinta', 'Hendra Jones', 'Biografi', 2),
(40, 'Kisah Kehidupan', 'Agus Saputra', 'Komputer', 1),
(41, 'Panduan Manusia', 'Eko Doe', 'Bisnis', 1),
(42, 'Prinsip Keuangan', 'David Jones', 'Pendidikan', 2),
(43, 'Filosofi Digital', 'Ahmad Pratama', 'Fiksi', 1),
(44, 'Dasar-dasar Mental', 'Dian Hidayat', 'Biografi', 1),
(45, 'Dasar-dasar Kepemimpinan', 'Linda Doe', 'Agama', 1),
(46, 'Misteri Kesuksesan', 'Agus Doe', 'Sastra', 1),
(47, 'Dasar-dasar Kesehatan', 'Nur Gunawan', 'Pendidikan', 1),
(48, 'Mengenal Kehidupan', 'Sarah Doe', 'Sastra', 1),
(49, 'Praktik Alam Semesta', 'Maya Williams', 'Agama', 2),
(50, 'Misteri Manusia', 'Sarah Johnson', 'Sejarah', 1),
(51, 'Konsep Keuangan', 'Andi Saputra', 'Komputer', 2),
(52, 'Dinamika Kehidupan', 'Eko Saputra', 'Sastra', 2),
(53, 'Rahasia Kesuksesan', 'Agus Wibowo', 'Bisnis', 1),
(54, 'Mengenal Modern', 'John Johnson', 'Bisnis', 2),
(55, 'Penerapan Perjuangan', 'Agus Martinez', 'Komputer', 2),
(56, 'Kisah Masa Lalu', 'Andi Williams', 'Fiksi', 2),
(57, 'Konsep Modern', 'Jane Sari', 'Sejarah', 1),
(58, 'Misteri Keuangan', 'Siti Rahman', 'Komputer', 1),
(59, 'Panduan Jaringan', 'Andi Gunawan', 'Sejarah', 1),
(60, 'Jejak Mental', 'Rina Smith', 'Sastra', 1),
(61, 'Seni Klasik', 'Hendra Saputra', 'Sejarah', 2),
(62, 'Strategi Cinta', 'John Lestari', 'Komputer', 1),
(63, 'Analisis Data', 'Linda Garcia', 'Agama', 1),
(64, 'Panduan Mental', 'Dewi Wijaya', 'Bisnis', 2),
(65, 'Kisah Masa Depan', 'John Pratama', 'Agama', 1),
(66, 'Seni Cinta', 'Tri Hidayat', 'Agama', 1),
(67, 'Kisah Keuangan', 'Kevin Pratama', 'Komputer', 1),
(68, 'Panduan Kesehatan', 'Dian Santoso', 'Komputer', 2),
(69, 'Kisah Teknologi', 'Linda Saputra', 'Agama', 1),
(70, 'Mengenal Kecerdasan Buatan', 'Siti Doe', 'Bisnis', 2),
(71, 'Misteri Kecerdasan Buatan', 'Ahmad Martinez', 'Sains', 2),
(72, 'Analisis Modern', 'Rina Setiawan', 'Sejarah', 2),
(73, 'Petualangan Masa Depan', 'Siti Sari', 'Sastra', 2),
(74, 'Panduan Pemrograman', 'Hendra Saputra', 'Sastra', 2),
(75, 'Praktik Teknologi', 'Siti Gunawan', 'Komputer', 1),
(76, 'Rahasia Keuangan', 'Maya Kusuma', 'Sejarah', 2),
(77, 'Pengantar Manusia', 'Hendra Rahman', 'Pendidikan', 2),
(78, 'Prinsip Pemrograman', 'Maya Kusuma', 'Bisnis', 2),
(79, 'Konsep Klasik', 'Agus Williams', 'Sains', 1),
(80, 'Mengenal Mental', 'Michael Rahman', 'Sains', 2),
(81, 'Pengantar Kehidupan', 'Nur Martinez', 'Biografi', 2),
(82, 'Sejarah Alam Semesta', 'Kevin Wibowo', 'Pendidikan', 1),
(83, 'Dasar-dasar Keuangan', 'Dian Smith', 'Sastra', 2),
(84, 'Jejak Kehidupan', 'Sarah Kusuma', 'Sejarah', 2),
(85, 'Penerapan Kepemimpinan', 'Tri Martinez', 'Sastra', 1),
(86, 'Petualangan Globalisasi', 'Agus Santoso', 'Sastra', 2),
(87, 'Petualangan Mental', 'Dewi Setiawan', 'Sains', 1),
(88, 'Dasar-dasar Manusia', 'Dian Setiawan', 'Sains', 2),
(89, 'Mengenal Jaringan', 'John Hidayat', 'Sastra', 2),
(90, 'Strategi Kesuksesan', 'Siti Kusuma', 'Bisnis', 2),
(91, 'Jejak Pemrograman', 'Budi Williams', 'Pengembangan Diri', 1),
(92, 'Jejak Teknologi', 'Kevin Pratama', 'Bisnis', 1),
(93, 'Dinamika Alam Semesta', 'Dewi Kusuma', 'Pengembangan Diri', 1),
(94, 'Dinamika Pemrograman', 'Sarah Sari', 'Sastra', 1),
(95, 'Seni Pemrograman', 'Nur Brown', 'Fiksi', 2),
(96, 'Kisah Mental', 'Dewi Brown', 'Sastra', 1),
(97, 'Analisis Globalisasi', 'Eko Rahman', 'Pengembangan Diri', 1),
(98, 'Rahasia Cinta', 'Budi Doe', 'Biografi', 1),
(99, 'Analisis Mental', 'Jane Hidayat', 'Pengembangan Diri', 2),
(100, 'Dasar-dasar Kecerdasan Buatan', 'Siti Saputra', 'Sejarah', 2),
(101, 'Mengenal Perjuangan', 'Sarah Johnson', 'Sejarah', 2),
(102, 'Kisah Alam Semesta', 'John Jones', 'Biografi', 2),
(103, 'Jejak Data', 'Agus Jones', 'Sastra', 2),
(104, 'Teori Alam Semesta', 'Dewi Hidayat', 'Pengembangan Diri', 1),
(105, 'Konsep Kesuksesan', 'Siti Lestari', 'Pendidikan', 1),
(106, 'Teori Cinta', 'Maya Gunawan', 'Komputer', 2),
(107, 'Pengantar Klasik', 'John Hidayat', 'Fiksi', 2);

-- --------------------------------------------------------

--
-- Table structure for table `denda`
--

CREATE TABLE `denda` (
  `id` bigint NOT NULL,
  `jumlah_denda` double NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `peminjaman_id` bigint NOT NULL,
  `hari_terlambat` int DEFAULT NULL,
  `jumlah` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `denda`
--

INSERT INTO `denda` (`id`, `jumlah_denda`, `status`, `peminjaman_id`, `hari_terlambat`, `jumlah`) VALUES
(1, 2000, 'BELUM_BAYAR', 4, 2, NULL),
(2, 2000, 'BELUM_BAYAR', 6, 2, NULL),
(7, 4000, 'BELUM_BAYAR', 12, 4, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `notifikasi`
--

CREATE TABLE `notifikasi` (
  `id` bigint NOT NULL,
  `dibaca` bit(1) NOT NULL,
  `pesan` varchar(255) NOT NULL,
  `tanggal_kirim` datetime(6) NOT NULL,
  `users_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `notifikasi`
--

INSERT INTO `notifikasi` (`id`, `dibaca`, `pesan`, `tanggal_kirim`, `users_id`) VALUES
(2, b'0', 'Anda terlambat mengembalikan buku \'Belajar C++\' selama 2 hari. Denda terkumpul: Rp 2000', '2026-06-05 09:38:41.284510', 2),
(4, b'0', 'Buku \'Belajar C++\' yang Anda reservasi sekarang SUDAH TERSEDIA dan siap diambil!', '2026-06-05 12:38:41.109007', 3),
(5, b'0', 'Anda terlambat mengembalikan buku \'Pemrograman Java\' selama 4 hari. Denda terkumpul: Rp 4000', '2026-06-05 12:50:09.074144', 3),
(6, b'0', 'Buku \'Belajar C++\' yang Anda reservasi sekarang SUDAH TERSEDIA dan siap diambil!', '2026-06-07 11:01:07.468572', 9),
(7, b'0', 'Buku \'Belajar C++\' yang Anda reservasi sekarang SUDAH TERSEDIA dan siap diambil!', '2026-06-07 11:28:27.185614', 12),
(8, b'0', 'Buku \'Belajar C++\' yang Anda reservasi sekarang SUDAH TERSEDIA dan siap diambil!', '2026-06-07 11:28:30.888579', 10),
(9, b'0', 'Buku \'Belajar C++\' yang Anda reservasi sekarang SUDAH TERSEDIA dan siap diambil!', '2026-06-07 11:28:36.617696', 3),
(10, b'0', 'Buku \'Bumi Manusia\' yang Anda reservasi sekarang SUDAH TERSEDIA dan siap diambil!', '2026-06-07 11:28:39.829218', 12),
(11, b'0', 'Buku \'Bumi Manusia\' yang Anda reservasi kini SUDAH TERSEDIA! Silakan lakukan peminjaman di sikat sirkulasi.', '2026-06-07 11:35:55.733872', 3);

-- --------------------------------------------------------

--
-- Table structure for table `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id` bigint NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tanggal_pinjam` date DEFAULT NULL,
  `buku_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `batas_kembali` date DEFAULT NULL,
  `denda` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `peminjaman`
--

INSERT INTO `peminjaman` (`id`, `status`, `tanggal_pinjam`, `buku_id`, `user_id`, `batas_kembali`, `denda`) VALUES
(4, 'Dikembalikan', '2026-05-27', 2, 3, '2026-06-03', 2000),
(5, 'Dikembalikan', '2026-06-05', 1, 2, '2026-06-12', 0),
(6, 'Dikembalikan', '2026-05-25', 2, 2, '2026-06-03', 2000),
(8, 'Dikembalikan', '2026-06-05', 2, 3, '2026-06-12', 0),
(9, 'Dikembalikan', '2026-06-05', 2, 2, '2026-06-12', 0),
(10, 'Dikembalikan', '2026-06-05', 2, 2, '2026-06-12', 0),
(11, 'Dipinjam', '2026-05-27', 2, 3, '2026-06-03', 0),
(12, 'Dikembalikan', '2026-05-25', 1, 3, '2026-06-01', 4000),
(13, 'Dikembalikan', '2026-06-07', 3, 2, '2026-06-14', 0),
(14, 'Dikembalikan', '2026-06-07', 3, 13, '2026-06-14', 0),
(15, 'Dikembalikan', '2026-06-07', 2, 9, '2026-06-14', 0),
(16, 'Dikembalikan', '2026-06-07', 2, 12, '2026-06-14', 0),
(17, 'Dikembalikan', '2026-06-07', 2, 10, '2026-06-14', 0),
(18, 'Dikembalikan', '2026-06-07', 2, 3, '2026-06-14', 0),
(19, 'Dikembalikan', '2026-06-07', 4, 12, '2026-06-14', 0),
(20, 'Dipinjam', '2026-06-07', 4, 12, '2026-06-14', 0),
(21, 'Dipinjam', '2026-06-07', 4, 3, '2026-06-14', 0);

-- --------------------------------------------------------

--
-- Table structure for table `reservasi`
--

CREATE TABLE `reservasi` (
  `id` bigint NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tanggal_reservasi` date DEFAULT NULL,
  `buku_id` bigint NOT NULL,
  `users_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `reservasi`
--

INSERT INTO `reservasi` (`id`, `status`, `tanggal_reservasi`, `buku_id`, `users_id`) VALUES
(1, 'TERSEDIA', '2026-06-05', 2, 2),
(2, 'TERSEDIA', '2026-06-05', 2, 3),
(3, 'TERSEDIA', '2026-06-07', 2, 9),
(4, 'TERSEDIA', '2026-06-07', 2, 12),
(5, 'TERSEDIA', '2026-06-07', 4, 12),
(6, 'TERSEDIA', '2026-06-07', 2, 10),
(7, 'TERSEDIA', '2026-06-07', 2, 3),
(8, 'TERSEDIA', '2026-06-07', 4, 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nama_lengkap` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `no_anggota` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `nama_lengkap`, `password`, `role`, `username`, `no_anggota`) VALUES
(1, 'admin@mail.com', 'Admin Perpus', 'admin123', 'Admin', 'admin', NULL),
(2, 'andi@mail.com', 'Andi Santoso', '12345', 'Anggota', 'andisantoso', 'AG001'),
(3, 'arif@mail.com', 'Arif Dwi Putra', '12345', 'Anggota', 'arifdwiputra', 'AG002'),
(9, 'auril@mail.com', 'Auril Adiya', '123', 'Anggota', 'udil', 'AG003'),
(10, 'wibi@mail.com', 'Gunawan Wibisono', '123', 'Anggota', 'wibi', 'AG005'),
(12, 'satrio@mail.com', 'Satrio', '123', 'Anggota', 'satrio', 'AG006'),
(13, 'mugata@gmail.com', 'Mugata', 'halonamakubagaslolo', 'Anggota', 'mugata', 'AG007'),
(14, 'ardyan@mail.com', 'Ardyan Cahyo', '123', 'Anggota', 'abang', 'AG008'),
(15, 'eko@mail.com', 'Arinatha Eka', '123', 'Anggota', 'eko', 'AG009'),
(16, 'akbar@mail.com', 'Akbar Nakano', '123', 'Anggota', 'nino', 'AG010');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `denda`
--
ALTER TABLE `denda`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK8gdovtkoqr3uqup75u48njfov` (`peminjaman_id`);

--
-- Indexes for table `notifikasi`
--
ALTER TABLE `notifikasi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKf52tlg12g1u35gek79covg4hx` (`users_id`);

--
-- Indexes for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKei7ilms0t9ck4xhmgvemlfqif` (`buku_id`),
  ADD KEY `FK4bjf6jyewgo1fn05jpwyrktso` (`user_id`);

--
-- Indexes for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKfehnl522qiep0r1b6053jw47s` (`buku_id`),
  ADD KEY `FKpba7xsrol3oq9g2bt56n3v9m5` (`users_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buku`
--
ALTER TABLE `buku`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

--
-- AUTO_INCREMENT for table `denda`
--
ALTER TABLE `denda`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `notifikasi`
--
ALTER TABLE `notifikasi`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `reservasi`
--
ALTER TABLE `reservasi`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `denda`
--
ALTER TABLE `denda`
  ADD CONSTRAINT `FKnko6tceplh31qpf5i4xwflad0` FOREIGN KEY (`peminjaman_id`) REFERENCES `peminjaman` (`id`);

--
-- Constraints for table `notifikasi`
--
ALTER TABLE `notifikasi`
  ADD CONSTRAINT `FKf52tlg12g1u35gek79covg4hx` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD CONSTRAINT `FK4bjf6jyewgo1fn05jpwyrktso` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKei7ilms0t9ck4xhmgvemlfqif` FOREIGN KEY (`buku_id`) REFERENCES `buku` (`id`);

--
-- Constraints for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD CONSTRAINT `FKfehnl522qiep0r1b6053jw47s` FOREIGN KEY (`buku_id`) REFERENCES `buku` (`id`),
  ADD CONSTRAINT `FKpba7xsrol3oq9g2bt56n3v9m5` FOREIGN KEY (`users_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
