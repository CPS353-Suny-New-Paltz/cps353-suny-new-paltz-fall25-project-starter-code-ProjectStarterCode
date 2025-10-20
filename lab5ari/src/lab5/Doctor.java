package lab5;

public class Doctor extends Medical
{
		private static long id=1000;
		private long d_ID;
		private String[] specialty;
		private String email;
		
		public Doctor(Name n,String[] specialty,double salary)
		{
			super(n,salary);
			d_ID=id++;
			this.specialty=specialty;
			//email=getEmail(); //Add this method
		}
		
		public long getId()
		{
			return d_ID;
		}
		public String[] getSpecialty()
		{
			return specialty;
		}
}
