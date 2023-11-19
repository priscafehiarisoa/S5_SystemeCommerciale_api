//package s5_systemecommerciale_api.webSecurity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
////import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.*;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
////import org.springframework.security.provisioning.InMemoryUserDetailsManager;
////import org.springframework.security.web.SecurityFilterChain;
////import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfiguration
//{
////    @Bean
////    public InMemoryUserDetailsManager userDetailsService() {
////        // InMemoryUserDetailsManager (see below)
////    }
////
////@Autowired
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        // http builder configurations for authorize requests and form login (see below)
////    http.csrf()
////            .disable()
////            .authorizeRequests()
////            .antMatchers("/admin/**")
////            .hasRole("ADMIN")
////            .antMatchers("/anonymous*")
////            .anonymous()
////            .antMatchers("/login*")
////            .permitAll()
////            .anyRequest()
////            .authenticated()
////            .and()
////    }
////
////}
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
