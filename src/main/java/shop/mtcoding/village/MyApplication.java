// package shop.mtcoding.village;

// import io.sentry.Sentry;
// import org.springframework.boot.SpringApplication;
// import org.springframework.context.ApplicationListener;
// import org.springframework.context.event.ContextClosedEvent;
// import org.springframework.stereotype.Component;

// @Component
// public class MyApplication implements ApplicationListener<ContextClosedEvent> {

// 	// 애플리케이션이 종료될때 센트리를 종료하기 위한 클래스입니다.
//     public static void main(String[] args) {
//         SpringApplication.run(MyApplication.class, args);
//     }

//     @Override
//     public void onApplicationEvent(ContextClosedEvent event) {
//         Sentry.close(); // Sentry SDK 리소스 해제
//     }
// }