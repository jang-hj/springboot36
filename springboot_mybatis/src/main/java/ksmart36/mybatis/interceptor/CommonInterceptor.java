package ksmart36.mybatis.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CommonInterceptor implements HandlerInterceptor{
	
	
	private static final Logger log = LoggerFactory.getLogger(CommonInterceptor.class);


	@Override 
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//요청이 일어나고 컨트롤러 가기 전단계
		log.info("CommonInterceptor==============================================START");
		log.info("ACCESS INFO=====================================STSRT");
		log.info("Port  		::::{}",request.getLocalPort());
		log.info("ServerName 	::::{}",request.getLocalName());
		log.info("getMethod  	::::{}",request.getMethod());
		log.info("getRequestURI ::::{}",request.getRequestURI());
		log.info("ACCESS INFO=======================================END");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
	@Override 
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 작업이 모두 끝나고 브라우저로 출력하기 전단계
		log.info("CommonInterceptor===============================================END");
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}
