<%--
  Created by IntelliJ IDEA.
  User: AnhAnh
  Date: 12/20/2021
  Time: 2:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Payment Form</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <c:if test="${payment != null}">
    <form action="update" method="POST">
    </c:if>
    <c:if test="${payment == null}">
    <form action="insert" method="POST">
    </c:if>
        <caption>
            <h2 style="text-align: center;">
                <a style="color: maroon;" href="<%=request.getContextPath()%>/list">
                    <c:if test="${payment != null}">
                        Edit User
                    </c:if>
                    <c:if test="${payment == null}">
                        Add New User
                    </c:if>
                </a>
            </h2>
        </caption>
        <br><br>

        <table class="table table-bordered">
            <tbody>
            <c:if test="${payment != null}">
                <tr>
                    <td><input type="hidden" name="customerNumber" value="${payment.customerNumber}"></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="checkNumber" value="${payment.checkNumber}"></td>
                </tr>
            </c:if>
            <c:if test="${payment == null}">
                <tr>
                    <td style="font-weight: 600">Customer number</td>
                    <td><input type="number" name="customerNumber" value="${payment.customerNumber}" required></td>
                </tr>
                <tr>
                    <td style="font-weight: 600">Check number</td>
                    <td><input type="text" name="checkNumber" value="${payment.checkNumber}" required></td>
                </tr>
            </c:if>
                <tr>
                    <td style="font-weight: 600">Payment Date</td>
                    <td><input type="text" name="paymentDate" value="${payment.paymentDate}" required></td>
                </tr>
                <tr>
                    <td style="font-weight: 600">Amount</td>
                    <td><input type="number" step="0.01" name="amount" value="${payment.amount}" required></td>
                </tr>
                <tr>
                    <td><input type="submit" class="btn btn-success" value="Save"></td>
                </tr>
            </tbody>
        </table>
    </form>
</body>
</html>
