package Folha;

import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Calendar;

public class Principal {
	public static void main(String []args) {
		Scanner input  = new Scanner (System.in);
		ArrayList<Employees> myemployees = new ArrayList<Employees>();
		ArrayList<NewCalendar> paymentschedules = new ArrayList<NewCalendar>();
		
		Stack<Employees> undo1 = new Stack<Employees>();
		Stack<Employees> redo1 = new Stack<Employees>();

		
		Undo_Redo NewUndo_Redo = new Undo_Redo();
		NewUndo_Redo.setUndoStack(undo1);
		NewUndo_Redo.setRedoStack(redo1);
		
		String first_entry = null,trash = null;
		String name,address,type,paymentmethod,syndicate,continuen,previous_entry = null;
		int previousday = 0,previousmonth = 0,previousyear = 0,previousdayofweek = 0;
		int id = 0, newhourlyverify = 0,positioninarray = -1,lasthourly = 0,previousarrival = 0;
		double salary = 0;
		double sellresult = 0;
		Calendar newCalendar = Calendar.getInstance();
		int day = newCalendar.get(Calendar.DAY_OF_MONTH);
		previousday = day;
		int month = newCalendar.get(Calendar.MONTH);
		month++;
		previousmonth = month;
		int year = newCalendar.get(Calendar.YEAR);
		previousyear = year;
		int dayofweek = newCalendar.get(Calendar.DAY_OF_WEEK);
		previousdayofweek = dayofweek;
		
		//SetDefaultPaymentSchedules
		NewCalendar SetDefault0 = new NewCalendar();
		SetDefault0.setPaymentType("semanalmente");
		SetDefault0.setEmployeeType("horista");
		paymentschedules.add(SetDefault0);
		
		NewCalendar SetDefault1 = new NewCalendar();
		SetDefault1.setPaymentType("mensalmente");
		SetDefault1.setEmployeeType("assalariado");
		paymentschedules.add(SetDefault1);
		
		NewCalendar SetDefault2 = new NewCalendar();
		SetDefault2.setPaymentType("bi-semanalmente");
		SetDefault2.setEmployeeType("comissionado");
		paymentschedules.add(SetDefault2);
		
			
		while(true) {
			
			previous_entry = first_entry;
			System.out.println("\nAdicionar Empregado(1)\n\nVisualizar Empregados(2)\n\nRemover Funcionario(3)\n\nLancar Cartao de Ponto(4)\n\nLancar Resultado de Venda(5)\n\nLancar uma Taxa de Servico(6)\n\nAlterar detalhes de um Empregado(7)\n\nRodar Folha de Pagamento(8)\n\nUndo/Redo(9)\n\nAgenda de Pagamento(10)\n\nNova Agenda de Pagamento(11)\n\nSair(0)\n");
			first_entry = input.nextLine();
			
			//Option 1
			if(first_entry.equals("1")) {
				System.out.println("Informe o nome do novo empregado: ");
				name = input.nextLine();
				System.out.println("Informe o endereco do novo empregado: ");
				address = input.nextLine();
				System.out.println("Informe o tipo do novo empregado(horista,assalariado ou comissionado): ");
				type = input.nextLine();
				System.out.println("Informe como o novo empregado deseja ser pago(cheque ou deposito): ");
				paymentmethod = input.nextLine();
				if(paymentmethod.equals("cheque")) {
					System.out.println("Se prefere cheque em maos, pressione 1. Se prefere cheque em banco, pressione 2");
					String entry = input.nextLine();
					if(entry.equals("1")) {
						paymentmethod = "cheque em maos";
					}
					else {
						paymentmethod = "cheque em banco";
					}
				}
				
				System.out.println("O novo empregado faz parte de algum sindicato?Se sim, pressione 1. Se nao, pressione 2");
				syndicate =  input.nextLine();
				
				id += 1;
				
				if(type.equals("horista")) {
					System.out.println("Digite o salario por hora: ");
					salary = input.nextDouble();
					trash = input.nextLine();
				}
				else if(type.equals("assalariado")) {
					System.out.println("Digite o salario mensal: ");
					salary = input.nextDouble();
					trash = input.nextLine();
					
				}
				else if(type.equals("comissionado")) {
					System.out.println("Digite o salario quizenal: ");
					salary = input.nextDouble();
					trash = input.nextLine();
				}
				
				Employees.addEmployee(myemployees,name,address,type,id,paymentmethod,syndicate,salary,input,day,month,year,dayofweek);
				
				int isemployee = 0, employeeindex = 0;
				for(int i = 0;i < myemployees.size();i++) {
					if(id == myemployees.get(i).getEmployeeId()) {
						isemployee++;
						employeeindex = i;
					}
				}
				if(isemployee == 0) System.out.println("Nao encontrado.");
				else {
					NewCalendar.PaymentSchedule(myemployees,employeeindex);
					Undo_Redo.CreateToUndo_Redo(myemployees, employeeindex, undo1);
				}
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
				
			}
			
			//Option 2
			else if(first_entry.equals("2")) {
				String entry;
				int currentemployeeid = 0;
				int hasemployee = 0;
				System.out.println("Deseja visualizar um Empregado(1), ou todos Empregados(2)");
				entry = input.nextLine();
				if(entry.equals("1")) {
					System.out.println("Informe o Id do Empregado: ");
					currentemployeeid = input.nextInt();
					trash = input.nextLine();
					for(int i = 0;i < myemployees.size();i++) {
						if(myemployees.get(i).getEmployeeId() == currentemployeeid) {
							hasemployee++;
							System.out.println("Nome: " + myemployees.get(i).getName());
							System.out.println("Id do Empregado: " + myemployees.get(i).getEmployeeId());
							System.out.println("Endereco: " + myemployees.get(i).getAddress());
							System.out.println("Tipo: " + myemployees.get(i).getType());
							System.out.println("O Modo de Pagamento: " + myemployees.get(i).getPaymentMethod());
							if(myemployees.get(i).getSyndicate().equals("1")) {
								System.out.println("Faz parte de Sindicato");
								System.out.println("Taxa de Sindicato: " + myemployees.get(i).getSyndicateTax());
								if(myemployees.get(i).getSyndicateServiceTax() != 0) {
									System.out.println("Taxa de Servico do Sindicato: " + myemployees.get(i).getSyndicateServiceTax());
								}
								else System.out.println("Nao ha taxas de servico do sindicato");
							}
							else System.out.println("Nao faz parte de Sindicato");
							System.out.println("Salario atual: " + myemployees.get(i).getSalary());
							System.out.println("Dia de Pagamento: " + myemployees.get(i).getDayOfPayment() + " " + myemployees.get(i).getMonthOfPayment() + " " + myemployees.get(i).getYearOFPayment() + " " + myemployees.get(i).getDayofWeekPayment());
						}
					}
					if(hasemployee == 0) System.out.println("Nao foi possivel achar o Id"); 
				}
				else if(entry.equals("2")) {
					for(int i = 0;i < myemployees.size();i++) {
						System.out.println("Nome do Empregado: " + myemployees.get(i).getName() + ". Id do Empregado: " + myemployees.get(i).getEmployeeId() + ". Salario do Empregado: " + myemployees.get(i).getSalary());
					}
				}
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
			}
			
			//Option 3
			else if(first_entry.equals("3")) {
				int removeid, validate = 0;
				System.out.println("Informe o Id do Empregado que deseja remover: ");
				removeid = input.nextInt();
				trash = input.nextLine();
				for(int i = 0;i < myemployees.size();i++) {
					if(myemployees.get(i).getEmployeeId() == removeid) {
						Undo_Redo.CreateToUndo_Redo(myemployees, i, undo1);
						myemployees.remove(i);
						validate++;
						System.out.println("Empregado removido com sucesso.");
					}
				}
				if(validate == 0) System.out.println("Empregado nao encontrado");
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
			}
			
			//Option 4
			else if(first_entry.equals("4")) {
				int currentemployeeid = 0,ishourly = 0,arrivaltime = 0,finaltime = 0;
				System.out.println("Informe o Id de empregado: ");
				currentemployeeid = input.nextInt();
				trash = input.nextLine();
				for(int i = 0;i < myemployees.size();i++) {
					if(currentemployeeid == myemployees.get(i).getEmployeeId()) {
						if(myemployees.get(i).getType().equals("horista")) {
							lasthourly = i;
							if(newhourlyverify == 0) {
								Hourly newhourly = new Hourly();
								newhourly.setArrivalTime(-1);
								newhourly.setExitTime(-1);
								newhourly.setPaymentOfDay(0);
								myemployees.get(i).getPaymentDaily().add(newhourly);
								newhourlyverify = 1;
								positioninarray++;
								Undo_Redo.CreateToUndo_Redo(myemployees, i, undo1);
							}
							if(newhourlyverify == 1){
								if((myemployees.get(i).getPaymentDaily().get(positioninarray).getArrivalTime() == -1)) {
									System.out.println("Informe a hora de chegada: ");
									arrivaltime = input.nextInt();
									previousarrival = arrivaltime;
									trash = input.nextLine();
									myemployees.get(i).getPaymentDaily().get(positioninarray).setArrivalTime(arrivaltime);
									ishourly++;
								}
								else if(myemployees.get(i).getPaymentDaily().get(positioninarray).getExitTime() == -1) {
									System.out.println("Informe a hora de saida: ");
									finaltime = input.nextInt();
									trash = input.nextLine();
									myemployees.get(i).getPaymentDaily().get(positioninarray).setExitTime(finaltime);
									ishourly++;
									newhourlyverify = 0;
									Undo_Redo.CreateToUndo_Redo(myemployees, i, undo1);
								}
							}
						}
					}
				}
				if(ishourly == 0) System.out.println("Nao e horista ou empregado nao existe");
			}
			
			//Option 5
			else if(first_entry.equals("5")) {
				int currentemployeeid = 0,iscommisioned = 0;
				String selldate;
				System.out.println("Informe o Id de empregado: ");
				currentemployeeid = input.nextInt();
				trash = input.nextLine();
				for(int i = 0;i < myemployees.size();i++) {
					if(currentemployeeid == myemployees.get(i).getEmployeeId()) {
						if(myemployees.get(i).getType().equals("comissionado")) {
							System.out.println("Informe a Data da Venda: ");
							selldate = input.nextLine();
							System.out.println(selldate + "\nInforme o Valor da Venda: ");
							sellresult = input.nextDouble();
							trash = input.nextLine();
							myemployees.get(i).setSells(sellresult);
							iscommisioned++;
							sellresult = 0;
							Undo_Redo.CreateToUndo_Redo(myemployees, i, undo1);
						}
					}
				}
				if(iscommisioned == 0) System.out.println("Nao e comissionado ou empregado nao existe");
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
			}
			
			//Option 6
			else if(first_entry.equals("6")) {
				int currentemployeeid = 0,ispartofsyndicate = 0;
				double servicefee = 0;
				System.out.println("Informe o Id de empregado: ");
				currentemployeeid = input.nextInt();
				trash = input.nextLine();
				for(int i = 0;i < myemployees.size();i++) {
					if(currentemployeeid == myemployees.get(i).getEmployeeId()) {
						if(myemployees.get(i).getSyndicate().equals("1")) {
							servicefee = input.nextDouble();
							trash = input.nextLine();
							myemployees.get(i).setSyndicateServiceTax(servicefee);
							ispartofsyndicate++;
							Undo_Redo.CreateToUndo_Redo(myemployees, i, undo1);
						}
					}
				}
				if(ispartofsyndicate == 0) System.out.println("Nao faz parte de sindicato ou empregado nao existe");
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
			}
			
			//Option 7
			else if(first_entry.equals("7")) {
				int currentemployeeid = 0,isemployee = 0,employeeindex = 0;
				System.out.println("Informe o Id de empregado: ");
				currentemployeeid = input.nextInt();
				trash = input.nextLine();
				for(int i = 0;i < myemployees.size();i++) {
					if(currentemployeeid == myemployees.get(i).getEmployeeId()) {
						isemployee++;
						employeeindex = i;
					}
				}
				if(isemployee == 0) System.out.println("Empregado nao existe");
				else {
					Edit_Employee.EditProfile(myemployees, input,employeeindex);
					Undo_Redo.CreateToUndo_Redo(myemployees, employeeindex, undo1);
				}
			}
			
			//Option 8
			else if(first_entry.equals("8")) {
				for(int i = 0;i < myemployees.size();i++) {
					if(myemployees.get(i).getType().equals("horista")) {
						NewSalary.HourlySalary(myemployees,i);
					}
					else if(myemployees.get(i).getType().equals("assalariado")) {
						NewSalary.SalariedSalary(myemployees,i);
						
					}
					else if(myemployees.get(i).getType().equals("comissionado")) {
						NewSalary.CommisionedSalary(myemployees,i);
					}
					Undo_Redo.CreateToUndo_Redo(myemployees, i, undo1);
				}
				
				NewCalendar.PassDay(myemployees,day,month,year,dayofweek);
				day++;
				previousday = day-1;
				dayofweek++;
				previousdayofweek = dayofweek-1;
				if(dayofweek == 8) dayofweek = 1;
				if(day == 29 && month == 2) {
					day = 1;
					previousday = 28;
					month++;
					previousmonth = month-1;
				}
				else if(day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
					day = 1;
					previousday = 30;
					month++;
					previousmonth = month-1;
				}
				else if(day == 32) {
					day = 1;
					previousday = 31;
					month++;
					previousmonth = month-1;
				}
				if(month == 13) {
					month = 1;
					previousmonth = 12;
					year++;
					previousyear = year-1;
				}
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
			}
			
			//Option 9
			else if(first_entry.equals("9")) {
				String option;
				System.out.println("Undo ou Redo?");
				option = input.nextLine();
				if(option.equals("Undo")) {
					if(previous_entry.equals("4")) {
						if(myemployees.get(lasthourly).getPaymentDaily().get(positioninarray).getArrivalTime() != -1) {
							myemployees.get(lasthourly).getPaymentDaily().get(positioninarray).setArrivalTime(-1);
						}
						
					}
					else if(previous_entry.equals("8")) {
						day = previousday;
						dayofweek = previousdayofweek;
						month = previousmonth;
						year = previousyear;
					}
					Undo_Redo.Undo(myemployees,undo1,redo1);
				}
				else if(option.equals("Redo")) {
					newhourlyverify = 1;
					if(myemployees.get(lasthourly).getPaymentDaily().get(positioninarray).getArrivalTime() == -1) {
						myemployees.get(lasthourly).getPaymentDaily().get(positioninarray).setArrivalTime(previousarrival);
					}
					if(day == previousday) day++;
					if(dayofweek == previousdayofweek) dayofweek++;
					if(previousmonth != month) month++;
					if(previousyear != year) year++;
					Undo_Redo.Redo(myemployees,redo1,undo1);
				}
			}
			
			//Option 10
			else if(first_entry.equals("10")) {
				int currentemployeeid = 0,isemployee = 0,employeeindex = 0;
				System.out.println("Informe o Id de empregado: ");
				currentemployeeid = input.nextInt();
				trash = input.nextLine();
				for(int i = 0;i < myemployees.size();i++) {
					if(currentemployeeid == myemployees.get(i).getEmployeeId()) {
						isemployee++;
						employeeindex = i;
					}
				}
				if(isemployee == 0) System.out.println("Empregado nao existe");
				else {
					String entry;
					System.out.println("Agenda de Pagamento atual - O Empregado e pago: " + myemployees.get(employeeindex).getPaymentSchedule());
					System.out.println("Deseja modificar a Agenda de Pagamento? Se sim, pressione 1. Se nao, pressione enter");
					entry = input.nextLine();
					if(entry.equals("1")) {
						System.out.println("Informe uma das opcoes abaixo: ");
						for(int i = 0;i < paymentschedules.size();i++) {
							if(myemployees.get(employeeindex).getType().equals(paymentschedules.get(i).getEmployeeType()))
							System.out.println(paymentschedules.get(i).getPaymentType());
						}
						String schedule;
						schedule = input.nextLine();
						myemployees.get(employeeindex).setPaymentSchedule(schedule);
						NewCalendar.PaymentSchedule(myemployees, employeeindex);
					}
				}
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
				
			}
			
			//Option 11
			else if(first_entry.equals("11")) {
				String newPaymentSchedule, newEmployeeTypeSchedule;
				int verify = 0;
				System.out.println("Informe que metodo deseja adicionar as Agendas de Pagamento: ");
				newPaymentSchedule = input.nextLine();
				System.out.println("Para qual tipo de Empregado? (horista, assalariado ou comissionado)");
				newEmployeeTypeSchedule = input.nextLine();
				for(int i = 0;i < paymentschedules.size();i++) {
					if(paymentschedules.get(i).getPaymentType().equals(newPaymentSchedule) && paymentschedules.get(i).getEmployeeType().equals(newEmployeeTypeSchedule)) {
						System.out.println("Agenda ja existe");
						verify++;
					}
				}
				if(verify == 0) {
					NewCalendar SetNew = new NewCalendar();
					SetNew.setPaymentType(newPaymentSchedule);
					SetNew.setEmployeeType(newEmployeeTypeSchedule);
					paymentschedules.add(SetNew);
					System.out.println("Criado.");
				}
				
				System.out.println("Para continuar, pressione enter");
				continuen = input.nextLine();
				System.out.println(continuen);
			}
			
			else {
				System.out.println(trash);
				System.out.println("Fim!");
				break;
			}
		}
		input.close();
	}
}
