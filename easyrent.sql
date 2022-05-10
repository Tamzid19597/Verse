-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 10, 2022 at 02:43 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `easyrent`
--

-- --------------------------------------------------------

--
-- Table structure for table `deals`
--

CREATE TABLE `deals` (
  `id` int(11) NOT NULL,
  `sid` text NOT NULL,
  `email` text NOT NULL,
  `name` text NOT NULL,
  `number` text NOT NULL,
  `address` text NOT NULL,
  `status` text NOT NULL DEFAULT 'Pending',
  `payinfo` text NOT NULL DEFAULT 'not yet'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `deals`
--

INSERT INTO `deals` (`id`, `sid`, `email`, `name`, `number`, `address`, `status`, `payinfo`) VALUES
(51, 'PL0002', 'a@g.com', 'A', '123', 'F', 'paid', 'Name: 123457866 Number: 344554545454545 CVV 444 Expiry 5545454545');

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `id` int(11) NOT NULL,
  `name` text NOT NULL,
  `email` text NOT NULL,
  `primarynumber` text NOT NULL,
  `secondarynumber` text DEFAULT NULL,
  `address` text NOT NULL,
  `city` text DEFAULT NULL,
  `country` text DEFAULT NULL,
  `postalcode` text DEFAULT NULL,
  `about` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `profile`
--

INSERT INTO `profile` (`id`, `name`, `email`, `primarynumber`, `secondarynumber`, `address`, `city`, `country`, `postalcode`, `about`) VALUES
(4, 'User', 'user@gmail.com', '1234', '1234567', '456789fvfv', 'dhaka', 'BD', '1212', '.......................'),
(7, 'A', 'a@g.com', '123', NULL, 'F', NULL, NULL, NULL, NULL),
(8, '19301045_Abu Mauze Tamzid Khan', 'vv@v.com', '+8801959752029', NULL, '111', NULL, NULL, NULL, NULL),
(9, 'Tamzid', 'tamzidkhan@g.com', '01959752029', NULL, 'abcd,24k', NULL, NULL, NULL, NULL),
(10, 'Tamzid', 'tamzidkhan1@g.com', '01959752029', NULL, 'abcd,24k', NULL, NULL, NULL, NULL),
(11, 'Tamzid', 'tamzidkhan111@g.com', '01959752029', NULL, 'abcd,24k', NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `type` text NOT NULL,
  `name` text NOT NULL,
  `img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`id`, `type`, `name`, `img`) VALUES
(3, 'Authorized', 'Web Development', 'images/webdev.jpg'),
(4, 'Unauthorized', 'Web Development', 'images/webdev.jpg'),
(5, 'Authorized', 'Data Science', 'images/data.jpg'),
(6, 'Unauthorized', 'Data Science', 'images/data.jpg'),
(7, 'Authorized', 'Mobile Development', 'images/mobileDev.png'),
(8, 'Unauthorized', 'Mobile Development', 'images/mobileDev.png'),
(9, 'Authorized', 'Programming Languages', 'images/programmingL.png'),
(10, 'Unauthorized', 'Programming Languages', 'images/programmingL.png'),
(11, 'Authorized', 'Graphic Design', 'images/GraphicD.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `single_services`
--

CREATE TABLE `single_services` (
  `id` int(11) NOT NULL,
  `sid` text NOT NULL,
  `type` text NOT NULL,
  `service` text NOT NULL,
  `subservice` text NOT NULL,
  `name` text NOT NULL,
  `description` text NOT NULL,
  `cost` text NOT NULL,
  `topics` text NOT NULL,
  `detail` text NOT NULL,
  `link` text NOT NULL,
  `img` text NOT NULL,
  `imgB` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `single_services`
--

INSERT INTO `single_services` (`id`, `sid`, `type`, `service`, `subservice`, `name`, `description`, `cost`, `topics`, `detail`, `link`, `img`, `imgB`) VALUES
(2, 'WD0001', 'Unauthorized', 'Web Development', 'JavaScripts', 'JavaScript Certification Training Course', 'Learn JavaScript Development And Become an JavaScript Expertise. Incl. Javascripts Tutorial Videos.', 'Tk.5000', 'Introduction~Why JS is so important?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?', 'Here we will discuss about course plan.~Explanation of the importance of JS.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.', 'https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M~https://www.youtube.com/embed/jS4aFq5-91M', 'images/JSC.png', 'images/JSC.png'),
(7, 'MD001', 'Authorized', 'Mobile Development', 'Android', 'Android Developement With Kotlin', 'Learn Kotlin Android App Development And Become an Android Developer. Incl. Kotlin Tutorial and Android Tutorial Videos.', '3000Tk.', 'Introduction~Why Android Development is so important?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?', 'Here we will discuss about course plan.~Explanation of the importance of Android Development.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.', 'https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8~https://www.youtube.com/embed/e7WIPwRd2s8', 'images/AndroidKotlin.png', 'images/AndroidKotlin.png'),
(8, 'PL0001', 'Authorized', 'Programming Languages', 'Java', 'Java In-Depth: Become a Complete Java Engineer!', 'Comprehensive Java programming course integrated with design principles, best practices & instructor-led Java EE project', '11000tk.', 'Introduction~Why Java is so important?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?', 'Here we will discuss about course plan.~Explanation of the importance of Java Development.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.', 'https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo~https://www.youtube.com/embed/Ae-r8hsbPUo', 'images/javaAd.jpg', 'images/javaAd.jpg'),
(9, 'PL0002', 'Authorized', 'Programming Languages', 'Java', 'Spring Boot Tutorial for Beginners (Java Framework)', 'Spring 5: Learn Spring 5 Core, AOP, Spring MVC, Spring Security, Spring REST, Spring Boot 2, Thymeleaf, JPA & Hibernate', '8999tk.', 'Introduction~Why Java Dpring framework is so important?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?~How we are going to operate this course?', 'Here we will discuss about course plan.~Explanation of the importance of Java Development.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.~We will wxplain Course sequence.', 'https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ~https://www.youtube.com/embed/vtPkZShrvXQ', 'images/javaSp.png', 'images/javaSp.png');

-- --------------------------------------------------------

--
-- Table structure for table `subservices`
--

CREATE TABLE `subservices` (
  `id` int(11) NOT NULL,
  `type` text NOT NULL,
  `service` text NOT NULL,
  `subservice` text NOT NULL,
  `img` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `subservices`
--

INSERT INTO `subservices` (`id`, `type`, `service`, `subservice`, `img`) VALUES
(1, 'Authorized', 'Mobile Development', 'Android', 'images/Android.jpg'),
(2, 'Unauthorized', 'Web Development', 'JavaScripts', 'images/JS.png'),
(5, 'Authorized', 'Programming Languages', 'Java', 'images/java.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `email` text NOT NULL,
  `password` text NOT NULL,
  `name` text NOT NULL,
  `number` text NOT NULL,
  `address` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `deals`
--
ALTER TABLE `deals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`(768));

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `single_services`
--
ALTER TABLE `single_services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subservices`
--
ALTER TABLE `subservices`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`) USING HASH;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `deals`
--
ALTER TABLE `deals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `single_services`
--
ALTER TABLE `single_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `subservices`
--
ALTER TABLE `subservices`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
