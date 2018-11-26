package top.lionxxw.juc.concurrencydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import top.lionxxw.juc.concurrencydemo.filter.HttpFilter;
import top.lionxxw.juc.concurrencydemo.interceptor.HttpInterceptor;

@SpringBootApplication
public class ConcurrencyDemoApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyDemoApplication.class, args);
	}

	/**
	 * 装载过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean httpFilter(){
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpFilter());
		registrationBean.addUrlPatterns("/threadLocal/*");
		return  registrationBean;
	}

	/**
	 * 装载拦截器
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
	}
}
