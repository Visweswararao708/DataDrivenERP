package applicatinLayer;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AdminLogin {
	// define Repositoryfor login
	@FindBy(name="txtUsername")
	WebElement ObjUser;
	@FindBy(name="txtPassword")
	WebElement objPass;
	@FindBy(name="btnLogin")
	WebElement ObjLogin;
	// write a methood
	public void verifyLogin(String user,String pass)
	{
		ObjUser.sendKeys(user);
		objPass.sendKeys(pass);
		ObjLogin.click();
	}

}
