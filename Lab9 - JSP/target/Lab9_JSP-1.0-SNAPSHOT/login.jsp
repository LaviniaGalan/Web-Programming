<!DOCTYPE html>
<html>
<head>
    <title>Url Management System</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="style.css">

</head>
<body>
<div class="central_div">
    <p class="h2">URL Management System</p>
    <br/>
    <br/>
    <form action="LoginController" method="post">
        <label class="label-default">Enter username: </label>
        <input class="form-control" placeholder="Username" type="text" name="username"> <br/>

        <label class="label-default">Enter password: </label>
        <input class="form-control" placeholder="Password" type="password" name="password"> <br/> <br/>
        <input type="submit" class="btn btn-primary" value="Login"/>
    </form>


    <br/><br>
    <p>OR see top 10 URLs without account:</p>
    <br>
    <a href="top10.jsp" type="button" class="btn btn-primary">Top 10 URL's</a>
</div>
</body>
