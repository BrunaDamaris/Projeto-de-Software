package Folha;

import java.util.ArrayList;
import java.util.Scanner;

public class Employees {
	private String Name;
	private String Address;
	private String Type;
	private String PaymentMethod;
	private int EmployeeId;
	private double Salary;
	private double Payment;
	private String PaymentSchedule;
	private int DayOfPayment;
	private int MonthOfPayment;
	private int YearOFPayment;
	private int DayofWeekPayment;
	private int FirstDay;
	private int FirstMonth;
	private int FirstYear;
	private int FirstDayOfWeek;
	
	//Syndicate
	private String Syndicate;
	private int SyndicateId;
	private double SyndicateTax;
	private double SyndicateServiceTax;
	
	//Commissioned
	private double Commission;
	private double Sells;
	
	//Hourly
	private ArrayList<Hourly> PaymentDaily = new ArrayList<Hourly>();
	
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
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		this.Address = address;
	}
	
	public String getPaymentMethod() {
		return PaymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.PaymentMethod = paymentMethod;
	}
	
	public String getSyndicate() {
		return Syndicate;
	}
	public void setSyndicate(String syndicate) {
		this.Syndicate = syndicate;
	}
	public int getSyndicateId() {
		return SyndicateId;
	}
	public void setSyndicateId(int syndicateId) {
		this.SyndicateId = syndicateId;
	}
	public double getSyndicateTax() {
		return SyndicateTax;
	}
	public void setSyndicateTax(double syndicateTax) {
		this.SyndicateTax = syndicateTax;
	}
	
	public int getEmployeeId() {
		return EmployeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.EmployeeId = employeeId;
	}
	
	public double getSalary() {
		return Salary;
	}
	public void setSalary(double salary) {
		this.Salary = salary;
	}
	public double getComission() {
		return Commission;
	}
	public void setComission(double commission) {
		this.Commission = commission;
	}
	
	public double getSyndicateServiceTax() {
		return SyndicateServiceTax;
	}
	public void setSyndicateServiceTax(double syndicateServiceTax) {
		this.SyndicateServiceTax = syndicateServiceTax;
	}
	
	public String getPaymentSchedule() {
		return PaymentSchedule;
	}
	public void setPaymentSchedule(String paymentSchedule) {
		this.PaymentSchedule = paymentSchedule;
	}
	

	public static void addEmployee(ArrayList<Employees> myemployees,String ename,String eaddress, String etype, int employeeid,String pmethod,String syndicate,double salary,Scanner input,int day,int month,int year,int dayofweek) {
		Employees newemployee = new Employees();
		newemployee.setName(ename);
		newemployee.setAddress(eaddress);
		newemployee.setEmployeeId(employeeid);
		newemployee.setType(etype);
		newemployee.setPaymentMethod(pmethod);
		newemployee.setSyndicate(syndicate);
		String trash = null;
		if(syndicate.equals("1")) {
			double syndicatetax;
			String entry;
			int newsyndicateid = employeeid + 1000;
			System.out.println("\nSua identificacao de Sindicato e: " + newsyndicateid);
			newemployee.setSyndicateId(newsyndicateid);
			System.out.println("Digite a taxa de Sindicato (Exemplo: 0,10): ");
			syndicatetax = input.nextDouble();
			trash = input.nextLine();
			newemployee.setSyndicateTax(syndicatetax);
			System.out.println("Deseja adicionar taxa de servico? Se sim pressione 1. Se nao pressione enter");
			entry = input.nextLine();
			if(entry.equals("1")) {
				System.out.println("Informe a taxa de servico (Exemplo: 0,10): ");
				syndicatetax = input.nextDouble();
				trash = input.nextLine();
				newemployee.setSyndicateServiceTax(syndicatetax);
			}
		}
		if(etype.equals("horista")) {
			newemployee.setPaymentSchedule("semanalmente");
		}
		else if(etype.equals("assalariado")) newemployee.setPaymentSchedule("mensalmente");
		else if(etype.equals("comissionado")) {
			double commission;
			System.out.println("Informe a comissao (Exemplo: 0,10): ");
			commission = input.nextDouble();
			trash = input.nextLine();
			newemployee.setComission(commission);
			newemployee.setPaymentSchedule("bi-semanalmente");
		}
		else System.out.println(trash);
		newemployee.setFirstDay(day);
		newemployee.setFirstMonth(month);
		newemployee.setFirstYear(year);
		newemployee.setFirstDayOfWeek(dayofweek);
		newemployee.setSalary(salary);
		myemployees.add(newemployee);
	}

	public int getDayOfPayment() {
		return DayOfPayment;
	}
	public void setDayOfPayment(int dayOfPayment) {
		this.DayOfPayment = dayOfPayment;
	}
	public int getMonthOfPayment() {
		return MonthOfPayment;
	}
	public void setMonthOfPayment(int monthOfPayment) {
		this.MonthOfPayment = monthOfPayment;
	}
	public int getYearOFPayment() {
		return YearOFPayment;
	}
	public void setYearOFPayment(int yearOFPayment) {
		this.YearOFPayment = yearOFPayment;
	}
	public double getPayment() {
		return Payment;
	}
	public void setPayment(double payment) {
		this.Payment = payment;
	}
	public double getSells() {
		return Sells;
	}
	public void setSells(double sells) {
		this.Sells += sells;
	}
	public int getFirstDay() {
		return FirstDay;
	}
	public void setFirstDay(int firstDay) {
		this.FirstDay = firstDay;
	}
	public int getFirstMonth() {
		return FirstMonth;
	}
	public void setFirstMonth(int firstMonth) {
		this.FirstMonth = firstMonth;
	}
	public int getFirstYear() {
		return FirstYear;
	}
	public void setFirstYear(int firstYear) {
		this.FirstYear = firstYear;
	}
	public int getDayofWeekPayment() {
		return DayofWeekPayment;
	}
	public void setDayofWeekPayment(int dayofWeekPayment) {
		this.DayofWeekPayment = dayofWeekPayment;
	}
	public int getFirstDayOfWeek() {
		return FirstDayOfWeek;
	}
	public void setFirstDayOfWeek(int firstDayOfWeek) {
		this.FirstDayOfWeek = firstDayOfWeek;
	}
	public ArrayList<Hourly> getPaymentDaily() {
		return PaymentDaily;
	}
	public void setPaymentDaily(ArrayList<Hourly> paymentDaily) {
		this.PaymentDaily = paymentDaily;
	}
}
