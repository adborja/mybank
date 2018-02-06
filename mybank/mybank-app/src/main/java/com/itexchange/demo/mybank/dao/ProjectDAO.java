package com.itexchange.demo.mybank.dao;

import javax.transaction.Transactional;

import com.itexchange.demo.mybank.domain.Project;
import com.itexchange.demo.mybank.domain.id.ProjectId;

public class ProjectDAO extends BaseDAO {

	@Transactional
	public Project save(Project project) {
		entityManager.persist(project);
		return project;
	}
	
	public Project findByPrimaryKey(ProjectId id) {
		return entityManager.find(Project.class, id);
	}
}
