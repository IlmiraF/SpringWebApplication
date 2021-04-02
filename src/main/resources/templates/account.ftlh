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
        <div>
            <#if usr.filename??>
                <img src="/img/${usr.filename}">
            </#if>
        </div>
    </div>
</body>
</html>