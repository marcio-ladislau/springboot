package curso.springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private ImplementacaoUserDetailsService implementacaoUserDetailsService;
	
	@Override //Configura as solicitações de acesso por http
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf()
		.disable() //Desativa as configuracoes padrao de memoria do spring
		.authorizeRequests() //Permitir restringir acessos
		.antMatchers(HttpMethod.GET, "/").permitAll()//Qualquer usuario acessa a pagina inicial
		.anyRequest().authenticated()
		.and().formLogin().permitAll() //permite qualquer usuario
		.and().logout() //Mapeia URL de logout e invalida usuario autenticado
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
	}
	
	@Override //cria autenticacao do usuario com banco de dados ou memoria
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		auth.userDetailsService(implementacaoUserDetailsService)
		.passwordEncoder(new BCryptPasswordEncoder());
		
		
		
		
//		auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
//		.withUser("alex")
//		.password("$2a$10$d5HoX9.eGV0ivdBYwpjOQuLzd4wu7B9lsWjb7m50s1IpIsFjQf.n6")
//		.roles("ADMIN");
	}
	
	@Override //Ignora URL especificas
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers("/materialize/**");
	}
	
	

}
