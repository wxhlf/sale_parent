package com.sales.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sales.pojo.TbSeller;
import com.sales.sellergoods.service.SellerService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/***
 *  自定义授权认证
 ****/
@Component      //spring中实例化
public class UserDetailsServiceImpl implements UserDetailsService {

    @Reference
    private SellerService sellerService;
    /****
     * 授权认证
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("=登录后调用=");
        //根据用户名查询用户数据
        TbSeller seller = sellerService.findOne(username);
        //查询用户角色
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));//
        if(seller!= null && "1".equals(seller.getStatus())){
            //return new User(username,"{noop}"+seller.getPassword(),authorities);
            return new User(username,seller.getPassword(),authorities);
        }
        return null;
    }
}
