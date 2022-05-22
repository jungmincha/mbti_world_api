package com.mbtiworld.main_api.security;
// package com.aquamarine.main_api.security;

// import org.springframework.stereotype.Component;
// import org.springframework.util.ObjectUtils;
// import org.springframework.web.servlet.HandlerInterceptor;

// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// @Component
// public class CertificationInterceptor implements HandlerInterceptor {

//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//         HttpSession session = request.getSession();
//         if(ObjectUtils.isEmpty(session.getAttribute("SPRING_SECURITY_CONTEXT"))) {
//             response.sendRedirect("/login");
//             return false;
//         }
//         else {
//             session.setMaxInactiveInterval(30*60);
//             return true;
//         }

//     }
// }
