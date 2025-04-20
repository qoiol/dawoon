package spring.project.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.project.domain.User;
import spring.project.service.UserService;
import spring.project.util.JwtTokenUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * The type Jwt authentication filter.
 */
@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final String key;
    private final UserService userService;


    @Value("${jwt.expired-time-ms}")
    private long expiredTimeMs;

    private final Set<String> uri = Set.of(
            "/user/login", "/user/create", "/main", "/user/logout", "/");
    private final Set<String> prefix = Set.of(
            "/favicon.",
            "/css/",
            "/js/",
            "/images/",
            "/webjars/"
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        if (uri.stream().anyMatch(requestUri::equals) || prefix.stream().anyMatch(requestUri::startsWith)) {
            filterChain.doFilter(request, response); // 필터 건너뛰기
            return;
        }

        //로그인 X
        if (!loginFlag(request.getSession())) {
            moveToLogin(request, response);
            return;
        }

        Cookie token = getToken("token", request);
        String newToken;
        String userId = request.getSession().getAttribute("userId").toString();
        String userType = request.getSession().getAttribute("userType").toString();

        //1 토큰 X or 만료
        //재발급
        if (token == null || JwtTokenUtils.isExpired(token.getValue(), key)) {

            newToken = JwtTokenUtils.generateToken(userId, userType, key, expiredTimeMs);
            token = JwtTokenUtils.addCookie("token", newToken, response);

            log.error("token is expired -> regenerate");
        }

        //2 유저 정보 x
        String tokenUserId = JwtTokenUtils.userId(token.getValue(), key);
        User user = userService.findOne(tokenUserId).orElse(null);
        if (!userId.equals(tokenUserId) || user == null) {
            log.error("userinfo does not match");
            //토큰 정보 불일치 -> 로그아웃
            response.sendRedirect("/user/logout?action=login&detail=match");
            return;
        }


        //3 유효
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user, null, List.of(new SimpleGrantedAuthority(user.getUserType()))
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        log.info("인증 객체: {}", SecurityContextHolder.getContext().getAuthentication());

        filterChain.doFilter(request, response);
    }

    //쿠키 가져오기
    private Cookie getToken(String name, HttpServletRequest request) {
        return Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(name))
                .findAny()
                .orElse(null);
    }


    //로그인페이지로 이동
    private void moveToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        String queryString = request.getQueryString() != null ? request.getQueryString() : "";
        String referer = URLEncoder.encode(URLEncoder.encode(uri + queryString, StandardCharsets.UTF_8), StandardCharsets.UTF_8);

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().append("<script>alert('로그인 후 다시 시도해주세요.');location.href='/user/login?referer=").append(referer).append("';</script>");
    }

    //로그인 여부 반환
    private boolean loginFlag(HttpSession session) {
        return (session.getAttribute("userId") != null && session.getAttribute("userType") != null);
    }
}
