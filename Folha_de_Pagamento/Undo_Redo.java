package Folha;

import java.util.ArrayList;
import java.util.Stack;

public class Undo_Redo {
	private Stack<Employees> UndoStack;
	private Stack<Employees> RedoStack;
	
	public static void CreateToUndo_Redo(ArrayList<Employees> myemployees,int index,Stack<Employees> undo1) {
		String Name = myemployees.get(index).getName();
		String Address = myemployees.get(index).getAddress();
		String Type = myemployees.get(index).getType();
		String PaymentMethod = myemployees.get(index).getPaymentMethod();
		int EmployeeId = myemployees.get(index).getEmployeeId();
		double Salary = myemployees.get(index).getSalary();
		double Payment = myemployees.get(index).getPayment();
		String PaymentSchedule = myemployees.get(index).getPaymentSchedule();
		int DayOfPayment = myemployees.get(index).getDayOfPayment();
		int MonthOfPayment = myemployees.get(index).getMonthOfPayment();
		int YearOFPayment = myemployees.get(index).getYearOFPayment();
		int DayofWeekPayment = myemployees.get(index).getDayofWeekPayment();
		int FirstDay = myemployees.get(index).getFirstDay();
		int FirstMonth = myemployees.get(index).getFirstMonth();
		int FirstYear = myemployees.get(index).getFirstYear();
		int FirstDayOfWeek = myemployees.get(index).getFirstDayOfWeek();
		String Syndicate = myemployees.get(index).getSyndicate();
		int SyndicateId = myemployees.get(index).getSyndicateId();
		double SyndicateTax = myemployees.get(index).getSyndicateTax();
		double SyndicateServiceTax = myemployees.get(index).getSyndicateServiceTax();
		double Commission = myemployees.get(index).getComission();
		double Sells = myemployees.get(index).getSells();
		ArrayList<Hourly> PaymentDaily = new ArrayList<Hourly>();
		PaymentDaily =  myemployees.get(index).getPaymentDaily();
		
		Employees Aux = new Employees();
		Aux.setName(Name);
		Aux.setAddress(Address);
		Aux.setType(Type);
		Aux.setPaymentMethod(PaymentMethod);
		Aux.setEmployeeId(EmployeeId);
		Aux.setSalary(Salary);
		Aux.setPayment(Payment);
		Aux.setPaymentSchedule(PaymentSchedule);
		Aux.setDayOfPayment(DayOfPayment);
		Aux.setMonthOfPayment(MonthOfPayment);
		Aux.setYearOFPayment(YearOFPayment);
		Aux.setDayofWeekPayment(DayofWeekPayment);
		Aux.setFirstDay(FirstDay);
		Aux.setFirstMonth(FirstMonth);
		Aux.setFirstYear(FirstYear);
		Aux.setFirstDayOfWeek(FirstDayOfWeek);
		Aux.setSyndicate(Syndicate);
		Aux.setSyndicateId(SyndicateId);
		Aux.setSyndicateTax(SyndicateTax);
		Aux.setSyndicateServiceTax(SyndicateServiceTax);
		Aux.setComission(Commission);
		Aux.setSells(Sells);
		Aux.setPaymentDaily(PaymentDaily);
		
		undo1.push(Aux);
	}
	
	public static void Undo(ArrayList<Employees> myemployees,Stack<Employees> undo1,Stack<Employees> redo1) {
		if(undo1.size() != 0) {
			Employees Current = undo1.pop();
			if(undo1.size() != 0 ) Current = undo1.pop();
			myemployees.add(Current);
			for(int i = 0;i < myemployees.size();i++) {
				if(Current.getEmployeeId() == myemployees.get(i).getEmployeeId()) {
					redo1.push(myemployees.get(i));
					myemployees.remove(i);
				}
			}
		}
	}
	public static void Redo(ArrayList<Employees> myemployees,Stack<Employees> redo1,Stack<Employees> undo1) {
		if(redo1.size() != 0) {
			Employees Current = redo1.pop();
			myemployees.add(Current);
			for(int i = 0;i < myemployees.size();i++) {
				if(Current.getEmployeeId() == myemployees.get(i).getEmployeeId()) {
					undo1.push(myemployees.get(i));
					myemployees.remove(i);
				}
			}
		}
	}
	public Stack<Employees> getUndoStack() {
		return UndoStack;
	}
	public void setUndoStack(Stack<Employees> undoStack) {
		this.UndoStack = undoStack;
	}
	public Stack<Employees> getRedoStack() {
		return RedoStack;
	}
	public void setRedoStack(Stack<Employees> redoStack) {
		this.RedoStack = redoStack;
	}
}
