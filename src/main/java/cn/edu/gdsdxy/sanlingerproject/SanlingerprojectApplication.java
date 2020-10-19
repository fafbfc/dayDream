package cn.edu.gdsdxy.sanlingerproject;

import cn.edu.gdsdxy.sanlingerproject.admin.interceptor.AdminNeedLoginInterceptor;
import cn.edu.gdsdxy.sanlingerproject.api.interceptor.ClientNeedLoginInterceptor;
import lombok.Data;
import lombok.Setter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//	扫描mapper指定包名
@SpringBootApplication
public class SanlingerprojectApplication implements WebMvcConfigurer {

	@Autowired
	private AdminNeedLoginInterceptor adminNeedLoginInterceptor;

	@Autowired
	private ClientNeedLoginInterceptor clientNeedLoginInterceptor;

	public static void main(String[] args) {
		SpringApplication.run(SanlingerprojectApplication.class, args);
	}

//		允许跨域请求
	@Bean
	public CorsFilter corsFilter()
	{
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		// 允许cookies跨域
		config.setAllowCredentials(true);
		// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
		config.addAllowedOrigin("*");
		// #允许访问的头信息,*表示全部
		config.addAllowedHeader("*");
		// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
		config.setMaxAge(18000L);
		// 允许提交请求的方法，*表示全部允许
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		// 允许Get的请求方法
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

//		添加登录拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(adminNeedLoginInterceptor);
		registry.addInterceptor(clientNeedLoginInterceptor);
	}

}
