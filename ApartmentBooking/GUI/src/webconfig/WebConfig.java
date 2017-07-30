package webconfig;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@EnableWebMvc
@Configuration
@ComponentScan({"webconfig"})
public class WebConfig extends WebMvcConfigurerAdapter {


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("WEB-INF/");
        internalResourceViewResolver.setSuffix(".jsp");
        registry.viewResolver(internalResourceViewResolver);
    }


    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }



}
