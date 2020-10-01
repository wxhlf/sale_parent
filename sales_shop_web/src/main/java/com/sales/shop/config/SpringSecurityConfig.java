package com.sales.shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/***
 *
 *  配置类,取代SpringSecurity配置文件
 ****/
@Component
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /***
    * 1.公开文件配置
    * */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/img/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/plugins/**");
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/*.html"); //包括shoplogin.html
        web.ignoring().antMatchers("/seller/add");
    }

    /*
    * 2.受访问限制配置
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().
                antMatchers("/**").         //其他访问路径
                access("hasAnyRole('SELLER')");  //必须登录后，角色为ADMIN才能访问

        //自定义登录配置
        http.formLogin().
                loginPage("/shoplogin.html").           //自定义登录页
                failureUrl("/shoplogin.html").          //登录失败跳转地址
                defaultSuccessUrl("/admin/index.html",true).     //登录成功跳转地址
                loginProcessingUrl("/login");       //登录处理地址

        //自定义登出
        http.logout().
                logoutUrl("/logout").               //登出处理地址
                logoutSuccessUrl("/shoplogin.html").    //登出成功后跳转地址
                invalidateHttpSession(true);         //让session无效


        //禁用CSRF
        http.csrf().disable();

        //iframe框架的启用
        http.headers().frameOptions().disable();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder encoder() { //密码加密
        return new BCryptPasswordEncoder();
    }

    /*
    * 3.授权认证管理器
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //自定义授权认证
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());       //指定加密算法
    }
}
