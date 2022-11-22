package DAO;

import util.DButil;
import entity.Record;
import util.DateUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.StreamSupport;

/**
 * DAO class CRUD: Create, Retrieve, Update & Delete。
 */
public class RecordDAO {
    /**
     * Add record
     * @param record
     */
    public void add(Record record) {
        String sql = "INSERT INTO record (`spend`,`cid`,`comment`,`date`) VALUES (?,?,?,?)";
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.util2sql(record.getDate()));
            ps.execute();
            ResultSet result = ps.getGeneratedKeys();
            if (result.next()) {
                int id = result.getInt(1);
                record.setId(id);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Update record
     * @param record
     * @return
     */
    public int update(Record record) {
        String sql = "UPDATE record SET `spend`=?, `cid`=?, `comment`=?, `date`=? where id = ?";
        int result = 0;
        try (Connection c = DButil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, record.getSpend());
            ps.setInt(2, record.getCid());
            ps.setString(3, record.getComment());
            ps.setDate(4, DateUtil.util2sql(record.getDate()));
            ps.setInt(5, record.getId());
            result = ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Delete
     * @param id
     * @return
     */
    public int delete(int id) {
        String sql = "DELETE FROM record WHERE id = ?";
        int result = 0;
        try (Connection c = DButil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            result = ps.executeUpdate();
            ps.close();
            c.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public Record get(int id) {
        Record record = null;
        String sql = "SELECT * FROM record WHERE id = ?";
        try (Connection c = DButil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                record = new Record(rs.getInt("id"),
                                    rs.getInt("spend"),
                                    rs.getInt("cid"),
                                    rs.getString("comment"),
                                    rs.getDate("date"));
            }
            ps.close();
            rs.close();
            c.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return record;
    }

    /**
     * List
     * @param start
     * @param count
     * @return
     */
    public List<Record> list(int start, int count) {
        String sql = "SELECT * FROM record ORDER by id desc limit ?, ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DButil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, start);
            ps.setInt(2, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                 records.add(record);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    public List<Record> list() {
        return list(0, Short.MAX_VALUE);
    }

    public List<Record> list(int cid) {
        String sql = "SELECT * FROM record WHERE `cid` = ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    public List<Record> list(java.util.Date day) {
        String sql = "SELECT * FROM record WHERE `date` = ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(day));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    public List<Record> listToday() {
        return list(new java.util.Date());
    }

    public List<Record> list(java.util.Date start, java.util.Date end) {
        String sql = "SELECT * FROM record WHERE `date` >= ? and `date` <= ?";
        List<Record> records = new ArrayList<>();
        try (Connection c = DButil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {
            ps.setDate(1, DateUtil.util2sql(start));
            ps.setDate(2, DateUtil.util2sql(end));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Record record = new Record(rs.getInt("id"),
                        rs.getInt("spend"),
                        rs.getInt("cid"),
                        rs.getString("comment"),
                        rs.getDate("date"));
                records.add(record);
            }
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
        return records;
    }

    public List<Record> listThisMonth() {
        return list(DateUtil.monthBegin(), DateUtil.monthEnd());
    }

    public int getTotalNum() throws IOException {
        String sql = "SELECT * FROM record";
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

    public static void main(String[] args) {
    }
}
