package gestao;

import java.util.ArrayList;
import java.util.Scanner;

public class Publications {
	private String PublicationName;
	private int PublicationYear;
	private ArrayList<String> Authors = new ArrayList<String>();
	private String ConferenceName;
	private String AssociatedProject;
	
	public String getPublicationName() {
		return PublicationName;
	}
	public void setPublicationName(String publicationName) {
		this.PublicationName = publicationName;
	}
	public int getPublicationYear() {
		return PublicationYear;
	}
	public void setPublicationYear(int publicationYear) {
		this.PublicationYear = publicationYear;
	}
	public ArrayList<String> getAuthors() {
		return Authors;
	}
	public void setAuthors(ArrayList<String> authors) {
		this.Authors = authors;
	}
	// Conference
	public String getConferenceName() {
		return ConferenceName;
	}
	public void setConferenceName(String conferenceName) {
		this.ConferenceName = conferenceName;
	}
	public String getAssociatedProject() {
		return AssociatedProject;
	}
	public void setAssociatedProject(String associatedProject) {
		this.AssociatedProject = associatedProject;
	}
	
	public static void addnewpublication(ArrayList<Publications> publication,ArrayList<Contributors> contributor,String name,String conference,int year,Scanner input) {
		Publications addPublication = new Publications();
		addPublication.setPublicationName(name);
		addPublication.setConferenceName(conference);
		addPublication.setPublicationYear(year);
		addPublication.setAssociatedProject("-1");
		String authors,trash = null,option = null;
		int number_of_authors = 0,verifycexistence = -1,verify = -1;
		System.out.println("Informe quantos autores deseja adicionar a publicacao: ");
		number_of_authors = input.nextInt();
		trash = input.nextLine();
		if(number_of_authors != 0 && contributor.size() != 0) {
			for(int i = 0;i < number_of_authors;i++) {
				System.out.println("Informe o(s) autor(es) da publicacao: ");
					authors = input.nextLine();
					for(int j = 0;j < contributor.size();j++) {
						if(contributor.get(j).getName().equals(authors)) {
							System.out.println("Deseja adicionar esse autor? " + "Nome: " + authors + "\nTipo: " + contributor.get(j).getType() +"\nEmail: " + contributor.get(j).getEmail());
							System.out.println("Se sim,pressione 1. Se nao, pressione 2");
							option = input.nextLine();
							if(option.equals("1")) {
								verifycexistence = j;
								break;
							}
						}
					}
					if(verifycexistence != -1) {
						addPublication.getAuthors().add(authors);
						contributor.get(verifycexistence).getMyPublications().add(addPublication);
						verify = 1;
					}
					else {
						number_of_authors++;
						System.out.println("Colaborador nao encontrado.");
					}
			}
			if(verify != -1) {
				publication.add(addPublication);
				System.out.println("Publicacao criada com sucesso");
			}
			else System.out.println("Nao foi possivel adicionar os autores. Publicacao nao criada.");
		}
		else System.out.println("Publicacao deve ter pelo menos um author e/ou devem existir colaboradores no laboratorio" + trash);
	}
}
