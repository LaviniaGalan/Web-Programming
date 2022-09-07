<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Top 10</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">

    <script>
        $(document).ready(function() {
            getData();
            function getData(data){
                $.get(
                    "Top10URLController",
                    function (data) {
                        let tableString = `<table id="topTable" class="table table-striped"><tr><th>UrlAddress</th><th>Frequency</th></tr>`
                        for (let i in data) {
                            let address = data[i].urlAddress;
                            let frequency = data[i].frequency;
                            tableString += "<tr>";
                            tableString += `<td><a href="` + address +`" target="_blank">` + address + "</a></td>";
                            tableString += "<td>" + frequency + "</td>";
                            tableString += "</tr>";
                        }
                        tableString += "</table>";
                        $("#topDiv").html(tableString);
                    }
                );
            };
        })
    </script>
</head>
<body>
<div class="central_div">
    <p class="h2">TOP 10 URLs</p>
    <br/>
    <br/>

    <div id="topDiv">

    </div>



</div>
</body>
</html>
