<?php
    if ($_SERVER['REQUEST_METHOD'] == 'POST') {
        require "Connect.php";
        $conn = mysqli_connect($servername, $username, $passwd, "DSA");

        $userID = $_POST["userID"];
        $fileTitle = $_POST["fileTitle"];
        $fileDesc = $_POST["fileDesc"];
        $fileData = $_POST["fileData"];

        //TODO: sanitize the input

        $sql = "INSERT INTO files (userID, fileTitle, fileDesc, fileData) VALUES ('$userID', '$fileTitle', '$fileDesc', '$fileData')";

        if (mysqli_query($conn, $sql)) {

            $result["success"] = "1";
            echo json_encode($result);

            mysqli_close($conn);
        } else {

            $result["success"] = "0";
            echo json_encode($result);

            mysqli_close($conn);
        }
    }    
