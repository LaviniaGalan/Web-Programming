<?php 
        $connect = mysqli_connect("localhost", "root", "", "enterprisedb");
        
        $filterType = mysqli_real_escape_string($connect, $_POST["filterType"]);
        $filterText = mysqli_real_escape_string($connect, $_POST["filterText"]);

        if ($filterType == "Role"){
            $query = "SELECT * FROM user_table WHERE role = '$filterText'";
        }
        else if ($filterType == "Name"){
            $query = "SELECT * FROM user_table WHERE name LIKE '%$filterText%'";
        }
        $result = mysqli_query($connect, $query);

        echo "<tr><th>Name</th><th>Username</th><th>Password</th><th>Age</th><th>Role</th><th>Email</th><th>Webpage</th></tr>";
        
        while($row = $result->fetch_assoc()) {
            echo "<tr>".
                "<td>" . $row["name"]. "</td>".
                "<td>" . $row["username"]. "</td>".
                "<td>" . $row["password"]. "</td>".
                "<td>" . $row["age"]. "</td>".
                "<td>" . $row["role"]. "</td>".
                "<td>" . $row["email"]. "</td>".
                "<td>" . $row["webpage"]. "</td>".
                "</tr>";
        }
?>