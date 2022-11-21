package DAO;

import entity.Config;
import entity.Record;
import util.DButil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO class CRUD: Create, Retrieve, Update & Delete。
 */
public class ConfigDAO {
    public void add(Config config) {
        String sql = "INSERT INTO config (`key_`, `value`) VALUES (?, ?)";
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int id = rs.getInt(1);
                config.setId(id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int update(Config config) {
        String sql = "UPDATE config SET key_ = ?, value = ? WHERE id = ?";
        int result = 0;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, config.getKey());
            ps.setString(2, config.getValue());
            ps.setInt(3, config.getId());
            ps.executeUpdate();
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public int delete(Config config) {
        String sql = "DELETE config WHERE id = ?";
        int result = 0;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, config.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Config get(int id) {
        String sql = "SELECT * FROM config WHERE id = ?";
        Config config = null;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                config = new Config(rs.getInt("id"),
                                    rs.getString("key_"),
                                    rs.getString("value"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    public Config get(String key) {
        String sql = "SELECT * FROM config WHERE key_ = ?";
        Config config = null;
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setString(1, key);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                config = new Config(rs.getInt("id"),
                                    rs.getString("key_"),
                                    rs.getString("value"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    public List<Config> list(int start, int count) {
        String sql = "SELECT * FROM config ORDER by id desc limit ?, ?";
        List<Config> configs = new ArrayList<>();
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Config config = new Config(rs.getInt("id"),
                                           rs.getString("key_"),
                                           rs.getString("value"));
                configs.add(config);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return configs;
    }

    public List<Config> list() {
        return list(0, Short.MAX_VALUE);
    }

    public int getTotalNum() throws IOException {
        String sql = "SELECT * FROM config";
        try (Connection c = DButil.getConnection();
             Statement s = c.createStatement();) {
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

}
