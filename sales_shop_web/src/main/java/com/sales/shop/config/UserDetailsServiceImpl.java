package com.sales.shop.config;

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
      //  System.out.println("=登录后调用=");There was an unexpected error (type=Forbidden, status=403).解决方案
        //根据用户名查询用户数据,原因是定义的角色名称不匹配
        //路径权限规则匹配中配置的是:ADMIN 这里程序猿不可以配置ROLE_开头的角色 不然直接报BUG
        //自定义权限验证中就要配置用户的权限:ROLE_ADMIN 需要加上ROLE_开头
        TbSeller seller = sellerService.findOne(username);
        System.out.println(seller);
        //查询用户角色
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));//
        if(seller!= null && "1".equals(seller.getStatus())){
            //return new User(username,"{noop}"+seller.getPassword(),authorities);
            System.out.println("=登录调用=");
            return new User(username,seller.getPassword(),authorities);
        }
        return null;
    }
}
