package dxc.bookstore.bookstoreapp.filter;

import dxc.bookstore.bookstoreapp.model.entity.User;
import dxc.bookstore.bookstoreapp.service.UserService;
import dxc.bookstore.bookstoreapp.util.Constants;
import dxc.bookstore.bookstoreapp.util.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Component
public class AuthenticationFilter implements Filter {

    private UserService userService;

    public AuthenticationFilter(UserService userService) {
        this.userService = userService;
    }

    private void rejectForbidden(ServletResponse response) {
        ((HttpServletResponse) response).setStatus(403);
    }

    private void rejectUnauthorized(ServletResponse response) {
        ((HttpServletResponse) response).setStatus(401);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("Entered Authentication Filter. Do header check here");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String userId = httpServletRequest.getHeader("userId");
        String apikey = httpServletRequest.getHeader("apiKey");
        System.out.println("userId: " + userId + " apiKey: " + apikey);

        if (StringUtils.hasLength(userId) && StringUtils.hasLength(apikey)) {

            ContentCachingRequestWrapper requestWrapper;
            requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) servletRequest);



            // Simplistic Secret Key Checking for this Demo
            if (Constants.getSECRETKEY().equals(apikey)) {

                try {
                    String requestUrl = requestWrapper.getRequestURI();

                    User user = userService.getUser(Integer.parseInt(userId));
                    System.out.println(user);
                    ThreadContext.setThreadUser(user);

                    if (requestUrl.contains("/secured/")) {
                        System.out.println("Secured Url, need to check for User Authorization");
                        if (!user.isAuthorized()) {
                            System.out.println("User is not allowed to use this url");
                            rejectForbidden(servletResponse);
                        } else {
                            filterChain.doFilter(requestWrapper, servletResponse);
                        }
                    } else {
                        filterChain.doFilter(requestWrapper, servletResponse);
                    }

                    //String requestBody2 = getRequestData(requestWrapper);
                    //System.out.println("requestBody2: " + requestBody2);
                } finally {
                    ThreadContext.removeUser();
                }

            } else {
                rejectUnauthorized(servletResponse);
            }

        } else {
            rejectUnauthorized(servletResponse);
        }

    }

    private static String getRequestData(final HttpServletRequest request) throws UnsupportedEncodingException {
        String payload = null;
        ContentCachingRequestWrapper wrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (wrapper != null) {
            byte[] buf = wrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payload = new String(buf, 0, buf.length, wrapper.getCharacterEncoding());
            }
        }
        return payload;
    }

}
