package com.capgemini.types;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private Date dateOfBirth;
	private String addres;
	private String phone;
}
