package iFace;
import java.util.Scanner;
import java.util.ArrayList;

public class PSystem {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Users> user = new ArrayList<Users>();
		ArrayList<Community> systemcommunities = new ArrayList<Community>();
		String first_entry,entry1;
		String name = null,password = null,newaccount = null;
		int newid = -1,yourid = -1,verify = -1;
		while(true){
			System.out.println("Já tem uma conta?(pressione 1). Ou Deseja criar uma conta?(pressione 2) ou fechar?(pressione 3)");
			first_entry = input.nextLine();
			
			if(first_entry.equals("3")) {
				System.out.println("Fim!");
				break;
			}
			else if(first_entry.equals("1")) {
				System.out.println("Informe seu nome:");
				name = input.nextLine();
				System.out.println("Informe sua senha:");
				password = input.nextLine();
				verify = Users.VerifyExistenceInSystem(user,name,password);
				if(verify == -1) {
					System.out.println("Conta nao existe!");
					break;
				}
			}
			else if(first_entry.equals("2")) {
				System.out.println("Digite seu novo nome de Perfil:");

				while(true) {
					int validate = 0;
					String exit;
					name = input.nextLine();
					for(int i = 0; i < user.size(); i++) {
						if(user.get(i).getUsername().equals(name)) {
							System.out.println("Nome ja existe!Pressione 1 para escolher novamente!. Ou pressione 2 para sair");
							exit = input.nextLine();
							if(exit.equals("1")) {
								validate++;
							}
						}
					}
					if(validate == 0) break;
				}
				System.out.println("Digite sua nova identificaçao de conta: ");
				while(true) {
					int validate = 0;
					newaccount = input.nextLine();
					for(int i = 0; i < user.size(); i++) {
						if(user.get(i).getMyAccount().equals(newaccount)) {
							System.out.println("Identificacao ja existe! Escolha novamente!");
							validate++;
						}
					}
					if(validate == 0) break;
				}
				newid += 1;
				System.out.println("Digite sua nova senha:");
				password = input.nextLine();
				NewAccount.addnew(user,name,newid,password,newaccount);
				System.out.println("Bem vindo " + name + "!" );
			}
			
			while(true) {
				System.out.println("\nOpções :\n\nCriar/Editar Perfil(1)\n\nAdicione Amigos(2)\n\nSeus Amigos(3)\n\nRemover Amigo(4)\n\nSolicitaçoes(5)\n\nEnviar Mensagem(6)\n\nSuas Mensagens(7)\n\nCrie uma Comunidade(8)\n\nEntre em sua comunidade(9)\n\nInformacoes de Usuario(10)\n\nExcluir Minha Conta(nao pode ser desfeito)(11)\n\nSair(12)");
				
				entry1 = input.nextLine();
				for(int i = 0; i < user.size(); i++) {
				    if(user.get(i).getUsername().equals(name)) {
				    	if(user.get(i).getPass().equals(password)) {
				    		yourid = user.get(i).getId();
				    	}
				    }
				}
				if(yourid == -1) {
					System.out.println("Nao foi possivel achar a conta");
					break;
				}
				
				//Option 1
				if(entry1.equals("1") ) {
					Create_EditProfile.yourprofile(user,systemcommunities,yourid,input);
				}
				
				//Option 2
				else if(entry1.equals("2") ) {
					System.out.println("Informe o nome do amigo que deseja adicionar :");
					String friend;
					int friendsid = -1;
					int hasfriend = 0;
					friend = input.nextLine();
					
					friendsid = Users.findUsersId(user,friend);
					
					if(friendsid == -1) {
						System.out.println("Esse usuario nao existe, nao pode ser adicionado!");
					}
					if(user.get(yourid).getFriends().contains(friend)) {
						System.out.println("Esse usuario ja e seu amigo!");
						hasfriend++;
					}
					if(hasfriend == 0) {
						user.get(friendsid).addRequests(name,user.get(friendsid).getUsername());
						hasfriend = 0;
						System.out.println("Solicitacao enviada a " + friend);
					}
				}
				
				//Option 3
				else if(entry1.equals("3") ) {
					if(user.get(yourid).getFriends().size() >= 1) {
						System.out.println(user.get(yourid).getFriends());
					}
					else System.out.println("Nao ha amigos!");
				}
				
				//Option 4
				else if(entry1.equals("4") ) {
					String friendtoremove;
					int idoffriendtoremove = -1;
					System.out.println("Informe o amigo que deseja remover: ");
					friendtoremove = input.nextLine();
					
					idoffriendtoremove = Users.findUsersId(user,friendtoremove);

					user.get(yourid).removeFriend(friendtoremove);
					user.get(idoffriendtoremove).removeFriend(name);
					System.out.println(friendtoremove + " foi removido!");
				}
				
				//Option 5
				else if(entry1.equals("5") ) {
					String friendsrequest;
					int idoffriend = -1;
					if(user.get(yourid).getRequests().size() != 0) {
						for(int i = 0; i < user.get(yourid).getRequests().size();i++) {
							System.out.println(user.get(yourid).getRequests().get(i).getSolicitor() + " deseja adicionar voce. Para confirmar pressione 1, para rejeitar pressione 2");
							friendsrequest = input.nextLine();
							
							idoffriend = Users.findUsersId(user,user.get(yourid).getRequests().get(i).getSolicitor());
							
							if(idoffriend == -1) {
								System.out.println("Esse usuario nao existe.");
								break;
							}
							if(user.get(yourid).getFriends().contains(user.get(idoffriend).getUsername())) {
								System.out.println("Esse usuario ja e seu amigo!");
								break;
							}
							if(friendsrequest.equals("1")) {
								user.get(yourid).setFriends(user.get(idoffriend).getUsername());
								user.get(idoffriend).setFriends(name);
								user.get(yourid).setNOfFriends(1);
								user.get(idoffriend).setNOfFriends(1);
								System.out.println(user.get(idoffriend).getUsername() + " adicionado");
								user.get(yourid).getRequests().remove(i);
							}
							else if(friendsrequest.equals("2")) {
								user.get(yourid).getRequests().remove(i);
								System.out.println(user.get(idoffriend).getUsername() + " rejeitado");
							}
						}
					}
					else System.out.println("Nao ha solicitaçoes no momento.");
				}
				
				//Option 6
				else if(entry1.equals("6") ) {
					String nameoffriendmessage,message;
					int receiverid = -1;
					System.out.println("Informe o usuario a quem deseja mandar a mensagem: ");
					nameoffriendmessage = input.nextLine();
					
					receiverid = Users.findUsersId(user,nameoffriendmessage);

					if(receiverid == -1) {
						System.out.println("Esse usuario nao existe");
					}
					else {
						System.out.println("Sua mensagem:");
						message = input.nextLine();
						user.get(receiverid).NewMessage(message,name,nameoffriendmessage);
						user.get(receiverid).addtoNamesofOwners(name);
						user.get(yourid).NewMessage(message,name,nameoffriendmessage);
					}
				}
				
				//Option 7
				else if(entry1.equals("7") ) {
					String seemessage;
					if(user.get(yourid).getMyMessages().size() != 0) {
						System.out.println("Voce tem mensagens de: ");
						System.out.println(user.get(yourid).getNamesofOwners());
						System.out.println("Deseja visualizar alguma dessas mensagens? Se sim, informe o nome do usuario. Se nao, pressione enter");
						seemessage = input.nextLine();
						user.get(yourid).getNamesofOwners().remove(seemessage);
						for(int i  = 0;i < user.get(yourid).getMyMessages().size();i++) {
							if(user.get(yourid).getMyMessages().get(i).getOwner().equals(seemessage) || (user.get(yourid).getMyMessages().get(i).getOwner().equals(name) && user.get(yourid).getMyMessages().get(i).getFriendReceiver().equals(seemessage))) {
								System.out.print(user.get(yourid).getMyMessages().get(i).getOwner() + ": ");
								System.out.println(user.get(yourid).getMyMessages().get(i).getMessage());
							}
						}
					}
					else System.out.println("Nao ha mensagens.");
				}
				
				//Option 8
				else if(entry1.equals("8") ) {
					int communityverify = -1,communityid = -1;
					String yournewcommunity,newdescription;
					System.out.println("Informe o nome da nova comunidade: ");
					yournewcommunity = input.nextLine();
					System.out.println("Descricao da sua nova comunidade: ");
					newdescription = input.nextLine();
					for(int i = 0;i < systemcommunities.size();i++) {
						if(systemcommunities.get(i).getCommunityName().equals(yournewcommunity)) {
							System.out.println("Comunidade ja existe!");
							communityverify++;
							break;
						}
					}
					if(communityverify == -1) {
						communityid++;
						NewCommunity.addnewCommunity(systemcommunities,communityid,yournewcommunity,newdescription,name);
						communityverify = -1;
						System.out.println("Comunidade criada com sucesso");
					}
				}
				
				//Option 9
				else if(entry1.equals("9") ) {
					String community;
					int ismember = -1, currentcommunityid = -1;
					System.out.println("Suas comunidades: ");
					for(int i = 0;i < systemcommunities.size();i++) {
						for(int j = 0;j < systemcommunities.get(i).getCommunityMembers().size();j++) {
							if(systemcommunities.get(i).getCommunityMembers().get(j).equals(name)) {
								System.out.println(systemcommunities.get(i).getCommunityName());
								ismember++;
							}
						}
					}
					if(ismember == -1) {
						System.out.println("Nao faz parte de nenhuma comunidade");
					}
					else {
						System.out.println("Qual de suas comunidades deseja visualizar agora?");
						community = input.nextLine();
						for(int i = 0;i < systemcommunities.size();i++) {
							if(systemcommunities.get(i).getCommunityName().equals(community)) {
								for(int j = 0;j < systemcommunities.get(i).getCommunityMembers().size();j++) {
									if(systemcommunities.get(i).getCommunityMembers().get(j).equals(name)) {
										currentcommunityid = systemcommunities.get(i).getCommunityId();
									}
								}
							}
						}
						if(currentcommunityid == -1) {
							System.out.println("Comunidade nao existe ou nao e membro");
						}
						else {
							System.out.println("Descricao de " + systemcommunities.get(currentcommunityid).getCommunityName() + ": " + systemcommunities.get(currentcommunityid).getCommunityDescription());
							String communitysoptions;
							if(systemcommunities.get(currentcommunityid).getCommunityAdmin().equals(name)) {
								System.out.println("Voce é o administrador!");
								while(true) {
									System.out.println("\nVer Membros(1)\n\nAdicionar Membro(2)\n\nRetirar Membro(3)\n\nEnviar Mensagem para Comunidade(4)\n\nVisualizar Mensagem da Comunidade(5)\n\nVoltar ao menu(6)");
									communitysoptions = input.nextLine();
									if(communitysoptions.equals("6")) {
										break;
									}
									else Community.CommunityOptionsSelectAdmin(communitysoptions,systemcommunities,user,currentcommunityid,name,input);
								}
							}
							else {
								while(true) {
									System.out.println("\nVer Membros(1)\n\nEnviar Mensagem para Comunidade(2)\n\nVisualizar Mensagem da Comunidade(3)\n\nVer administrador(4)\n\nVoltar ao menu(5)");
									communitysoptions = input.nextLine();
									if(communitysoptions.equals("5")) {
										break;
									}
									else Community.CommunityOptionsSelect(communitysoptions,systemcommunities,currentcommunityid,name,input);
								}
							}
							
						}
					}
				}
				
				//Option 10
				else if(entry1.equals("10") ) {
					int ismemberofcommunity = -1;
					String control;
					System.out.println("Nome: " + user.get(yourid).getUsername());
					System.out.println("ID: " + user.get(yourid).getId());
					System.out.println("Conta: " + user.get(yourid).getMyAccount());
					System.out.println("Senha: " + user.get(yourid).getPass());
					if(user.get(yourid).getStatus() != null) System.out.println("O Status atual do usuario é: " + user.get(yourid).getStatus());
					else System.out.println("Usuario ainda não tem Status");
					if(user.get(yourid).getAddress() != null) System.out.println("O endereco atual do usuario é: " + user.get(yourid).getAddress());
					else System.out.println("Usuario ainda não tem Endereco");
					if(user.get(yourid).getBirthday() != null) System.out.println("Data de nascimento: "+ user.get(yourid).getBirthday());
					else System.out.println("Usuario ainda não tem Aniversario");
					System.out.println("Suas comunidades: ");
					for(int i = 0;i < systemcommunities.size();i++) {
						for(int j = 0;j < systemcommunities.get(i).getCommunityMembers().size();j++) {
							if(systemcommunities.get(i).getCommunityMembers().get(j).equals(name)) {
								System.out.println(systemcommunities.get(i).getCommunityName());
								ismemberofcommunity++;
							}
						}
					}
					if(ismemberofcommunity == -1) {
						System.out.println("Nao faz parte de nenhuma comunidade");
					}
					System.out.println("Numero de amigos: " + user.get(yourid).getNOfFriends());
					System.out.println("Pressione enter para voltar ao menu");
					control = input.nextLine();
					System.out.println(control);
				}
				
				
				//Option 11
				else if(entry1.equals("11") ) {
					int removeid;
					String removeaccount;
					System.out.println("Sua conta sera apagada, para confirmar, pressione 1. Para cancelar pressione 2");
					removeaccount = input.nextLine();
					removeid = Users.findUsersId(user,name);
					if(removeaccount.equals("1")) {
						RemoveAccount.toRemoveTheUser(user,systemcommunities,name,removeid);
						break;
					}
				}
				
				//Option 12
				else if(entry1.equals("12") ) {
					System.out.println("Deslogado!");
					break;
				}
			}
			
		}
		input.close();
	}
}

