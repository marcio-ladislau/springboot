package curso.springboot.springboot;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CriptografarSenha {

	public static void main(String[] args) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("123");
		System.out.println(result);

	}

}
