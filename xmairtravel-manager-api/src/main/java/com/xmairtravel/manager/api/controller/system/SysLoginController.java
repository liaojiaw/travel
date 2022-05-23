package com.xmairtravel.manager.api.controller.system;

import com.xmairtravel.core.common.service.SysLoginService;
import com.xmairtravel.core.common.service.SysPermissionService;
import com.xmairtravel.core.server.manager.entity.bo.SysMenu;
import com.xmairtravel.core.server.manager.entity.bo.SysUser;
import com.xmairtravel.core.server.manager.entity.vo.in.LoginRequest;
import com.xmairtravel.core.server.manager.service.ISysMenuService;
import com.xmairtravel.core.server.wechat.entity.Constants;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.utils.SecurityUtil;
import com.xmairtravel.core.utils.text.StringUtils;
import com.xmairtravel.core.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public ResultBean login(@RequestBody LoginRequest loginBody)
    {
        if(StringUtils.isEmpty(loginBody.getUuid())){
            loginBody.setUuid(IdUtils.fastUUID());
        }
        ResultBean result = ResultBean.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        result.put(Constants.TOKEN, token);
        return result;

    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public ResultBean getInfo()
    {
        SysUser user = SecurityUtil.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);

        ResultBean result = ResultBean.success();
        result.put("user", user);
        result.put("roles", roles);
        result.put("permissions", permissions);
        return result;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public ResultBean getRouters()
    {
        Long userId = SecurityUtil.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return ResultBean.success(menuService.buildMenus(menus));
    }
}
