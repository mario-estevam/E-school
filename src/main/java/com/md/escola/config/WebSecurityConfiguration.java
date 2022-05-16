package com.md.escola.config;



import com.md.escola.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MyUserDetailsService userDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        String loginPage = "/login";
        String logoutPage = "/logout";
        String index = "/index";
        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(loginPage).permitAll()
                .antMatchers(index).permitAll()
                .antMatchers("/cadastro-professor").permitAll()
                .antMatchers("/home-professor").permitAll()
                .antMatchers("/buscar-professor").permitAll()
                .antMatchers("/salvar-professor").permitAll()
                .antMatchers("/editar/usuario/salvar").permitAll()
                .antMatchers("/cadastro-disciplina").permitAll()
                .antMatchers("/listar-disciplinas").permitAll()
                .antMatchers("/listar-turmas").permitAll()
                .antMatchers("/salvar-disciplina").permitAll()
                .antMatchers("/cadastro-turma").permitAll()
                .antMatchers("/salvar-turma").permitAll()
                .antMatchers("/save").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/pessoa/cadastro").permitAll()
                .antMatchers("/professor/**").hasAuthority("PROFESSOR")
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and().csrf().disable()
                .formLogin()
                .loginPage(loginPage)
                .loginPage("/")
                .failureUrl("/login?error=true")
                .defaultSuccessUrl(index)
                .defaultSuccessUrl(index)
                .usernameParameter("user_name")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(logoutPage))
                .logoutSuccessUrl(loginPage);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/error");
    }

}