package com.demo.shop;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;
import javax.servlet.MultipartConfigElement;


@Configuration

@SpringBootApplication
@MapperScan("com.demo.shop.mapper")//指定当前项目下的mapper接口路径
public class ShopApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }
    @Bean
    public MultipartConfigElement getMultipartConfigElement(){
        //创建一个配置的工厂类对象
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //设置需要创建对象的相关信息
        factory.setMaxFileSize(DataSize.of(10, DataUnit.MEGABYTES));
        factory.setMaxRequestSize(DataSize.of(30,DataUnit.MEGABYTES));
        //通过工厂类创建MultipartConfigElement对象
        return factory.createMultipartConfig();
    }
}