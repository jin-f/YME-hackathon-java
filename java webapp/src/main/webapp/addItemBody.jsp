<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add item body</title>
</head>
<body>
<div class="main">
    <h1>Add item body</h1>
    <% String listName = request.getParameter("listName");
        String itemType1 = request.getParameter("itemType");
        String newItemName = request.getParameter("newItemName");
        String itemType2 = request.getParameter("itemType2");
        ArrayList<String> itemTypes = new ArrayList<>();
        itemTypes.add(itemType1);
        if (itemType2 != null) {
            itemTypes.add(itemType2);
        }

    %>
    <form method="POST" action="${pageContext.request.contextPath}/newItem.html" enctype="multipart/form-data">
        <%
            int counter = 0;
            String itemBody;
            String itemTypeParam;
            for (String itemType : itemTypes) {
                if (counter == 0) {
                    itemBody = "itemBody1";
                    itemTypeParam = "itemType1";
                }
                else {
                    itemBody = "itemBody2";
                    itemTypeParam = "itemType2";
                }
        %>

        <% if (itemType.equals("text")) { %>
        <label for="<%=itemBody%>">Enter text for item:</label>
        <input type="text" id="<%=itemBody%>" name="<%=itemBody%>" placeholder="Enter text for Item" required/>
        <% }%>
        <% if (itemType.equals("URL")) { %>
        <label for="<%=itemBody%>">Enter URL for item:</label>
        <input type="url" id="<%=itemBody%>" name="<%=itemBody%>" placeholder="Enter URL for Item" required/>
        <% }%>
        <% if (itemType.equals("Image")) { %>
        <label for="<%=itemBody%>">Choose image file:</label>
        <input type="file" id="<%=itemBody%>" name="<%=itemBody%>" required/>
        <% }%>
        <% if (itemType.equals("List")) {
            String listBody = "*" + listName + "*" + newItemName;
        %>
        <label>Item Body not required for Lists types</label>
        <input type="hidden" name="<%=itemBody%>" value="<%=listBody%>"/>
        <% }%>
        <input type="hidden" name="listName" value="<%=listName%>"/>
        <input type="hidden" name="newItemName" value="<%=newItemName%>"/>
        <input type="hidden" name="<%=itemTypeParam%>" value="<%=itemType%>"/>

        <% counter++;
        }
        %>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>