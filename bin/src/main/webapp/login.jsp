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
  <form method="post" action="UserLoginServlet">
  <h4>Enter your User ID and Password and click Login</h4>
  <table cellpadding='2' cellspacing='1'>
    <tr>
        <td>User ID</td>
        <td><input type="text" size="15" name="userId"></input></td>
    </tr>
    <tr>
        <td>Password</td>
        <td><input type="password" size="15" name="password"/></td>
    </tr>
    <tr>
        <td colspan='2'>
            <center><input type="submit" value="Login" /></center>
        </td>
    </tr>
 </table>
 </form>
 <a href="register.jsp">New Customer ? Sign In</a>
<footer>
    <div class="footer-bottom">
        <p>CS 425 - Database Organization Team Project</p>
    </div>
</footer>
</div>

 </body>
</html>