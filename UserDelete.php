<?php
    require "Connect.php";
    $conn = mysqli_connect($servername, $username, $passwd, "DSA");

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        $email = $_POST["email"];

        $sql = "DELETE FROM users WHERE email = '$email'";

        if (mysqli_query($conn, $sql)) {
               
            $result['success'] = "1";
            mysqli_close($conn);
            
            echo json_encode($result);

        } else {
            $result['success'] = "0";
            mysqli_close($conn);

            echo json_encode($result);
        }
    }
       