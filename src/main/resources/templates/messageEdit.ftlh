<html>
<body>
<div>
    <form method="post" enctype="multipart/form-data">
        <input type="text" name="text" placeholder="${message.text}" />
        <input type="text" name="tag" placeholder="${message.tag}">
        <input type="file" name="file">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Добавить</button>
    </form>
</div>
</body>
</html>