package com.sales.com.sales.controller;



import com.sales.pojo.TbBrand;
import com.sales.sellergoods.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;


    @RequestMapping("/findall")
    public List<TbBrand>  findall(){
        return  brandService.findAll();
    }

    @RequestMapping("/findone")
    public TbBrand  findOne(Long id){
        return  brandService.findOne(id);
    }

}
