package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Community {
	private ArrayList<Messages> CommunityMessages = new ArrayList<Messages>();
	private ArrayList<String> CommunityMembers = new ArrayList<String>();
	private int Communityid;
	private String CommunityAdmin;
	private String CommunityName;
	private String CommunityDescription;
	
	//Messages
	public void addCommunityMessages(String message,String username) {
		Messages newm = new Messages();
		newm.setMessage(message);
		newm.setOwner(username);
		newm.setFriendReceiver("Todos");
		this.CommunityMessages.add(newm);
	}
	public ArrayList<Messages> getCommunityMessages(){ 
		return CommunityMessages;
	}
	
	//Id
	public void setCommunityId(int id) {
		this.Communityid = id;
	}
	public int getCommunityId() {
		return Communityid;
	}
	
	//Administrator
	public void setCommunityAdmin(String username) {
		this.CommunityAdmin = username;
	}
	public String getCommunityAdmin() {
		return CommunityAdmin;
	}
	
	//Members
	public void addMembers(String username) {
		if(!(CommunityMembers.contains(username))) {
			this.CommunityMembers.add(username);
			System.out.println("Usuario adicionado!");
		}
		else System.out.println("Esse usuario ja e membro!");
	}
	public ArrayList<String> getCommunityMembers(){
		return CommunityMembers;
	}
	
	//Name
	public void setCommunityName(String Cname) {
		this.CommunityName = Cname;
	}
	public String getCommunityName() {
		return CommunityName;
	}
	
	//Description
	public void setCommunityDescription(String description) {
		this.CommunityDescription = description;
	}
	public String getCommunityDescription() {
		return CommunityDescription;
	}
	
	//CommunityOptions
	//Administrator
	public static void CommunityOptionsSelectAdmin(String option,ArrayList<Community> communities,ArrayList<Users> user,int communityid,String username,Scanner input) {
		if(option.equals("1")) {
			System.out.println(communities.get(communityid).getCommunityMembers());
		}
		else if(option.equals("2")) {
			String newmember;
			System.out.println("Informe o nome do membro que deseja adicionar: ");
			newmember = input.nextLine();
			communities.get(communityid).addMembers(newmember);
			communities.get(communityid).addCommunityMessages(newmember + " foi adicionado", "Admin Status Message");
		}
		else if(option.equals("3")) {
			String removemember;
			int removevalidy = -1;
			System.out.println("Informe o nome do membro que deseja remover: ");
			removemember = input.nextLine();
			for(int i = 0;i < communities.get(communityid).getCommunityMembers().size();i++) {
				if(communities.get(communityid).getCommunityMembers().get(i).equals(removemember)) {
					communities.get(communityid).getCommunityMembers().remove(removemember);
					System.out.println("Usuario removido!");
					communities.get(communityid).addCommunityMessages(removemember + " foi removido", "Admin Status Message");
					removevalidy++;
				}
			}
			if(removevalidy == -1) {
				System.out.println("Nao foi possivel remover.");
			}
		}
		else if(option.equals("4")) {
			String communitymessage;
			System.out.println("Sua Mensagem: ");
			communitymessage = input.nextLine();
			communities.get(communityid).addCommunityMessages(communitymessage, username);
		}
		else if(option.equals("5")) {
			if(communities.get(communityid).getCommunityMessages().size() != 0) {
				System.out.println("Mensagens: ");
				for(int i  = 0;i < communities.get(communityid).getCommunityMessages().size();i++) {
					System.out.print(communities.get(communityid).getCommunityMessages().get(i).getOwner() + ": ");
					System.out.println(communities.get(communityid).getCommunityMessages().get(i).getMessage());
				}
			}
			else System.out.println("Nao ha mensagens.");
		}
	}
	//Normal Members
	public static void CommunityOptionsSelect(String option,ArrayList<Community> communities,int communityid,String username,Scanner input) {
		if(option.equals("1")) {
			System.out.println(communities.get(communityid).getCommunityMembers());
		}
		else if(option.equals("2")) {
			String communitymessage;
			System.out.println("Sua Mensagem: ");
			communitymessage = input.nextLine();
			communities.get(communityid).addCommunityMessages(communitymessage, username);
		}
		else if(option.equals("3")) {
			if(communities.get(communityid).getCommunityMessages().size() != 0) {
				System.out.println("Mensagens: ");
				for(int i  = 0;i < communities.get(communityid).getCommunityMessages().size();i++) {
					System.out.print(communities.get(communityid).getCommunityMessages().get(i).getOwner() + ": ");
					System.out.println(communities.get(communityid).getCommunityMessages().get(i).getMessage());
				}
			}
			else System.out.println("Nao ha mensagens.");
		}
		else if(option.equals("4")) {
			System.out.println("Administrador:" + communities.get(communityid).getCommunityAdmin());
		}
	}
	
}
