//package com.Interceptor;
//
//import com.util.JwtUtil;
//import io.jsonwebtoken.Claims;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import io.jsonwebtoken.Jwts;
//import org.apache.commons.lang3.time.FastDateParser;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//import static com.config.Codes.SECRET_KEY;
//@Component
//public class TokenInterceptor implements HandlerInterceptor {
//
//    private static final String SECRET_KEY = "bdfajnitacnmbfdbdfbdfajnitacnmbfdbdfbdfajnitacnmbfdbdfbdfajnitacnmbfdbdfbdfajnitacnmbfdbdfbdfajn"; // 替换为实际的密钥
//
//    /**
//     * 在请求处理之前进行拦截
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 从请求头中获取 Authorization 字段
//        String authHeader = request.getHeader("Authorization");
//
//        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//            // 如果没有 token 或者格式不正确，返回 401 未授权
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("缺少或错误的授权信息");
//            return false;
//        }
//
//        // 截取 token 字符串
//        String token = authHeader.substring(7);
//
//        try {
//            // 解析 JWT Token
//
//            Claims claims = Jwts.parserBuilder()
//                    .setSigningKey(SECRET_KEY)  // 设置签名密钥，密钥需要和生成 JWT 时的密钥一致
//                    .build()
//                    .parseClaimsJws(token)  // 解析 JWT
//                    .getBody();  // 获取 JWT 的 Body 部分
//            // 如果需要，你可以在这里进一步处理 claims 中的数据
//            System.out.printf("用户====："+claims.getSubject());
//            // 将 token 中的信息放入请求上下文中
//            request.setAttribute("claims", claims);
//        } catch (Exception e) {
//            // 如果 token 无效，返回 401
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            response.getWriter().write("无效的 token");
//            return false;
//        }
//
//        // token 有效，继续处理请求
//        return true;
//    }
//}
