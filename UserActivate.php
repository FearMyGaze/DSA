<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");

        $username = $_POST["username"];
                
        $sql = "UPDATE users SET activated = 1 WHERE username = '$username'";

        if (mysqli_query($conn, $sql)) {
        
            $result['success'] = "1";
            echo json_encode($result);

            mysqli_close($conn);
        } else {

            $result['success'] = "0";
            echo json_encode($result);

            mysqli_close($conn);
        }

    }