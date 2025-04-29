plugins {
    id("java")
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.fincodehub"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.security:spring-security-core")
    implementation("eu.bitwalker:UserAgentUtils:1.21") // 解析 UserAgent 信息
    implementation("com.alibaba:fastjson:2.0.53") // JSON 解析
    implementation("cn.hutool:hutool-all:5.8.37")
    implementation("commons-lang:commons-lang:2.6")

}

tasks.test {
    useJUnitPlatform()
}