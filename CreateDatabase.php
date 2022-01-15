<?php
require "Connect.php";

// Checking for the connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error() . "<br>");
} else {
    echo "Connection establised" . "<br>";

    //SQL code goes here yes that is a deadMau5 reference

    $sqlCreateDatabase = "CREATE DATABASE DSA";

    $sqlCreateUsers = "CREATE TABLE Users (
            id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(30) NOT NULL,
            email VARCHAR(50) UNIQUE NOT NULL,
            passwd VARCHAR(255) NOT NULL,
            device_id VARCHAR(50) NOT NULL,
            enabled BOOLEAN NOT NULL DEFAULT 0,
            reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
            )";

    $sqlCreateDoctors = "CREATE TABLE Doctors (
            id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(30) NOT NULL,
            email VARCHAR(50) UNIQUE NOT NULL,
            passwd VARCHAR(255) NOT NULL,
            device_id VARCHAR(50) NOT NULL,
            phone VARCHAR(50) NOT NULL, 
            enabled BOOLEAN NOT NULL DEFAULT 0,
            reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
            )";

    $sqlCreateFiles = "CREATE TABLE Files (
            id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            userID INT(6) UNSIGNED NOT NULL REFERENCES Users(id) ON UPDATE CASCADE ON DELETE CASCADE,
            fileTitle VARCHAR(40) NOT NULL, 
            fileDesc VARCHAR(100) NOT NULL,
            fileData LONGTEXT NOT NULL,
            fileUploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
            )";

    $sqlCreateBugListings = "CREATE TABLE BugListings (
            id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            userID INT(6) UNSIGNED NOT NULL REFERENCES Users(id), 
            bugDesc VARCHAR(100) NOT NULL,
            bugUploadDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
            )";        

    if (mysqli_query($conn, $sqlCreateDatabase)) {
        echo "Database created successfully " . "<br>";
    } else {
        echo "Error on creating database: " . mysqli_error($conn) . "<br>";
    }

    $conn = mysqli_connect($servername, $username, $passwd, "DSA"); //This goes here because the previus connection is made before the creation of the database
    if (mysqli_query($conn, $sqlCreateUsers)) {
        echo "Table Users created successfully " . "<br>";
    } else {
        echo "Error creating table: " . mysqli_error($conn) . "<br>";
    }

    if (mysqli_query($conn, $sqlCreateDoctors)) {
        echo "Table Doctors created successfully " . "<br>";
    } else {
        echo "Error creating table: " . mysqli_error($conn) . "<br>";
    }

    if (mysqli_query($conn, $sqlCreateFiles)) {
        echo "Table Files created successfully " . "<br>";
    } else {
        echo "Error creating table: " . mysqli_error($conn) . "<br>";
    }

    if (mysqli_query($conn, $sqlCreateBugListings)) {
        echo "Table BugListings created successfully " . "<br>";
    } else {
        echo "Error creating table: " . mysqli_error($conn) . "<br>";
    }
}
