package com.hrxb.ssm.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 异常处理类
 * @author Administrator
 *
 */
//不好分类就标 @Component
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String message="系统出现未知异常，请与管理员联系 邮箱是110@qq.com";
		ModelAndView mv = new ModelAndView();
		//判断是否是自定义异常
		if(ex instanceof MyException){
			//强制转换为自定义异常
			MyException myEx = (MyException) ex;
			message = myEx.getMessage();
		}
		mv.addObject("message",message);
		mv.setViewName("exception");
		
		return mv;
		/*try {
			request.getRequestDispatcher("WEB-INF/jsp/exception.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/				
	}

}
