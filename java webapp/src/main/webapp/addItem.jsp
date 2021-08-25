<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add new Item</title>
</head>
<body>
<div class="main">
    <h1>Add new Item</h1>
    <% String listName = request.getParameter("listName");
    %>
    <form method="POST" action="addItemBody.jsp">
        <ul>
            <li>
                <label for="newItemName">Enter Name of new Item</label>
                <input type="text" id="newItemName" name="newItemName" placeholder="Enter name of new item" required/>
                <label for="itemType">Choose a itemType:</label>
                <select id="itemType" name="itemType">
                    <option value="text">Text</option>
                    <option value="URL">URL</option>
                    <option value="Image">Image</option>
                    <option value="List">List</option>
                </select>
            </li>

        </ul>
        <input type="hidden" name="listName" value="<%=listName%>"/>
        <input type="submit" value="Enter Item Body"/>
    </form>
</div>
</body>
</html>