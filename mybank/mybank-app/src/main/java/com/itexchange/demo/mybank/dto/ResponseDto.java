package com.itexchange.demo.mybank.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
	private StatusDto status;
	private String response;
}