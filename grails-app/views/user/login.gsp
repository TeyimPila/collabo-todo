<%@ page import="collabo.todo.User" %>
<html>
<head>
    <title>Login Page</title>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="body">
    <g:if test="${flash.message}">
        <div class="message">
            <g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMsg}"/>
        </div>
    </g:if>
    <p>
        Welcome to Your ToDo List. Login below
    </p>

    <g:form action="handleLogin" method="POST">
        <span class='nameClear'>
            <label for="login">
                Sign In:
            </label>
        </span>
        <g:select id="login" name='userName' from="${User.list()}"
                  optionKey="userName" optionValue="userName"/>
        <br/>

        <div class="buttons">
            <span class="button">
                <g:actionSubmit value="Login" controller="user" action="handleLogin"/>
            </span>
        </div>
    </g:form>
</div>
</body>
</html>