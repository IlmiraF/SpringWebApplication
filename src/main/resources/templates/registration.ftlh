<#import "parts/common.ftlh" as c>
<#import "parts/login1.ftlh" as l>

<@c.page>
    Add new user
    ${message!}
    <@l.login "/registration" true/>
</@c.page>