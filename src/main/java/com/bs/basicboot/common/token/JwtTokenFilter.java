package com.bs.basicboot.common.token;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.security.Security;


@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends GenericFilter {

    private final JwtTokenUtils tokenUtils;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //사용자가 전달한 token값을 확인하는 로직을 구현
        //사용자가 요청시 Authorization헤더에 Token을 저장해서 보냄


        String token = ((HttpServletRequest)servletRequest).getHeader("Authorization");
        if(token!=null&&tokenUtils.validToToken(token)){
            //인증처리를 해준다.
            Authentication authentication = tokenUtils.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
