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
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import edu.remad.ical4jbuilder.models.Address;
import edu.remad.ical4jbuilder.models.IRole;
import edu.remad.tutoring2.appconstants.RegexAppConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "userentity")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity implements edu.remad.ical4jbuilder.models.User {

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
	//@Pattern(regexp = RegexAppConstants.FIRST_NAME_REGEX)
	private String firstName;
	
	@NotBlank
	//@Pattern(regexp = RegexAppConstants.LAST_NAME_REGEX)
	private String lastName;
	
	@NotBlank
	@Pattern(regexp = RegexAppConstants.GENDER_REGEX)
	private String gender;
	
	@NotBlank
	//@Pattern(regexp = RegexAppConstants.CELL_PHONE_REGEX)
	private String cellPhone;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "userentity_id")
	private List<AddressEntity> addresses;
	
	@Column(name = "creation_date", columnDefinition = "TIMESTAMP")
	private LocalDateTime creationDate;

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUsername(String username) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getEnabled() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEnabled(Boolean enabled) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IRole> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRoles(List<IRole> roles) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getGender() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGender(String gender) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCellPhone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCellPhone(String cellPhone) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Address> getAddresses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAddresses(List<Address> addresses) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public LocalDateTime getCreationDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCreationDate(LocalDateTime creationDate) {
		// TODO Auto-generated method stub
		
	}
}
