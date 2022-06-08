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
                .antMatchers("/atualizar-professor").permitAll()
                .antMatchers("/professor/editar").permitAll()
                .antMatchers("/save-atualizar-professor").permitAll()
                .antMatchers("/admin/atualizar-disciplina").permitAll()
                .antMatchers("/admin/matricular").permitAll()
                .antMatchers("/admin/matricular-save").permitAll()
                .antMatchers("/admin/listar-matriculas").permitAll()
                .antMatchers("/profesor/editar-nota/{id}").permitAll()
                .antMatchers("/salvar-nota").permitAll()
                .antMatchers("/cadastro-turma").permitAll()
                .antMatchers("/cadastro-professor").permitAll()
                .antMatchers("/aluno/listar-matriculas").permitAll()
                .antMatchers("/aluno/visualizar-nota/{id}").permitAll()
                .antMatchers("/editar/usuario/salvar").permitAll()
                .antMatchers("/cadastro-disciplina").permitAll()
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
