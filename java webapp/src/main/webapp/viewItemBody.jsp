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

    <% String itemName = request.getParameter("itemName");
        String itemType = request.getParameter("itemType");
        String itemBody = request.getParameter("itemBody");
    %>

    <h1>Item body for <%=itemName%>
    </h1>

    <%
        if (itemType.equals("text")) {%>
    <%=itemBody%>
    <% }%>
    <%
        if (itemType.equals("URL")) {%>
    <a href="<%=itemBody%>"><%=itemBody%>
    </a>
    <% } %>
    <%
        if (itemType.equals("Image")) {%>
    <img src="<%=itemBody%>" alt="<%=itemName%>">
    <% } %>


</div>
<a href="viewList.html">Main menu</a>
</body>
</html>
