package com.itexchange.demo.mybank.dao;

import java.math.BigDecimal;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.Project;
import com.itexchange.demo.mybank.domain.id.ProjectId;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectDAOTest {

	@Autowired
	private TestEntityManager testEntityManager;

	private ProjectDAO projectDAO;

	@Before
	public void before() {
		projectDAO = new ProjectDAO();
		projectDAO.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testSave() {
		Project project = Project.builder()
				.projectId(ProjectId.builder()
						.projectId(80302)
						.departmentId(101).build())
				.budget(BigDecimal.valueOf(20000))
				.createdDate(new Timestamp(System.currentTimeMillis()))
				.description("some_description")
				.name("some_name")
				.status("some_status")
				.build();
		projectDAO.save(project);
		
		// Finding the project
		ProjectId projectId = new ProjectId(80302, 101);
		Project found = projectDAO.findByPrimaryKey(projectId);
		
		// asserts
		assertThat(found).isNotNull();
		assertThat(found.getProjectId()).isNotNull();
		assertThat(found.getProjectId().getProjectId()).isEqualTo(80302);
		assertThat(found.getProjectId().getDepartmentId()).isEqualTo(101);
		assertThat(found.getName()).isEqualTo("some_name");
	}
}
