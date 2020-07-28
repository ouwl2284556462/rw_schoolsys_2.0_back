package schoolsys.config;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.github.pagehelper.PageHelper;

@Configuration
/**
 * MVC配置
 */
public class MVCConfig implements WebMvcConfigurer {


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 设置静态资源位置
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
	}
	
	@Bean
	public HttpMessageConverter<String> responseBodyConverter() {
		// 设置字符集
		StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		return converter;
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// 设置转换器
		converters.add(responseBodyConverter());
	}

	@Bean
	/**
	 * 分页插件配置
	 * 
	 * @return
	 */
	public PageHelper pageHelper() {
		// 分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		// 超过页数仍然可以查出数据。如：如果pageNum>pageSize,会查询最后一页的数据
		properties.setProperty("reasonable", "true");
		// 如果参数中有pageNum，pageSize分页参数，则会自动分页
		properties.setProperty("supportMethodsArguments", "true");
		// 是否返回PageInfo类，有三个选项：
		// always:总是返回PageInfo类型
		// check:检查返回类型是否为PageInfo，是则返回，否则返回Page
		// none:返回Page
		properties.setProperty("returnPageInfo", "check");
		// 用于从Map或ServletRequest中取值
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);

		// 添加插件
		new SqlSessionFactoryBean().setPlugins(new Interceptor[] { pageHelper });
		return pageHelper;
	}

}
