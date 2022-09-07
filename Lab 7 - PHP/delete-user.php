<?php
        $output='';
        $connect = mysqli_connect("localhost", "root", "", "enterprisedb");

        $name=mysqli_real_escape_string($connect, $_POST["name"]);
        $username = mysqli_real_escape_string($connect, $_POST["username"]);
        $password = mysqli_real_escape_string($connect, $_POST["password"]);
        $age = mysqli_real_escape_string($connect, $_POST["age"]);
        $role = mysqli_real_escape_string($connect, $_POST["role"]);
        $email = mysqli_real_escape_string($connect, $_POST["email"]);
        $webpage = mysqli_real_escape_string($connect, $_POST["webpage"]);

        $deleteQuery = "DELETE FROM user_table WHERE name='$name' AND username='$username' AND password='$password' AND age=$age AND role='$role' AND email='$email' AND webpage='$webpage'";
        
        $result = mysqli_query($connect, $deleteQuery);

        if (!$result)
            echo 'Could not delete the user!';
        else
            echo 'The user was deleted!';
        
?>