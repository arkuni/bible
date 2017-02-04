package common.util.aop.service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;
@Aspect
public class ServiceAspect {
	private final long MAX_TIME = 300000;
	//private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private HttpSession session = null;
	private String method = "";
	private String classNm = "";
	private String id = "";
	private String deptCode = "";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object checkService(final ProceedingJoinPoint joinPoint) throws Throwable {

		method = joinPoint.getSignature().getName();
		classNm = joinPoint.getTarget().getClass().getSimpleName();
		Object [] args = joinPoint.getArgs();
		try {

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}


		//시간확인
		ExecutorService service = Executors.newSingleThreadExecutor();
		Future futureResult = null;
		Object retVal = null;
		final Object [] finalArgs = args;
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		futureResult = service.submit(new Callable() {
			@Override
			public Object call() throws Exception {
				try {
					return joinPoint.proceed(finalArgs);
				} catch (Throwable e) {
					e.printStackTrace();
					throw (Exception)e;
				}
			}
		});

		classNm = joinPoint.getTarget().getClass().getName();

		try {
			retVal = futureResult.get(MAX_TIME, TimeUnit.MILLISECONDS);
		} catch(TimeoutException e) {
			//logger.error("################### too long");
			throw e;
		} finally {
			stopWatch.stop();
			//logger.info(makeLog(classNm, method, stopWatch.getTotalTimeMillis()));
		}

		return retVal;
	}


	private String makeLog(String classNm, String methodNm, long timeMil){
		StringBuffer logMessage = new StringBuffer();
		logMessage.append(classNm);
		logMessage.append(".");
		logMessage.append(methodNm);
		logMessage.append("(");
		logMessage.append(")");
		logMessage.append(" execution time: ");
		logMessage.append(timeMil);
		logMessage.append(" ms");
		return logMessage.toString();
	}
}