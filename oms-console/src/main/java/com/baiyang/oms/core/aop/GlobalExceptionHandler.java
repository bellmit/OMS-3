package com.baiyang.oms.core.aop;

import com.baiyang.oms.core.base.tips.ErrorTip;
import com.baiyang.oms.core.common.exception.BizExceptionEnum;
import com.baiyang.oms.core.common.exception.InvalidKaptchaException;
import com.baiyang.oms.core.common.response.Result;
import com.baiyang.oms.core.exception.GunsException;
import com.baiyang.oms.core.log.LogManager;
import com.baiyang.oms.core.log.factory.LogTaskFactory;
import com.baiyang.oms.core.shiro.ShiroKit;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.CredentialsException;
import org.apache.shiro.authc.DisabledAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.UndeclaredThrowableException;

import static com.baiyang.oms.core.support.HttpKit.getIp;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午3:19:56
 */
@ControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(GunsException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result<ErrorTip> notFount(GunsException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        log.error("业务异常:", e);
        ErrorTip errorTip = new ErrorTip(e.getCode(), e.getMessage());
        return new Result<>(errorTip);
    }

    /**
     * 用户未登录异常
     */
    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result<Void> unAuth(AuthenticationException e) {
        log.error("用户未登陆：", e.getMessage());
        return new Result<>(HttpStatus.OK.name(), "用户未登陆", null);
    }

    /**
     * 账号被冻结异常
     */
    @ExceptionHandler(DisabledAccountException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result<Void> accountLocked(DisabledAccountException e) {
        log.error("账号被冻结!", e.getMessage());
        String username = ShiroKit.getSession().getAttribute("userName").toString();
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号被冻结", getIp()));
        return new Result<>(HttpStatus.OK.name(), "账号被冻结", null);
    }

    /**
     * 账号密码错误异常
     */
    @ExceptionHandler(CredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result<Void> credentials(CredentialsException e) {
        String username = ShiroKit.getSession().getAttribute("userName").toString();
        log.error("账号或密码错误,账号为:" + username);
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "账号或密码错误", getIp()));
        return new Result<>(HttpStatus.OK.name(), "账号或密码错误", null);
    }

    /**
     * 验证码错误异常
     */
    @ExceptionHandler(InvalidKaptchaException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> credentials(InvalidKaptchaException e) {
        log.error("验证码错误!", e.getMessage());
        String username = ShiroKit.getSession().getAttribute("userName").toString();
        LogManager.me().executeLog(LogTaskFactory.loginLog(username, "验证码错误", getIp()));
        return new Result<>(HttpStatus.OK.name(), "验证码错误", null);
    }

    /**
     * 无权访问该资源异常
     */
    @ExceptionHandler(UndeclaredThrowableException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Result<ErrorTip> credentials(UndeclaredThrowableException e) {
        log.error("权限异常!", e.getMessage());
        ErrorTip errorTip = new ErrorTip(BizExceptionEnum.NO_PERMITION.getCode(), BizExceptionEnum.NO_PERMITION.getMessage());
        return new Result<>(HttpStatus.OK.name(), errorTip);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Result<ErrorTip> notFount(RuntimeException e) {
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(ShiroKit.getUser().getId(), e));
        log.error("运行时异常:", e);
        ErrorTip errorTip = new ErrorTip(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
        return new Result<>(errorTip);
    }
}
