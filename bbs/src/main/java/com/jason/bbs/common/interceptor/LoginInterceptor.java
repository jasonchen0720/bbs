package com.jason.bbs.common.interceptor;

import com.jason.bbs.common.SysConstant;
import com.jason.bbs.pojo.vo.UserVo;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 2016/8/10.
 */
public class LoginInterceptor implements HandlerInterceptor {


    private static final Logger log = Logger.getLogger(LoginInterceptor.class);
    private static Set<String> interceptUrl = null;
    static{
        interceptUrl=new HashSet<>();

        interceptUrl.add("/user/userInfo");

        interceptUrl.add("/issue/publish");

        interceptUrl.add("/issue/toPublish");

        interceptUrl.add("/comment/publishComment");

        interceptUrl.add("/comment/publishComment");

        interceptUrl.add("/reply/sendReply");
    }
    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
        //log.info("afterCompletion enter");

    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
                           Object arg2, ModelAndView arg3) throws Exception {
        //log.info("postHandle enter");

    }

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,Object handler) throws Exception {
        log.info("preHandle enter "+req.getRequestURI());
        boolean interceptFlag=false;
        String uri=req.getRequestURI();
        for(String str:interceptUrl){
            if(uri.contains(str)){
                interceptFlag=true;
            }
        }
        if(!interceptFlag){
            return true;
        }else{
            UserVo user=(UserVo)req.getSession().getAttribute(SysConstant.CURRENT_USER);
            if(null==user){
                log.info("未登录或登录超时");
                System.out.println(req.getContextPath());
                resp.sendRedirect("/user/toLogin");
                return false;
            }else{
                return true;
            }
        }
    }
}
