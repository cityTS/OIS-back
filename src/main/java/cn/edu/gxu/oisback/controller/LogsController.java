package cn.edu.gxu.oisback.controller;

import cn.edu.gxu.oisback.domain.Logs;
import cn.edu.gxu.oisback.domain.Re;
import cn.edu.gxu.oisback.service.LogsService;
import cn.edu.gxu.oisback.utils.IpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/api/logs")
public class LogsController {
    @Autowired
    LogsService logsService;

    @PostMapping
    public Re addLog(HttpServletRequest request, @RequestBody Logs logs) {
        logs.setIp(IpUtil.loadRemoteUserIP(request));
        logs.setCreateTime(System.currentTimeMillis());
        if(logsService.addLog(logs)) {
            return new Re(0, null, "日志已记录");
        }
        return new Re(1, null, "系统异常，记录失败");
    }

    @GetMapping
    public Re queryLogs(Integer currentPage, Integer pageNumber, String level) {
        return new Re(0, logsService.queryLogsPage(currentPage, pageNumber, level), "查询成功");
    }
}
