package edu.remad.tutoring2.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import edu.remad.tutoring2.appconstants.RegexAppConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UserEntity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Pattern(regexp = RegexAppConstants.USERNAME_REGEX)
	private String username;

	@NotBlank
	@Pattern(regexp = RegexAppConstants.EMAIL_REGEX)
	private String email;

	@NotBlank
	private String password;

	private Boolean enabled;

	@Default
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "user_rules", joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id") })
	private List<Role> roles = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private List<TokenEntity> tokens;
	
	@NotBlank
	@Max(256)
	@Pattern(regexp = RegexAppConstants.FIRST_NAME_REGEX)
	private String firstName;
	
	@NotBlank
	@Max(256)
	@Pattern(regexp = RegexAppConstants.LAST_NAME_REGEX)
	private String lastName;
	
	@NotBlank
	@Pattern(regexp = RegexAppConstants.GENDER_REGEX)
	private String gender;
	
	@NotBlank
	@Max(256)
	@Pattern(regexp = RegexAppConstants.CELL_PHONE_REGEX)
	private String cellPhone;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "userentity_id")
	private List<AddressEntity> addresses;
	
	@Column(name = "creation_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime creationDate;
}
