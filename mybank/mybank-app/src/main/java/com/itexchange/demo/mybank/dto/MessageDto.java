package com.itexchange.demo.mybank.dto;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MessageDto {

	public static final String SUCCESS = "success";

	private String message = MessageDto.SUCCESS;

	@Getter(onMethod = @__(@JsonIgnore))
	private HttpStatus statusCode = HttpStatus.OK;

	public MessageDto(String message) {
		this.message = message;
	}

	public MessageDto(Boolean result) {
		if (result != null) {
			this.message = result.toString();
		}
	}
}
