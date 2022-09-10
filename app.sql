-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 08 sep. 2022 à 23:36
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 7.4.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `app`
--

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `id_evenement` int(11) DEFAULT NULL,
  `date_c` date NOT NULL,
  `comment` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `commentaire`
--

INSERT INTO `commentaire` (`id`, `user_id`, `id_evenement`, `date_c`, `comment`) VALUES
(27, 2, 8, '2022-09-07', 'hahahha'),
(29, 1, 8, '2022-09-07', '****'),
(30, 1, 8, '2022-09-07', 'son of a ***** i hate you'),
(36, 1, 9, '2022-09-08', 'hhhhhhhhhhh'),
(42, 1, 7, '2022-09-08', 'hello'),
(43, 1, 7, '2022-09-08', 'jjjjjj hhh'),
(46, 1, 7, '2022-09-08', 'hhh'),
(48, 1, 7, '2022-09-08', 'fuck');

-- --------------------------------------------------------

--
-- Structure de la table `doctrine_migration_versions`
--

CREATE TABLE `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20220830182131', '2022-08-30 20:21:35', 77);

-- --------------------------------------------------------

--
-- Structure de la table `evenements`
--

CREATE TABLE `evenements` (
  `id` int(11) NOT NULL,
  `titre` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lieu` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nb_participant` int(11) NOT NULL,
  `datadeb` date NOT NULL,
  `date_fin` date NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `evenements`
--

INSERT INTO `evenements` (`id`, `titre`, `lieu`, `nb_participant`, `datadeb`, `date_fin`, `description`, `image`) VALUES
(7, 'msi', 'raoued', 32, '2022-09-06', '2022-09-07', 'msi reward camp wse', '301740364_800665707608928_1032258442425905796_n.jpg'),
(8, 'alexsss', 'alan', 12, '2022-09-06', '2022-09-08', 'repassage', '300277953_1224779304971460_3073110891618914666_n.jpg'),
(9, 'Rayban', 'marsa', 22, '2022-09-06', '2022-09-07', 'acheter', 'discord.jpg'),
(10, 'Justin', 'Timberlake', 0, '2021-01-13', '2019-01-01', 'Mirrors', 'pdp.jpg'),
(12, 'wawawa', 'wawawaawawa', 0, '2017-01-01', '2017-01-01', 'hhhhhhhhhhhhh', 'wallpaper_16487800940ff7b95bc63e4c56a8843b2c50d3b0de.jpeg'),
(13, 'aaa', 'aaa', 0, '2017-01-01', '2017-01-01', 'aaa', 'wallpaper_15590048505cec86b2b6db0.jpg'),
(14, 'awwawa', 'awwaa', 0, '2017-01-01', '2017-01-01', 'sqsqsqsq', 'wallpaper_16487800940ff7b95bc63e4c56a8843b2c50d3b0de.jpeg'),
(15, 'dadada', 'dadadada', 0, '2017-01-01', '2017-01-01', 'dadadada', 'wallpaper_16479199139b74d7b8203df686fed0aca2fd93eb07.jpeg'),
(16, 'waawawaw', 'waawawaawaw', 0, '2017-01-01', '2017-01-01', 'wawawawaawa', 'wallpaper_15731779485dc4ca5c2ec94.jpg'),
(17, 'wawaa', 'wawa', 0, '2017-01-01', '2017-01-01', 'wawawa', 'wallpaper_15783783425e142466e5db7.jpg'),
(19, 'wawaa', 'wawa', 0, '2024-01-01', '2025-08-15', 'saaas', 'wallpaper_15590048505cec86b2b6db0.jpg'),
(22, 'ffffff', 'hhhhhhh', 23, '2022-09-08', '2022-09-08', 'wwwwwwwwww', '20220908114903.jpg'),
(23, 'jjjjjjjj', 'crcrcrcf', 33, '2022-09-08', '2022-09-08', 'kjkjkjkjkjk', '20220908115411.jpg'),
(24, 'jjjjjjjjjjjjjjjjjjjjjjjjjjj', 'hhhhhhhhhhhhhh', 22, '2022-09-08', '2022-09-08', 'ggggggggggggggggggggg', '20220908120743.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`) VALUES
(1, 'mohamed'),
(2, 'Eya');

-- --------------------------------------------------------

--
-- Structure de la table `vote`
--

CREATE TABLE `vote` (
  `id` int(11) NOT NULL,
  `id_user` int(11) DEFAULT NULL,
  `type` int(11) NOT NULL,
  `id_Commentaire` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `vote`
--

INSERT INTO `vote` (`id`, `id_user`, `type`, `id_Commentaire`) VALUES
(9, 2, 2, 27),
(12, 1, 1, 43);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_67F068BCA76ED395` (`user_id`),
  ADD KEY `IDX_67F068BC8B13D439` (`id_evenement`);

--
-- Index pour la table `doctrine_migration_versions`
--
ALTER TABLE `doctrine_migration_versions`
  ADD PRIMARY KEY (`version`);

--
-- Index pour la table `evenements`
--
ALTER TABLE `evenements`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `vote`
--
ALTER TABLE `vote`
  ADD PRIMARY KEY (`id`),
  ADD KEY `IDX_5A108564F97E2A9C` (`id_Commentaire`),
  ADD KEY `IDX_5A1085646B3CA4B` (`id_user`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT pour la table `evenements`
--
ALTER TABLE `evenements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `vote`
--
ALTER TABLE `vote`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_67F068BC8B13D439` FOREIGN KEY (`id_evenement`) REFERENCES `evenements` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_67F068BCA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `vote`
--
ALTER TABLE `vote`
  ADD CONSTRAINT `FK_5A1085646B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_5A108564F97E2A9C` FOREIGN KEY (`id_Commentaire`) REFERENCES `commentaire` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
