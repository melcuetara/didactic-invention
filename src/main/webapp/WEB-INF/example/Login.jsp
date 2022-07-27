<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <title>Login</title>
    <style>
         body {
            background-image: url("https://img.freepik.com/free-photo/chicken-wings-barbecue-sweetly-sour-sauce-picnic-summer-menu-tasty-food-top-view-flat-lay_2829-6471.jpg?w=2000");
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        form {border: 3px solid #f1f1f1;
             margin-top: 200px;}
        
        input[type=text], input[type=password] {
          width: 100%;
          padding: 12px 20px;
          margin: 8px 0;
          display: inline-block;
          border: 1px solid #ccc;
          box-sizing: border-box;
        }
        
        button {
          background-color: #04AA6D;
          color: white;
          padding: 14px 20px;
          margin: 8px 0;
          border: none;
          cursor: pointer;
          width: 100%;
        }
        
        button:hover {
          opacity: 0.8;
        }
        
        .cancelbtn {
          width: auto;
          padding: 10px 18px;
          background-color: #f44336;
        }
        
        .imgcontainer {
          text-align: center;
          margin: 24px 0 12px 0;
        }
        
        img.avatar {
          width: 40%;
          border-radius: 50%;
        }
        
        .container {
          padding: 16px;
        }
        
        span.psw {
          float: right;
          padding-top: 16px;
        }
        
        /* Change styles for span and cancel button on extra small screens */
        @media screen and (max-width: 300px) {
          span.psw {
             display: block;
             float: none;
          }
          .cancelbtn {
             width: 100%;
          }
        }
        </style>
</head>

<body>
    <nav class="navbar navbar-light navbar-expand-lg fixed-top bg-white clean-navbar" style="border: outset;">
        <div class="container">
            <h3 class="navbar-brand">Login</h3>
            <a class="navbar-brand logo" href="#">
                <img src="data:image/png;base64,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"
                    alt="logo" 
                    style="width:50px;" 
                    class="rounded-pill">
            </a>
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <s:url var="url" action="register" />
                        <s:a href="%{url}" theme="bootstrap" cssClass="nav-link">Register</s:a>
                    </li>
                </ul>
        </div>
    </nav>

    <s:form action="login" theme="simple" method="post">
      
        <div class="container bg-white p-5">
            <label for="uname"><b>Username</b></label>
          <s:textfield name="username" label="Username" />
      
          <label for="psw"><b>Password</b></label>
          <s:password name="password" label="Password" />
              
          <button type="submit">Login</button>
        </div>
    
      </s:form>
</body>

</html>