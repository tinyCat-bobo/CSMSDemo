package com.macrosan.pojo;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BasePojo {
	private static final long serialVersionUID = 6666194691203822284L;
	private Integer id;
	private String username;
	private String password;
	private String salt;
	private Integer superiorId;
	private Integer mobile;
	private Boolean valid;
	private String deptName;
}
