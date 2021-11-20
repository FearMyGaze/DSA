<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    require "connect.php";

    $username = $_POST["username"];
    $passwd = $_POST["passwd"];
    $passwd = password_hash($passwd, PASSWORD_DEFAULT);
    $email = $_POST["email"];
    $device_id = $_POST["device_id"];

    $sql = "INSERT INTO users (username, email, passwd,device_id) VALUES ('$username', '$email', '$passwd','$device_id')";

    if (mysqli_query($conn, $sql)) {

        $result["success"] = "1";
        echo json_encode($result);

        mysqli_close($conn);
    } else {

        $result["success"] = "0";
        echo json_encode($result);

        mysqli_close($conn);
    }
}