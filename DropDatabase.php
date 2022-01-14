<?php
require "Connect.php";
/*
==========================================================================
|                       USE IT WITH CAUTION                              |
==========================================================================
*/


// Checking for the connection
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error() . "<br>");
} else {
    echo "Connection establised" . "<br>";

    $db = "DROP DATABASE dsa";

    if (mysqli_query($conn, $db))
        echo "Database dropped successfully";
    else
        echo "Error dropping database: " . mysqli_error($conn);
}
