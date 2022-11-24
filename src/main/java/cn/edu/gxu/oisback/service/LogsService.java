package cn.edu.gxu.oisback.service;

import cn.edu.gxu.oisback.dao.LogsDao;
import cn.edu.gxu.oisback.domain.Logs;
import cn.edu.gxu.oisback.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogsService {
    @Autowired
    private LogsDao logsDao;

    public Boolean addLog(Logs logs) {
        return logsDao.insertLog(logs) == 1;
    }

    public Page queryLogsPage(Integer currentPage, Integer pageNumber, String level) {

        return new Page(logsDao.selectLogsCount(level), logsDao.selectLogsPage((currentPage - 1) * pageNumber, pageNumber, level));
    }
}
