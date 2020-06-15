package com.macrosan.pojo;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class WorkOrder extends BasePojo{
	   private Integer id;
	   private String status;
	   private String name;
	   private String gdType;
	   private String principalUser;
}
