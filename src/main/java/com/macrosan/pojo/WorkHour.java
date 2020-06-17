package com.macrosan.pojo;

import com.macrosan.common.BasePojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkHour extends BasePojo{
	private static final long serialVersionUID = -6955051682446429490L;
	private Integer id;
	private Integer hours;
	private Integer relatedProjectId;
	private String note;
}
