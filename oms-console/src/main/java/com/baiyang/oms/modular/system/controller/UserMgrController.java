package com.baiyang.oms.modular.system.controller;

import com.baiyang.oms.core.base.controller.BaseController;
import com.baiyang.oms.core.common.annotion.BussinessLog;
import com.baiyang.oms.core.common.annotion.Permission;
import com.baiyang.oms.core.common.constant.Const;
import com.baiyang.oms.core.common.constant.dictmap.UserDict;
import com.baiyang.oms.core.common.constant.factory.ConstantFactory;
import com.baiyang.oms.core.common.constant.factory.PageFactory;
import com.baiyang.oms.core.common.constant.state.ManagerStatus;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.datascope.DataScope;
import com.baiyang.oms.core.db.Db;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.core.log.LogObjectHolder;
import com.baiyang.oms.core.page.PageInfoBT;
import com.baiyang.oms.core.shiro.ShiroKit;
import com.baiyang.oms.core.shiro.ShiroUser;
import com.baiyang.oms.core.util.ToolUtil;
import com.baiyang.oms.modular.system.dao.UserMapper;
import com.baiyang.oms.modular.system.factory.UserFactory;
import com.baiyang.oms.modular.system.model.User;
import com.baiyang.oms.modular.system.pojo.UserResponse;
import com.baiyang.oms.modular.system.service.IUserService;
import com.baiyang.oms.modular.system.transfer.UserDto;
import com.baiyang.oms.modular.system.transfer.UserEditDto;
import com.baiyang.oms.modular.system.warpper.UserWarpper;
import com.baomidou.mybatisplus.plugins.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 系统管理员控制器
 *
 * @author fengshuonan
 * @Date 2017年1月11日 下午1:08:17
 */
@Controller
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private IUserService userService;

    @Value("${web-upload-path}")
    private String path;

    /**
     * 跳转到角色分配页面
     */
    @Permission
    @RequestMapping("/role_assign/{userId}")
    public String roleAssign(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = Db.create(UserMapper.class).selectOneByCon("id", userId);
        model.addAttribute("userId", userId);
        model.addAttribute("userAccount", user.getAccount());
        return "";
    }

    /**
     * 跳转到编辑管理员页面
     */
    @Permission
    @RequestMapping("/user_edit/{userId}")
    public String userEdit(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = this.userService.selectById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return "";
    }

    /**
     * 跳转到查看用户详情页面
     */
    @RequestMapping("/user_info")
    public String userInfo(Model model) {
        Integer userId = ShiroKit.getUser().getId();
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        User user = this.userService.selectById(userId);
        model.addAttribute(user);
        model.addAttribute("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        model.addAttribute("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        return "";
    }


    /**
     * 修改当前用户的密码
     */
    @RequestMapping("/changePwd")
    @ResponseBody
    public Result<Void> changePwd(@RequestBody Map<String, String> params) {
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");
        if (!newPwd.equals(rePwd)) {
            throw new GunsException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        Integer userId = ShiroKit.getUser().getId();
        User user = userService.selectById(userId);
        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
        if (user.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
            user.setPassword(newMd5);
            user.updateById();
            return new Result<>();
        } else {
            throw new GunsException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }

    /**
     * 查询管理员列表
     */
    @RequestMapping("/list")
    @Permission
    @ResponseBody
    public Result<PageInfoBT<User>> list(@RequestBody Map<String, String> params) {
        Page<User> page = new PageFactory<User>().defaultPage(Integer.parseInt(params.get("pageNo")),
                Integer.parseInt(params.get("pageSize")), params.get("sort"), params.get("order"));
        String keyword = params.get("keyword");
        String beginTime = params.get("beginTime");
        String endTime = params.get("endTime");
        String name = params.get("name");
        List<Map<String, Object>> users;
        if (ShiroKit.isAdmin()) {
            users = userService.getUsersByPage(page, null, name, beginTime, endTime, null, keyword);
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            users = userService.getUsersByPage(page, dataScope, name, beginTime, endTime, null, keyword);
        }
        page.setRecords((List<User>) new UserWarpper(users).warp());
        return new Result<>(new PageInfoBT<>(page));
    }

    /**
     * 添加管理员
     */
    @RequestMapping("/add")
    @BussinessLog(value = "添加管理员", key = "account", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> add(@RequestBody UserDto user) {
        // 判断账号是否重复
        User theUser = userService.getByAccount(user.getAccount());
        if (theUser != null) {
            throw new GunsException(BizExceptionEnum.USER_ALREADY_REG);
        }
        ShiroUser shiroUser = ShiroKit.getUser();
        // 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(user.getPassword(), user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreatetime(new Date());
        user.setTenantId(shiroUser.getTenantId());
        this.userService.insert(UserFactory.createUser(user));
        return new Result<>();
    }

    /**
     * 修改管理员
     */
    @RequestMapping("/edit")
    @BussinessLog(value = "修改管理员", key = "account", dict = UserDict.class)
    @ResponseBody
    public Result<Void> edit(@RequestBody UserEditDto user) {
        User usr = userService.selectById(user.getId());
        BeanUtils.copyProperties(user, usr);
        //不能修改超级管理员
        if (user.getId().equals(Const.ADMIN_ID) && !user.getRoleid().equals(Const.ADMIN_ROLE_ID.toString())) {
            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            this.userService.updateById(usr);
            return new Result<>();
        } else {
            assertAuth(user.getId());
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser.getId().equals(user.getId())) {
                this.userService.updateById(usr);
                return new Result<>();
            } else {
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
            }
        }
    }

    /**
     * 删除管理员（逻辑删除）
     */
    @RequestMapping("/delete")
    @BussinessLog(value = "删除管理员", key = "userId", dict = UserDict.class)
    @Permission
    @ResponseBody
    public Result<Void> delete(@RequestBody List<Integer> idList) {
        for (Integer userId : idList) {
            if (ToolUtil.isEmpty(userId)) {
                throw new GunsException(BizExceptionEnum.REQUEST_NULL);
            }
            //不能删除超级管理员
            if (userId.equals(Const.ADMIN_ID)) {
                throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
            }
            assertAuth(userId);
            this.userService.setStatus(userId, ManagerStatus.DELETED.getCode());
        }
        return new Result<>();
    }

    /**
     * 查看管理员详情
     */
    @RequestMapping("/view/{userId}")
    @ResponseBody
    public User view(@PathVariable Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        return this.userService.selectById(userId);
    }

    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> reset(@RequestBody List<Integer> idList) {
        for (Integer userId : idList) {
            if (ToolUtil.isEmpty(userId)) {
                throw new GunsException(BizExceptionEnum.REQUEST_NULL);
            }
            assertAuth(userId);
            User user = this.userService.selectById(userId);
            user.setSalt(ShiroKit.getRandomSalt(5));
            user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
            this.userService.updateById(user);
        }
        return new Result<>();
    }

    /**
     * 冻结用户
     */
    @RequestMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> freeze(@RequestBody List<Integer> idList) {
        for (Integer userId : idList) {
            if (ToolUtil.isEmpty(userId)) {
                throw new GunsException(BizExceptionEnum.REQUEST_NULL);
            }
            //不能冻结超级管理员
            if (userId.equals(Const.ADMIN_ID)) {
                throw new GunsException(BizExceptionEnum.CANT_FREEZE_ADMIN);
            }
            assertAuth(userId);
            this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        }
        return new Result<>();
    }

    /**
     * 解除冻结用户
     */
    @RequestMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    @ResponseBody
    public Result<Void> unfreeze(@RequestBody List<Integer> idList) {
        for (Integer userId : idList) {
            if (ToolUtil.isEmpty(userId)) {
                throw new GunsException(BizExceptionEnum.REQUEST_NULL);
            }
            assertAuth(userId);
            this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        }
        return new Result<>();
    }


    /**
     * 上传图片(地址：${web-upload-path})
     */
    @RequestMapping(path = "/upload")
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest request) throws IOException {
        String context = request.getContextPath();
        String imgName = this.saveImg(multipartFile, path);
        ShiroUser shiroUser = ShiroKit.getUser();
        User user = userService.selectById(shiroUser.getId());
        String imgUrl = context + '/' + imgName;
        user.setAvatar(imgUrl);
        userService.updateById(user);
        return imgUrl;
    }

    /**
     * 保存文件，直接以multipartFile形式
     *
     * @param multipartFile
     * @param path          文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    private String saveImg(MultipartFile multipartFile, String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        String rootFileName = multipartFile.getOriginalFilename();
        String suffix = rootFileName.substring(rootFileName.indexOf("."));
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = rootFileName.substring(0, rootFileName.indexOf(".")) + '@' + sf.format(new Date());
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = newFileName + suffix;
        logger.info("开始传送文件:{}", fileName);
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }

    /**
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(Integer userId) {
        if (ShiroKit.isAdmin()) {
            return;
        }
        List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
        User user = this.userService.selectById(userId);
        Integer deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new GunsException(BizExceptionEnum.NO_PERMITION);
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @RequestMapping("/onlineUser")
    @ResponseBody
    public Result<UserResponse> getLoginUserInfo() {
        UserResponse userResponse = new UserResponse();
        ShiroUser shiroUser = ShiroKit.getUser();
        User user = userService.selectById(shiroUser.getId());
        user.setPassword(null);
        BeanUtils.copyProperties(user, userResponse);

        if (user.getBirthday() != null) {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            userResponse.setBirthday(sf.format(user.getBirthday()));
        }
        userResponse.setSexName(ConstantFactory.me().getSexName(user.getSex()));
        userResponse.setRoleName(ConstantFactory.me().getRoleName(user.getRoleid()));
        userResponse.setDeptName(ConstantFactory.me().getDeptName(user.getDeptid()));
        return new Result<>(userResponse);
    }

    /**
     * session断开处理
     */
    @RequestMapping("/online")
    @ResponseBody
    public Result<Void> online() {
        if (ShiroKit.getSession().getAttribute("sessionFlag") == null) {
            throw new GunsException(BizExceptionEnum.SESSION_TIMEOUT);
        }
        return new Result<>();
    }

}