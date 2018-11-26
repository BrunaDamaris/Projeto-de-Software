package iFace;

import java.util.ArrayList;

public class Users {
	private String Username;
	private int Id;
	private String MyAccount;
	private String Password;
	private String Status;
	private String Address;
	private String Birthday;
	private int NOfFriends = 0;
	private String YourCommunity;
	private ArrayList<String> Friends = new ArrayList<String>();
	private ArrayList<AddFriends> Requests = new ArrayList<AddFriends>();
	private ArrayList<Messages> MyMessages = new ArrayList<Messages>();
	private ArrayList<String> NamesofOwners = new ArrayList<String>();
	
	//User name
	public void setUsername(String name) {
		this.Username = name;
	}
	public String getUsername() {
		return Username;
	}
	
	//Id
	public void setId(int id) {
		this.Id = id;
	}
	public int getId() {
		return Id;
	}
	
	//Account
	public void setMyAccont(String newaccount) {
		this.MyAccount = newaccount;
	}
	public String getMyAccount() {
		return MyAccount;
	}
	
	//Password
	public void setPassword(String password) {
		this.Password = password;
	}
	public String getPass() {
		return Password;
	}
	
	//Status
	public void setStatus(String sts) {
		this.Status = sts;
	}
	public String getStatus() {
		return Status;
	}
	
	//Address
	public void setAddress(String endereco) {
		this.Address = endereco;
	}
	public String getAddress() {
		return Address;
	}
	
	//Birthday
	public void setBirthday(String aniversario) {
		this.Birthday = aniversario;
	}
	public String getBirthday() {
		return Birthday;
	}
	
	//Community
	public void setYourCommunity(String comunidade) {
		this.YourCommunity = comunidade;
	}
	public String getYourCommunity() {
		return YourCommunity;
	}
	
	
	//Friends
	public void setNOfFriends(int more) {
		this.NOfFriends += more;
	}
	public int getNOfFriends() {
		return NOfFriends;
	}
	
	public void setFriends(String Friend) {
		this.Friends.add(Friend);
	}
	public void removeFriend(String Friend) {
		if(Friends.contains(Friend)) {
			this.Friends.remove(Friend);
			this.NOfFriends -= 1;
		}
		else System.out.println("Não foi possivel remover, amigo nao existe");
	}
	public ArrayList<String> getFriends() {
		return Friends;
	}
	
	
	public void addRequests(String username,String friendusername) {
		AddFriends newrequests =  new AddFriends();
		newrequests.setSolicitor(username);
		newrequests.setReceiver(friendusername);
		this.Requests.add(newrequests);
	}
	public ArrayList<AddFriends> getRequests() {
		return Requests;
	}
	
	//Messages
	public void NewMessage(String message,String username,String friendusername) {
		Messages newm = new Messages();
		newm.setMessage(message);
		newm.setOwner(username);
		newm.setFriendReceiver(friendusername);
		this.MyMessages.add(newm);
	}
	public ArrayList<Messages> getMyMessages() {
		return MyMessages;
	}
	public void addtoNamesofOwners(String ownerusername) {
		if(!(this.NamesofOwners.contains(ownerusername))) {
			this.NamesofOwners.add(ownerusername);
		}
	}
	public ArrayList<String> getNamesofOwners(){
		return NamesofOwners;
	}
	
	//Find User Id
	public static int findUsersId(ArrayList<Users> user,String username) {
		int result = -1;
		for(int i = 0; i < user.size();i++) {
			 if(user.get(i).getUsername().equals(username)) {
				 result = user.get(i).getId();
			 }
		}
		return result;
	}
	
	//Verify
	public static int VerifyExistenceInSystem(ArrayList<Users> user,String username,String password) {
		int result = -1;
		for(int i = 0; i < user.size(); i++) {
		    if(user.get(i).getUsername().equals(username)) {
		    	if(user.get(i).getPass().equals(password)) {
		    		result = user.get(i).getId();
		    	}
		    }
		}
		return result;
		
	}
	
}
