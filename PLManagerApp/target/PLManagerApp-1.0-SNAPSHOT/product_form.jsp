<%--
  Created by IntelliJ IDEA.
  User: AnhAnh
  Date: 12/18/2021
  Time: 9:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Add Product Line</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <c:if test="${productLines != null}">
        <form action="update" method="POST">
    </c:if>
    <c:if test="${productLines == null}">
        <form action="ínsert" method="POST">
    </c:if>

            <caption>
                <h2 style="text-align: center;">
                    <c:if test="${productLines != null}">
                        Edit User
                    </c:if>
                    <c:if test="${productLines == null}">
                        Add New User
                    </c:if>
                </h2>
            </caption>
            <br>
            <table class="table table-bordered">
                <tbody>
                <c:if test="${productLines != null}">
                    <tr>
                        <td>
                            <input type="hidden" name="productLine" value="${productLines.productLine}">
                        </td>
                    </tr>
                </c:if>

                <c:if test="${productLines == null}">
                    <tr>
                        <td style="font-weight: 600;">ProductLine</td>
                        <td>
                            <input type="text" name="productLine" value="${productLines.productLine}" required>
                        </td>
                    </tr>
                </c:if>
                    <tr>
                        <td style="font-weight: 600;">Text Description</td>
                        <td>
                            <input type="text" value="${productLines.textDescription}" name="textDescription">
                        </td>
                    </tr>
                    <tr>
                        <td style="font-weight: 600;">HTML Description</td>
                        <td>
                            <input type="text" value="${productLines.htmlDescription}" name="htmlDescription">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" class="btn btn-success" value="Save">
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
</body>
</html>
