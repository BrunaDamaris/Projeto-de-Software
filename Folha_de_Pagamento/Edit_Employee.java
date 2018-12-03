package Folha;

import java.util.Scanner;
import java.util.ArrayList;

public class Edit_Employee {
	public static void EditProfile(ArrayList <Employees> myemployees,Scanner input,int employeeindex) {
		String changes,entry,newname;
		int ids;
		
		//Name
		System.out.println("O Nome atual e: " + myemployees.get(employeeindex).getName());
		System.out.println("Se deseja mudar o Nome pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe o novo Nome: ");
			newname = input.nextLine();
			myemployees.get(employeeindex).setName(newname);
		}
		else {
			newname = myemployees.get(employeeindex).getName();
		}
		
		//Address
		System.out.println("O Endereco atual e: " + myemployees.get(employeeindex).getAddress());
		System.out.println("Se deseja mudar o Endereco pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe o novo Endereco: ");
			changes = input.nextLine();
			myemployees.get(employeeindex).setAddress(changes);
		}
		
		//Type
		System.out.println("O Tipo(horista, assalariado,comissionario) atual e: " + myemployees.get(employeeindex).getType());
		System.out.println("Se deseja mudar o Tipo pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			double salary = 0;
			System.out.println("Informe o novo Tipo(horista, assalariado,comissionario): ");
			changes = input.nextLine();
			myemployees.get(employeeindex).setType(changes);
			
			if(changes.equals("horista")) {
				System.out.println("Digite o salario por hora: ");
				salary = input.nextDouble();
			}
			else if(changes.equals("assalariado")) {
				System.out.println("Digite o salario mensal: ");
				salary = input.nextDouble();
			}
			else if(changes.equals("comissionado")) {
				System.out.println("Digite o salario quizenal: ");
				salary = input.nextDouble();
				double commission;
				System.out.println("Informe a comissao: ");
				commission = input.nextDouble();
				myemployees.get(employeeindex).setComission(commission);
			}
			myemployees.get(employeeindex).setSalary(salary);
		}
		
		//Payment Method
		System.out.println("O Modo de Pagamento atual e: " + myemployees.get(employeeindex).getPaymentMethod());
		System.out.println("Se deseja mudar o Modo de Pagamento pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe o novo Modo de Pagamento: ");
			changes = input.nextLine();
			myemployees.get(employeeindex).setType(changes);
		}
		
		//Syndicate
		if(myemployees.get(employeeindex).getSyndicate().equals("1")) {
			System.out.println("Faz parte de Sindicato");
		}
		else System.out.println("Nao faz parte de Sindicato");
		
		System.out.println("Se deseja mudar o Status de Sindicato pressione 1. Se nao, pressione enter");
		entry = input.nextLine();
		if(entry.equals("1")) {
			System.out.println("Informe o novo Status de Sindicato(1 ou 2): ");
			changes = input.nextLine();
			myemployees.get(employeeindex).setSyndicate(changes);
		}
		
		//Syndicate Id
		if(myemployees.get(employeeindex).getSyndicate().equals("1")) {
			int employeeid = myemployees.get(employeeindex).getEmployeeId();
			int newsyndicateid = employeeid + 1000;
			myemployees.get(employeeindex).setSyndicateId(newsyndicateid);
			System.out.println("O Id de Sindicato atual e: " +  myemployees.get(employeeindex).getSyndicateId());
			System.out.println("Se deseja mudar o Id de Sindicato pressione 1. Se nao, pressione enter");
			entry = input.nextLine();
			if(entry.equals("1")) {
				int validatesyndicate = 0;
				while(true) {
					System.out.println("Informe o novo Id de Sindicato: ");
					ids = input.nextInt();
					for(int i = 0;i < myemployees.size();i++) {
						if(myemployees.get(i).getSyndicateId() == ids) {
							System.out.println("Id ja existe, tente novamente");
							validatesyndicate++;
						}
					}
					if(validatesyndicate == 0) {
						myemployees.get(employeeindex).setSyndicateId(ids);
						System.out.println("Novo Id de Sindicato mudado com sucesso");
						break;
					}
					validatesyndicate = 0;
				}
			}
			
			//Syndicate Tax
			double newtax;
			System.out.println("A Taxa de Sindicato atual e:" + myemployees.get(employeeindex).getSyndicateTax());
			System.out.println("Se deseja mudar a taxa de Sindicato pressione 1. Se nao, pressione enter");
			entry = input.nextLine();
			if(entry.equals("1")) {
				System.out.println("Informe a nova Taxa de Sindicato: ");
				newtax = input.nextDouble();
				myemployees.get(employeeindex).setSyndicateTax(newtax);
			}
		}
	}
}
