package com.hmdp.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.hmdp.utils.LoginInterceptor;
import com.hmdp.utils.RefreshTokenInterceptor;

@Configuration 
public class MvcConfig implements WebMvcConfigurer {

  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // token刷新拦截器
    registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate));
    
    // 登录拦截器
    registry.addInterceptor(new LoginInterceptor())
        .excludePathPatterns(
      "/shop-type/**",
      "/shop/**",
      "/upload/**",
      "/voucher/**",
      "/blog/hot",
      "/user/code",
      "/user/login"
        );
  }
}
