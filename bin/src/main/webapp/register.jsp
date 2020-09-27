<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "DTD/xhtml1-transitional.dtd">

<!--
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
-->



<html>
 <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Library</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
</head>
 <body>
<div id="container">
<header>
        <h1><a href='/'>Library<span>Store</span></a></h1>
</header>
    <form id='register' action='Registeration.jsp' method='post'
    accept-charset='UTF-8'>
         <h4>Enter your Form Details</h4>
        <label for='firstname' >First Name:</label>
        <input type='text' name='firstName' id='firstName' maxlength="50" />
         <br><br>
         <label for='middlename' >Middle Name*:</label>
        <input type='text' name='middleName' id='firstName' maxlength="50" />
         <br><br>
         <label for='lastname' >Last Name*:</label>
        <input type='text' name='lastName' id='lastName' maxlength="50" />
         <br><br>
         <label for='email' >Email*:</label>
        <input type='text' name='email' id='email' maxlength="50" />
         <br><br>
         <label for='username' >UserName*:</label>
        <input type='text' name='username' id='username' maxlength="50" />
         <br><br>
        <label for='password' >Password*:</label>
        <input type='password' name='password' id='password' maxlength="50" />
        <br><br>
        <input type='submit' name='Submit' value='Submit' />
    </form>
    <br>
    <br>
    <a href='login.jsp'>Go Back To Login</a>
<footer>
    <div class="footer-bottom">
            <p>CS 425 - Database Organization Team Project</p>
    </div>
</footer>
</div>
 </body>
</html>


