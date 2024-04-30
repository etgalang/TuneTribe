-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 01, 2024 at 12:19 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `csc340_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `admin_id` bigint(20) NOT NULL,
  `community_guidelines` varchar(255) DEFAULT NULL,
  `terms_of_service` varchar(255) DEFAULT NULL,
  `id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `artist`
--

CREATE TABLE `artist` (
  `artist_id` bigint(20) NOT NULL,
  `artist_email` varchar(255) DEFAULT NULL,
  `artistfname` varchar(255) DEFAULT NULL,
  `artistlname` varchar(255) DEFAULT NULL,
  `artist_password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `moderator`
--

CREATE TABLE `moderator` (
  `mod_id` bigint(20) NOT NULL,
  `mod_email` varchar(255) DEFAULT NULL,
  `modfname` varchar(255) DEFAULT NULL,
  `modlname` varchar(255) DEFAULT NULL,
  `mod_password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `mod_report`
--

CREATE TABLE `mod_report` (
  `id` bigint(20) NOT NULL,
  `mod_explanation` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `post_id` bigint(20) NOT NULL,
  `caption` varchar(255) DEFAULT NULL,
  `like_count` int(11) NOT NULL,
  `post_date` datetime(6) DEFAULT NULL,
  `song_id` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `caption`, `like_count`, `post_date`, `song_id`, `username`) VALUES
(1, 'Love this song😎', 0, '2024-04-30 00:00:00.000000', 2, 'user');

-- --------------------------------------------------------

--
-- Table structure for table `report`
--

CREATE TABLE `report` (
  `id` bigint(20) NOT NULL,
  `explanation` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) NOT NULL,
  `reported_id` bigint(20) NOT NULL,
  `reporter_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `id` bigint(20) NOT NULL,
  `artist` varchar(255) DEFAULT NULL,
  `cover_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `spotify_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `song`
--

INSERT INTO `song` (`id`, `artist`, `cover_url`, `name`, `spotify_id`) VALUES
(1, 'Peso Pluma, Junior H, Oscar Maydon', 'https://i.scdn.co/image/ab67616d00001e02a7a330bed7c3b2217e3be9a8', 'Rompe La Dompe', '2OWv3lRR8PtOVuHWzQNRs2'),
(2, 'Camila', 'https://i.scdn.co/image/ab67616d00001e02d46baecb11453061ea9fa795', 'De Mí', '1hCswNajdTPRtuggsGtpCX');

-- --------------------------------------------------------

--
-- Table structure for table `top_songs`
--

CREATE TABLE `top_songs` (
  `id` bigint(20) NOT NULL,
  `song_one` bigint(20) NOT NULL,
  `song_three` bigint(20) NOT NULL,
  `song_two` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `top_songs`
--

INSERT INTO `top_songs` (`id`, `song_one`, `song_three`, `song_two`) VALUES
(1, 1, 1, 1),
(2, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `banned` bit(1) NOT NULL,
  `followers` int(11) NOT NULL,
  `following` int(11) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `top_song` int(11) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `userfname` varchar(255) DEFAULT NULL,
  `userlname` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `banned`, `followers`, `following`, `role`, `top_song`, `user_email`, `userfname`, `userlname`, `user_name`, `user_password`) VALUES
(1, b'0', 0, 0, 'User', 0, 'user@user.com', 'User', 'userlast', 'user', '$2a$10$HXP4VRNyM6lsOT8eUkLc1u2ymcOQApozClWBKvjnaAjLlRM/Mqiwm'),
(2, b'0', 0, 0, 'Admin', 0, 'admin@admin.com', 'Admin', 'adminlast', 'admin', '$2a$10$k1Do6SvE4j8jSsXU0aJetOqilPJ849B3z5mReU7mMKHEZ3jatjRyq');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`),
  ADD UNIQUE KEY `UK_2d37nsdu6wfjfoo0q1l857edx` (`id`);

--
-- Indexes for table `artist`
--
ALTER TABLE `artist`
  ADD PRIMARY KEY (`artist_id`);

--
-- Indexes for table `moderator`
--
ALTER TABLE `moderator`
  ADD PRIMARY KEY (`mod_id`);

--
-- Indexes for table `mod_report`
--
ALTER TABLE `mod_report`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`);

--
-- Indexes for table `report`
--
ALTER TABLE `report`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `top_songs`
--
ALTER TABLE `top_songs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `admin_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `artist`
--
ALTER TABLE `artist`
  MODIFY `artist_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `moderator`
--
ALTER TABLE `moderator`
  MODIFY `mod_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `mod_report`
--
ALTER TABLE `mod_report`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `report`
--
ALTER TABLE `report`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `FK1ja8rua032fgnk9jmq7du3b3a` FOREIGN KEY (`id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
