package dxc.bookstore.bookstoreapp;

import dxc.bookstore.bookstoreapp.filter.AuthenticationFilter;
import dxc.bookstore.bookstoreapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Autowired
    private UserService userService;

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean<AuthenticationFilter>();
        registrationBean.setFilter(new AuthenticationFilter(userService));
        registrationBean.addUrlPatterns("/api/*");

        return registrationBean;
    }

}
