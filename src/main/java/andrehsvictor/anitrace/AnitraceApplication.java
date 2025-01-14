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
		getGetPermission();
		generateRsaKeyPair();
		SpringApplication.run(AnitraceApplication.class, args);
	}

	private static void generateRsaKeyPair() throws IOException {
		ProcessBuilder generateKeyPairProcessBuilder = new ProcessBuilder();
		generateKeyPairProcessBuilder.command(generateKeyPairCommand);
		generateKeyPairProcessBuilder.start();
	}

	private static void getGetPermission() throws IOException {
		ProcessBuilder getPermissionProcessBuilder = new ProcessBuilder();
		getPermissionProcessBuilder.command(getPermissionCommand);
		getPermissionProcessBuilder.start();
	}

}
