package com.tsdv.plmanagerapp.control;

import com.tsdv.plmanagerapp.DAO.ProductLineDA;
import com.tsdv.plmanagerapp.entity.ProductLines;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/")
public class ProductLinesServlet extends HttpServlet {

    private ProductLineDA productLineDA;

    @Override
    public void init() {
        productLineDA = new ProductLineDA();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    showNewProductLine(request, response);
                    break;
                case "/ínsert":
                    insertProductLine(request, response);
                    break;
                case "/delete":
                    deleteProductLine(request, response);
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateProductLine(request, response);
                    break;
                default:
                    showAllProductLine(request, response);
                    break;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showAllProductLine(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        ArrayList<ProductLines> listProductLine = productLineDA.searchAllProductLines();
        request.setAttribute("listProductLine", listProductLine);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product_list.jsp");
        dispatcher.forward(request, response);
    }

    public void showNewProductLine(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product_form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String producLine = request.getParameter("productLine");
        ProductLines productLines = productLineDA.searchProductLine(producLine);
        request.setAttribute("productLines", productLines);
        RequestDispatcher díDispatcher = request.getRequestDispatcher("product_form.jsp");
        díDispatcher.forward(request, response);
    }

    private void insertProductLine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String pLine = request.getParameter("productLine");
        String textDesc = request.getParameter("textDescription");
        String htmlDesc = request.getParameter("htmlDescription");

        ProductLines productLines = new ProductLines(pLine, textDesc, htmlDesc);
        productLineDA.insertRecord(productLines);
        response.sendRedirect("list");
    }

    private void updateProductLine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String pLine = request.getParameter("productLine");
        String textDesc = request.getParameter("textDescription");
        String htmlDesc = request.getParameter("htmlDescription");

        ProductLines productLines = new ProductLines(pLine, textDesc, htmlDesc);
        productLineDA.updateProductLine(productLines);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
//        dispatcher.forward(request, response);
        response.sendRedirect("list");
    }

    private void deleteProductLine(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        String pLine = request.getParameter("productLine");
        productLineDA.deleteProductLine(pLine);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list");
        dispatcher.forward(request, response);
//        response.sendRedirect("list");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
