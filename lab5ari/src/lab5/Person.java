package lab5;

public class Person
{
		private Name name;
		private Date birthday;
		private long ssn;
		
		public Person(Name n,Date d,long s)
		{
			name=n;
			birthday=d;
			ssn=s;
		}
		public Person(Name n)
		{
			this(n,new Date("January",1,2023),123456789);
		}
		public Name getName()
		{
			return name;
		}
		public Date getBirthday()
		{
			return birthday;
		}
		private long getSSN()
		{
			return ssn;
		}
		public void setName(Name n)
		{
			name=n;
		}
		public void setBirthday(Date d)
		{
			birthday=d;
		}
		public String toString()
		{
			return "Name:"+name+"\nBirthday: "+birthday+"\n";
		}
		
	
}
