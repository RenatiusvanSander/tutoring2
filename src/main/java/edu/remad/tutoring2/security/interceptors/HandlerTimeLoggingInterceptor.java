package edu.remad.tutoring2.security.interceptors;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HandlerTimeLoggingInterceptor implements AsyncHandlerInterceptor {
	
	private Logger logger;
	
	public HandlerTimeLoggingInterceptor() {
		logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		logger.setLevel(Level.ALL);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		request.setAttribute("startTime", System.currentTimeMillis());

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.setAttribute("endTime", System.currentTimeMillis());
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		long endTime = request.getAttribute("endTime") != null ? (Long) request.getAttribute("endTime") : 0L ;
		logger.info("####Custom Message#### Time Spent in Handler in ms : " + (endTime - startTime));
	}
}
