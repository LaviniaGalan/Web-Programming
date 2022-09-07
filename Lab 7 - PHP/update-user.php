<?php
        $connect = mysqli_connect("localhost", "root", "", "enterprisedb");

        $id = mysqli_real_escape_string($connect, $_POST["id"]);
        $name = mysqli_real_escape_string($connect, $_POST["name"]);
        $username = mysqli_real_escape_string($connect, $_POST["username"]);
        $password = mysqli_real_escape_string($connect, $_POST["password"]);
        $age = mysqli_real_escape_string($connect, $_POST["age"]);
        $role = mysqli_real_escape_string($connect, $_POST["role"]);
        $email = mysqli_real_escape_string($connect, $_POST["email"]);
        $webpage = mysqli_real_escape_string($connect, $_POST["webpage"]);

        $query = "UPDATE user_table SET name='$name', username='$username', password='$password', age=$age, role='$role', email='$email', webpage='$webpage'" . 
                 "WHERE userid=$id";
        $result = mysqli_query($connect, $query);

        if (!$result)
            echo 'Could not update the user!';
        else
            echo 'User updated!';
        
?>