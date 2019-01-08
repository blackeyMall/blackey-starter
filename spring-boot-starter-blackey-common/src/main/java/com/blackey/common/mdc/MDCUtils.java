package com.blackey.common.mdc;

import com.blackey.common.constants.LogConstants;
import com.blackey.common.runtime.RuntimeApplication;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.blackey.common.constants.LogConstants.*;

public final class MDCUtils {

    public static void addNewLine() {
        MDC.put(LogConstants.MDC_KEY_NEW_LINE, "\n");
    }

    public static void removeNewLine() {
        MDC.remove(LogConstants.MDC_KEY_NEW_LINE);
    }

    public static void addTransactionId() {
        MDC.put(MDC_KEY_REQ_ID, "\nREQ_ID: " + TransactionId.next());
    }

    public static void removeTransactionId() {
        MDC.remove(MDC_KEY_REQ_ID);
    }

    public static void addUserIdMDC(Long userId) {
        MDC.put(LogConstants.MDC_KEY_USER_ID, "\nUSER_ID: " + userId);
    }

    public static void removeUserIdMDC() {
        MDC.remove(LogConstants.MDC_KEY_USER_ID);
    }

    public static void addControllerMethod(String reqMethod) {
        MDC.put(LogConstants.MDC_KEY_CONTROLLER, "\nCONTROLLER_METHOD: " + reqMethod);
    }

    public static void removeControllerMethod() {
        MDC.remove(LogConstants.MDC_KEY_CONTROLLER);
    }

    public static void addApplicationMDC() {
        MDC.put(LogConstants.MDC_KEY_APPLICATION, "\nAPPLICATION: " + RuntimeApplication.applicationName+"("+RuntimeApplication.applicationIp+")");
    }

    public static void removeApplicationMDC() {
        MDC.remove(LogConstants.MDC_KEY_APPLICATION);
    }

    public static void addExecuteStartTime(Long time) {
        MDC.put(LogConstants.MDC_KEY_EXECUTE_START_TIME, String.valueOf(time));
    }

    public static Long getExecuteStartTime() {
        return Long.valueOf(MDC.get(LogConstants.MDC_KEY_EXECUTE_START_TIME));
    }

    public static void removeExecuteStartTime() {
        MDC.remove(LogConstants.MDC_KEY_EXECUTE_START_TIME);
    }

    public static void addServletRequestMDC(ServletRequest servletRequest) {
        if (!(servletRequest instanceof HttpServletRequest)) {
            return;
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        String method = request.getMethod();
        if (StringUtils.isNotBlank(method)) {
            MDC.put(MDC_KEY_REQUEST_METHOD, "\nREQ_METHOD: " + method);
        }
        String requestURI = request.getRequestURI();
        if (StringUtils.isNotBlank(requestURI)) {
            MDC.put(MDC_KEY_REQUEST_REQUEST_URI, "\nREQ_URI: " + requestURI);
        }
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            MDC.put(MDC_KEY_REQUEST_QUERY_STRING, "\nREQ_QUERY_STRING: " + queryString);
        }
        String host = request.getHeader("Host");
        if (StringUtils.isNotBlank(host)) {
            MDC.put(MDC_KEY_REQUEST_HOST, "\nREQ_HOST: " + host);
        }
        String remoteHost = request.getRemoteHost();
        if (StringUtils.isNotBlank(remoteHost)) {
            MDC.put(MDC_KEY_REQUEST_REMOTE_HOST, "\nREQ_REMOTE_HOST: " + remoteHost);
        }
        String referer = request.getHeader("Referer");
        if (StringUtils.isNotBlank(referer)) {
            MDC.put(MDC_KEY_REQUEST_REFERER, "\nREQ_REFERER: " + referer);
        }
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent)) {
            MDC.put(MDC_KEY_REQUEST_USER_AGENT, "\nREQ_USER-AGENT: " + userAgent);
        }
        String xFrontendNode = request.getHeader("X-Frontend-Node");
        if (StringUtils.isNotBlank(xFrontendNode)) {
            MDC.put(MDC_KEY_REQUEST_X_FRONTEND_NODE, "\nREQ_X-FRONTEND_NODE: " + xFrontendNode);
        }
        String xRealIP = request.getHeader("X-Real-IP");
        if (StringUtils.isNotBlank(xRealIP)) {
            MDC.put(MDC_KEY_REQUEST_X_REAL_IP, "\nREQ_X-REAL-IP: " + xRealIP);
        }
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotBlank(xForwardedFor)) {
            MDC.put(MDC_KEY_REQUEST_X_FORWARDED_FOR, "\nREQ.X-FORWARDED-FOR: " + xForwardedFor);
        }
    }

    public static void removeServletRequestMDC() {
        MDC.remove(MDC_KEY_REQUEST_METHOD);
        MDC.remove(MDC_KEY_REQUEST_REQUEST_URI);
        MDC.remove(MDC_KEY_REQUEST_QUERY_STRING);
        MDC.remove(MDC_KEY_REQUEST_HOST);
        MDC.remove(MDC_KEY_REQUEST_REMOTE_HOST);
        MDC.remove(MDC_KEY_REQUEST_REFERER);
        MDC.remove(MDC_KEY_REQUEST_USER_AGENT);
        MDC.remove(MDC_KEY_REQUEST_X_FRONTEND_NODE);
        MDC.remove(MDC_KEY_REQUEST_X_REAL_IP);
        MDC.remove(MDC_KEY_REQUEST_X_FORWARDED_FOR);
    }

    public static String MDC_INFO() {
        StringBuilder sb = new StringBuilder();
        if (Objects.nonNull(MDC.get(MDC_KEY_NEW_LINE)))
            sb.append(MDC.get(MDC_KEY_NEW_LINE));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQ_ID)))
            sb.append(MDC.get(MDC_KEY_REQ_ID));

        if (Objects.nonNull(MDC.get(MDC_KEY_USER_ID)))
            sb.append(MDC.get(MDC_KEY_USER_ID));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_METHOD)))
            sb.append(MDC.get(MDC_KEY_REQUEST_METHOD));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_REQUEST_URI)))
            sb.append(MDC.get(MDC_KEY_REQUEST_REQUEST_URI));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_QUERY_STRING)))
            sb.append(MDC.get(MDC_KEY_REQUEST_QUERY_STRING));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_HOST)))
            sb.append(MDC.get(MDC_KEY_REQUEST_HOST));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_REMOTE_HOST)))
            sb.append(MDC.get(MDC_KEY_REQUEST_REMOTE_HOST));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_REFERER)))
            sb.append(MDC.get(MDC_KEY_REQUEST_REFERER));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_USER_AGENT)))
            sb.append(MDC.get(MDC_KEY_REQUEST_USER_AGENT));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_X_FRONTEND_NODE)))
            sb.append(MDC.get(MDC_KEY_REQUEST_X_FRONTEND_NODE));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_X_REAL_IP)))
            sb.append(MDC.get(MDC_KEY_REQUEST_X_REAL_IP));

        if (Objects.nonNull(MDC.get(MDC_KEY_REQUEST_X_FORWARDED_FOR)))
            sb.append(MDC.get(MDC_KEY_REQUEST_X_FORWARDED_FOR));

        if (Objects.nonNull(MDC.get(MDC_KEY_CONTROLLER)))
            sb.append(MDC.get(MDC_KEY_CONTROLLER));

        if (Objects.nonNull(MDC.get(MDC_KEY_APPLICATION)))
            sb.append(MDC.get(MDC_KEY_APPLICATION));

        return sb.toString();
    }

}
