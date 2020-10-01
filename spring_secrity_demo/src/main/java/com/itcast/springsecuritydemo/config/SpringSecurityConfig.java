package com.itcast.springsecuritydemo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

/***
 *SpringSecurity配置类说明
 **/
@Component      //
@EnableWebSecurity      //可以使用springsecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    /*****
     * 1.公开资源配置
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/login.html");
//        .antMatchers("/img/**")
//        .antMatchers("/js/**");
    }


    /****
     * 2.受访问限制的路径配置
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //指定对应路径
        http.authorizeRequests().
                antMatchers("/**").                 //指定访问路径
                access("hasAnyRole('USER')");           //指定能访问的角色

        //自定义配置登录
        http.formLogin().
                loginPage("/login.html").               //指定登录页
                defaultSuccessUrl("/welcome.html",true). //登录成功地址
                loginProcessingUrl("/login1");           //登录请求地址



        //自定义配置登出
        http.logout().
                logoutSuccessUrl("/login.html").    //登出成功后跳转地址
                logoutUrl("/logout").               //SpringSecurity内置处理地址
                invalidateHttpSession(true);        //session无效处理

        //禁用CSRF跨站点请求伪造
        http.csrf().disable();
    }

    /****
     * 3.授权认证管理器
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //创建账号存储内存
        auth.inMemoryAuthentication().
                withUser("admin").
                password("{noop}123456").
                roles("USER");

    }
}
