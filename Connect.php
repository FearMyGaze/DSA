<?php
    //Change the parameters to match your database , username and password
    $servername = "localhost";
    $username = "root";
    $passwd = "";
    $dbname = "dsa";

    // Create connection before creating the database
    $beginOfCreation = mysqli_connect($servername, $username, $passwd);

    $conn = mysqli_connect($servername, $username, $passwd, $dbname);
