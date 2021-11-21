<?php

    require "Connect.php";

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        
        $username = $_POST["username"];
        $email = $_POST["email"];
        $oldName = $_POST["oldName"];

        $sql = "UPDATE users set username = '$username' , email = '$email' WHERE username ='$oldName'";

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