package iFace;
import java.util.Scanner;
import java.util.ArrayList;

public class Create_EditProfile {
	public static void yourprofile(ArrayList<Users> user,ArrayList<Community> systemcommunities,int idnow,Scanner input) {
		//Scanner input = new Scanner(System.in);
		String entry,entry1,entry2,entry3,entry4,entry5,newi,username;
		int ismemberofcommunity = -1,exitcurrentcommunityid = -1;
		
		if(user.contains(user.get(idnow))){
			
			//Change User name
			System.out.println("Seu nome atual é: " + user.get(idnow).getUsername());
			System.out.println("Deseja mudar seu nome?Se sim, pressione 1. Se não, pressione enter");
			entry = input.nextLine();
			if(entry.equals("1")) {
				newi = input.nextLine();
				user.get(idnow).setUsername(newi);
			}
			
			//Change Password
			System.out.println("Sua senha atual é: " + user.get(idnow).getPass());
			System.out.println("Deseja mudar sua senha?Se sim, pressione 1. Se não, pressione enter");
			entry1 = input.nextLine();
			if(entry1.equals("1")) {
				newi = input.nextLine();
				user.get(idnow).setPassword(newi);
			}
			
			//Change Status
			if(user.get(idnow).getStatus() != null) System.out.println("Seu Status atual é: " + user.get(idnow).getStatus());
			else System.out.println("Usuario ainda não tem Status");
			System.out.println("Deseja modificar/adicionar seu Status?Se sim, pressione 1. Se não, pressione enter");
			entry2 = input.nextLine();
			if(entry2.equals("1")) {
				newi = input.nextLine();
				user.get(idnow).setStatus(newi);
			}
			
			//Change Address
			if(user.get(idnow).getAddress() != null) System.out.println("Seu endereco atual é: " + user.get(idnow).getAddress());
			else System.out.println("Usuario ainda não tem Endereco");
			System.out.println("Deseja modificar/adicionar endereco?Se sim, pressione 1. Se não, pressione enter");
			entry3 = input.nextLine();
			if(entry3.equals("1")) {
				newi = input.nextLine();
				user.get(idnow).setAddress(newi);
			}
			
			//Change Birthday
			if(user.get(idnow).getBirthday() != null) System.out.println("Sua data de nascimento atual é: "+ user.get(idnow).getBirthday());
			else System.out.println("Usuario ainda não tem Aniversario");
			System.out.println("Deseja modificar/adicionar data de nascimento?Se sim, pressione 1. Se não, pressione enter");
			entry4 = input.nextLine();
			if(entry4.equals("1")) {
				newi = input.nextLine();
				user.get(idnow).setBirthday(newi);
			}
			
			//See/Exit Community
			System.out.println("Suas Comunidades: ");
			username = user.get(idnow).getUsername();
			for(int i = 0;i < systemcommunities.size();i++) {
				for(int j = 0;j < systemcommunities.get(i).getCommunityMembers().size();j++) {
					if(systemcommunities.get(i).getCommunityMembers().get(j).equals(username)) {
						System.out.println(systemcommunities.get(i).getCommunityName());
						ismemberofcommunity++;
					}
				}
			}
			if(ismemberofcommunity == -1) {
				System.out.println("Nao faz parte de nenhuma comunidade");
			}
			else {
				System.out.println("Deseja sair de alguma comunidade? Se sim, pressione 1. Se não, pressione enter");
				entry5 = input.nextLine();
				if(entry5.equals("1")) {
					String exitcommunity;
					System.out.println("Informe o nome da comunidade na qual deseja sair: ");
					exitcommunity = input.nextLine();
					for(int i = 0;i < systemcommunities.size();i++) {
						if(systemcommunities.get(i).getCommunityName().equals(exitcommunity)) {
							for(int j = 0;j < systemcommunities.get(i).getCommunityMembers().size();j++) {
								if(systemcommunities.get(i).getCommunityMembers().get(j).equals(username)) {
									exitcurrentcommunityid = systemcommunities.get(i).getCommunityId();
								}
							}
						}
					}
					if(exitcurrentcommunityid == -1) {
						System.out.println("Comunidade nao existe ou nao e membro");
					}
					else {
						String decision;
						System.out.println("Deseja sair da comunidade? Se sim ,pressione 1. Se não, pressione enter");
						decision = input.nextLine();
						if(decision.equals("1")) {
							RemoveAccount.RemoveFromCommunity(systemcommunities,exitcurrentcommunityid,username);
						}
					}
				}
			}
		}
		else {
			System.out.println("Não foi possivel achar sua conta");
		}
	}
}
