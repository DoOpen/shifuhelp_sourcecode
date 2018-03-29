package com.project.others;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bean.member.MemberBean;
import com.project.bean.others.AjaxResult;
import com.project.bean.system.SystemAccountBean;
import com.project.service.controller.SystemServiceC;
import com.project.service.interfaces.MemberServiceI;

/**
 * token验证切面类，在需要进行token验证的方法前切入验证代码，若通过程序继续执行，否则返回token验证失败
 * 
 * @author 方林
 *
 */
@Component
@Aspect()
public class NotTokenTransaction {

	@Autowired
	SystemServiceC systemService;
	@Autowired
	MemberServiceI memberService;
	
	/**
	 * interfaces包切入点规则：com.project.webservice.interfaces包下及其子包下的任何参数的带有@RequestMapping注解并且不带有@NotToken自定义注解的方法
	 */
	@Pointcut(value = "execution(@org.springframework.web.bind.annotation.RequestMapping !@com.project.others.NotToken * com.project.webservice.interfaces.*.*(..))")
	public void pointInterfaces() {

	}
	/**
	 * controller包切入点规则：com.project.webservice.controller包下及其子包下的任何参数的带有@RequestMapping注解并且不带有@NotToken自定义注解的方法
	 */
	@Pointcut(value = "execution(@org.springframework.web.bind.annotation.RequestMapping !@com.project.others.NotToken * com.project.webservice.controller.*.*(..))")
	public void pointController() {

	}
	/**
	 * 切入的验证代码
	 * 
	 * @param point
	 * @throws Throwable
	 */
	@Around(value = "pointInterfaces()")
	public void startTransactionInterfaces(ProceedingJoinPoint point) throws Throwable {
		PrintWriter out=null;
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Content-Type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("utf-8");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
		response.addHeader("Access-Control-Allow-Headers", "Content-Type");
		response.addHeader("Access-Control-Max-Age", "1800");
		out = response.getWriter();
		String interfases_id=request.getParameter("member_id");
		String interfaces_token=request.getParameter("member_token");
		boolean pass=false;
		if(interfases_id!=null&&interfaces_token!=null) {
			pass=interfacesValidate(interfases_id,interfaces_token);
		}
		//验证通过，继续执行，否则返回token验证失败
		if (pass) {
			point.proceed();
		} else {
			AjaxResult ajaxResult = new AjaxResult();
			ajaxResult.setStatus("pending").setError("token验证失败");
			out.write(new ObjectMapper().writeValueAsString(ajaxResult));
		}
	}
	/**
	 * 切入的验证代码
	 * 
	 * @param point
	 * @throws Throwable
	 */
	@Around(value = "pointController()")
	public void startTransactionController(ProceedingJoinPoint point){
		PrintWriter out=null;
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Content-Type", "text/html;charset=UTF-8");
			response.setCharacterEncoding("utf-8");
			response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Max-Age", "1800");
			out = response.getWriter();
			String controller_id=request.getParameter("account_login_id");
			String controller_token=request.getParameter("system_token");
			boolean pass=false;
			if(controller_id!=null&&controller_token!=null) {
				pass=controllerValidate(controller_id,controller_token);
			}
			//验证通过，继续执行，否则返回token验证失败
			if (pass) {
				point.proceed();
			} else {
				AjaxResult ajaxResult = new AjaxResult();
				ajaxResult.setStatus("pending").setError("token验证失败");
				out.write(new ObjectMapper().writeValueAsString(ajaxResult));
			}
		}catch (Throwable e) {
			AjaxResult ajaxResult = new AjaxResult();
			if("".equals(e.getMessage())) {
				ajaxResult.setStatus("pending").setError("token验证失败");
			}else {
				ajaxResult.setStatus("error").setError(e.getMessage());
			}
			try {
				out.write(new ObjectMapper().writeValueAsString(ajaxResult));
			} catch (JsonProcessingException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * 前端interfaces层接口验证
	 * 
	 * @param object
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private boolean interfacesValidate(String member_id,String member_token) throws Exception {
		MemberBean memberBean=new MemberBean().setMember_id(Integer.valueOf(member_id)).setMember_token(member_token);
		if (memberService.validationToken(memberBean)) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * 后台controller层接口验证
	 * @param object
	 * @return
	 * @throws Exception
	 */
	private boolean controllerValidate(String id,String token) throws Exception {
		SystemAccountBean systemAccountBean = new SystemAccountBean();
		systemAccountBean.setAccount_login_id(Integer.valueOf(id));
		systemAccountBean.setSystem_token(token);
		if (systemService.validationToken(systemAccountBean)) {
			return true;
		} else {
			return false;
		}
	}
}
