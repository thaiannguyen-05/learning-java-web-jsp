/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HanhKhachDao;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author An
 */
@WebServlet("/add")
public class Add extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy parameter dạng String trước
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        String gender = request.getParameter("gender");
        String lessMoneyStr = request.getParameter("lessMoney");

        // Validate: kiểm tra null hoặc rỗng TRƯỚC khi parse
        if (name == null || name.trim().isEmpty() ||
                ageStr == null || ageStr.trim().isEmpty() ||
                gender == null || gender.trim().isEmpty() ||
                lessMoneyStr == null || lessMoneyStr.trim().isEmpty()) {

            request.getSession().setAttribute("error", "Vui lòng điền đầy đủ thông tin!");
            response.sendRedirect("loading");
            return;
        }

        try {
            // Parse SAU khi đã validate không rỗng
            int age = Integer.parseInt(ageStr.trim());
            double lessMoney = Double.parseDouble(lessMoneyStr.trim());

            HanhKhachDao dao = new HanhKhachDao();
            boolean isSuccess = dao.add(name.trim(), age, gender.trim(), lessMoney);

            if (isSuccess) {
                request.getSession().setAttribute("message", "Thêm khách hàng thành công!");
            } else {
                request.getSession().setAttribute("error", "Thêm khách hàng thất bại!");
            }

        } catch (NumberFormatException ex) {
            request.getSession().setAttribute("error", "Tuổi và tiền giảm phải là số hợp lệ!");
            Logger.getLogger(Add.class.getName()).log(Level.WARNING, "Invalid number", ex);
        } catch (SQLException ex) {
            request.getSession().setAttribute("error", "Lỗi database: " + ex.getMessage());
            Logger.getLogger(Add.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("loading");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
