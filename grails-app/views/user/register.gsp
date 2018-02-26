<%@ page import="org.springframework.validation.FieldError" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'user.label', default: 'User')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-user" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="create-user" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.user}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.user}" var="error">
                <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form resource="${this.user}" method="POST" action="handleRegistration">

        <div class="dialog">
            <table>
                <tbody>
                <tr class='prop'>
                    <td valign='top' class='nameClear'>
                        <label for="login">Login:</label>
                    </td>
                    <td valign='top'
                        class='valueClear ${hasErrors(bean: user, field: 'userName', 'errors')}'>
                        <input id="login" type="text" name="userName"/>
                    </td>
                </tr>
                <tr class='prop'>
                    <td valign='top' class='nameClear'>
                        <label for="password">Password:</label>
                    </td>
                    <td valign='top'
                        class='valueClear ${hasErrors(bean: user, field: 'password', 'errors')}'>
                        <input id="password" type="password" name="password"/>
                    </td></tr>
                <tr class='prop'>
                    <td valign='top' class='nameClear'>
                        <label for="confirm">Confirm Password:</label>
                    </td>
                    <td valign='top'
                        class='valueClear
                     ${hasErrors(bean: user, field: 'password', 'errors')}'>
                        <input id="confirm" type="password" name="confirm"/>
                    </td></tr>

                <tr class='prop'>
                    <td valign='top' class='nameClear'>
                        <label for="firstName">First Name:</label>
                    </td>
                    <td valign='top'
                        class='valueClear
                    ${hasErrors(bean: user, field: 'firstName', 'errors')}'>
                        <input id="firstName" type="text" name="firstName"/>
                    </td></tr>
                <tr class='prop'>
                    <td valign='top' class='nameClear'>
                        <label for="lastName">Last Name:</label>
                    </td>
                    <td valign='top'
                        class='valueClear ${hasErrors(bean: user, field: 'lastName', 'errors')}'>
                        <input id="lastName" type="text" name="lastName"/>
                    </td>
                </tr>
                <tr class='prop'>
                    <td valign='top' class='nameClear'>
                        <label for="email">Email:</label>
                    </td>
                    <td valign='top'
                        class='valueClear ${hasErrors(bean: user, field: 'email', 'errors')}'>
                        <input id="email" type="text" name="email"/>
                    </td></tr>
                <tr class='prop'>
                    <td valign='top' class='nameClear'>
                        <label for="code">Enter Code:</label>
                    </td>
                    <td valign='top' class='valueClear'>
                        <input id="code" type="text" name="captcha"><br/>
                        <img src="${createLink(controller: 'captcha', action: 'index')}"/>
                    </td></tr>
                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button">
                <input class="save" type="submit" value="Register">
            </span>
        </div>

    </g:form>
</div>
</body>
</html>
