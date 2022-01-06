<?php

    require "Connect.php";
    $conn = mysqli_connect($servername, $username, $passwd, "DSA");

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        
        $email = $_POST["email"];
        $oldEmail = $_POST["oldEmail"];
        $username = $_POST["username"];
        $oldUsername = $_POST["oldUsername"];

        $sql = "UPDATE users set username = '$username' , email = '$email' WHERE username ='$oldUsername' AND email = '$oldEmail'";

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