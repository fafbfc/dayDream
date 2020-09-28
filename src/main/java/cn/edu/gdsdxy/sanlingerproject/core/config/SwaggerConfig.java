package cn.edu.gdsdxy.sanlingerproject.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Value("${swagger2.enable}")
    private String swagger2EnableStr;

    public SwaggerConfig() { }

    @Bean
    public Docket api() {

        boolean swagger2Enable = Boolean.parseBoolean(this.swagger2EnableStr);

//        选择文档类型 SWAGGER_2，指定组名，再进行初始化构造
//        .enable(swagger2Enable)
        return (new Docket(DocumentationType.SWAGGER_2)).groupName("dayDream")
                .enable(swagger2Enable).apiInfo(this.apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("cn.edu.gdsdxy.sanlingerproject.api.controller"))
                .build();
    }

//    自定义UI的配置信息
    private ApiInfo apiInfo()
    {
//        title: 大的标题；description：描述
        ApiInfo apiInfo = new ApiInfo("dayDream的Swagger2 API文档"
                , "dayDream的Swagger2 API文档"
                , "1.0", "urn:tos"
                , ApiInfo.DEFAULT_CONTACT, "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0"
                , new ArrayList());
        return apiInfo;

    }
}