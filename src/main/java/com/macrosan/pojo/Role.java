package com.macrosan.pojo;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BasePojo {
	private static final long serialVersionUID = 6838702042446135610L;
	private Integer id;
	private String name;
	private String note;
}
