package com.itexchange.demo.mybank.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.itexchange.demo.mybank.domain.id.ProjectId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = { "projectId" })
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Project implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProjectId projectId;

	private String name;

	private String description;

	private String status;

	@Column(name = "created_date")
	private Timestamp createdDate;

	@Column(name = "termination_date")
	private Timestamp terminationDate;

	private BigDecimal budget;
}
