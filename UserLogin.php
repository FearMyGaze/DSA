<?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");
    
        $email = $_POST["email"];
        $passwd = $_POST["passwd"];

    
        //$sql = "SELECT * FROM users WHERE email = '$email' AND activated = 1";
    
        $sql = "SELECT * FROM users WHERE email = '$email'";
    
        $response = mysqli_query($conn, $sql);
    
        if (mysqli_num_rows($response) === 1 ) {
        
            $row = mysqli_fetch_assoc($response);
        
            if(password_verify($passwd, $row['passwd'])) {
            
                $result['username'] = $row['username'];
                $result['userID'] = $row['id'];
                
                $result['success'] = "1";
            
                echo json_encode($result);
            
                mysqli_close($conn);
            
            } else {
            
                $result['success'] = "0";
            
                echo json_encode($result);
            
                mysqli_close($conn);
            
            }
        }else {
        
            $result['success'] = "0";
        
            echo json_encode($result);
        
            mysqli_close($conn);
        }
    }