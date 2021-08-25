<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Remove an Item</title>
</head>
<body>
<div class="main">

    <% String listName = request.getParameter("listName");
    %>
    <h1>Remove an Item from <%=listName%>
    </h1>
    <form method="POST" action="removeItem.html">
        <label for="removeItemName">Enter Name of Item you wish to remove</label>
        <input type="text" id="removeItemName" name="removeItemName"
               placeholder="Enter the name of the item you would like to delete" required/>
        <input type="hidden" name="listName" value="<%=listName%>"/>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>