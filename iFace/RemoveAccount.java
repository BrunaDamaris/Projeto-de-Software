package iFace;

import java.util.ArrayList;

public class RemoveAccount {
	public static void toRemoveTheUser(ArrayList<Users> user,ArrayList<Community> communities,String username,int userid) {
		int exitcommunities = -1;
		for(int i = 0;i < user.size();i++) {
			RemoveAccount.RemoveRequests(user.get(i).getRequests(),username);
		}
		RemoveAccount.RemoveFromFriendsList(user,user.get(userid).getFriends(),username,userid);
		for(int i = 0;i < communities.size();i++) {
			for(int j = 0;j < communities.get(i).getCommunityMembers().size();j++) {
				if(communities.get(i).getCommunityMembers().get(j).equals(username)) {
					exitcommunities = communities.get(i).getCommunityId();
				}
			}
			if(exitcommunities != -1) RemoveAccount.RemoveFromCommunity(communities,exitcommunities, username);
		}
		user.remove(userid);
		System.out.println("Conta removida com sucesso.");
	}

	public static void RemoveFromCommunity(ArrayList<Community> systemcommunities, int exitcurrentcommunityid,String username) {
		int currentcommunityid = -1;
		RemoveAccount.RemoveMessages(systemcommunities.get(exitcurrentcommunityid).getCommunityMessages(),username);
		if(systemcommunities.get(exitcurrentcommunityid).getCommunityAdmin().equals(username)) {
			for(int i = 0;i < systemcommunities.size();i++) {
				if(i > exitcurrentcommunityid) {
					currentcommunityid = systemcommunities.get(i).getCommunityId();
					systemcommunities.get(i).setCommunityId(currentcommunityid - 1);
				}
			}
			systemcommunities.remove(exitcurrentcommunityid);
		}
		else {
			for(int i = 0;i < systemcommunities.get(exitcurrentcommunityid).getCommunityMembers().size();i++) {
				if(systemcommunities.get(exitcurrentcommunityid).getCommunityMembers().get(i).equals(username)){
					systemcommunities.get(exitcurrentcommunityid).getCommunityMembers().remove(username);
				}
			}
		}
	}
	
	public static void RemoveMessages(ArrayList<Messages> messages,String username) {
		for(int i = 0;i < messages.size();i++) {
			if(messages.get(i).getOwner().equals(username)) {
				messages.remove(i);
			}
		}
		
	}
	public static void RemoveFromFriendsList(ArrayList<Users> user,ArrayList<String> Friends,String username,int userid) {
		String friend;
		int friendsid = -1;
		for(int i = 0;i < Friends.size();i++) {
			friend = Friends.get(i);
			for(int j = 0; j < user.size();j++) {
				if(user.get(j).getUsername().equals(friend)) {
					friendsid = user.get(j).getId();
				}
			}
			if(friendsid != -1) {
				Friends.remove(friend);
				user.get(friendsid).getFriends().remove(username);
				user.get(friendsid).setNOfFriends(-1);
				RemoveAccount.RemoveMessages(user.get(friendsid).getMyMessages(),username);
				if(friendsid > userid) user.get(friendsid).setId(friendsid-1);
			}
			friendsid = -1;
		}
	}
	public static void RemoveRequests(ArrayList<AddFriends> Requests,String username){
		for(int i = 0;i < Requests.size();i++) {
			if(Requests.get(i).getSolicitor().equals(username)) {
				Requests.remove(i);
			}
		}	
	}

}
