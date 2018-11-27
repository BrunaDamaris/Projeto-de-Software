package iFace;
import java.util.ArrayList;

public class NewAccount {
	public static void addnew(ArrayList<Users> user,String newname, int newid, String newpassword, String newaccount){
		Users newuser = new Users();
		newuser.setUsername(newname);
		newuser.setId(newid);
		newuser.setMyAccont(newaccount);
		newuser.setPassword(newpassword);
		user.add(newuser);
	}
}
