<?php 
        $connect = mysqli_connect("localhost", "root", "", "enterprisedb");
        
        $action = mysqli_real_escape_string($connect, $_POST["action"]);

        $buttonClass = $action . "Button";
        $query = "SELECT * FROM user_table";
        $result = mysqli_query($connect, $query);
        
        echo "<tr>";

        if($action == "Update"){
                echo "<th>Id</th>";
        }
        
        echo "<th>Name</th><th>Username</th><th>Password</th><th>Age</th><th>Role</th><th>Email</th><th>Webpage</th><th>$action</th></tr>";
        
        while($row = $result->fetch_assoc()) {
            echo "<tr>";
            if($action == "Update"){
                    echo "<td>" . $row["userid"]. "</td>";
            }

            echo "<td>" . $row["name"]. "</td>".
                "<td>" . $row["username"]. "</td>".
                "<td>" . $row["password"]. "</td>".
                "<td>" . $row["age"]. "</td>".
                "<td>" . $row["role"]. "</td>".
                "<td>" . $row["email"]. "</td>".
                "<td>" . $row["webpage"]. "</td>".
                "<td> <button class=\"$buttonClass\"> $action </button></td>".
                "</tr>";
        }

?>