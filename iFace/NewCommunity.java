package iFace;
import java.util.ArrayList;

public class NewCommunity {
	public static void addnewCommunity(ArrayList<Community> community,int communityid,String nameofcommunity,String newdescription,String username) {
		Community newc = new Community();
		newc.setCommunityId(communityid);
		newc.setCommunityName(nameofcommunity);
		newc.setCommunityDescription(newdescription);
		newc.setCommunityAdmin(username);
		newc.addMembers(username);
		community.add(newc);
	}
}
