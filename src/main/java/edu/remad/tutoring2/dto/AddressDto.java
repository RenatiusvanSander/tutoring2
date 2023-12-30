package edu.remad.tutoring2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddressDto {

	/**
	 * the street
	 */
	private String addressStreet;

	/**
	 * house number
	 */
	private String addressHouseNo;
}
