/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  daniel
 * Created: Feb 8, 2017
 */
-- Dumping database structure for darkaiv

-- Dumping database structure for darkaiv
CREATE DATABASE IF NOT EXISTS `darkaiv`;
USE `darkaiv`;

CREATE TABLE IF NOT EXISTS `document` (
             `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--              `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,
             `title` varchar(500),
             `created_at` varchar(255),
             `updated_at` varchar(255),
             `creation_date` varchar(255),
             `updated_date` varchar(255),
             `abstract` varchar(2096),
             `year` varchar(255),
             `key_words` varchar(255),
             `pages` varchar(255),
             `publisher` varchar(255),
             `volume` varchar(255),
             `source` varchar(255),
             `idiom` varchar(255),
             `editors` varchar(255),
             `edition` varchar(255),
             `city` varchar(255),
             `chapter` varchar(255),
             `department` varchar(255),
             `university` varchar(255),
             `thesis_type` varchar(255),
             `issue` varchar(255),
             `institution` varchar(255),
             `type` varchar(255)
);

    CREATE TABLE IF NOT EXISTS `identifier` (
            `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
--             `id` bigint(20) NOT NULL AUTO_INCREMENT PRIMARY KEY,    
            `type` varchar(255),
            `value` varchar(255),
            `document_id` int,
             FOREIGN KEY (document_id) REFERENCES document(id)

);

    



