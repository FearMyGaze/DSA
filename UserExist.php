<?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {

        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");

        $email = $_POST["email"];
    
        $sql = "SELECT * FROM users WHERE email = '$email'";

        if (mysqli_num_rows(mysqli_query($conn, $sql)) === 1 ) {

            $result['success'] = "1";
            mysqli_close($conn);

            echo json_encode($result);

        } else {

            $result['success'] = "0";
            mysqli_close($conn);

            echo json_encode($result);

        }

    }   