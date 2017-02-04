package common.util.aop.controller;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import common.constant.ValidatorCD;
import common.exception.HNTException;
import common.util.HNTTrans;
@Aspect
public class ControllerAspect {

	public ModelAndView checkController(ProceedingJoinPoint joinPoint)
			throws Throwable {
		HttpSession session = null;
		HttpServletRequest request = null;
		String method = "";
		String id = "";
		String ip = "";
		String deptCode = "";
		String callUrl = "";
		String errorMsg = "";
		ModelAndView mav = new ModelAndView();
		for (Object o : joinPoint.getArgs()) {
			if (o instanceof HttpServletRequest) {
				request = (HttpServletRequest) o;
			}
		}
		//세션체크
		ip = getIp(request);

		UrlPathHelper urlPathHelper = new UrlPathHelper();
		callUrl = urlPathHelper.getOriginatingRequestUri(request);




		//파라미터 체크
		try {

			Set<Entry<String, String[]>> entry = request.getParameterMap().entrySet();

			StringBuilder sb = new StringBuilder();

			for(Entry<String, String[]> data : entry) {
				String[] paramVals = data.getValue();
				for(String val : paramVals) {
					sb.append("&");
					sb.append(HNTTrans.trim(data.getKey()));
					sb.append("=");
					sb.append(val);
				}
			}



			for(Entry<String, String[]> data : entry) {
				String paramName = HNTTrans.trim(data.getKey()).toLowerCase();
				String[] paramVals = data.getValue();
				ValidatorCD cd = null;



				/* start : equals 조건*/

				/* end : equals 조건*/


				/* start : startWith 조건*/

				/* end : startWith 조건*/


				/* start : endsWith 조건*/

				/* end : endsWith 조건*/
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
			return setErrorPage(mav, callUrl, errorMsg);
		}

		//버튼권한체크
		try {
			String classNm = "";
			classNm = joinPoint.getTarget().getClass().getSimpleName();
			method = joinPoint.getSignature().getName();
			mav = (ModelAndView)joinPoint.proceed();


			// 내용추가
			Map<String, Object> model = mav.getModel();


		} catch (Exception e) {
			e.printStackTrace();
			errorMsg = e.getMessage();
			return setErrorPage(mav, callUrl, errorMsg);
		}

		mav.getModelMap().put("msg", errorMsg);
		return mav;


	}


	private String getIp(HttpServletRequest request) {
		String ip = "";
		ip = getIpCore(request, ip, "HTTP_X_FORWARDED_FOR");
		ip = getIpCore(request, ip, "X_Forwarded_For");
		ip = getIpCore(request, ip, "Proxy-Client_IP");
		ip = getIpCore(request, ip, "REMOTE_ADDR");
		ip = HNTTrans.trim(request.getRemoteAddr());
		return ip;
	}

	private String getIpCore(HttpServletRequest request, String ip, String header) {
		if (ip.equals("")) {
			ip = HNTTrans.trim(request.getHeader(header));
			ip = ip.toLowerCase().equals("unknown") ? "" : ip;
		}
		return ip;
	}

	private ModelAndView setErrorPage(ModelAndView mav, String url, String errorMsg) throws HNTException {
		if (url.indexOf(".json") > -1) {
			mav.setViewName("common/json/error");
			mav.getModelMap().put("msg", "입력값을 확인 해주세요.");
			mav.getModelMap().put("error_msg", errorMsg);
		} else if (url.indexOf(".hnt") > -1) {
			mav.setViewName("common/hnt/error");
			mav.getModelMap().put("msg", "입력값을 확인 해주세요.");
			mav.getModelMap().put("error_msg", errorMsg);
		}

		return mav;
	}
}
