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
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")  // 添加JUnit平台测试支持
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("eu.bitwalker:UserAgentUtils:1.21") // 解析 UserAgent 信息
    implementation("com.alibaba:fastjson:2.0.53") // JSON 解析
    implementation("cn.hutool:hutool-all:5.8.37")
    implementation("commons-lang:commons-lang:2.6")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java:8.0.33")

}

tasks.test {
    useJUnitPlatform()
}