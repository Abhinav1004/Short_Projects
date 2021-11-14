//import java.util.Scanner;
import java.text.NumberFormat; 
import java.util.Locale;
//import java.util.Currency; 
import java.util.*;
import java.text.DecimalFormat;
import java.text.DateFormatSymbols;

public class Concert {

	int day;
	int month;
	int year;
	String artistName;
	String venueOfConcert;
	int numberOfTicketsUpper;
	int numberOfTicketsLower;
	int numberOfTicketsFloor;
	double concertTotalSales;
	private static final int MAX_VALID_YR = 9999; 
	private static final int MIN_VALID_YR = 1800; 
	
	
	/** ticket prices for the different sections */
	private static final double PRICE_UPPER_TICKET = 29.90;
	private static final double PRICE_LOWER_TICKET = 99.0;
	private static final double PRICE_FLOOR_TICKET = 180.0;
	/** total number of tickets per section */
	private static final int TOTAL_NUMBER_UPPER_TICKETS= 300;
	private static final int TOTAL_NUMBER_LOWER_TICKETS= 300;
	private static final int TOTAL_NUMBER_FLOOR_TICKETS= 400;
	
	private boolean isLeapYear(int y){
	    // If a year is multiple of 400,  
	    // then it is a leap year  
	    if (y % 400 == 0)  
	        return true;  
	  
	    // Else If a year is multiple of 100,  
	    // then it is not a leap year  
	    if (y % 100 == 0)  
	        return false;  
	  
	    // Else If a year is multiple of 4,  
	    // then it is a leap year  
	    if (y % 4 == 0)  
	        return true;  
	    return false;
	}
	// Returns true if given 
	// year is valid or not. 
	private boolean isDateValid(int d, int m, int y) 
	{ 
	    // If year, month and day  
	    // are not in given range 
	    if (y > MAX_VALID_YR ||  
	        y < MIN_VALID_YR) 
	    return false; 
	    if (m < 1 || m > 12) 
	    return false; 
	    if (d < 1 || d > 31) 
	    return false; 
	  
	    // Handle February month  
	    // with leap year 
	    if (m == 2) 
	    { 
	        if (isLeapYear(y)) 
	        return (d <= 29); 
	        else
	        return (d <= 28); 
	    } 
	    // Months of April, June,  
	    // Sept and Nov must have  
	    // number of days less than 
	    // or equal to 30. 
	    if (m == 4 || m == 6 || 
	        m == 9 || m == 11) 
	        return (d <= 30); 
	  
	    return true; 
	}
	
	public Concert() {
		// TODO Auto-generated constructor stub
		day=9;
		month=8;
		year=2019;
		artistName = "Jonas Brothers";
		venueOfConcert = "Van Andel Arena";
		concertTotalSales = 0.0;
		numberOfTicketsUpper = TOTAL_NUMBER_UPPER_TICKETS;
		numberOfTicketsLower = TOTAL_NUMBER_LOWER_TICKETS;
		numberOfTicketsFloor = TOTAL_NUMBER_FLOOR_TICKETS;
	}
	public Concert (int m, int d, int y, String a, String v){
	if(!isDateValid(d, m, y)){
			System.out.println("Invalid date entered");
			return;
	}
	this.month = m;
	this.day = d;
	this.year = y;
	
	this.artistName = a;
	this.venueOfConcert = v;
	}
	public String getArtist(){
		return artistName;
	}
	public String getVenue(){
		return venueOfConcert;
	}
	public double getTicketPrice(char ticketType){
		if(ticketType=='U'){
			return PRICE_UPPER_TICKET;
		}
		else if(ticketType=='L'){
			return PRICE_LOWER_TICKET;
		}
		else if(ticketType=='F'){
			return PRICE_FLOOR_TICKET;
		}
		else return 0.0;
	}
	public int getAvailableUpperTickets (){
		return numberOfTicketsUpper;
	}
	public int getAvailableLowerTickets (){
		return numberOfTicketsLower;
	}
	public int getAvailableFloorTickets (){
		return numberOfTicketsFloor;
	}
	public double getTotalSales (){
		return concertTotalSales;
	}
	public int getMonth(){
		return month;
	}
	public int getDay(){
		return day;
	}
	public int getYear(){
		return year;
	}
	public void setArtist (String n){
		this.artistName = n;
	}
	public void setVenue (String n){
		this.venueOfConcert = n;
	}
	public void setDate(int m,int d,int year){
		if(isDateValid(d, m,year)){
			System.out.println("Invalid Date entered!!!");
			return;
		}
		this.month = m;
		this.day = d;
		this.year = year;
	}
	private void parseDate (String date){
		String[] dateParts = date.split("/");
		day = Integer.parseInt(dateParts[0]);
		month = Integer.parseInt(dateParts[1]);
		year = Integer.parseInt(dateParts[2]);
		if(!isDateValid(day, month, year)){
			System.out.println("Invalid Date Found!!!");
			return;
		}
	}
	public Concert (String date, String a, String v){
		parseDate(date);
		this.artistName = a;
		this.venueOfConcert = v;
	}
	public void buyTickets (char ticketType, int numTickets,double pmt){
		if(numTickets<0){
			System.out.println("Invalid Entry!! Cannot buy ticket");
			return;
		}
		else if(pmt<0){
			System.out.println("Invalid Entry!! Cannot buy ticket");
			return;
		}
		else if((ticketType!='U' && ticketType!='L') && ticketType!='F'){
			System.out.println("Invalid Entry!! Cannot buy ticket");
			return;
		}
		double calcAmount=0.0;
		if(ticketType=='U'){
			if(numberOfTicketsUpper<numTickets){
				System.out.println("Tickets not available in the Upper Section");
			}
			else{
				calcAmount = PRICE_UPPER_TICKET*numTickets;
				if(calcAmount<=pmt){
					System.out.println("Ticket Booked");
					numberOfTicketsUpper-= numTickets;
					concertTotalSales+= calcAmount;
					System.out.println("Amount to be returned="+(calcAmount-pmt));
				}
				else{
					System.out.println("Error - Payment not enough to buy the tickets");
				}
			}
		}
		else if(ticketType=='L'){
			if(numberOfTicketsLower<numTickets){
				System.out.println("Tickets not available in Lower Section");
			}
			else{
				calcAmount = PRICE_LOWER_TICKET*numTickets;
				if(calcAmount<=pmt){
					System.out.println("Ticket Booked");
					numberOfTicketsLower-= numTickets;
					concertTotalSales+= calcAmount;
					System.out.println("Amount to be returned="+(calcAmount-pmt));
				}
				else{
					System.out.println("Error - Payment not enough to buy the tickets");
				}
			}
		}
		else if(ticketType=='F'){
			if(numberOfTicketsFloor<numTickets){
				System.out.println("Tickets not available in Floor section");
			}
			else{
				calcAmount = PRICE_FLOOR_TICKET*numTickets;
				if(calcAmount<=pmt){
					System.out.println("Ticket Booked");
					numberOfTicketsFloor-= numTickets;
					concertTotalSales+= calcAmount;
					System.out.println("Amount Paid="+calcAmount);
					System.out.println("Amount to be returned="+(calcAmount-pmt));
				}
				else{
					System.out.println("Error - Payment not enough to buy the tickets");
				}
			}
		}
	}
	
	public String formatDate(int format) {
		String emptyString="";
		if(format==1) {
			String dateString = month+"/"+day+"/"+year;
			return dateString;
		}
		else if(format==2) {
			DecimalFormat df = new DecimalFormat("00");
			String dateString = df.format(month)+"/"+df.format(day)+"/"+df.format(year);
			return dateString;
		}
		else if(format==3) {
			String monthString = new DateFormatSymbols().getMonths()[month-1];
			String dateString = monthString+" "+day+","+year;
			return dateString;
		}
		else if(format==4) {
			DecimalFormat df = new DecimalFormat("00");
			String monthString = new DateFormatSymbols().getMonths()[month-1];
			String dateString = monthString+" "+df.format(day)+","+df.format(year);
			return dateString;
		}
		else return emptyString;
	}

	public void printReport(){
		System.out.println(formatDate(4));
		int ticketSoldUpper = TOTAL_NUMBER_UPPER_TICKETS-numberOfTicketsUpper;
		int ticketSoldLower = TOTAL_NUMBER_LOWER_TICKETS-numberOfTicketsLower;
		int ticketSoldFloor = TOTAL_NUMBER_FLOOR_TICKETS-numberOfTicketsFloor;
		double upperAmount = ticketSoldUpper*PRICE_UPPER_TICKET;
		double lowerAmount = ticketSoldLower*PRICE_LOWER_TICKET;
		double floorAmount = ticketSoldFloor*PRICE_FLOOR_TICKET;
		
		System.out.println("Concert Report");
		System.out.println("==============================");
		System.out.println("Artist:\t"+artistName);
		System.out.println("Venue:\t"+venueOfConcert);
		
		System.out.println("");
		System.out.println("");
		
		NumberFormat currency = NumberFormat.getCurrencyInstance(Locale.US);
		
		System.out.println("Tickets Sold");
		System.out.println("===============================");
		System.out.println("Upper:  "+ticketSoldUpper+"\t"+currency.format(upperAmount));
		System.out.println("Lower:  "+ticketSoldLower+"\t"+currency.format(lowerAmount));
		System.out.println("Floor:  "+ticketSoldFloor+"\t"+currency.format(floorAmount));
		
		System.out.println("================================");
		System.out.println("Total Sales:  "+currency.format(concertTotalSales));
	}
	public void simulateCompanyBuyingTickets(int numberTickets) {
		char ticketType;
		int numTickets=1;double payment=0.0;
		while(numberTickets>0) {
			Random r = new Random();
			int ticket = r.nextInt(2)+1;
			if(ticket==1) {
				ticketType='U';
				payment = PRICE_UPPER_TICKET*numTickets;
				buyTickets(ticketType, numTickets, payment);
			}
			else if(ticket==2) {
				ticketType='L';
				payment = PRICE_LOWER_TICKET*numTickets;
				buyTickets(ticketType, numTickets, payment);
			}
			else if(ticket==3) {
				ticketType='F';
				payment = PRICE_FLOOR_TICKET*numTickets;
				buyTickets(ticketType, numTickets, payment);
			}		
			numberTickets--;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Concert c  = new Concert();
//		System.out.println(c.formatDate(6));
		c.simulateCompanyBuyingTickets(4);
			c.printReport();	
//		System.out.println(c.getVenue());
		
	}
}