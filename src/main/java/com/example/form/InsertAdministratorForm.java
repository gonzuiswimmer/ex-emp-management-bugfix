package com.example.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * 管理者情報登録時に使用するフォーム.
 * 
 * @author igamasayuki
 * 
 */
public class InsertAdministratorForm {
	/** 名前 */
	@NotBlank
	@Length(min = 2, max = 20)
	private String name;
	/** メールアドレス */
	@NotBlank
	@Email(message = "Email形式で入力してください")
	private String mailAddress;
	/** パスワード */
	@NotBlank
	@Pattern(regexp = "^[0-9a-zA-Z]+$", message = "パスワードは英数字で記載してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank
	private String passwordConf;

	@AssertTrue(message = "パスワードと確認用パスワードが一致しません")
	public boolean isPasswordValid(){
		if(password == null || password.isEmpty()){
			return false;
		}
		return password.equals(passwordConf);
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConf() {
		return passwordConf;
	}

	public void setPasswordConf(String passwordConf) {
		this.passwordConf = passwordConf;
	}

	@Override
	public String toString() {
		return "InsertAdministratorForm [name=" + name + ", mailAddress=" + mailAddress + ", password=" + password
				+ ", passwordConf=" + passwordConf + "]";
	}
}
