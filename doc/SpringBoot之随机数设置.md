### SpringBoot之随机数设置

添加config/random.properties文件，添加以下内容：

```
# 随机字符串：32位MD5字符串
user.random.secret=${random.value}
# 随机uuid
user.random.uuid=${random.uuid}
# 随机int数字
user.random.intNumber=${random.int}
# 随机long数字
user.random.longNumber=${random.long}
# 100以内的随机数
user.random.lessTen=${random.int(100)}
# [5000,65536]范围内的随机数
user.random.range=${random.int[5000,6000]}
```

```
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user.random")
@PropertySource(value = { "config/random.properties" })
public class RandomConfig {
	privateString secret;
	privateint intNumber;
	privateint lessTen;
	privateint range;
	privatelong longNumber;
	privateString uuid;

	publicString getSecret() {
		return secret;
	}

	publicvoid setSecret(String secret) {
		this.secret = secret;
	}

	publicint getIntNumber() {
		return intNumber;
	}

	publicvoid setIntNumber(int intNumber) {
		this.intNumber = intNumber;
	}

	publicint getLessTen() {
		return lessTen;
	}

	publicvoid setLessTen(int lessTen) {
		this.lessTen = lessTen;
	}

	publicint getRange() {
		return range;
	}

	publicvoid setRange(int range) {
		this.range = range;
	}

	publiclong getLongNumber() {
		return longNumber;
	}

	publicvoid setLongNumber(long longNumber) {
		this.longNumber = longNumber;
	}

	publicString getUuid() {
		return uuid;
	}

	publicvoid setUuid(String uuid) {
		this.uuid = uuid;
	}
}
```

