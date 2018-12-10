package gestao;

import java.util.ArrayList;

public class Projects {
	private String Title;
	private int DayOfBeginning;
	private int MonthOfBeginning;
	private int YearOfBeginning;
	private int DayOfConclusion;
	private int MonthOfConclusion;
	private int YearOfConclusion;
	private String Financier;
	private double FinancierValue;
	private String Objective;
	private String Description;
	private ArrayList<Contributors> ProjectContributors = new ArrayList<Contributors>();
	private String Status;
	private String ProjectProfessor;
	private ArrayList<Publications> ProjectPublications = new ArrayList<Publications>();
	private ArrayList<Orientation> ProjectOrientation = new ArrayList<Orientation>();
	private ArrayList<String> ProjectMentors = new ArrayList<String>();
	
	//Title
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		this.Title = title;
	}
	
	//Date of Beginning
	public int getDayOfBeginning() {
		return DayOfBeginning;
	}
	public void setDayOfBeginning(int dayOfBeginning) {
		this.DayOfBeginning = dayOfBeginning;
	}
	public int getMonthOfBeginning() {
		return MonthOfBeginning;
	}
	public void setMonthOfBeginning(int monthOfBeginning) {
		this.MonthOfBeginning = monthOfBeginning;
	}
	public int getYearOfBeginning() {
		return YearOfBeginning;
	}
	public void setYearOfBeginning(int yearOfBeginning) {
		this.YearOfBeginning = yearOfBeginning;
	}
	
	//Date of Conclusion
	public int getDayOfConclusion() {
		return DayOfConclusion;
	}
	public void setDayOfConclusion(int dayOfConclusion) {
		this.DayOfConclusion = dayOfConclusion;
	}
	public int getMonthOfConclusion() {
		return MonthOfConclusion;
	}
	public void setMonthOfConclusion(int monthOfConclusion) {
		this.MonthOfConclusion = monthOfConclusion;
	}
	public int getYearOfConclusion() {
		return YearOfConclusion;
	}
	public void setYearOfConclusion(int yearOfConclusion) {
		this.YearOfConclusion = yearOfConclusion;
	}
	
	//Financiers
	public String getFinancier() {
		return Financier;
	}
	public void setFinancier(String financier) {
		this.Financier = financier;
	}
	public double getFinancierValue() {
		return FinancierValue;
	}
	public void setFinancierValue(double financierValue) {
		this.FinancierValue = financierValue;
	}
	
	//Description
	public String getObjective() {
		return Objective;
	}
	public void setObjective(String objective) {
		this.Objective = objective;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		this.Description = description;
	}
	
	//Contributors
	public ArrayList<Contributors> getProjectContributors() {
		return ProjectContributors;
	}
	public void setContributors(ArrayList<Contributors> contributors) {
		this.ProjectContributors = contributors;
	}
	
	//Status
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		this.Status = status;
	}
	public String getProjectProfessor() {
		return ProjectProfessor;
	}
	public void setProjectProfessor(String projectProfessor) {
		this.ProjectProfessor = projectProfessor;
	}
	public ArrayList<Publications> getProjectPublications() {
		return ProjectPublications;
	}
	public void setProjectPublications(ArrayList<Publications> projectPublications) {
		this.ProjectPublications = projectPublications;
	}
	
	//Add
	public static void addproject(ArrayList<Projects> project,ArrayList<Contributors> contributor,String title,int bday,int bmonth,int byear,int fday,int fmonth,int fyear,String financier,double value, String objective, String description,String manager,String manageremail) {
		Projects newproject = new Projects();
		newproject.setTitle(title);
		newproject.setDayOfBeginning(bday);
		newproject.setMonthOfBeginning(bmonth);
		newproject.setYearOfBeginning(byear);
		newproject.setDayOfConclusion(fday);
		newproject.setMonthOfConclusion(fmonth);
		newproject.setYearOfConclusion(fyear);
		newproject.setFinancier(financier);
		newproject.setFinancierValue(value);
		newproject.setObjective(objective);
		newproject.setDescription(description);
		newproject.setStatus("Em elaboracao");
		newproject.setProjectProfessor(manager);
		for(int i = 0;i < contributor.size();i++) {
			if(contributor.get(i).getName().equals(manager) && contributor.get(i).getEmail().equals(manageremail)) {
				newproject.getProjectContributors().add(contributor.get(i));
				newproject.getProjectMentors().add(contributor.get(i).getName());
				contributor.get(i).getMyProjects().add(newproject);
			}
		}
		project.add(newproject);
	}
	public ArrayList<Orientation> getProjectOrientation() {
		return ProjectOrientation;
	}
	public void setProjectOrientation(ArrayList<Orientation> projectOrientation) {
		this.ProjectOrientation = projectOrientation;
	}
	public ArrayList<String> getProjectMentors() {
		return ProjectMentors;
	}
	public void setProjectMentors(ArrayList<String> projectMentors) {
		this.ProjectMentors = projectMentors;
	}
	
	public static ArrayList<Projects> sortProjects(ArrayList<Projects> sortp){
		int i,j,minp;
		Projects auxiliarp = new Projects();
		int sizep = sortp.size();
		for(i = 0;i < sizep;i++) {
			minp = i;
			for(j = i+1;j < sizep;j++) {
				if(sortp.get(j).getYearOfConclusion() > sortp.get(minp).getYearOfConclusion()) {
					minp = j;
				}
				auxiliarp = sortp.get(i);
				sortp.set(i,sortp.get(minp));
				sortp.set(minp,auxiliarp);
			}
		}
		for(i = 0;i < sizep;i++) {
			minp = i;
			for(j = i+1;j < sizep;j++) {
				if(sortp.get(j).getYearOfConclusion() >= sortp.get(minp).getYearOfConclusion()){
					if(sortp.get(j).getMonthOfConclusion() > sortp.get(minp).getMonthOfConclusion()){
						minp = j;
					}
				}
				auxiliarp = sortp.get(i);
				sortp.set(i,sortp.get(minp));
				sortp.set(minp,auxiliarp);
			}
				
		}
		for(i = 0;i < sizep;i++) {
			minp = i;
			for(j = i+1;j < sizep;j++) {
				if(sortp.get(j).getYearOfConclusion() >= sortp.get(minp).getYearOfConclusion()){
					if(sortp.get(j).getMonthOfConclusion() >= sortp.get(minp).getMonthOfConclusion()){
						if(sortp.get(j).getDayOfConclusion() > sortp.get(minp).getDayOfConclusion()) {
							minp = j;
						}
						
					}
				}
				auxiliarp = sortp.get(i);
				sortp.set(i,sortp.get(minp));
				sortp.set(minp,auxiliarp);
			}
				
		}
		return sortp;
	}
	
	public static void sortPublications(ArrayList<Publications> sort){
		int i,j,min;
		Publications auxiliar = new Publications();
		int size = sort.size();
		for(i = 0;i < size;i++) {
			min = i;
			for(j = i+1;j < size;j++) {
				if(sort.get(j).getPublicationYear() > sort.get(min).getPublicationYear()) {
					min = j;
				}
				auxiliar = sort.get(i);
				sort.set(i,sort.get(min));
				sort.set(min,auxiliar);
			}
		}
	}
}
