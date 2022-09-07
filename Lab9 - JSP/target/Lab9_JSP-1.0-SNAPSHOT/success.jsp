<%@ page import="com.example.Model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Url Management System</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            $("#addDiv").hide();

            $("#addButton").click(function () {
                if ($("#addDiv").is(":hidden")) {
                    $("#addDiv").show();
                } else {
                    $("#addDiv").hide();
                }

            });

            getData();

            function getData() {
                $.get(
                    "MyURLController",
                    function (data) {
                        let tableString = `<table id="dataTable" class="table table-striped"><tr><th>UrlAddress</th></tr>`
                        for (let i in data) {
                            let address = data[i].urlAddress;
                            tableString += "<tr>";
                            tableString += `<td><a href="` + address + `" target="_blank">` + address + "</a></td>";
                            tableString += `<td><button class="btn btn-outline-primary deleteButton"> Delete` + "</button></td>";
                            tableString += "</tr>";
                        }
                        tableString += "</table>";
                        $("#myUrls").html(tableString);
                    }
                );
            }

            $("#submitButton").click(function () {
                const urlAddress = $("#addUrlLabel").val();
                if (urlAddress !== '') {
                    $.ajax({
                        url: "MyURLController",
                        type: "POST",
                        data: {URLAddress: urlAddress},
                        success: function (data) {
                            window.alert(data.result);
                            $("#addUrlLabel").val("");
                            getData();
                        }
                    })
                } else {
                    window.alert("Complete the required fields!");
                }
            });

            $(document).on("click", ".deleteButton", function () {

                const row = $(this).closest('tr');
                const rowIndex = row.index();
                const table = document.querySelector("#dataTable");
                const allRows = table.rows;
                const urlAddress = allRows[rowIndex].getElementsByTagName("td")[0].children[0].innerHTML;

                if(confirm("Are you sure you want to delete " + urlAddress + "?")) {
                    console.log(urlAddress);
                    $.ajax({
                        url: "DeleteController",
                        type: "POST",
                        data: {URLAddress: urlAddress},
                        success: function (data) {
                            alert(data.result);
                            getData();
                        }
                    })
                }
                else {
                    return false;
                }
            });
        })
    </script>
</head>
<body>
<%--<%--%>
<%--    User user = (User) session.getAttribute("user");--%>
<%--    if (user == null) {--%>
<%--        response.sendRedirect("login.jsp");--%>
<%--        return;--%>
<%--    }--%>
<%--%>--%>
<div class="central_div">
    <p class="h2">URL Management System</p>
    <br/>
    <br/>

    <div id="myUrls"></div>

    <br>
    <br>
    <button type="button" class="btn btn-primary" id = "addButton">Add URL</button>
    <div id = "addDiv">
        <br>

        <form id="addForm" >
            <label class="label-default">Enter URL: </label>
            <input id="addUrlLabel" class="form-control" placeholder="URL" type="text" name="URLAddress"> <br/>

            <input id="submitButton" type="button" class="btn btn-primary" value="Submit"/>
        </form>
        <br>
<%--        action="MyURLController" method="post"--%>
    </div>

    <br/>
    <a href="topn.jsp" type="button" class="btn btn-primary">Top N URL's</a>
    <br><br><br><br><br>
</div>
</body>
</html>
