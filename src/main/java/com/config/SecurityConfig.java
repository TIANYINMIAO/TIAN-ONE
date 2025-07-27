package com.config;

import com.util.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // 1. 配置放行的路径（顺序很重要）
                .antMatchers(


                        "/huanghe/login",
                        "/huanghe/register",
                        "/huanghe/sendVerificationCode",
                     //   "/huanghe/question",
                       // "/huanghe/option",
                      //  "/huanghe/download",
                        "/login.html",
                        "/static/**",
                        "/*.html",           // 放行所有html文件
                        "/*.js",            // 放行所有js文件
                        "/*.css",           // 放行所有css文件
                        "/favicon.ico"      // 放行网站图标
                ).permitAll()
                // 2. 配置需要认证的路径
                .antMatchers("/huanghe/**", "/api/ai/lishi").authenticated()
                // 3. 其他请求放行
                .anyRequest().permitAll()
                .and()
                // 4. 添加JWT过滤器，放在 UsernamePasswordAuthenticationFilter 后面执行
                .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 5. 配置 session 管理（可选）
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        System.out.println("1===1");
    }

    // 配置静态资源处理
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/css/**",
                "/js/**",
                "/img/**",
                "/fonts/**",
                "/static/**"
        );
        System.out.println("1===1");
    }
}
