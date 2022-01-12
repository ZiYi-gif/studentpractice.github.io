package net.puge.student.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class StudentInfoQuery {


    @ApiModelProperty(value = "姓名 模糊查询")
    private String name;

    @ApiModelProperty(value = "性别")
    private String sex;
}
