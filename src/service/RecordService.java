package service;

import DAO.RecordDAO;
import entity.Record;
import util.DateUtil;

import java.util.Date;
import java.util.List;

public class RecordService {
    private RecordDAO dao = new RecordDAO();

    public void add(int spend, int cid, String comment, Date date) {
        Record r = new Record();
        r.setSpend(spend);
        r.setCid(cid);
        r.setComment(comment);
        r.setDate(date);
        dao.add(r);
    }

    public void update(int id, int spend, int cid, String comment, Date date) {
        Record r = new Record();
        r.setId(id);
        r.setSpend(spend);
        r.setCid(cid);
        r.setComment(comment);
        r.setDate(date);
        dao.update(r);
    }

    public void delete(int id) {
        dao.delete(id);
    }

    public List<Record> listMonth(Date startDay) {
        Date endDay = DateUtil.monthEnd(startDay);
        return dao.list(startDay, endDay);
    }
}
