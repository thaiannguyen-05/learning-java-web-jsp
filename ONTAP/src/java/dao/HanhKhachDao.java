/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.UpdateDto;
import java.util.ArrayList;
import java.util.List;
import model.KhachHang;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;

/**
 *
 * @author An
 */
public class HanhKhachDao {

    public List<KhachHang> loading() throws SQLException {
        String query = "SELECT * FROM khachhang";
        List<KhachHang> list = new ArrayList<>();

        try (
                Connection con = Dao.getConnection();
                PreparedStatement pr = con.prepareStatement(query);
                ResultSet rs = pr.executeQuery();) {

            while (rs.next()) {
                KhachHang object = new KhachHang(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getDouble("lessMoney"));
                list.add(object);
            }
            return list;
        }
    }

    public boolean add(String name, int age, String gender, double lessMoney) throws SQLException {
        String query = "INSERT INTO khachhang (name, age, gender, lessMoney) VALUES (?,?,?,?)";

        try (
                Connection con = Dao.getConnection();
                PreparedStatement pr = con.prepareStatement(query);) {
            pr.setString(1, name);
            pr.setInt(2, age);
            pr.setString(3, gender);
            pr.setDouble(4, lessMoney);

            return pr.executeUpdate() > 0;
        }
    }

    public boolean update(UpdateDto updateDto, int id) throws SQLException {
        StringBuilder query = new StringBuilder("UPDATE khachhang SET ");
        List<Object> params = new ArrayList<>();

        if (updateDto.name.isPresent()) {
            query.append("name = ?, ");
            params.add(updateDto.name.get());
        }

        if (updateDto.age.isPresent()) {
            query.append("age = ?, ");
            params.add(updateDto.age.get()); // Integer
        }

        if (updateDto.gender.isPresent()) {
            query.append("gender = ?, ");
            params.add(updateDto.gender.get());
        }

        if (updateDto.lessMoney.isPresent()) {
            query.append("lessMoney = ?, ");
            params.add(updateDto.lessMoney.get()); // Double
        }

        if (params.isEmpty()) {
            return false;
        }

        // bỏ ", " cuối
        query.setLength(query.length() - 2);

        query.append(" WHERE id = ?");
        params.add(id);

        try (Connection con = Dao.getConnection(); PreparedStatement pr = con.prepareStatement(query.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object ob = params.get(i);
                int idx = i + 1;

                if (ob instanceof String) {
                    pr.setString(idx, (String) ob);
                } else if (ob instanceof Integer) {
                    pr.setInt(idx, (Integer) ob);
                } else if (ob instanceof Double) {
                    pr.setDouble(idx, (Double) ob);
                } else {
                    pr.setObject(idx, ob);
                }
            }

            return pr.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM khachhang WHERE id = ?";
        try (
                Connection con = Dao.getConnection();
                PreparedStatement pr = con.prepareStatement(sql);) {
            pr.setInt(1, id);
            return pr.executeUpdate() > 0;
        }
    }

    public List<KhachHang> find(UpdateDto dto) throws SQLException {
        StringBuilder query = new StringBuilder("SELECT * FROM khachhang WHERE 1=1 ");
        List<Object> params = new ArrayList<>();

        if (dto.name.isPresent()) {
            query.append("AND name LIKE ? ");
            params.add("%" + dto.name.get() + "%"); // LIKE với wildcard
        }
        if (dto.age.isPresent()) {
            query.append("AND age = ? "); // Tuổi giữ nguyên =
            params.add(dto.age.get());
        }
        if (dto.gender.isPresent()) {
            query.append("AND gender LIKE ? ");
            params.add("%" + dto.gender.get() + "%"); // LIKE với wildcard
        }
        if (dto.lessMoney.isPresent()) {
            query.append("AND lessMoney = ? "); // Tiền giữ nguyên =
            params.add(dto.lessMoney.get());
        }

        List<KhachHang> list = new ArrayList<>();

        try (Connection con = Dao.getConnection(); PreparedStatement pr = con.prepareStatement(query.toString())) {

            for (int i = 0; i < params.size(); i++) {
                Object ob = params.get(i);
                int idx = i + 1;

                if (ob instanceof String) {
                    pr.setString(idx, (String) ob);
                } else if (ob instanceof Integer) {
                    pr.setInt(idx, (Integer) ob);
                } else if (ob instanceof Double) {
                    pr.setDouble(idx, (Double) ob);
                } else {
                    pr.setObject(idx, ob);
                }
            }

            try (ResultSet rs = pr.executeQuery()) {
                while (rs.next()) {
                    list.add(new KhachHang(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("gender"),
                            rs.getDouble("lessMoney")));
                }
            }
        }
        return list;
    }

}
