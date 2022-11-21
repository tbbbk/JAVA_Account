package DAO;

import entity.Category;
import entity.Config;
import util.DButil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class CRUD: Create, Retrieve, Update & Delete。
 */
public class CategoryDAO {
    public void add(Category category) {
        String sql = "INSERT INTO category (`name`) VALUES (?)";
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, category.getName());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                category.setId(id);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Category category) {
        String sql = "UPDATE category SET name = ? WHERE id = ?";
        int result = 0;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int delete(Category category) {
        String sql = "DELETE category WHERE id = ?";
        int result = 0;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, category.getId());
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Category get(int id) {
        String sql = "SELECT * FROM category WHERE id = ?";
        Category category = null;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    public Category getByKey(String key) {
        String sql = "SELECT * FROM category WHERE key_ = ?";
        Category category = null;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return category;
    }

    public List<Category> list(int start, int count) {
        String sql = "SELECT * FROM category ORDER by id desc limit ?, ?";
        List<Category> categories = new ArrayList<>();
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(rs.getInt("id"), rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return categories;
    }

    public List<Category> list() {
        return list(0, Short.MAX_VALUE);
    }

    public int getTotal() {
        String sql = "select count(*) from category";
        try (Connection c = DButil.getConnection();
             Statement s = c.createStatement()) {
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
