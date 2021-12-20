package tsdv.paymentmanager.controller;

import tsdv.paymentmanager.DAO.PaymentDAO;
import tsdv.paymentmanager.model.Payment;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/")
public class PaymentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        String action =request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response); break;
                case "/insert":
                    insertPayment(request, response); break;
                case "/delete":
                    deletePayment(request, response); break;
                case "/edit":
                    showEditForm(request, response); break;
                case  "/update":
                    updatePayment(request, response); break;
                default:
                    listPayment(request, response);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void listPayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        PaymentDAO paymentDAO = new PaymentDAO();
        ArrayList<Payment> listPayment = paymentDAO.selectAllPayment();
        request.setAttribute("listP", listPayment);
        RequestDispatcher dispatcher = request.getRequestDispatcher("payment_list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("payment_form.jsp");
        dispatcher.forward(request, response);
//        response.sendRedirect("payment_form.jsp");
    }

    private void insertPayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
        String checkNumber = request.getParameter("checkNumber");
        Date paymentDate = Date.valueOf(request.getParameter("paymentDate"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        PaymentDAO paymentDAO = new PaymentDAO();
        Payment payment = new Payment(customerNumber, checkNumber, paymentDate, amount);
        paymentDAO.insertPayment(payment);
        response.sendRedirect("list");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
//        dispatcher.forward(request, response);
    }

    private void deletePayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
        String checkNumber = request.getParameter("checkNumber");
        PaymentDAO paymentDAO = new PaymentDAO();
        paymentDAO.deletePayment(customerNumber, checkNumber);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
        dispatcher.forward(request, response);
//        response.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
        String checkNumber = request.getParameter("checkNumber");
        PaymentDAO paymentDAO = new PaymentDAO();
        Payment payment = paymentDAO.selectPayment(customerNumber, checkNumber);
        request.setAttribute("payment", payment);
        RequestDispatcher dispatcher = request.getRequestDispatcher("payment_form.jsp");
        dispatcher.forward(request, response);
//        response.sendRedirect("payment_form.jsp");
    }

    public void updatePayment(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int customerNumber = Integer.parseInt(request.getParameter("customerNumber"));
        String checkNumber = request.getParameter("checkNumber");
        Date paymentDate = Date.valueOf(request.getParameter("paymentDate"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        PaymentDAO paymentDAO = new PaymentDAO();
        Payment payment = new Payment(customerNumber, checkNumber, paymentDate, amount);
        paymentDAO.updatePayment(payment);
        response.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
