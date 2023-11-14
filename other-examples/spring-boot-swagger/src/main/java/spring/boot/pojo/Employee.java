package spring.boot.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yangjian
 * @date 2022/11/24 21:04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "员工实体类")
public class Employee {

    @ApiModelProperty(value = "域账号")
    private String account;

    @ApiModelProperty(value = "密码，即yyyymmdd格式生日")
    private String password;

    @ApiModelProperty(value = "姓")
    private String familyName;

    @ApiModelProperty(value = "名")
    private String firstName;

    @ApiModelProperty(value = "获取用户全名")
    public String getFullName() {
        return familyName + firstName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}