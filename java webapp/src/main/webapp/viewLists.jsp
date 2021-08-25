<%@ page import="yme.hackathon.model.ListCollection" %>
<%@ page import="yme.hackathon.model.ItemCollection" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css"/>
    <title>View Lists</title>
</head>
<body>
<h1>Lists Viewing App</h1>
<div class="main">
    <h2>Lists:</h2>
    <% ListCollection lists = (ListCollection) request.getAttribute("listCollection");
        session.setAttribute("lists", lists);
    %>
    <ul>
        <li><a href="addList.html">Add a new List</a></li>
        <li><a href="removeList.html">Remove an existing List</a></li>
        <li><a href="renameList.html">Rename an existing list</a></li>
        <li>
            <form method="POST" action="${pageContext.request.contextPath}/searchList.html">
                <label for="searchListName">Search for a list by name</label>
                <input type="text" id="searchListName" name="searchListName" placeholder="Enter name of the List"/>
                <input type="submit" value="Search"/>
            </form>
        </li>
        <li>
            <form method="POST" action="${pageContext.request.contextPath}/searchItem.jsp">

                <input type="submit" value="Search for an Item"/>
            </form>
        </li>
    </ul>

    <table>
        <tr>
            <th>List name</th>
            <th>Number of Items in List</th>
        </tr>
        <%

            if (lists != null) {
                String itemCount = "0";
                for (String listNames : lists.getListNames()) {
                    char firstChar = listNames.charAt(0);
                    if (firstChar != '*') {
                        ItemCollection items = lists.getList(listNames);
                        if (items != null) {
                            itemCount = String.valueOf(items.getSize());
                        }

        %>
        <tr>
            <td>
                <form action="${pageContext.request.contextPath}/listItems.html" method="post">
                    <input type="hidden" name="listName" value="<%=listNames%>"/>
                    <input type="submit" value="<%=listNames%>"/>
                </form>
            </td>
            <td><%=itemCount%>
            </td>

        </tr>
        <% }
        }
        }%>

    </table>
</div>
</body>
</html>
