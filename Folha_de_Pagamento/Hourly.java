package Folha;

public class Hourly {
	private int ArrivalTime;
	private int ExitTime;
	private double PaymentOfDay;
	
	public int getArrivalTime() {
		return ArrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.ArrivalTime = arrivalTime;
	}
	public int getExitTime() {
		return ExitTime;
	}
	public void setExitTime(int exitTime) {
		this.ExitTime = exitTime;
	}
	public double getPaymentOfDay() {
		return PaymentOfDay;
	}
	public void setPaymentOfDay(double paymentOfDay) {
		this.PaymentOfDay = paymentOfDay;
	}
}
