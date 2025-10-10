package org.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("username") != null) {
            return true; // Đã đăng nhập → cho phép đi tiếp
        }

        // Nếu chưa đăng nhập → quay về trang login
        response.sendRedirect(request.getContextPath() + "/");
        return false;
    }
}
