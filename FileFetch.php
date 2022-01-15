<?php

    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");

        $userID = $_POST["userID"];

        $sql = "SELECT * FROM FILES WHERE userID = '$userID'";

        $response = mysqli_query($conn, $sql);

        if (mysqli_num_rows($response) > 0) {

            $json = mysqli_fetch_all ($response, MYSQLI_ASSOC);
            
            $result['success'] = "1";
            
            $result['Files'] = $json;
        
            echo json_encode($result);

            mysqli_close($conn);

        } else {

            $result['success'] = "0";

            echo json_encode($result);

            mysqli_close($conn);

        }
    }