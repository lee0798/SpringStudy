package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//Spring 안에서 build하는 방법
// 터미널 해당 디렉토리까지 이동 ./gradlew build 빌드 파일 생성후 cd build로 이동 cd libs로 이동 ls -arlth 실행
//java -jar {위의 커멘드의 jar파일 붙여넣기}
@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
