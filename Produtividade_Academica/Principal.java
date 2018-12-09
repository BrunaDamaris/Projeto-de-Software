package gestao;

import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
	public static void main( String[] args) {
		Scanner input = new Scanner(System.in);
		int projectsinelaboration = 0,projectsongoing = 0,finishedprojects  = 0;
		int check_contributor = -1,approve = -1,currentprojectindex = -1;
		
		String first_entry,second_entry,continuen = null;
		String projectname = null;
		ArrayList<Contributors> contributor = new ArrayList<Contributors>();
		ArrayList<Projects> project = new ArrayList<Projects>();
		ArrayList<Publications> publication = new ArrayList<Publications>();
		ArrayList<Orientation> orientation = new ArrayList<Orientation>();
		
		while(true) {
			System.out.println("Escolha o que deseja acessar: \n");
			System.out.println("Colaboradores(1)\n\nProjetos(2)\n\nPublicacoes(3)\n\nFechar(0)\n");
			first_entry = input.nextLine();
			if((!first_entry.equals("1")) && (!first_entry.equals("2"))  && (!first_entry.equals("3"))  && (!first_entry.equals("4"))) {
				System.out.println("Fim!");
				break;
			}
			while(true) {
				//Option 1 - Contributors
				if(first_entry.equals("1")) {
					System.out.println("Adicionar Colaborador ao Laboratorio(1)\n\nVisualizar Colabores(2)\n\nVoltar(0)\n");
					second_entry = input.nextLine();
					if((!second_entry.equals("1")) && (!second_entry.equals("2"))) {
						break;
					}
					//Option 1.1 - Add Contributors
					else if(second_entry.equals("1")) {
						String name,type,email;
						System.out.println("Nome do Colaborador: ");
						name = input.nextLine();
						System.out.println("Tipo(Graduacao,Mestrado,Doutorado,Professor ou Pesquisador): ");
						type = input.nextLine();
						System.out.println("E-mail(Esse email deve ser unico): ");
						email = input.nextLine();
						check_contributor = Contributors.check(contributor,name,type,email);
						if(check_contributor == 1) {
							Contributors.addcontributors(contributor,name,type,email);
							System.out.println("Colaborador adicionado.");
						}
						else System.out.println("Nao foi possivel adicionar, colaborador ja existe");
						
						System.out.println("Pressione enter para continuar");
						continuen = input.nextLine();
					}
					//Option 1.2 -  See Contributors
					else if(second_entry.equals("2")) {
						if(contributor.size() != 0) {
							String colab_entry;
							System.out.println("Visualizar um colaborador(1)\n\nVisualizar todos colaboradores(2)\n");
							colab_entry = input.nextLine();
							if(colab_entry.equals("1")) {
								String currentname,currentemail;
								int currentindex = -1;
								System.out.println("Informe o Nome do colaborador: ");
								currentname = input.nextLine();
								System.out.println("Informe o Email do colaborador: ");
								currentemail = input.nextLine();
								for(int i = 0;i < contributor.size();i++) {
									if((contributor.get(i).getName().equals(currentname)) && (contributor.get(i).getEmail().equals(currentemail))) {
										currentindex = i;
									}
								}
								if(currentindex != -1) {
									System.out.println("Nome: " + currentname);
									System.out.println("Tipo: " + contributor.get(currentindex).getType());
									System.out.println("Email: " + contributor.get(currentindex).getEmail());
									if(contributor.get(currentindex).getMyProjects().size() != 0) {
										System.out.println("Faz ou fez parte de Projeto(s)\nProjeto(s): ");
										//Sort of projects
										ArrayList<Projects> sortp = new ArrayList<Projects>();
										sortp = contributor.get(currentindex).getMyProjects();
										sortp = Projects.sortProjects(sortp);
										for(int i = 0;i < sortp.size();i++) {
											System.out.println("Nome do Projeto: " + sortp.get(i).getTitle());
											System.out.println("Status do Projeto: " + sortp.get(i).getStatus());
											System.out.println("Descricao do Projeto: " + sortp.get(i).getDescription());
											System.out.println("Objetivo do Projeto: " + sortp.get(i).getObjective());
											System.out.println("Data de termino: " + "\nDia: " + sortp.get(i).getDayOfConclusion() + "\nMes do termino: " + sortp.get(i).getMonthOfConclusion() + "\nAno de termino: " + sortp.get(i).getYearOfConclusion());
											System.out.println("--------------------------------------------------------");
										}
										sortp = contributor.get(currentindex).getMyProjects();
									}
									else System.out.println("Nao faz ou fez parte de Projeto(s)");
									//Sort of publications
									if(contributor.get(currentindex).getMyPublications().size() != 0) {
										Projects.sortPublications(contributor.get(currentindex).getMyPublications());
										System.out.println("Faz ou fez parte de Publicacao(oes)\nPublicacao(oes): ");
										Projects.sortPublications(contributor.get(currentindex).getMyPublications());
										for(int i = 0;i < contributor.get(currentindex).getMyPublications().size();i++) {
											System.out.println("Nome da Publicacao: " + contributor.get(currentindex).getMyPublications().get(i).getPublicationName());
											System.out.println("Ano de Publicacao: " + contributor.get(currentindex).getMyPublications().get(i).getPublicationYear());
										}
									}
									else System.out.println("Nao faz ou fez parte de Publicacoes");
									if(contributor.get(currentindex).getType().equals("Professor")) {
										if(contributor.get(currentindex).getMyOrientation().size() != 0) {
											for(int i = 0;i < contributor.get(currentindex).getMyOrientation().size();i++) {
												System.out.println("Orientacao: " + contributor.get(currentindex).getMyOrientation().get(i).getOrientation());
												System.out.println("Projeto associado: " + contributor.get(currentindex).getMyOrientation().get(i).getAssociateProject());
											}
										}
										else System.out.println("Nao ha orientacoes");
									}
								}
								else System.out.println("Nao foi possivel achar o colaborador.");
								
							}
							else if(colab_entry.equals("2")){
								System.out.println("Colaboradores: ");
								for(int i = 0;i < contributor.size();i++) {
									System.out.println("Nome: " + contributor.get(i).getName() + " - " + "Tipo: " + contributor.get(i).getType() + " - " + "Email: " + contributor.get(i).getEmail());
								}
							}
						}
						else System.out.println("Nao existem colaboradores. Adicione primeiro.");
						
						System.out.println("Pressione enter para continuar");
						continuen = input.nextLine();
					}
					
				}
				//Option 2 - Projects
				else if(first_entry.equals("2")) {
					if(contributor.size() == 0) { 
						System.out.println("Nao existem colaboradores no laboratorio, portanto nao e possivel iniciar e/ou acessar projetos");
						break;
					}
					System.out.println("Criar Projeto(1)\n\nVisualizar Projeto(2)\n\nRelatorio de Producao(3)\n\nVoltar(0)\n");
					second_entry = input.nextLine();
					if((!second_entry.equals("1")) && (!second_entry.equals("2")) && (!second_entry.equals("3"))) {
						break;
					}
					//Option 2.1 - Create Project
					else if(second_entry.equals("1")) {
						String newfinancier,newdescription,newobjective,newpmanager = null,newmanageremail = null;
						int b_day,b_month,b_year,f_day,f_month,f_year,validateproject = 1,findmanager = -1;
						double new_value;
						System.out.println("Informe o nome do novo Projeto: ");
						projectname = input.nextLine();
						System.out.println("Informe a data de inicio: \nDia: ");
						b_day = input.nextInt();
						continuen = input.nextLine();
						System.out.println("Mes de inicio: ");
						b_month = input.nextInt();
						continuen = input.nextLine();
						System.out.println("Ano de inicio: ");
						b_year = input.nextInt();
						continuen = input.nextLine();
						System.out.println("Informe a data de termino do projeto: ");
						System.out.println("Dia: ");
						f_day = input.nextInt();
						continuen = input.nextLine();
						System.out.println("Mes de termino: ");
						f_month = input.nextInt();
						continuen = input.nextLine();
						System.out.println("Ano de termino: ");
						f_year = input.nextInt();
						continuen = input.nextLine();
						System.out.println("Informe a agencia financiadora: ");
						newfinancier = input.nextLine();
						System.out.println("Informe o valor a ser financiado: ");
						new_value = input.nextDouble();
						continuen = input.nextLine();
						System.out.println("Informe o objetivo do Projeto: ");
						newobjective = input.nextLine();
						System.out.println("Informe a descricao do Projeto: ");
						newdescription = input.nextLine();
						System.out.println("Professores disponiveis: ");
						if(contributor.size() != 0) {
							for(int i = 0;i < contributor.size();i++) {
								if(contributor.get(i).getType().equals("Professor")) {
									System.out.println("Nome: " + contributor.get(i).getName() + " - Email: " + contributor.get(i).getEmail()); 
									findmanager = 1;
								}
							}
							if(findmanager != -1) {
								System.out.println("Informe o nome do professor escolhido: ");
								newpmanager = input.nextLine();
								System.out.println("Informe o Email do professor escolhido: ");
								newmanageremail = input.nextLine();
							}
							else { 
								System.out.println("Nao ha professores disponiveis.");
								break;
							}
						}
						if(project.size() != 0) {
							for(int i = 0;i < project.size();i++) {
								if(project.get(i).getTitle().equals(projectname)) {
									validateproject = -1;
								}
							}
						}
						if(validateproject == 1 && findmanager != -1) {
							Projects.addproject(project,contributor,projectname,b_day,b_month,b_year,f_day,f_month,f_year,newfinancier,new_value,newobjective,newdescription,newpmanager,newmanageremail);
							approve = 1;
						}
						else {
							System.out.println("Nao foi possivel adicionar.");
							break;
						}
					}
					//Option 2.2 - See Project
					else if(second_entry.equals("2")) {
						if(project.size() != 0) {
							System.out.println("Informe o nome do Projeto: ");
							projectname = input.nextLine();
							for(int i = 0;i < project.size();i++) {
								if(project.get(i).getTitle().equals(projectname)) {
									approve = 1;
								}
							}
							if(approve == -1) System.out.println("Projeto nao encontrado");
						}
						else System.out.println("Nao existem Projetos.");
						System.out.println("Pressione enter para continuar");
						continuen = input.nextLine();
						
					}
					//Option 2.3 - Production
					else if(second_entry.equals("3")) {
						System.out.println("Numero de colaboradores: " + contributor.size());
						for(int i = 0;i < project.size();i++) {
							if(project.get(i).getStatus().equals("Em elaboracao")) {
								projectsinelaboration++;
							}
							else if(project.get(i).getStatus().equals("Em andamento")) {
								projectsongoing++;
							}
							else if(project.get(i).getStatus().equals("Concluido")) {
								finishedprojects++;
							}
						}
						System.out.println("Numero de projetos em elaboracao: " + projectsinelaboration);
						System.out.println("Numero de projetos em andamento: " + projectsongoing);
						System.out.println("Numero de projetos concluidos: " + finishedprojects);
						System.out.println("Numero total de projetos: " + project.size());
						System.out.println("Numero de producao academica por tipo de Producao(Publicacoes,Orientacoes): ");
						System.out.println("Publicacoes: " + publication.size());
						System.out.println("Orientacoes: " + orientation.size());
						projectsinelaboration = projectsongoing = finishedprojects = 0;
						
						System.out.println("Pressione enter para continuar");
						continuen = input.nextLine();
					}
					if(approve != -1) {
						while(true) {
							approve = -1;
							String entry;
							for(int i = 0;i < project.size();i++) {
								if(project.get(i).getTitle().equals(projectname)) {
									currentprojectindex = i;
								}
							}
							System.out.println("Adicionar Colaboradores(1)\n\nAtualizar Status do Projeto(2)\n\nInclusao de Producao Academica(3)\n\nDados do Projeto(4)\n\nVoltar(0)\n");
							entry = input.nextLine();
							//Option 2.(1/2).1 - Add Contributors to Project
							if(entry.equals("1")) {
								if(project.get(currentprojectindex).getStatus().equals("Em elaboracao") && (!project.get(currentprojectindex).getProjectProfessor().equals(null))) {
									String contributor_name,contributor_email,option;
									System.out.println("Deseja ver os colaboradores disponiveis? Se sim,pressione 1. Se nao pressione enter");
									option = input.nextLine();
									if(option.equals("1")) {
										for(int i = 0;i < contributor.size();i++) {
											if(contributor.get(i).getType().equals("Graduacao")) {
												if(contributor.get(i).getHasProject().equals("2")) {
													System.out.println("Nome: " + contributor.get(i).getName());
													System.out.println("Tipo: " + contributor.get(i).getType());
													System.out.println("Email: " + contributor.get(i).getEmail());
													System.out.println("------------------------------------");
												}
											}
											else {
												System.out.println("Nome: " + contributor.get(i).getName());
												System.out.println("Tipo: " + contributor.get(i).getType());
												System.out.println("Email: " + contributor.get(i).getEmail());
												System.out.println("------------------------------------");
											}
										}
									}
									System.out.println("Informe o nome do colaborador que deseja adicionar: ");
									contributor_name = input.nextLine();
									System.out.println("Informe o email do colaborador que deseja adicionar: ");
									contributor_email = input.nextLine();
									int currentcontributor = -1;
									for(int i = 0;i < contributor.size();i++) {
										if((contributor.get(i).getName().equals(contributor_name)) && (contributor.get(i).getEmail().equals(contributor_email))) {
											currentcontributor = i;
										}
									}
									if(currentcontributor != -1) {
										int alreadyinproject = 1;
										if(contributor.get(currentcontributor).getMyProjects().size() != 0) {
											for(int j = 0;j < contributor.get(currentcontributor).getMyProjects().size();j++) {
													if(contributor.get(currentcontributor).getMyProjects().get(j).getTitle().equals(project.get(currentprojectindex).getTitle())) {
														alreadyinproject = -1;
												}
											}
										}
										if(alreadyinproject != -1) {
											if(contributor.get(currentcontributor).getType().equals("Graduacao")) {
												if(contributor.get(currentcontributor).getHasProject().equals("2")) {
													contributor.get(currentcontributor).setHasProject("1");
													project.get(currentprojectindex).getProjectContributors().add(contributor.get(currentcontributor));
													contributor.get(currentcontributor).getMyProjects().add(project.get(currentprojectindex));
													System.out.println("Colaborador adicionado ao projeto.");
												}
												else System.out.println("Aluno de graducao ja tem projeto, nao pode ser adicionado");
											}
											else if(contributor.get(currentcontributor).getType().equals("Professor")) {
												project.get(currentprojectindex).getProjectMentors().add(contributor.get(currentcontributor).getName());
												project.get(currentprojectindex).getProjectContributors().add(contributor.get(currentcontributor));
												contributor.get(currentcontributor).getMyProjects().add(project.get(currentprojectindex));
												System.out.println("Colaborador adicionado ao projeto.");
											}
											else {
												project.get(currentprojectindex).getProjectContributors().add(contributor.get(currentcontributor));
												contributor.get(currentcontributor).getMyProjects().add(project.get(currentprojectindex));
												System.out.println("Colaborador adicionado ao projeto.");
											}
										}
										else System.out.println("Colaborador ja esta no projeto.");
									}
									else System.out.println("Colaborador nao encontrado.");
								}
								else System.out.println("Nao foi possivel adicionar, projeto nao esta em elaboracao e/ou projeto sem orientador");
								
								System.out.println("Pressione enter para continuar");
								continuen = input.nextLine();
							}
							//Option 2.(1/2).2  - Project Status
							else if(entry.equals("2")) {
								int verifynewstatus = -1;
								String option;
								System.out.println("Status atual: " + project.get(currentprojectindex).getStatus());
								System.out.println("Deseja alterar o Status? Se sim, pressione 1. Se nao pressione enter");
								option = input.nextLine();
								if(option.equals("1")) {
									String newstatus;
									System.out.println("Informe o novo Status(Em andamento;Concluido): ");
									newstatus = input.nextLine();
									if(newstatus.equals("Em andamento")) {
										if(project.get(currentprojectindex).getStatus().equals("Em elaboracao")) {
											if((!project.get(currentprojectindex).getTitle().equals(null)) && (!project.get(currentprojectindex).getFinancier().equals(null)) && (project.get(currentprojectindex).getFinancierValue() != 0) && (!project.get(currentprojectindex).getObjective().equals(null)) && (!project.get(currentprojectindex).getDescription().equals(null)) && (!project.get(currentprojectindex).getProjectProfessor().equals(null))) {
												if(project.get(currentprojectindex).getProjectContributors().size() > 1){
													verifynewstatus = 1;
												}
											}
											if(verifynewstatus != -1) {
												project.get(currentprojectindex).setStatus(newstatus);
												System.out.println("Status atualizado");
											}
											else System.out.println("Nao foi possivel atualizar o status. Projeto nao esta em elaboracao e/ou nao cumpriu os requisitos minimos");
										}
									}
									else if(newstatus.equals("Concluido")) {
										if(project.get(currentprojectindex).getProjectPublications().size() != 0) {
											project.get(currentprojectindex).setStatus(newstatus);
											System.out.println("Status atualizado.");
										}
										else System.out.println("Nao foi possivel atualizar o status. Projeto nao esta em andamento e/ou nao contem produtividade minima");
									}
								}
								
								System.out.println("Pressione enter para continuar");
								continuen = input.nextLine();
							}
							//Option 2.(1/2).3 - Academic Production
							else if(entry.equals("3")) {
								String option;
								System.out.println("Associar Publicacao(1)\n\nVer Publicacoes associadas(2)\n\nNova Orientacao(3)\n\nVer Orientaçoes(4)\n");
								option = input.nextLine();
								if(option.equals("1")) {
									if(project.get(currentprojectindex).getStatus().equals("Em andamento")) {
										int currentpindex = -1;
										String currentpublication;
										System.out.println("Informe o nome da publicacao que deseja associar: ");
										currentpublication = input.nextLine();
										for(int i = 0;i < publication.size();i++) {
											if(publication.get(i).getPublicationName().equals(currentpublication) && (publication.get(i).getAssociatedProject().equals("-1"))) {
												currentpindex = i;
											}
										}
										if(currentpindex != -1) {
											if(project.get(currentprojectindex).getYearOfConclusion() < publication.get(currentpindex).getPublicationYear()) {
												System.out.println("Nao foi possivel associar. Falha no ano de publicacao.");
											}
											else if(project.get(currentprojectindex).getProjectPublications().size() != 0){
												int verifypublication = 1;
												for(int i = 0;i < project.get(currentprojectindex).getProjectPublications().size();i++) {
													if(project.get(currentprojectindex).getProjectPublications().get(i).getPublicationName().equals(currentpublication)) {
														verifypublication = -1;
													}
												}
												if(verifypublication != -1) {
													project.get(currentprojectindex).getProjectPublications().add(publication.get(currentpindex));
													Projects.sortPublications(project.get(currentprojectindex).getProjectPublications());
													publication.get(currentpindex).setAssociatedProject(project.get(currentprojectindex).getTitle());
													System.out.println("Publicacao associada com sucesso");
												}
												else System.out.println("Publicacao ja esta associada a esse projeto.");
											}
											else
											{
												project.get(currentprojectindex).getProjectPublications().add(publication.get(currentpindex));
												Projects.sortPublications(project.get(currentprojectindex).getProjectPublications());
												publication.get(currentpindex).setAssociatedProject(project.get(currentprojectindex).getTitle());
												System.out.println("Publicacao associada com sucesso");
											}
										}
										else System.out.println("Publicacao nao encontrada ou Publicacao ja associada a um projeto.");
									}
									else System.out.println("Projeto nao esta em andamento.");
									
									System.out.println("Pressione enter para continuar");
									continuen = input.nextLine();
								}
								else if(option.equals("2")) {
									if(project.get(currentprojectindex).getProjectPublications().size() != 0) {
										for(int i  = 0;i < project.get(currentprojectindex).getProjectPublications().size();i++){
											System.out.println("Nome da publicacao: " + project.get(currentprojectindex).getProjectPublications().get(i).getPublicationName());
											System.out.println("Autor(es): ");
											for(int j = 0;j < project.get(currentprojectindex).getProjectPublications().get(i).getAuthors().size();j++) {
												System.out.println(project.get(currentprojectindex).getProjectPublications().get(i).getAuthors().get(j));
											}
											System.out.println("Nome da Conferencia de Publicacao: " + project.get(currentprojectindex).getProjectPublications().get(i).getConferenceName());
											System.out.println("Ano de publicacao: " + project.get(currentprojectindex).getProjectPublications().get(i).getPublicationYear());
											System.out.println("----------------------------------------------------");
										}
									}
									else System.out.println("Nao ha publicacoes associadas.");
									
									System.out.println("Pressione enter para continuar");
									continuen = input.nextLine();
								}
								else if(option.equals("3")) {
									String currentmentor,choice = null,currentemail = null;
									int mentorindex = -1;
									System.out.println("Informe o nome do Orientador: ");
									currentmentor = input.nextLine();
									for(int i = 0;i < project.get(currentprojectindex).getProjectContributors().size();i++) {
										if(project.get(currentprojectindex).getProjectContributors().get(i).getName().equals(currentmentor)){
											currentemail = project.get(currentprojectindex).getProjectContributors().get(i).getEmail();
											System.out.println("Confirme o Orientador(pressione 1.Se nao pressione 2): " + "\nNome: " + currentmentor + "\nEmail: " + currentemail);
											choice = input.nextLine();
											if(choice.equals("1")) {
												mentorindex = i;
												break;
											}
										}
									}
									if(mentorindex != -1) {
										int currentcontributor = -1;
										String neworientation;
										System.out.println("Informe a orientacao: ");
										neworientation = input.nextLine();
										Orientation addnew = new Orientation();
										addnew.setProfessorName(currentmentor);
										addnew.setOrientation(neworientation);
										addnew.setAssociateProject(project.get(currentprojectindex).getTitle());
										for(int i = 0;i < contributor.size();i++) {
											if((contributor.get(i).getName().equals(currentmentor)) && (contributor.get(i).getEmail().equals(currentemail))) {
												currentcontributor = i;
											}
										}
										if(currentcontributor != -1) {
											orientation.add(addnew);
											contributor.get(currentcontributor).getMyOrientation().add(addnew);
											project.get(currentprojectindex).getProjectOrientation().add(addnew);
											System.out.println("Sucesso");
										}
										else System.out.println("Nao encontrado como colaborador. Falha.");
										
									}
									else System.out.println("Orientador nao encontrado.");
									
									System.out.println("Pressione enter para continuar");
									continuen = input.nextLine();
								}
								else if(option.equals("4")) {
									if(project.get(currentprojectindex).getProjectOrientation().size() != 0) {
										for(int i = 0;i < project.get(currentprojectindex).getProjectOrientation().size();i++) {
											System.out.println("Orientador: " + project.get(currentprojectindex).getProjectOrientation().get(i).getProfessorName() + " - " + "Orientacao: " + project.get(currentprojectindex).getProjectOrientation().get(i).getOrientation());
										}
									}
									else System.out.println("Nao ha orientacoes");
									
									System.out.println("Pressione enter para continuar");
									continuen = input.nextLine();
								}
								
							}
							//Option 2.(1/2).4 - Project Data
							else if(entry.equals("4")) {
								System.out.println("-Titulo: " + project.get(currentprojectindex).getTitle());
								System.out.println("-Status do Projeto: " + project.get(currentprojectindex).getStatus());
								System.out.println("-Descricao do Projeto: " + project.get(currentprojectindex).getDescription());
								System.out.println("-Objetivo do Projeto: " + project.get(currentprojectindex).getObjective());
								System.out.println("-Data de inicio: " + project.get(currentprojectindex).getDayOfBeginning() + "/" + project.get(currentprojectindex).getMonthOfBeginning() + "/" + project.get(currentprojectindex).getYearOfBeginning());
								System.out.println("\n-Data de termino: " + project.get(currentprojectindex).getDayOfConclusion() + "/" + project.get(currentprojectindex).getMonthOfConclusion() + "/" + project.get(currentprojectindex).getYearOfConclusion());
								System.out.println("\n-Finaciador(a): " + project.get(currentprojectindex).getFinancier());
								System.out.println("Valor financiado: " + project.get(currentprojectindex).getFinancierValue());
								System.out.println("\n-Participantes: ");
								for(int i = 0;i < project.get(currentprojectindex).getProjectContributors().size();i++) {
									System.out.println("Nome: " + project.get(currentprojectindex).getProjectContributors().get(i).getName());
									System.out.println("Tipo: " + project.get(currentprojectindex).getProjectContributors().get(i).getType());
									System.out.println("Email: " + project.get(currentprojectindex).getProjectContributors().get(i).getEmail());
									System.out.println("-----------------------------------------");
									
								}
								System.out.println("-Producao Academica: \n\nPublicacoes: ");
								if(project.get(currentprojectindex).getProjectPublications().size() != 0) {
									//Sort Publications
									for(int i = 0;i < project.get(currentprojectindex).getProjectPublications().size();i++) {
										System.out.println("Nome da Publicacao: " + project.get(currentprojectindex).getProjectPublications().get(i).getPublicationName());
										System.out.println("Ano de Publicacao: " + project.get(currentprojectindex).getProjectPublications().get(i).getPublicationYear());
									}
									System.out.println("------------------------------------");
									Projects.sortPublications(project.get(currentprojectindex).getProjectPublications());
									for(int i = 0;i < project.get(currentprojectindex).getProjectPublications().size();i++) {
										System.out.println("Nome da Publicacao: " + project.get(currentprojectindex).getProjectPublications().get(i).getPublicationName());
										System.out.println("Ano de Publicacao: " + project.get(currentprojectindex).getProjectPublications().get(i).getPublicationYear());
									}
								}
								else System.out.println("Nao ha publicacoes.");
								System.out.println("\nOrientacoes: ");
								if(project.get(currentprojectindex).getProjectOrientation().size() != 0) {
									for(int j = 0;j < project.get(currentprojectindex).getProjectOrientation().size();j++){
										System.out.println("Orientador: " + project.get(currentprojectindex).getProjectOrientation().get(j).getProfessorName() + " - " + "Orientacao: " + project.get(currentprojectindex).getProjectOrientation().get(j).getOrientation());
									}
								}
								else System.out.println("Nao existem orientacoes.");

								System.out.println("Pressione enter para continuar");
								continuen = input.nextLine();
							}
							else {
								break;
							}
						}
					}
				}
				//Option 3 - Publications
				else if(first_entry.equals("3")) {
					if(contributor.size() == 0) { 
						System.out.println("Nao existem colaboradores no laboratorio, portanto nao e possivel iniciar e/ou acessar publicacoes");
						break;
					}
					String entry;
					System.out.println("Nova Publicacao(1)\n\nVisualizar Publicacoes(2)\n\nVoltar(0)\n");
					entry = input.nextLine();
					if(entry.equals("1")) {
						String publicationname,publicationconference;
						int publicationyear,verifyexistence = 1;
						System.out.println("Informe o nome da publicacao: ");
						publicationname = input.nextLine();
						System.out.println("Informe o nome da conferencia: ");
						publicationconference = input.nextLine();
						System.out.println("Informe o ano de publicacao: ");
						publicationyear = input.nextInt();
						continuen = input.nextLine();
						if(publication.size() != 0) {
							for(int i = 0;i < publication.size();i++){
								if((publication.get(i).getPublicationName().equals(publicationname)) && (publication.get(i).getConferenceName().equals(publicationconference)) && (publication.get(i).getPublicationYear() == publicationyear)) {
									verifyexistence = -1;
								}
							}
							if(verifyexistence != -1) {
								Publications.addnewpublication(publication,contributor,publicationname,publicationconference,publicationyear,input);
							}
							else System.out.println("Publicacao ja existe.");
						}
						else {
							Publications.addnewpublication(publication,contributor,publicationname,publicationconference,publicationyear,input);
						}
						
						System.out.println("Pressione enter para continuar");
						continuen = input.nextLine();
					}
					else if(entry.equals("2")) {
						if(publication.size() != 0) {
							for(int i = 0;i < publication.size();i++) {
								System.out.println("Nome da Publicacao: " + publication.get(i).getPublicationName());
								System.out.println("Autor(es): ");
								for(int j = 0;j < publication.get(i).getAuthors().size();j++) {
									System.out.println(publication.get(i).getAuthors().get(j));
								}
								System.out.println("Nome da conferencia de publicacao: " + publication.get(i).getConferenceName());
								System.out.println("Ano de publicacao: " + publication.get(i).getPublicationYear());
								if(publication.get(i).getAssociatedProject().equals("-1")) System.out.println("Nao ha projetos associados.");
								else System.out.println("Projeto associado: " + publication.get(i).getAssociatedProject());

								System.out.println("----------------------------------------------------");
							}
						}
						else System.out.println("Nao existem publicacoes");
						
						System.out.println("Pressione enter para continuar");
						continuen = input.nextLine();
					}
					else break;
				}
			}
		}
		System.out.println(continuen);
		input.close();
	}
}
