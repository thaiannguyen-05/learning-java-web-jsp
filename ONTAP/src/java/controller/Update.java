/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.HanhKhachDao;
import dto.UpdateDto;
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
@WebServlet("/update")
public class Update extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("loading");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Lấy ID - bắt buộc phải có
        String idStr = request.getParameter("id");

        if (idStr == null || idStr.trim().isEmpty()) {
            request.getSession().setAttribute("error", "Vui lòng chọn khách hàng để cập nhật!");
            response.sendRedirect("loading");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());

            // Tạo UpdateDto với helper methods - siêu ngắn gọn!
            UpdateDto dto = new UpdateDto();
            dto.setNameIfPresent(request.getParameter("name"));
            dto.setAgeIfPresent(request.getParameter("age"));
            dto.setGenderIfPresent(request.getParameter("gender"));
            dto.setLessMoneyIfPresent(request.getParameter("lessMoney"));

            HanhKhachDao dao = new HanhKhachDao();
            boolean isSuccess = dao.update(dto, id);

            if (isSuccess) {
                request.getSession().setAttribute("message", "Cập nhật khách hàng thành công!");
            } else {
                request.getSession().setAttribute("error", "Cập nhật thất bại hoặc không có gì thay đổi!");
            }

        } catch (NumberFormatException ex) {
            request.getSession().setAttribute("error", "ID, tuổi hoặc tiền giảm không hợp lệ!");
            Logger.getLogger(Update.class.getName()).log(Level.WARNING, "Invalid number", ex);
        } catch (SQLException ex) {
            request.getSession().setAttribute("error", "Lỗi database: " + ex.getMessage());
            Logger.getLogger(Update.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.sendRedirect("loading");
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý cập nhật khách hàng";
    }
}
