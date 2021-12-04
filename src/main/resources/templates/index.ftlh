<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spy</title>
</head>
<body>
<form action="/" method="post">
    <label>
        <span>Ключ:</span>
        <input type="text" value="key"/>
    </label>
</form>
<br>
<div>
    <#list objects as object>
        <div>${object}</div>
    <#else>
        База данных не содержит записей
    </#list>
</div>
</body>
</html>