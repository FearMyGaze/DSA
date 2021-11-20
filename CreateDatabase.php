<?php
require "Connect.php";

// Checking for the connection
if (!$beginOfCreation) {
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
            reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
            )";

    $sqlCreateDoctors = "CREATE TABLE Doctors (
            id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(30) NOT NULL,
            email VARCHAR(50) UNIQUE NOT NULL,
            passwd VARCHAR(255) NOT NULL,
            device_id VARCHAR(50) NOT NULL,
            phone VARCHAR(50) NOT NULL, 
            reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
            )";



    if (mysqli_query($beginOfCreation, $sqlCreateDatabase)) {
        echo "Database created successfully " . "<br>";
    } else {
        echo "Error on creating database: " . mysqli_error($beginOfCreation) . "<br>";
    }

    $conn = mysqli_connect($servername, $username, $passwd, "DSA");
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
}
