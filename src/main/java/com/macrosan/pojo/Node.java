package com.macrosan.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node implements Serializable {
	private static final long serialVersionUID = 1712502090893946235L;
	private Integer id;
	private String name;
	private Integer parentId;
}
