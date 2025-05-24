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



dependencyManagement {
    imports {
        mavenBom("com.baomidou:mybatis-plus-bom:3.5.11")
    }
}
dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("commons-lang:commons-lang:2.6")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.baomidou:mybatis-plus-spring-boot3-starter")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("com.baomidou:mybatis-plus-jsqlparser")
//    implementation("org.apache.shiro:shiro-spring-boot-starter:2.0.2:jakarta")

    implementation("org.apache.shiro:shiro-spring-boot-starter:2.0.4:jakarta") {
        exclude(group = "org.apache.shiro", module = "shiro-crypto-cipher")
        exclude(group = "org.apache.shiro", module = "shiro-crypto-hash")
        exclude(group = "org.apache.shiro", module = "shiro-web")
        exclude(group = "org.apache.shiro", module = "shiro-spring")
    }
    implementation("org.apache.shiro:shiro-web:2.0.4:jakarta")
    implementation("org.apache.shiro:shiro-spring:2.0.4:jakarta") {
        exclude(group = "org.apache.shiro", module = "shiro-web")
    }
    implementation("com.github.theborakompanioni:thymeleaf-extras-shiro:2.1.0")
    implementation("net.sf.ehcache:ehcache:2.10.9.2")
    implementation("org.apache.shiro:shiro-ehcache:2.0.4")
    implementation("commons-io:commons-io:2.19.0")
}


tasks.test {
    useJUnitPlatform()
}