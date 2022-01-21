<?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");
    
        $userID = $_POST["userID"];
        $id = $_POST["id"];
        
        $sql = "DELETE FROM files WHERE id = '$id' && userID = '$userID'";

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