package com.studytips.entities;

import com.studytips.dto.UserDTO;
import com.studytips.enums.UserProfile;
import com.studytips.enums.UserStatus;
import com.studytips.util.AuthoritiesUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by comp-dev on 4/13/17.
 *
 */
@Entity
@Table(name="tb_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User extends GenericEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String login;

	@JsonIgnore
	private String password;
	
	private String firstName;

	private String lastName;

	@Column(name = "birth_date")
	private Date birthDate;

	@Column(name = "save_date")
	private Date saveDate;

	@Column(name="user_status", nullable = false)
	private String status = UserStatus.ACTIVE.getNome();

	@Enumerated(value = EnumType.STRING)
	private UserProfile profile;

	@Column(name = "public_key")
	@JsonIgnore
	private String publicSecret;

	@Column(name = "private_key")
	@JsonIgnore
	private String privateSecret;

	public User() {
	}

	public User(UserDTO userDTO) {
		this.id = userDTO.getId();
		this.login = userDTO.getLogin();
		this.profile = userDTO.getProfile();
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(Date saveDate) {
		this.saveDate = saveDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public String getPublicSecret() {
		return publicSecret;
	}

	public void setPublicSecret(String publicSecret) {
		this.publicSecret = publicSecret;
	}

	public String getPrivateSecret() {
		return privateSecret;
	}

	public void setPrivateSecret(String privateSecret) {
		this.privateSecret = privateSecret;
	}

	public List<String> getAuthorities(){
		if( profile != null ){
			return AuthoritiesUtils.get(profile);
		}
		return null;
	}
}
