package andrehsvictor.anitrace;

import org.springframework.boot.SpringApplication;

public class TestAnitraceApplication {

	public static void main(String[] args) {
		SpringApplication.from(AnitraceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
