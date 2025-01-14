package andrehsvictor.anitrace;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnitraceApplication {

	private static String[] getPermissionCommand = {
			"chmod", "+x", "generate_rsa_key_pair.sh"
	};

	private static String[] generateKeyPairCommand = {
			"./generate_rsa_key_pair.sh"
	};

	public static void main(String[] args) throws IOException {
		run(getPermissionCommand);
		run(generateKeyPairCommand);
		SpringApplication.run(AnitraceApplication.class, args);
	}

	public static void run(String[] command) throws IOException {
		ProcessBuilder processBuilder = new ProcessBuilder();
		processBuilder.command(command);
		processBuilder.start();
	}

}
