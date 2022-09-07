<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Top N</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style.css">

    <script>
        $(document).ready(function () {

            function getData(){
                console.log("send post");
                let readN = $("#nInput").val();
                $.post(
                    "TopNController",
                    {
                        n: readN
                    },
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

            $("#showButton").click(function(){
                getData();
            })

        })
    </script>
</head>
<body>
<div class="central_div">
    <p class="h2">TOP N URLs</p>
    <br/>
    <br/>

    <label class="label-default">N = </label>
    <input class="form-control" placeholder="N"  name="n" id="nInput"> <br/> <br/>
    <button class="btn btn-primary" id="showButton">Show URLs</button>
    <br>
    <div id="topDiv">

    </div>

</div>
</body>
</html>
