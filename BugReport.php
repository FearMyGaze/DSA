<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    require "Connect.php";
    $conn = mysqli_connect($servername, $username, $passwd, "DSA");


    $bugEmail = $_POST["bugEmail"];
    $bugDesc = $_POST["bugDesc"];

    $sql = "INSERT INTO BugListings (bugEmail, bugDesc) VALUES ('$bugEmail', '$bugDesc')";

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