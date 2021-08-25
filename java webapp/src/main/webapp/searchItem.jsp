<%@ page import="yme.hackathon.model.ListCollection" %>
import ListCollection;
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css"/>
    <title>Search Item</title>
</head>
<body>
<h1>Lists Viewing App</h1>
<div class="main">
    <h2>Lists:</h2>
    <% ListCollection lists = (ListCollection) session.getAttribute("lists");
    %>
    <form method="POST" action="${pageContext.request.contextPath}/findItem.html">
        <label for="itemName">Search for item by its name</label>
        <input type="text" id="itemName" name="itemName" placeholder="Enter name of the Item"/><br>

        <% if (lists != null) {
            for (String listName : lists.getListNames()) {
                char firstChar = listName.charAt(0);
                if (firstChar != '*') {
        %>

        <input type="checkbox" id="<%=listName%>" name="<%=listName%>" value="<%=listName%>">
        <label for="<%=listName%>"><%=listName%>
        </label><br>
        <% }
        }
        }%>
        <input type="submit" value="Search for Item in selected lists"/>
    </form>


</div>
<a href="viewList.html">Main menu</a>
</body>
</html>
