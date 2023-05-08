# 공간 대여 앱 제작 프로젝트

> ## 시연영상 ( Youtube )

추가 예정

<br>


> ## 발표자료 ( PDF )

추가예정

<br>

> ## 프로젝트 소개

- 주변에서 춤 연습을 하거나 회의를 하거나 공부를 하려는 등의 공간을 알아보고 있나요 ? <br>
  여러분에게 필요한 여러 공간을 여기서 빌려보세요 !<br>
  호스트는 공간을 등록하고 사용자는 예약, 결제를 통해 공간을 빌리는 서비스 입니다.

<br>

> ## 프로젝트 기간

- 2023-04-10 ~ 2023.05.10

<br>

> ## 기술 스택

### 서버

- JDK 11
- Spring Boot 2.7.8
- MyBatis
- H2 DB
- MySql DB
- Spring Data Jpa
- Spring Security
- JUnit5
- Rest Doc
- AWS EC2
- AWS S3
- Sentry.io
- Firebase Cloud Message
- BootPay

### 프론트

- Flutter
- Dart
- Dio
- Riverpod

<br>

> ## 기능정리



<br>

> ## spring 의존성 주입

```

        // restdoc
	asciidoctorExt 'org.springframework.restdocs:spring-restdocs-asciidoctor'
	testImplementation 'org.springframework.restdocs:spring-restdocs-mockmvc'

	// jsp
	implementation 'javax.servlet:jstl'
	implementation 'org.apache.tomcat.embed:tomcat-embed-jasper'

	// AWS S3
	implementation 'org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE'

	//native query qlrm
	implementation group: 'org.qlrm', name: 'qlrm', version: '2.1.1'
	
	// firebase 의존성
	implementation group: 'com.squareup.okhttp3', name: 'okhttp', version: '4.2.2'
	implementation 'com.google.firebase:firebase-admin:9.1.1'

	implementation 'org.springframework.boot:spring-boot-starter-aop'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-mustache'
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'org.springframework.security:spring-security-test'
	implementation group: 'com.auth0', name: 'java-jwt', version: '4.3.0'
	implementation 'io.sentry:sentry-spring-boot-starter:6.17.0'
	implementation 'io.sentry:sentry-logback:6.17.0'
```

> ## 플러터 의존성 주입

```
  cupertino_icons: ^1.0.2
  intl: ^0.18.0
  animated_text_kit: ^4.2.2
  animate_do: ^3.0.2
  dio: ^5.1.1
  flutter_riverpod: ^2.3.2
  riverpod: ^2.3.6
  font_awesome_flutter: ^9.0.0
  lottie: ^2.3.2
  http: ^0.13.5
  geolocator: ^9.0.2
  fluttertoast: ^8.2.1
  motion_toast: ^2.6.6
  remedi_kopo: ^0.0.2
  image_picker: ^0.8.7+3
  google_maps_flutter: ^2.2.5
  validators: ^3.0.0
  logger: ^1.1.0
  flutter_secure_storage: ^7.0.0
  flutter_swiper_null_safety: ^1.0.2
  bootpay: ^4.6.1
  api_provider_builder: ^1.0.4
  firebase_messaging: ^14.4.1
  scrollable_positioned_list: ^0.3.5
  async: ^2.10.0
  firebase_core: ^2.10.0
  provider: ^6.0.5
  cached_network_image: ^3.2.3
  flutter_html: ^2.2.1
```
<br>

> ## 테이블 모델링

<br>

![img.png](img.png)

<br>
<br>

> ## 기술 블로그

[![NOTION](https://img.shields.io/badge/-NOTION-222222?style=for-the-badge&logo=NOTION)](https://www.notion.so/getinthere/bc3034ca8e5c4206977c9351124c71c6)

<br>

> ## 담당 기능

추가예정

<br>

> ## 구현 화면

추가예정

<br>

> ## 보완점

추가예정

<br>

> ## 후기

추가예정
