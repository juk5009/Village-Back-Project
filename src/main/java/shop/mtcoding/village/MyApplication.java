package shop.mtcoding.village;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

@SpringBootApplication
public class MyApplication implements ApplicationListener<ContextClosedEvent> {

	// 센트리가 로그 수집할때 메모리를 너무 많이 먹어서 
	// http 요청이 종료될때 로그수집을 종료하기 위한 클래스입니다.
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        Sentry.close(); // Sentry SDK 리소스 해제
    }
}