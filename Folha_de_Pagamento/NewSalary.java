package Folha;

import java.util.ArrayList;

public class NewSalary {
	
	public static void HourlySalary(ArrayList<Employees> myemployees,int employeeindex) {
		int verifypayment = 0;
		for(int i = 0; i < myemployees.get(employeeindex).getPaymentDaily().size();i++) {
			if((myemployees.get(employeeindex).getPaymentDaily().get(i).getArrivalTime() != -1) && (myemployees.get(employeeindex).getPaymentDaily().get(i).getExitTime() != -1)) {
				verifypayment++;
				int begin = myemployees.get(employeeindex).getPaymentDaily().get(i).getArrivalTime();
				int end = myemployees.get(employeeindex).getPaymentDaily().get(i).getExitTime();
				int totaltime = end-begin;
				if(totaltime <= 8) {
					 myemployees.get(employeeindex).getPaymentDaily().get(i).setPaymentOfDay(totaltime*(myemployees.get(employeeindex).getSalary()));
				}
				else {
					double currentpayment = 8 * (myemployees.get(employeeindex).getSalary());
					int extrahours = totaltime - 8;
					double finalpayment = (extrahours*((myemployees.get(employeeindex).getSalary())*1.5)) + currentpayment;
					 myemployees.get(employeeindex).setPayment(finalpayment);
					 myemployees.get(employeeindex).getPaymentDaily().get(i).setPaymentOfDay(finalpayment);
				}
			}
			else  myemployees.get(employeeindex).getPaymentDaily().get(i).setPaymentOfDay(0.0);
		}
		double finalpaymentsum = 0;
		for(int i = 0; i < myemployees.get(employeeindex).getPaymentDaily().size();i++) {
			finalpaymentsum += myemployees.get(employeeindex).getPaymentDaily().get(i).getPaymentOfDay();
		}
		if(verifypayment == 0) myemployees.get(employeeindex).setPayment(0.0);
		else  myemployees.get(employeeindex).setPayment(finalpaymentsum);
	}
	
	public static void SalariedSalary(ArrayList<Employees> myemployees,int employeeindex) {
		myemployees.get(employeeindex).setPayment(myemployees.get(employeeindex).getSalary());
	}
	
	public static void CommisionedSalary(ArrayList<Employees> myemployees,int employeeindex) {
		double commissiontotal = myemployees.get(employeeindex).getSells();
		commissiontotal = commissiontotal * (myemployees.get(employeeindex).getComission());
		double finalsalary = commissiontotal + myemployees.get(employeeindex).getSalary();
		myemployees.get(employeeindex).setPayment(finalsalary);
	}
	
	public static void SyndicateCharge(ArrayList<Employees> myemployees,int employeeindex) {
		if(myemployees.get(employeeindex).getSyndicate().equals("1") && myemployees.get(employeeindex).getPayment() != 0) {
			double salary = myemployees.get(employeeindex).getPayment();
			double tax =  myemployees.get(employeeindex).getSyndicateTax();
			double servicetax = myemployees.get(employeeindex).getSyndicateServiceTax();
			tax = salary * tax;
			System.out.println("First Aqui : " + salary);
			salary = salary - tax;
			System.out.println("Aqui : " + salary);
			if(servicetax != 0) {
				servicetax = salary * servicetax;
				salary = salary - servicetax;
				System.out.println("E aqui : " + salary);
			}
			myemployees.get(employeeindex).setPayment(salary);
		}
	}
}
