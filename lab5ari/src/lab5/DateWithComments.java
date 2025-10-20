package lab5;
/*
 * @author Ashley Suchy
 * 
 */


 // TODO: Auto-generated Javadoc
/**
  * The Class Date is used to capture the idea of a date in the format day,month,year.
  */
import lab5.Date;

public class DateWithComments {
		
		/** The month. */
		private String month;
		
		/** The year. */
		private int day,year;
		
		/** The months. */
		private static String[] months={"January","February","March","April","May","June","July","August","September","October","November","December"};
		
		/**
		 * Instantiates a new date.
		 *
		 * @param m the month
		 * @param d the day
		 * @param y the year
		 */
		public DateWithComments(String m,int d,int y)
		{
			setMonth(m);//TODO: fix bug here
			setDay(d);
			setYear(y);
		}
		
		/**
		 * Instantiates a new date with the day set to 0.
		 *
		 * @param m the month
		 * @param y the year
		 * 
		 */
		public DateWithComments(String m,int y)
		{
			this(m,0,y);
		}
		
		/**
		 * Sets the month.
		 *
		 * @param m the new month
		 */
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
		
		/**
		 * Sets the day.
		 *
		 * @param d the new day
		 */
		public void setDay(int d)
		{
			if(d>=1 && d<=31)
				day=d;
			else
				day=1;
		}
		
		/**
		 * Sets the year.
		 *
		 * @param y the new year
		 */
		public void setYear(int y)
		{
			if(y>=1800 && y<=2030)
				year=y;
			else
				year=1800;
		}
		
		/**
		 * Gets the month.
		 *
		 * @return the month
		 */
		public String getMonth()
		{
			return month;
		}
		
		/**
		 * Gets the day.
		 *
		 * @return the day
		 */
		public int getDay()
		{
			return day;
		}
		
		/**
		 * Gets the year.
		 *
		 * @return the year
		 */
		public int getYear()
		{
			return year;
		}
		
		/**
		 * To string.
		 *
		 * @return the string
		 */
		public String toString()
		{
			return convert()+"/"+day+"/"+year;
		}
		
		/**
		 * Convert the string month into an integer.
		 *
		 * @return the int form of the month
		 */
		public int convert()
		{
			for(int i=0;i<months.length;i++)
			{
				if(month.equals(months[i]))
					return i+1;
			}
			return 0;
		}
		
		/**
		 * Equals.
		 *
		 * @param d the Date
		 * @return true, if the dates have the same month, day and year values
		 */
		public boolean equals(Date d)
		{
			return month.equals(d.getMonth())&&day==d.getDay()&&year==d.getYear();
		}
	
}
