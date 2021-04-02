<#import "parts/common.ftlh" as c>
<#import "parts/login1.ftlh" as l>

<@c.page>
    <#if Session.SPRING_SECURITY_CONTEXT??>
        <div>
            <@l.logout />
        </div>
        <div>
            <form method="post">
                <input type="text" name="text" placeholder="Введите сообщение" />
                <input type="text" name="tag" placeholder="Тэг">
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit">Добавить</button>
            </form>
        </div>
    <#else>
        <a href="/login">Login</a>
    </#if>
    <div>Список сообщений</div>
    <form method="get" action="/main">
        <input type="text" name="filter" value="${filter!}">
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            <a href="/profile/${message.authorName}">${message.authorName}</a>
        </div>
    <#else>
        No message
    </#list>
</@c.page>