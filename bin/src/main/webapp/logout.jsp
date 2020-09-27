<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Library</title>
    <link rel="stylesheet" href="styles.css" type="text/css" />
</head>
<body>
<div id='container'>
<header>
        <h1><a href='/'>Library<span>Store</span></a></h1>
</header>
<%     
         session.removeAttribute("userId");
         session.removeAttribute("password");
         session.invalidate();
     %>   
 <a href='login.jsp'>Go Back To Login</a>
<footer>
<div class='footer-bottom'>
  <p>CS 425 - Database Organization Team Project</p>
</div>
</footer>
</div>
</body>
</html>