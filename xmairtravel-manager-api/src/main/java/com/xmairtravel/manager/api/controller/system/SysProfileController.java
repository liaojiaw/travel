package com.xmairtravel.manager.api.controller.system;

import com.xmairtravel.core.annotation.Log;
import com.xmairtravel.core.common.entity.LoginUser;
import com.xmairtravel.core.common.entity.constant.UserConstants;
import com.xmairtravel.core.enums.BusinessType;
import com.xmairtravel.core.server.manager.entity.bo.SysUser;
import com.xmairtravel.core.server.manager.service.ISysUserService;
import com.xmairtravel.core.server.service.TokenService;
import com.xmairtravel.core.server.wechat.entity.ResultBean;
import com.xmairtravel.core.utils.SecurityUtil;
import com.xmairtravel.core.utils.file.FileUploadUtils;
import com.xmairtravel.core.utils.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    /**
     * 个人信息
     */
    @GetMapping
    public ResultBean profile()
    {
        LoginUser loginUser = getLoginUser();
        SysUser user = loginUser.getUser();
        ResultBean ajax = ResultBean.success(user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(loginUser.getUsername()));
        ajax.put("postGroup", userService.selectUserPostGroup(loginUser.getUsername()));
        return ajax;
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public ResultBean updateProfile(@RequestBody SysUser user)
    {
        LoginUser loginUser = getLoginUser();
        SysUser sysUser = loginUser.getUser();
        user.setUserName(sysUser.getUserName());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return ResultBean.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return ResultBean.error("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUserId(sysUser.getUserId());
        user.setPassword(null);
        if (userService.updateUserProfile(user) > 0)
        {
            // 更新缓存用户信息
            sysUser.setNickName(user.getNickName());
            sysUser.setPhonenumber(user.getPhonenumber());
            sysUser.setEmail(user.getEmail());
            sysUser.setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return ResultBean.success();
        }
        return ResultBean.error("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public ResultBean updatePwd(String oldPassword, String newPassword)
    {
        LoginUser loginUser = getLoginUser();
        String userName = loginUser.getUsername();
        String password = loginUser.getPassword();
        if (!SecurityUtil.matchesPassword(oldPassword, password))
        {
            return ResultBean.error("修改密码失败，旧密码错误");
        }
        if (SecurityUtil.matchesPassword(newPassword, password))
        {
            return ResultBean.error("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(userName, SecurityUtil.encryptPassword(newPassword)) > 0)
        {
            // 更新缓存用户密码
            loginUser.getUser().setPassword(SecurityUtil.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return ResultBean.success();
        }
        return ResultBean.error("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public ResultBean avatar(@RequestParam("avatarfile") MultipartFile file) throws IOException
    {
        if (!file.isEmpty())
        {
            LoginUser loginUser = getLoginUser();
            String avatar = FileUploadUtils.upload("RuoYiConfig.getAvatarPath()", file);
            if (userService.updateUserAvatar(loginUser.getUsername(), avatar))
            {
                ResultBean ajax = ResultBean.success();
                ajax.put("imgUrl", avatar);
                // 更新缓存用户头像
                loginUser.getUser().setAvatar(avatar);
                tokenService.setLoginUser(loginUser);
                return ajax;
            }
        }
        return ResultBean.error("上传图片异常，请联系管理员");
    }
}
