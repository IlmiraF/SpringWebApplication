<html>
<body>
    <div>
        <#if logged == usr.username>
        <form method="post" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
        </#if>
        <b>${usr.username}</b>
        <div>${usr.email}</div>
        <div>
            <#if usr.filename??>
                <img src="/img/${usr.filename}" width="150" height="150">
            <#else>
                <img src="/img/unnamed.jpg" width="150" height="150">
            </#if>
        </div>
        <#if logged == usr.username>
        <a href="/profile/${usr.username}/edit_page">Edit</a>
        </#if>
        <#if logged == usr.username>
            <a href="/profile/${usr.username}/delete">Delete</a>
        </#if>
    </div>
</body>
</html>