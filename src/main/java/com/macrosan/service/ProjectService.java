package com.macrosan.service;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Project;

public interface ProjectService {
	PageObject<Project> findObjects(String proName, Integer pageCurrent);
	void saveObject(Project projet);
	void deleteObject(Integer id);
	Project findObjectById(Integer id);
	int updateObject(Project project);
}
