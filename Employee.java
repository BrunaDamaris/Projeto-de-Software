import java.util.Scanner;
import java.util.ArrayList;

public class Employee {
		String ename;
		String eadress;
		String etype;
		int employeenumber;
	public void setName(String name) {
		ename = name;
	}
	public void setAdress(String adress) {
		eadress = adress;
	}
	public void setType(String type) {
		etype = type;
	}
	public void setNumber(int number) {
		employeenumber = number;
	}
	public static void addnewemployee(String name, String adress, String type, int number,ArrayList<Employee> employees) {
		Employee newemployee =  new Employee();
		newemployee.setName(name);
		newemployee.setAdress(adress);
		newemployee.setType(type);
		newemployee.setNumber(number);
		employees.add(newemployee);
	}
	public static int generatenumber(int number) {
		number++;
		return number;
	}
	/*public removeemployee(int[] employees) {

	}*/
	public static void main( String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Operações disponiveis:\n\nAdicionar(1)\n\nDeletar(2)\n\nLançar um Cartão de Ponto(3)\n\nLançar um Resultado Venda(4)\n\nLançar uma taxa de serviço(5)\n\nAlterar detalhes de um empregado(6)\n\nRodar folha de pagamento para hoje(7)\n\nUndo/redo(8)\n\nAgenda de Pagamento(9)\n\nCriação de Novas Agendas de Pagamento(10)");
		int operation;
		String name;
		String adress;
		String type;
		int number;
		String trash;
		operation = input.nextInt();
		trash = input.nextLine();
		java.util.ArrayList<Employee> employees = new java.util.ArrayList<Employee>();
		if(operation == 1) {
			name = input.nextLine();
			trash = input.nextLine();
			adress = input.nextLine();
			trash = input.nextLine();
			type = input.nextLine();
			trash = input.nextLine();
			number = 0;
			number = generatenumber(number);
			addnewemployee(name,adress,type,number,employees);
			System.out.println(name);
		}
		else if(operation == 2) {
			System.out.println("Quem você quer remover? Digite o número do empregado");
			int removenumber,index;
			removenumber = input.nextInt();
			for(index = 0; index < 100;index++){
				System.out.println(removenumber);
				if(removenumber == index) {
					employees.remove(index);
					System.out.println("Empregado número " + index + "removido");
				}
			}
		}
		else if(operation == 3) {

		}
		else if(operation == 4) {

		}
		else if(operation == 5) {

		}
		else if(operation == 6) {

		}
		else if(operation == 7) {

		}
		else if(operation == 8) {

		}
		else if(operation == 9) {

		}
		else if(operation == 10) {

		}

		input.close();
	}
}
