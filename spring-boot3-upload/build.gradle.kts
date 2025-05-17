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
// 依赖管理
// 使用 maven bom 管理依赖，减少版本号的冲突。因为 jsqlparser 5.0+ 版本不再支持 jdk8 针对这个问题解耦 jsqlparser 依赖。
// 正确打开姿势，引入 mybatis-plus-bom 模块，然后引入 ..starter和 ..jsqlparser.. 依赖


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("eu.bitwalker:UserAgentUtils:1.21") // 解析 UserAgent 信息
    implementation("com.alibaba:fastjson:2.0.53") // JSON 解析
    implementation("cn.hutool:hutool-all:5.8.37")
    implementation("commons-lang:commons-lang:2.6")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")

}

tasks.test {
    useJUnitPlatform()
}