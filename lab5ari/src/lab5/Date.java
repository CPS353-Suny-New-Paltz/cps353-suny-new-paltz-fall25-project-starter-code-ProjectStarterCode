package lab5;
public class Date {
		private String month;
		private int day,year;
		private static String[] months={"January","February","March","April","May","June","July","August","September","October","November","December"};
		public Date(String m,int d,int y)
		{
			setMonth(m);
			setDay(d);
			setYear(y);
		}
		public Date(String m,int y)
		{
			this(m,0,y);
		}
		
		public void setMonth(String m)
		{
			for(int i=0;i<months.length;i++)
			{
				if(m.equals(months[i]))
				{
					month=m;
					return;
				}
			}
			System.out.println("Invalid month");
			System.exit(1);
		}
		public void setDay(int d)
		{
			if(d>=1 && d<=31)
				day=d;
			else
				day=1;
		}
		public void setYear(int y)
		{
			if(y>=1800 && y<=2030)
				year=y;
			else
				year=1800;
		}
		public String getMonth()
		{
			return month;
		}
		public int getDay()
		{
			return day;
		}
		public int getYear()
		{
			return year;
		}
		
		public String toString()
		{
			return convert()+"/"+day+"/"+year;
		}
		public int convert()
		{
			for(int i=0;i<months.length;i++)
			{
				if(month.equals(months[i]))
					return i+1;
			}
			return 0;
		}
		public boolean equals(Date d)
		{
			return month.equals(d.getMonth())&&day==d.getDay()&&year==d.getYear();
		}
	
}
