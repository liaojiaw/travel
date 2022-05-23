package com.xmairtravel.manager.api.controller.monitor;

import com.xmairtravel.core.annotation.Log;
import com.xmairtravel.core.common.entity.page.TableDataInfo;
import com.xmairtravel.core.enums.BusinessType;
import com.xmairtravel.core.server.manager.entity.bo.SysLogininfor;
import com.xmairtravel.core.server.manager.service.ISysLogininforService;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.manager.api.controller.system.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统访问记录
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController
{
    @Autowired
    private ISysLogininforService logininforService;

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysLogininfor logininfor)
    {
        startPage();
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public ResultBean remove(@PathVariable Long[] infoIds) {
        return logininforService.deleteLogininforByIds(infoIds);
    }

    @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public ResultBean clean() {

        return logininforService.cleanLogininfor();
    }
}
