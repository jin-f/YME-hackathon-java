<%@ page import="yme.hackathon.model.ListCollection" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="yme.hackathon.model.Item" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css"/>
    <title>Item result</title>
</head>
<body>
<h1>Lists Viewing App</h1>
<div class="main">

    <% ListCollection lists = (ListCollection) request.getAttribute("lists");
        ArrayList<String> hasItem = (ArrayList<String>) request.getAttribute("hasItem");
        String itemName = (String) request.getAttribute("itemName");
    %>

    <h2>Lists with <%=itemName%> item
    </h2>

    <table>
        <tr>
            <th>List name</th>
            <th>Item type1</th>
            <th>Item body1</th>
            <th>Item type2</th>
            <th>Item body2</th>
        </tr>
        <%

            if (lists != null) {
                for (String listName : hasItem) {
                    Item item = lists.getList(listName).getItem(itemName);
                    String itemType1 = item.getItemType1();
                    String itemBody1 = item.getItemBody1();
                    String itemType2 = item.getItemType2();
                    String itemBody2 = item.getItemBody2();

        %>
        <tr>
            <td><%=listName%>
            </td>
            <td><%=itemType1%>
            </td>
            <td>
                <form method="POST" action="viewItemBody.jsp">
                    <input type="hidden" name="itemName" value="<%=itemName%>"/>
                    <input type="hidden" name="itemType" value="<%=itemType1%>"/>
                    <input type="hidden" name="itemBody" value="<%=itemBody1%>"/>
                    <input type="submit" value="View Item Body 1"/>
                </form>
            </td>
            <% if (itemType2 != null) {
            %>
            <td><%=itemType2%>
            </td>
            <td>
                <form method="POST" action="viewItemBody.jsp">
                    <input type="hidden" name="itemName" value="<%=itemName%>"/>
                    <input type="hidden" name="itemType" value="<%=itemType2%>"/>
                    <input type="hidden" name="itemBody" value="<%=itemBody2%>"/>
                    <input type="submit" value="View Item Body 2"/>
                </form>
            </td>
            <% } %>
        </tr>
        <% }
        }
        %>
    </table>

</div>
<a href="viewList.html">Main menu</a>
</body>
</html>
