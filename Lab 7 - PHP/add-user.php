<?php
        $connect = mysqli_connect("localhost", "root", "", "enterprisedb");

        $name = mysqli_real_escape_string($connect, $_POST["name"]);
        $username = mysqli_real_escape_string($connect, $_POST["username"]);
        $password = mysqli_real_escape_string($connect, $_POST["password"]);
        $age = mysqli_real_escape_string($connect, $_POST["age"]);
        $role = mysqli_real_escape_string($connect, $_POST["role"]);
        $email = mysqli_real_escape_string($connect, $_POST["email"]);
        $webpage = mysqli_real_escape_string($connect, $_POST["webpage"]);

        $query = "INSERT INTO user_table (name, username, password, age, role, email, webpage) VALUES ('$name', '$username', '$password', $age, '$role', '$email', '$webpage')";
        $result = mysqli_query($connect, $query);

        if (!$result)
            echo 'Could not insert new user!';
        else
            echo 'New user inserted!';
        
?>