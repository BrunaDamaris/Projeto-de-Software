package gestao;

import java.util.ArrayList;

public class Contributors {
	private String Name;
	private String Type;
	private String Email;
	private ArrayList<Projects> MyProjects = new ArrayList<Projects>();
	private ArrayList<Publications> MyPublications = new ArrayList<Publications>();
	
	//Graduation
	private String HasProject;
	//Mentor
	private ArrayList<Orientation> MyOrientation = new ArrayList<Orientation>();
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public String getHasProject() {
		return HasProject;
	}
	public void setHasProject(String hasProject) {
		this.HasProject = hasProject;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		this.Email = email;
	}
	public ArrayList<Projects> getMyProjects() {
		return MyProjects;
	}
	public void setMyProjects(ArrayList<Projects> myProjects) {
		this.MyProjects = myProjects;
	}
	
	public static void addcontributors(ArrayList<Contributors> contributor, String name, String type, String email) {
		Contributors newcontributor = new Contributors();
		newcontributor.setName(name);
		newcontributor.setType(type);
		newcontributor.setEmail(email);
		newcontributor.setHasProject("2");
		contributor.add(newcontributor);
	}
	
	public static int check(ArrayList<Contributors> contributor,String name,String type,String email) {
		if(contributor.size() != 0) {
			int hascontributor = 0;
			for(int i = 0;i < contributor.size();i++) {
				if((contributor.get(i).getEmail().equals(email))) {
					hascontributor = 1;
				}
			}
			if(hascontributor == 0) {
				return 1;
			
			}
		}
		else return 1;
		return -1;
	}
	public ArrayList<Publications> getMyPublications() {
		return MyPublications;
	}
	public void setMyPublications(ArrayList<Publications> myPublications) {
		this.MyPublications = myPublications;
	}
	public ArrayList<Orientation> getMyOrientation() {
		return MyOrientation;
	}
	public void setMyOrientation(ArrayList<Orientation> myOrientation) {
		this.MyOrientation = myOrientation;
	}
}
