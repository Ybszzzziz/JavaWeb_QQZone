package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.ioc.BeanFactory;
import com.atguigu.myssm.util.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author Yan
 * @create 2023-04-09 10:54
 **/
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    public DispatcherServlet() {


    }

    public void init() throws ServletException {
        super.init();
        //之前是在这里主动创建IOC容器
        //现在又从application作用域获取
//        beanFactory = new ClassPathXmlApplicationContext();
        ServletContext application = getServletContext();
        Object beanFactoryObj = application.getAttribute("beanFactory");
        if (beanFactoryObj != null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new  RuntimeException("IOC容器获取失败");
        }
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

//        req.setCharacterEncoding("UTF-8");
        String servletPath = req.getServletPath();
        String substring = servletPath.substring(1);
        int index = substring.lastIndexOf(".do");
        servletPath = substring.substring(0, index);
        Object controllerBeanObj = beanFactory.getBean(servletPath);

        String operate = req.getParameter("operate");
        if (StringUtils.isEmpty(operate)) {
            operate = "index";
        }
        try {
            Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
            for (Method method : methods) {
                if (operate.equals(method.getName())) {
                    //统一获取请求参数
                    //获取当前方法的参数，返回参数数组
                    Parameter[] parameters = method.getParameters();
                    //parameterValues 用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        String parameterName = parameter.getName();
                        if ("request".equals(parameterName)) {
                            parameterValues[i] = req;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = resp;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = req.getSession();
                        } else {
                            String parameterValue = req.getParameter(parameterName);
                            String typeName = parameter.getType().getName();
                            Object parameterObj = parameterValue;
                            if (parameterObj != null){
                                if ("java.lang.Integer".equals(typeName)){
                                    parameterObj = Integer.parseInt(parameterValue);
                                }
                            }
                            parameterValues[i] = parameterObj;
                        }
                    }
                    //controller组件方法调用
                    method.setAccessible(true);

                    Object returnObj = method.invoke(controllerBeanObj, parameterValues);
                    //视图处理
                    String methodReturnStr = (String) returnObj;
                    if (methodReturnStr.startsWith("redirect:")) {
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        resp.sendRedirect(redirectStr);
                    } else {
                        super.processTemplate(methodReturnStr, req, resp);
                    }
                }


            }

//        } else {
//            throw new RuntimeException("operate值非法");
//        }

        } catch (Exception e){
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet出错了...");
        }
    }
}
