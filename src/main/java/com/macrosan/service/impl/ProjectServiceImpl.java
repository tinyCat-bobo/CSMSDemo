package com.macrosan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.macrosan.common.PageObject;
import com.macrosan.common.exception.ServiceException;
import com.macrosan.mapper.ProjectMapper;
import com.macrosan.pojo.Project;
import com.macrosan.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService{
	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public PageObject<Project> findObjects(String proName, Integer pageCurrent) {
		if(pageCurrent == null || pageCurrent < 1) throw new IllegalArgumentException("当前页码不正确");
		int rowCount = projectMapper.getRowCount(proName);
		if(rowCount == 0) {
			throw new ServiceException("未找到相关记录");
		};
		int pageSize = 5;
		int startIndex = (pageCurrent-1)*pageSize;
		List<Project> projectsList = projectMapper.findObjects(proName,startIndex,pageSize);
		PageObject<Project> pageobject = new PageObject<>();
		pageobject.setPageCurrent(pageCurrent)
				.setPageSize(pageSize)
				.setRecords(projectsList)
				.setRowCount(rowCount)
				.setPageCount((rowCount-1)/pageSize+1);
		return pageobject;
	}

	@Override
	public void saveObject(Project projet) {
		//参数校验
		if(StringUtils.isEmpty(projet.getName())) {
			throw new IllegalArgumentException("项目名字不能为空");
		}
		if(StringUtils.isEmpty(projet.getServiceDept())) {
			throw new IllegalArgumentException("服务落地部门不能为空");
		}
		projectMapper.saveObject(projet);
	}

	@Override
	public void deleteObject(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("id参数不合法");
		}
		projectMapper.deleteObject(id);
	}

	@Override
	public Project findObjectById(Integer id) {
		if(id == null || id < 1) {
			throw new IllegalArgumentException("参数不合法");
		}
		Project project = projectMapper.findObjectById(id);
		if(project == null) {
			throw new ServiceException("项目可能已经不存在");
		}
		return project;
	}

	@Override
	public int updateObject(Project project) {
		if(project == null || StringUtils.isEmpty(project.getName())) {
			throw new IllegalArgumentException("名字不能为空");
		}
		if(StringUtils.isEmpty(project.getProWeihu()) || StringUtils.isEmpty(project.getProType()) || StringUtils.isEmpty(project.getServiceDept())) {
			throw new IllegalArgumentException("参数不能为空");
		}
		int row = projectMapper.updateObject(project);
		return row;
	}

	@Override
	public List<Project> findObjectByName(String proName) {
		if(StringUtils.isEmpty(proName)) {
			throw new IllegalArgumentException("项目名字不能为空");
		}
		List<Project> projectList = projectMapper.findObjectsByName(proName);
		if(projectList == null) {
			throw new ServiceException("无法查找到相关项目");
		}
		return projectList;
	}

}
