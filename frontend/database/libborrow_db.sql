-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 05, 2026 at 01:11 PM
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
(2, 'Belajar C++', 'Rian', 'Teknologi', 0),
(3, 'Laskar Pelangi', 'Andrea Hirata', 'Novel', 3),
(4, 'Bumi Manusia', 'Pramoedya Ananta Toer', 'Novel', 1);

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
(5, b'0', 'Anda terlambat mengembalikan buku \'Pemrograman Java\' selama 4 hari. Denda terkumpul: Rp 4000', '2026-06-05 12:50:09.074144', 3);

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
(12, 'Dikembalikan', '2026-05-25', 1, 3, '2026-06-01', 4000);

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
(2, 'TERSEDIA', '2026-06-05', 2, 3);

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
(9, 'auril@mail.com', 'Auril Adiya', '123', 'Anggota', 'udil', 'AG003');

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
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `denda`
--
ALTER TABLE `denda`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `notifikasi`
--
ALTER TABLE `notifikasi`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `reservasi`
--
ALTER TABLE `reservasi`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

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
