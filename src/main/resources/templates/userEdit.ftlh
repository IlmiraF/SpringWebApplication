<html>
<body>
<#if logged.username == usr.username>
<form action="/profile/${usr.username}/edit_page" method="post">
    <div><label> Password: <input type="password" name="password" placeholder="${logged.password}"/> </label></div>
    <div><label> Email: <input type="email" name="email" placeholder="${logged.email!}"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}" />
    <div><input type="submit" value="Edit"/></div>
</form>
</#if>
</body>
</html>