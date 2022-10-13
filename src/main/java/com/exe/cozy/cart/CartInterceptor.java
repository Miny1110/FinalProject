package com.exe.cozy.cart;

import com.exe.cozy.domain.CustomerDto;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CartInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        CustomerDto cdto = (CustomerDto) session.getAttribute("customerEmail");

        if(cdto ==null){
            response.sendRedirect("/index");
            return false;
        }else {
            return true;
        }

    }
}
