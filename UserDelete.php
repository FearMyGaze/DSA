<?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");
        
        $email = $_POST["email"];

        $sql = "DELETE FROM Users WHERE email = '$email'";
           
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
       
