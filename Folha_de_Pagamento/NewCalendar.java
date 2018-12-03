package Folha;

import java.util.ArrayList;

public class NewCalendar {
	private String PaymentType;
	private String EmployeeType;
	
	public static void PassDay(ArrayList<Employees> myemployees,int day,int month,int year,int dayofweek) {
		System.out.println("Hoje: " + day + "/" + month + "/" + year + " Dia da Semana: " + dayofweek);
		
		int hasemployees = 0;
		
		System.out.println("\n-----------FOLHA DE PAGAMENTO----------");
		for(int i = 0;i < myemployees.size();i++) {
			if((myemployees.get(i).getDayOfPayment() == day) && (myemployees.get(i).getMonthOfPayment() == month) && (myemployees.get(i).getYearOFPayment() == year) && (myemployees.get(i).getDayofWeekPayment() == dayofweek)) {
				hasemployees++;
				System.out.println("Nome: " + myemployees.get(i).getName());
				System.out.println("Endereco: " + myemployees.get(i).getAddress());
				System.out.println("Tipo: " + myemployees.get(i).getType());
				System.out.println("O Modo de Pagamento: " + myemployees.get(i).getPaymentMethod());
				if(myemployees.get(i).getSyndicate().equals("1")) {
					System.out.println("Faz parte de Sindicato");
					NewSalary.SyndicateCharge(myemployees, i);
					System.out.println("Taxa de Sindicato: " + myemployees.get(i).getSyndicateTax());
					if(myemployees.get(i).getSyndicateServiceTax() != 0) {
						System.out.println("Taxa de Servico do Sindicato: " + myemployees.get(i).getSyndicateServiceTax());
						myemployees.get(i).setSyndicateServiceTax(0);
					}
					else System.out.println("Nao ha taxas de servico do sindicato");
				}
				else System.out.println("Nao faz parte de Sindicato");
				System.out.println("Pagamento: " + myemployees.get(i).getPayment());
				System.out.println("---------------------------------------");
				myemployees.get(i).setFirstDay(day);
				myemployees.get(i).setFirstMonth(month);
				myemployees.get(i).setFirstYear(year);
				myemployees.get(i).setFirstDayOfWeek(dayofweek);
				NewCalendar.PaymentSchedule(myemployees,i);
				if(myemployees.get(i).getType().equals("horista")) {
					myemployees.get(i).getPaymentDaily().clear();
				}
				else if(myemployees.get(i).getType().equals("comissionado")) {
					double currentsells = myemployees.get(i).getSells();
					currentsells = currentsells * -1;
					myemployees.get(i).setSells(currentsells);
				}
				myemployees.get(i).setPayment(0);
			}
		}
		if(hasemployees == 0) {
			System.out.println("Nao ha nenhum funcionario a ser pago hoje");
		}
		System.out.println("---------------------------------------");
	}
	
	public static void PaymentSchedule(ArrayList<Employees> myemployees,int index) {
		if(myemployees.get(index).getType().equals("horista")) {
			String currentPaymentSchedule = myemployees.get(index).getPaymentSchedule();
			int DayIWant = 0;
			if(currentPaymentSchedule.equals("semanal 1 segunda")) DayIWant = 2;
			else if(currentPaymentSchedule.equals("semanal 1 terca")) DayIWant = 3;
			else if(currentPaymentSchedule.equals("semanal 1 quarta")) DayIWant = 4;
			else if(currentPaymentSchedule.equals("semanal 1 quinta"))  DayIWant = 5;
			else if(currentPaymentSchedule.equals("semanal 1 sexta") || currentPaymentSchedule.equals("semanalmente")) DayIWant = 6;
				
			int dayp,monthp,yearp,dayofweekp;
			dayp = myemployees.get(index).getFirstDay();
			monthp = myemployees.get(index).getFirstMonth();
			yearp = myemployees.get(index).getFirstYear();
			dayofweekp = myemployees.get(index).getFirstDayOfWeek(); 
				
			for(int i = 0;i < 7;i++) {
				dayp++;
				dayofweekp++;
				if(dayofweekp == DayIWant) {
					break;
				}
				if(dayofweekp == 8) dayofweekp = 1;
				if(dayp == 29 && monthp == 2) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 32) {
					dayp = 1;
					monthp++;
				}
				if(monthp == 13) {
					monthp = 1;
					yearp++;
				}
			}
			myemployees.get(index).setDayOfPayment(dayp);
			myemployees.get(index).setMonthOfPayment(monthp);
			myemployees.get(index).setYearOFPayment(yearp);
			myemployees.get(index).setDayofWeekPayment(dayofweekp);
		}
		else if(myemployees.get(index).getType().equals("assalariado")){
			if(myemployees.get(index).getPaymentSchedule().equals("mensalmente") || myemployees.get(index).getPaymentSchedule().equals("mensal $")) {
				int dayp,monthp,yearp,dayofweekp;
				dayp = myemployees.get(index).getFirstDay();
				monthp = myemployees.get(index).getFirstMonth();
				yearp = myemployees.get(index).getFirstYear();
				dayofweekp = myemployees.get(index).getFirstDayOfWeek(); 
				for(int i = 0;i < 31;i++) {
					dayp++;
					dayofweekp++;
					if(dayofweekp == 8) dayofweekp = 1;
					if(dayp == 28 && monthp == 2) break;
					else if(dayp == 29 && monthp == 2) {
						dayp = 1;
						monthp++;
					}
					if(dayp == 30 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) break;
					else if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
						dayp = 1;
						monthp++;
					}
					else if(dayp == 31) break;
					else if(dayp == 32) {
						dayp = 1;
						monthp++;
					}
					if(monthp == 13) {
						monthp =1;
						yearp++;
					}
				}
				if(dayofweekp == 7) {
					dayp--;
				}
				else if(dayofweekp == 1) {
					dayp -= 2;
				}
				myemployees.get(index).setDayOfPayment(dayp);
				myemployees.get(index).setMonthOfPayment(monthp);
				myemployees.get(index).setYearOFPayment(yearp);
				myemployees.get(index).setDayofWeekPayment(dayofweekp);
			}
			else {
				String currentSchedule = myemployees.get(index).getPaymentSchedule();
				String now = null;
				int DayOFMonth = 0;
				now = currentSchedule.replaceAll("[^0-9]", "");
				DayOFMonth = Integer.parseInt(now);
				
				int dayp,monthp,yearp,dayofweekp;
				dayp = myemployees.get(index).getFirstDay();
				monthp = myemployees.get(index).getFirstMonth();
				yearp = myemployees.get(index).getFirstYear();
				dayofweekp = myemployees.get(index).getFirstDayOfWeek(); 
				
				for(int i = 0;i < 30;i++) {
					dayp++;
					dayofweekp++;
					if(dayp == DayOFMonth) break;
					if(dayofweekp == 8) dayofweekp = 1;
					else if(dayp == 29 && monthp == 2) {
						dayp = 1;
						monthp++;
					}
					if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
						dayp = 1;
						monthp++;
					}
					else if(dayp == 32) {
						dayp = 1;
						monthp++;
					}
					if(monthp == 13) {
						monthp = 1;
						yearp++;
					}
				}
				myemployees.get(index).setDayOfPayment(dayp);
				myemployees.get(index).setMonthOfPayment(monthp);
				myemployees.get(index).setYearOFPayment(yearp);
				myemployees.get(index).setDayofWeekPayment(dayofweekp);
			}
		}
		else if(myemployees.get(index).getType().equals("comissionado")) {
			
			String currentPaymentSchedule = myemployees.get(index).getPaymentSchedule();
			int DayIWant = 0;
			if(currentPaymentSchedule.equals("semanal 2 segunda")) DayIWant = 2;
			else if(currentPaymentSchedule.equals("semanal 2 terca")) DayIWant = 3;
			else if(currentPaymentSchedule.equals("semanal 2 quarta")) DayIWant = 4;
			else if(currentPaymentSchedule.equals("semanal 2 quinta"))  DayIWant = 5;
			else if(currentPaymentSchedule.equals("semanal 2 sexta") || currentPaymentSchedule.equals("bi-semanalmente")) DayIWant = 6;
			
			int dayp,monthp,yearp,dayofweekp,totaldays = 0;
			dayp = myemployees.get(index).getFirstDay();
			monthp = myemployees.get(index).getFirstMonth();
			yearp = myemployees.get(index).getFirstYear();
			dayofweekp = myemployees.get(index).getFirstDayOfWeek(); 
			
			for(int i = 0;i < 15;i++) {
				dayp++;
				dayofweekp++;
				if(dayofweekp == 8) dayofweekp = 1;
				if(dayofweekp == DayIWant) {
					totaldays++;
				}
				if(dayofweekp == DayIWant && totaldays == 2) {
					break;
				}
				if(dayp == 29 && monthp == 2) {
					dayp = 1;
					monthp++;
				}
				if(dayp == 31 && (monthp == 4 || monthp == 6 || monthp == 9 || monthp == 11)) {
					dayp = 1;
					monthp++;
				}
				else if(dayp == 32) {
					dayp = 1;
					monthp++;
				}
				if(monthp == 13) {
					monthp = 1;
					yearp++;
				}
			}
			myemployees.get(index).setDayOfPayment(dayp);
			myemployees.get(index).setMonthOfPayment(monthp);
			myemployees.get(index).setYearOFPayment(yearp);
			myemployees.get(index).setDayofWeekPayment(dayofweekp);
		}
	}

	public String getPaymentType() {
		return PaymentType;
	}

	public void setPaymentType(String paymentType) {
		this.PaymentType = paymentType;
	}

	public String getEmployeeType() {
		return EmployeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.EmployeeType = employeeType;
	}
}