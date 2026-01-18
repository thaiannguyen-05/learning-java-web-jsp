<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.User"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản Lý Thành Viên</title>
        <style>
            table {
                border-collapse: collapse;
                width: 100%;
                margin-bottom: 30px;
            }
            th, td {
                border: 1px solid #000;
                padding: 8px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            .row {
                margin-bottom: 10px;
            }
            label {
                display: inline-block;
                width: 100px;
            }
            input, select {
                padding: 5px;
                width: 200px;
            }
            .listButtons {
                margin-top: 20px;
            }
            .listButtons button {
                margin-right: 10px;
                padding: 8px 16px;
            }
            form.inline {
                display: inline;
                margin: 0;
            }
        </style>
    </head>
    <body>
        <h2>Quản Lý Thành Viên</h2>
        <p>Danh sách thành viên đã đăng ký</p>

        <table>
            <tr>
                <th>STT</th>
                <th>Tên đăng nhập</th>
                <th>Họ tên</th>
                <th>Email</th>
                <th>Cấp độ</th>
                <th>Hành Động</th>
            </tr>
            <%
                List<User> userList = (List<User>) request.getAttribute("list");
                if (userList != null && !userList.isEmpty()) {
                    int index = 1;
                    for (User user : userList) {
            %>
            <tr>
                <td><%= index++ %></td>
                <td><%= user.getUsername() %></td>
                <td><%= user.getName() %></td>
                <td><%= user.getEmail() %></td>
                <td><%= user.getLevel() %></td>
                <td>
                    <!-- Button Sửa -->
                    <form action="editUser" method="get" class="inline">
                        <input type="hidden" name="id" value="<%= user.getId() %>">
                        <input type="submit" value="Sửa">
                    </form>

                    <!-- Button Xóa -->
                    <form action="deleteUser" method="post" class="inline"
                          onsubmit="return confirm('Bạn có chắc muốn xóa không?');">
                        <input type="hidden" name="id" value="<%= user.getId() %>">
                        <input type="submit" value="Xóa">
                    </form>
                </td>
            </tr>
            <%
                    }
                } else {
            %>
            <tr>
                <td colspan="6" style="text-align:center;">Chưa có thành viên nào đăng ký.</td>
            </tr>
            <% } %>
        </table>

        <h2>Thêm / Sửa Thành Viên</h2>
        <form action="" method="post">
            <div class="row">
                <label>Username:</label>
                <input type="text" name="username" required>
            </div>

            <div class="row">
                <label>Họ tên:</label>
                <input type="text" name="name" required>
            </div>

            <div class="row">
                <label>Email:</label>
                <input type="email" name="email" required>
            </div>

            <div class="row">
                <label>Level:</label>
                <select name="level">
                    <option value="admin">admin</option>
                    <option value="user" selected>user</option>
                </select>
            </div>

            <div class="listButtons">
                <button type="submit" formaction="loading" formnovalidate>Loading</button>
                <button type="submit" formaction="add">Add</button>
                <button type="submit" formaction="update">Update</button>
                <button type="submit" formaction="delete"
                        onclick="return confirm('Bạn có chắc muốn xóa không?');">Delete</button>
                <button type="submit" formaction="find">Find</button>
            </div>
        </form>
    </body>
</html>
