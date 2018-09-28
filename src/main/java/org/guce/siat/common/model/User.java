package org.guce.siat.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.apache.commons.lang.StringUtils;
import org.guce.siat.common.utils.Constants;
import org.guce.siat.common.utils.enums.PositionType;
import org.guce.siat.common.utils.enums.Theme;

/**
 * The Class User.
 */
@javax.persistence.Entity
@Table(name = "USERS", uniqueConstraints
		= {
			@UniqueConstraint(columnNames
					= {"LOGIN", "ENABLED", "DELETED"}) /*
											   * , @UniqueConstraint(columnNames = { "EMAIL", "ENABLED", "DELETED" })
		 */})
@XmlRootElement
public class User extends AbstractModel implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The id.
	 */
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	private Long id;

	/**
	 * The login.
	 */
	@Column(name = "LOGIN")
	private String login;

	/**
	 * The first name.
	 */
	@Column(name = "FIRST_NAME")
	private String firstName;

	/**
	 * The last name.
	 */
	@Column(name = "LAST_NAME")
	private String lastName;

	/**
	 * The password.
	 */
	@Column(name = "PASSWORD")
	private String password;

	/**
	 * The telephone.
	 */
	@Column(name = "TELEPHONE")
	private String telephone;

	/**
	 * The email.
	 */
	@Column(name = "EMAIL")
	private String email;

	/**
	 * The prefered language.
	 */
	@Column(name = "PREFERED_LANGUAGE")
	private String preferedLanguage;

	/**
	 * The enabled.
	 */
	@Column(name = "ENABLED")
	private Boolean enabled;

	/**
	 * The first login.
	 */
	@Column(name = "FIRST_LOGIN")
	private Boolean firstLogin;

	/**
	 * The deleted.
	 */
	@Column(name = "DELETED")
	private Boolean deleted;

	/**
	 * The account non expired.
	 */
	@Column(name = "ACCOUNT_NON_EXPIRED")
	private Boolean accountNonExpired;

	/**
	 * The account non locked.
	 */
	@Column(name = "ACCOUNT_NON_LOCKED")
	private Boolean accountNonLocked;

	/**
	 * The credentials non expired.
	 */
	@Column(name = "CREDENTIALS_NON_EXPIRED")
	private Boolean credentialsNonExpired;

	/**
	 * The attempts.
	 */
	@Column(name = "ATTEMPTS")
	private Integer attempts;

	/**
	 * The last attempts time.
	 */
	@Column(name = "LAST_ATTEMPTS_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAttemptsTime;

	/**
	 * The position.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "POSITION")
	private PositionType position;

	/**
	 * The theme.
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "THEME")
	private Theme theme;

	/**
	 * The administration.
	 */
	@ManyToOne
	@JoinColumn(name = "ADMINISTRATION_ID", referencedColumnName = "id")
	private Administration administration;

	/**
	 * The administration.
	 */
	@ManyToOne
	@JoinColumn(name = "ADMINISTRATION_EXTEND_ROLES", referencedColumnName = "id")
	private Administration administrationExtendRoles;

	/**
	 * The user authority list.
	 */
	@OneToMany(mappedBy = "user")
	private List<UserAuthority> userAuthorityList;

	/**
	 * The authorities string.
	 */
	@Transient
	private String authoritiesString;

	/**
	 * The authorities.
	 */
	@Transient
	private Collection<Authority> authorities;

	/**
	 * The authorities list.
	 */
	@Transient
	private List<String> authoritiesList;

	/**
	 * The delegated authority list.
	 */
	@Transient
	private List<Authority> delegatedAuthorityList;

	/**
	 * The combined authorities : user authorities with their delegated
	 * authorities.
	 */
	@Transient
	private List<Authority> mergedAuthoritiesList;

	/**
	 * The delegator list.
	 */
	@Transient
	private List<User> delegatorList;

	/**
	 * The merged delegator list.
	 */
	@Transient
	private List<User> mergedDelegatorList;

	/**
	 * The delegated user authority list.
	 */
	@Transient
	private List<UserAuthority> delegatedUserAuthorityList;

	/**
	 * The merged user authority list.
	 */
	@Transient
	private List<UserAuthority> mergedUserAuthorityList;

	/**
	 * Instantiates a new user.
	 */
	public User() {
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.guce.siat.common.model.AbstractModel#setId(java.lang.Long)
	 */
	@Override
	public void setId(final Long id) {
		this.id = id;
	}

	/**
	 * Gets the login.
	 *
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login.
	 *
	 * @param login the new login
	 */
	public void setLogin(final String login) {
		this.login = login;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(final String password) {
		this.password = password;
	}

	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * Sets the telephone.
	 *
	 * @param telephone the new telephone
	 */
	public void setTelephone(final String telephone) {
		this.telephone = telephone;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * Gets the prefered language.
	 *
	 * @return the prefered language
	 */
	public String getPreferedLanguage() {
		return preferedLanguage;
	}

	/**
	 * Sets the prefered language.
	 *
	 * @param preferedLanguage the new prefered language
	 */
	public void setPreferedLanguage(final String preferedLanguage) {
		this.preferedLanguage = preferedLanguage;
	}

	/**
	 * Gets the enabled.
	 *
	 * @return the enabled
	 */
	public Boolean getEnabled() {
		return enabled;
	}

	/**
	 * Sets the enabled.
	 *
	 * @param enabled the new enabled
	 */
	public void setEnabled(final Boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * Gets the first login.
	 *
	 * @return the first login
	 */
	public Boolean getFirstLogin() {
		return firstLogin;
	}

	/**
	 * Sets the first login.
	 *
	 * @param firstLogin the new first login
	 */
	public void setFirstLogin(final Boolean firstLogin) {
		this.firstLogin = firstLogin;
	}

	/**
	 * Gets the deleted.
	 *
	 * @return the deleted
	 */
	public Boolean getDeleted() {
		return deleted;
	}

	/**
	 * Sets the deleted.
	 *
	 * @param deleted the new deleted
	 */
	public void setDeleted(final Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Gets the account non expired.
	 *
	 * @return the accountNonExpired
	 */
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * Sets the account non expired.
	 *
	 * @param accountNonExpired the accountNonExpired to set
	 */
	public void setAccountNonExpired(final Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * Gets the account non locked.
	 *
	 * @return the accountNonLocked
	 */
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * Sets the account non locked.
	 *
	 * @param accountNonLocked the accountNonLocked to set
	 */
	public void setAccountNonLocked(final Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * Gets the credentials non expired.
	 *
	 * @return the credentialsNonExpired
	 */
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * Sets the credentials non expired.
	 *
	 * @param credentialsNonExpired the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(final Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * Gets the attempts.
	 *
	 * @return the attempts
	 */
	public Integer getAttempts() {
		return attempts;
	}

	/**
	 * Sets the attempts.
	 *
	 * @param attempts the new attempts
	 */
	public void setAttempts(final Integer attempts) {
		this.attempts = attempts;
	}

	/**
	 * Gets the last attempts time.
	 *
	 * @return the last attempts time
	 */
	public Date getLastAttemptsTime() {
		return lastAttemptsTime;
	}

	/**
	 * Sets the last attempts time.
	 *
	 * @param lastAttemptsTime the new last attempts time
	 */
	public void setLastAttemptsTime(final Date lastAttemptsTime) {
		this.lastAttemptsTime = lastAttemptsTime;
	}

	/**
	 * Gets the administration.
	 *
	 * @return the administration
	 */
	public Administration getAdministration() {
		return administration;
	}

	/**
	 * Sets the administration.
	 *
	 * @param administration the new administration
	 */
	public void setAdministration(final Administration administration) {
		this.administration = administration;
	}

	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public PositionType getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 *
	 * @param position the new position
	 */
	public void setPosition(final PositionType position) {
		this.position = position;
	}

	/**
	 * Gets the theme.
	 *
	 * @return the theme
	 */
	public Theme getTheme() {
		return theme;
	}

	/**
	 * Sets the theme.
	 *
	 * @param theme the theme to set
	 */
	public void setTheme(final Theme theme) {
		this.theme = theme;
	}

	/**
	 * Gets the user authority list.
	 *
	 * @return the user authority list
	 */
	public List<UserAuthority> getUserAuthorityList() {
		return userAuthorityList;
	}

	/**
	 * Sets the user authority list.
	 *
	 * @param userAuthorityList the new user authority list
	 */
	public void setUserAuthorityList(final List<UserAuthority> userAuthorityList) {
		this.userAuthorityList = userAuthorityList;
	}

	/**
	 * Gets the authorities string.
	 *
	 * @return the authoritiesString
	 */
	public String getAuthoritiesString() {
		if (StringUtils.isEmpty(authoritiesString)) {
			authoritiesString = Constants.EMPTY_STRING;

			if (userAuthorityList != null) {
				for (final UserAuthority userAuthority : userAuthorityList) {
					authoritiesString += userAuthority.getAuthority() + ", ";
				}
			}
		}

		return authoritiesString;
	}

	/**
	 * Sets the authorities string.
	 *
	 * @param authoritiesString the authoritiesString to set
	 */
	public void setAuthoritiesString(final String authoritiesString) {
		this.authoritiesString = authoritiesString;
	}

	/**
	 * Gets the authorities.
	 *
	 * @return the authorities
	 */
	public Collection<Authority> getAuthorities() {
		authorities = new HashSet<Authority>();

		if (CollectionUtils.isNotEmpty(userAuthorityList)) {
			for (final UserAuthority userAuthority : userAuthorityList) {
				authorities.add(userAuthority.getAuthorityGranted());
			}
		}
		return authorities;
	}

	/**
	 * Sets the authorities.
	 *
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(final Collection<Authority> authorities) {
		this.authorities = authorities;
	}

	/**
	 * Gets the authorities list.
	 *
	 * @return the authoritiesList
	 */
	@SuppressWarnings("unchecked")
	public List<String> getAuthoritiesList() {
		if (CollectionUtils.isEmpty(authoritiesList)) {
			authoritiesList = (List<String>) CollectionUtils.collect(userAuthorityList, new Transformer() {
				@Override
				public Object transform(final Object userAuthoriy) {
					return ((UserAuthority) userAuthoriy).getAuthorityGranted().getRole();
				}
			});
		}

		return authoritiesList;
	}

	/**
	 * Sets the authorities list.
	 *
	 * @param authoritiesList the authoritiesList to set
	 */
	public void setAuthoritiesList(final List<String> authoritiesList) {
		this.authoritiesList = authoritiesList;
	}

	/**
	 * Gets the delegated authority list.
	 *
	 * @return the delegatedAuthorityList
	 */
	public List<Authority> getDelegatedAuthorityList() {
		return delegatedAuthorityList;
	}

	/**
	 * Sets the delegated authority list.
	 *
	 * @param delegatedAuthorityList the delegatedAuthorityList to set
	 */
	public void setDelegatedAuthorityList(final List<Authority> delegatedAuthorityList) {
		this.delegatedAuthorityList = delegatedAuthorityList;
	}

	/**
	 * Gets the merged authorities list.
	 *
	 * @return the mergedAuthoritiesList
	 */
	public List<Authority> getMergedAuthoritiesList() {
		if (CollectionUtils.isEmpty(mergedAuthoritiesList)) {
			final Set<Authority> authoritiesSet = new HashSet<Authority>();

			authoritiesSet.addAll(this.getAuthorities());

			if (this.getDelegatedAuthorityList() != null) {
				authoritiesSet.addAll(this.getDelegatedAuthorityList());
			}

			mergedAuthoritiesList = new ArrayList<Authority>(authoritiesSet);
		}

		return mergedAuthoritiesList;
	}

	/**
	 * Sets the merged authorities list.
	 *
	 * @param mergedAuthoritiesList the mergedAuthoritiesList to set
	 */
	public void setMergedAuthoritiesList(final List<Authority> mergedAuthoritiesList) {
		this.mergedAuthoritiesList = mergedAuthoritiesList;
	}

	/**
	 * Gets the delegator list.
	 *
	 * @return the delegatorList
	 */
	public List<User> getDelegatorList() {
		return delegatorList;
	}

	/**
	 * Sets the delegator list.
	 *
	 * @param delegatorList the delegatorList to set
	 */
	public void setDelegatorList(final List<User> delegatorList) {
		this.delegatorList = delegatorList;
	}

	/**
	 * Gets the merged delegator list.
	 *
	 * @return the mergedDelegatorList
	 */
	public List<User> getMergedDelegatorList() {
		if (CollectionUtils.isEmpty(mergedDelegatorList)) {
			mergedDelegatorList = new ArrayList<User>();

			mergedDelegatorList.add(this);

			if (this.getDelegatorList() != null) {
				mergedDelegatorList.addAll(this.getDelegatorList());
			}
		}

		return mergedDelegatorList;
	}

	/**
	 * Sets the merged delegator list.
	 *
	 * @param mergedDelegatorList the mergedDelegatorList to set
	 */
	public void setMergedDelegatorList(final List<User> mergedDelegatorList) {
		this.mergedDelegatorList = mergedDelegatorList;
	}

	public Administration getAdministrationExtendRoles() {
		return administrationExtendRoles;
	}

	public void setAdministrationExtendRoles(Administration administrationExtendRoles) {
		this.administrationExtendRoles = administrationExtendRoles;
	}

	/**
	 * Gets the delegated user authority list.
	 *
	 * @return the delegatedUserAuthorityList
	 */
	public List<UserAuthority> getDelegatedUserAuthorityList() {
		return delegatedUserAuthorityList;
	}

	/**
	 * Sets the delegated user authority list.
	 *
	 * @param delegatedUserAuthorityList the delegatedUserAuthorityList to set
	 */
	public void setDelegatedUserAuthorityList(final List<UserAuthority> delegatedUserAuthorityList) {
		this.delegatedUserAuthorityList = delegatedUserAuthorityList;
	}

	/**
	 * Gets the merged user authority list.
	 *
	 * @return the mergedUserAuthorityList
	 */
	public List<UserAuthority> getMergedUserAuthorityList() {
		if (CollectionUtils.isEmpty(mergedUserAuthorityList)) {
			mergedUserAuthorityList = new ArrayList<UserAuthority>();

			mergedUserAuthorityList.addAll(this.userAuthorityList);

			if (this.getDelegatedUserAuthorityList() != null) {
				mergedUserAuthorityList.addAll(this.getDelegatedUserAuthorityList());
			}
		}

		return mergedUserAuthorityList;
	}

	/**
	 * Sets the merged user authority list.
	 *
	 * @param mergedUserAuthorityList the mergedUserAuthorityList to set
	 */
	public void setMergedUserAuthorityList(final List<UserAuthority> mergedUserAuthorityList) {
		this.mergedUserAuthorityList = mergedUserAuthorityList;
	}

	/**
	 * Gets the audit bad credentials string.
	 *
	 * @return the audit bad credentials string
	 */
	public String getAuditBadCredentialsString() {
		final StringBuilder builder = new StringBuilder();

		builder.append("User [login = ");
		builder.append(login);
		builder.append(", attempts = ");
		builder.append(attempts);
		builder.append(", lastAttemptsTime = ");
		builder.append(lastAttemptsTime);
		builder.append(", userLocked = ");
		builder.append(accountNonLocked ? "False" : "True");
		builder.append("]");

		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof User)) {
			return false;
		}
		final User other = (User) object;
		if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", login=");
		builder.append(login);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", position=");
		builder.append(position);
		builder.append("]");
		return builder.toString();
	}

}
