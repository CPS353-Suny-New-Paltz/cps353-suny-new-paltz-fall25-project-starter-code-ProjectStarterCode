package lab5;

public class Medical extends Person
{
	
		private double salary;
		public Medical(Name n,double s)
		{
			super(n);
			salary=s;
		}
		public void setSalary(double s)
		{
			salary=s;
		}
		public double getSalary()
		{
			return salary;
		}
	
}
