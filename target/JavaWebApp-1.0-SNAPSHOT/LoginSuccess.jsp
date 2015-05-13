<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="css/bootstrap-responsive.min.css" rel="stylesheet" media="screen">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/app.js"></script>
    <title>CHAT</title>
</head>
<body onload="getContacts();">
    <script>
        setInterval(getContacts,1000);
    </script>
    <%
    String userName = null;
    Cookie[] cookies = request.getCookies();
    if(cookies !=null){
    for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
    }
    }
    if(userName == null) response.sendRedirect("login.html");
    %>
    <h3>Hi <%=userName %>, Login successful.</h3>
    
    <div class="container chat-wrapper">
            <form id="do-chat">
                    <div style="height:200px; overflow:auto;">
                        <table id="response" class="table table-bordered"></table>
                    </div>
                    <fieldset>
                        <legend>Enter your message..</legend>
			<div class="controls">
                            <div class="control-group">
                                <div class="controls">
                                    <input type="text" class="input-block-level" placeholder="Your message..." name="mesText" style="height:60px"/>
                                </div>
                            </div>
                            <input type="submit" class="btn btn-large btn-block btn-primary"
						onclick="addBtn();" value="Send message" />
                        </div>
                    </fieldset>
		</form>
	</div>
    
    <form action="LogoutServlet" method="post">
        <input class="btn-primary" type="submit" value="Logout" >
    </form>
</body>
</html>
