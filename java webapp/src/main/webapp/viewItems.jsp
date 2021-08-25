<%@ page import="yme.hackathon.model.ItemCollection" %>
<%@ page import="yme.hackathon.model.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles.css"/>
    <title>View Items</title>
</head>
<body>
<h1>Lists Viewing App</h1>
<div class="main">
    <h2>Item controls:</h2>

    <ul>
        <% String listName = (String) request.getAttribute("listName");
            String[] ss = listName.split("\\*");
            String actualListName = ss[ss.length - 1];
        %>
        <li>
            <form action="addItem.jsp" method="post">
                <input type="hidden" name="listName" value="<%=listName%>"/>
                <input type="submit" value="Add Item"/>
            </form>
        </li>
        <li>
            <form action="addComboItem.jsp" method="post">
                <input type="hidden" name="listName" value="<%=listName%>"/>
                <input type="submit" value="Add Combo Item"/>
            </form>
        </li>
        <li>
            <form action="removeItem.jsp" method="post">
                <input type="hidden" name="listName" value="<%=listName%>"/>
                <input type="submit" value="Remove Item"/>
            </form>
        </li>
    </ul>

    <h1>Items for <%=actualListName%>
    </h1>
    <table>
        <tr>
            <th>Item name</th>
            <th>Item type1</th>
            <th>Item body1</th>
            <th>Item type2</th>
            <th>Item body2</th>
        </tr>
        <%
            ItemCollection itemCollection = (ItemCollection) request.getAttribute("itemCollection");
            if (itemCollection != null) {
                for (String itemName : itemCollection.getItemNames()) {

                    Item item = itemCollection.getItem(itemName);
                    String itemType1 = item.getItemType1();
                    String itemBody1 = item.getItemBody1();
                    String itemType2 = item.getItemType2();
                    String itemBody2 = item.getItemBody2();


        %>
        <tr>
            <td><%=itemName%>
            </td>
            <td><%=itemType1%>
            </td>
            <td>
                <% if (itemType1.equals("List")) {
                %>
                <form method="POST" action="${pageContext.request.contextPath}/listItems.html">
                    <input type="hidden" name="listName" value="<%=itemBody1%>"/>
                    <input type="submit" value="View Item Body 1"/>
                </form>
                <%
                }
                else {
                %>
                <form method="POST" action="viewItemBody.jsp">
                    <input type="hidden" name="itemName" value="<%=itemName%>"/>
                    <input type="hidden" name="itemType" value="<%=itemType1%>"/>
                    <input type="hidden" name="itemBody" value="<%=itemBody1%>"/>
                    <input type="submit" value="View Item Body 1"/>
                </form>
                <%}%>

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
