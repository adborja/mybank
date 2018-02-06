package com.itexchange.demo.mybank.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.itexchange.demo.mybank.domain.Branch;
import com.itexchange.demo.mybank.domain.DigitalBranch;
import com.itexchange.demo.mybank.domain.PhysicalBranch;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BranchDAOTest {
	@Autowired
	private TestEntityManager testEntityManager;

	private BranchDAO branchDAO;

	@Before
	public void before() {
		branchDAO = new BranchDAO();
		branchDAO.setEntityManager(testEntityManager.getEntityManager());
	}

	@Test
	public void testSaveBranch() {
		// Creating digital branch
		DigitalBranch digitalBranchPeople = new DigitalBranch();
		digitalBranchPeople.setName("digital_for_people");
		digitalBranchPeople.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		digitalBranchPeople.setDescription("Digital branch for people");
		digitalBranchPeople.setStatus("ACTIVE");
		digitalBranchPeople.setUrl("www.people.mybank.com");

		DigitalBranch digitalBranchCompanies = new DigitalBranch();
		digitalBranchCompanies.setName("digital_for_companies");
		digitalBranchCompanies.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		digitalBranchCompanies.setDescription("Digital branch for companies");
		digitalBranchCompanies.setStatus("ACTIVE");
		digitalBranchCompanies.setUrl("www.companies.mybank.com");

		// Creating physical branch
		PhysicalBranch physicalBranch = new PhysicalBranch();
		physicalBranch.setName("unicentro");
		physicalBranch.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		physicalBranch.setDescription("Unicentro branch");
		physicalBranch.setStatus("ACTIVE");
		physicalBranch.setAddress("Cra. 66b #34A - 76");
		physicalBranch.setPhone("+5745678900");
		physicalBranch.setOpensAt(LocalTime.of(9, 0, 0));
		physicalBranch.setClosesAt(LocalTime.of(17, 0, 0));

		// Saving branches
		branchDAO.save(digitalBranchPeople);
		branchDAO.save(digitalBranchCompanies);
		branchDAO.save(physicalBranch);

		// Finding all branches
		List<Branch> branches = branchDAO.findAll();

		// asserts
		assertThat(branches).isNotEmpty();
		assertThat(branches.size()).isEqualTo(3);
		assertThat(branches.get(0)).isInstanceOf(DigitalBranch.class);
		assertThat(branches.get(1)).isInstanceOf(DigitalBranch.class);
		assertThat(branches.get(2)).isInstanceOf(PhysicalBranch.class);
	}

	@Test
	public void testFindAllDigitalBranches() {
		// Creating digital branch
		DigitalBranch digitalBranchPeople = new DigitalBranch();
		digitalBranchPeople.setName("digital_for_people");
		digitalBranchPeople.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		digitalBranchPeople.setDescription("Digital branch for people");
		digitalBranchPeople.setStatus("ACTIVE");
		digitalBranchPeople.setUrl("www.people.mybank.com");

		DigitalBranch digitalBranchCompanies = new DigitalBranch();
		digitalBranchCompanies.setName("digital_for_companies");
		digitalBranchCompanies.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		digitalBranchCompanies.setDescription("Digital branch for companies");
		digitalBranchCompanies.setStatus("ACTIVE");
		digitalBranchCompanies.setUrl("www.companies.mybank.com");

		// Saving branches
		branchDAO.save(digitalBranchPeople);
		branchDAO.save(digitalBranchCompanies);

		// Finding all digital branches
		List<DigitalBranch> digitalBranches = branchDAO.findAllDigitalBranches();

		// asserts
		assertThat(digitalBranches).isNotEmpty();
		assertThat(digitalBranches.size()).isEqualTo(2);
		assertThat(digitalBranches.get(0).getUrl()).isEqualTo("www.people.mybank.com");
		assertThat(digitalBranches.get(1).getUrl()).isEqualTo("www.companies.mybank.com");
	}

	@Test
	public void testFindAllPhysicalBranches() {
		// Creating physical branches
		PhysicalBranch physicalBranch1 = new PhysicalBranch();
		physicalBranch1.setName("unicentro");
		physicalBranch1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		physicalBranch1.setDescription("Unicentro branch");
		physicalBranch1.setStatus("ACTIVE");
		physicalBranch1.setAddress("Cra. 66b #34A - 76");
		physicalBranch1.setPhone("+5745678900");
		physicalBranch1.setOpensAt(LocalTime.of(9, 0, 0));
		physicalBranch1.setClosesAt(LocalTime.of(17, 0, 0));

		PhysicalBranch physicalBranch2 = new PhysicalBranch();
		physicalBranch2.setName("coltejer");
		physicalBranch2.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		physicalBranch2.setDescription("Coltejer branch");
		physicalBranch2.setStatus("ACTIVE");
		physicalBranch2.setAddress("Cl. 52 # 47 - 42");
		physicalBranch2.setPhone("+5744512007");
		physicalBranch2.setOpensAt(LocalTime.of(9, 30, 0));
		physicalBranch2.setClosesAt(LocalTime.of(17, 0, 0));

		// Saving branches
		branchDAO.save(physicalBranch1);
		branchDAO.save(physicalBranch2);

		// Finding all physical branches
		List<PhysicalBranch> physicalBranches = branchDAO.findAllPhysicalBranches();

		// asserts
		assertThat(physicalBranches).isNotEmpty();
		assertThat(physicalBranches.size()).isEqualTo(2);
		assertThat(physicalBranches.get(0).getPhone()).isEqualTo("+5745678900");
		assertThat(physicalBranches.get(1).getPhone()).isEqualTo("+5744512007");
	}
}
