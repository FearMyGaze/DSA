<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");
    
        $username = $_POST["username"];
        $passwd = $_POST["passwd"];
        $passwd = password_hash($passwd, PASSWORD_DEFAULT);
        $email = $_POST["email"];
        $device_id = $_POST["device_id"];
    
        $sql = "INSERT INTO users (username, email, passwd,device_id) VALUES ('$username', '$email', '$passwd','$device_id')";
    
        if (mysqli_query($conn, $sql)) {
        
            // $to = $email;
            // $subject = "Welcome to DSA";
            // $message = "Welcome to DSA, your username is: " . $username . " and your password is: " . $passwd . 
            //     "please click the following link to activate your account: http://localhost/DSA/UserActivate.php?username=" . $username;
            // $headers = "From: fearMyGaze@outlook.com";
            // mail($to, $subject, $message, $headers);
            
            $result['userID'] = mysqli_insert_id($conn);
            $result["success"] = "1";
            echo json_encode($result);
        
            mysqli_close($conn);
        } else {
        
            $result["success"] = "0";
            echo json_encode($result);
        
            mysqli_close($conn);
        }
    }