package com.itexchange.demo.mybank.domain.id;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = { "projectId", "departmentId" })
@AllArgsConstructor
@Embeddable
public class ProjectId implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer projectId;
	
	private Integer departmentId;
}
