package com.hrxb.ssm.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * �쳣������
 * @author Administrator
 *
 */
//���÷���ͱ� @Component
@Component
public class ExceptionHandler implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		String message="ϵͳ����δ֪�쳣���������Ա��ϵ ������110@qq.com";
		ModelAndView mv = new ModelAndView();
		//�ж��Ƿ����Զ����쳣
		if(ex instanceof MyException){
			//ǿ��ת��Ϊ�Զ����쳣
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
