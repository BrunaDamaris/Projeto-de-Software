package iFace;

public class Messages {
	private String Message;
	private String Owner;
	private String FriendReceiver;

	public void setMessage(String newm) {
		this.Message = newm;
	}
	public String getMessage() {
		return Message;
	}
	
	public void setOwner(String username) {
		this.Owner = username;		
	}
	public String getOwner() {
		return Owner;
	}
	
	public void setFriendReceiver(String username2) {
		this.FriendReceiver = username2;
	}
	public String getFriendReceiver() {
		return FriendReceiver;
	}
	
}
