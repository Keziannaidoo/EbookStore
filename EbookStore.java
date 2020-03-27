import java.sql.*;
import java.util.Scanner;

public class EbookStore {
	
	public static void main(String[] args) {
				
		int userChoice; //empty variable used to store users selection in variable userChoice 
         
        
        
        try (
				 // Step 1: Allocate a database 'Connection' object
				Connection conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/EbookStore_db?useSSL=false", "kez", "Kez");
					
				 // Step 2: Allocate a 'Statement' object in the Connection
					Statement stmt = conn.createStatement();
					)//close Try block 
			
        {
        	//while loop that will cause menu to keep showing until user selects "0"
        	while (true) {
        		userChoice = menuOption(); //calling the menuOption method and returning value to empty variable

        		//Part 1.2 : Enter book to Database 
        		//If user enter 1 "enter book to database them they will be prompted to enter info below 
            	if (userChoice == 1) {
            		Scanner input = new Scanner(System.in); // user must enter option 
                    //Getting user input in terms of the book they want to enter 
                    System.out.println("Id : ");
                    int id = input.nextInt(); //User input will be stored 
                    System.out.println("Title : ");
                    String title = input.next(); //User input will be stored 
                    System.out.println("Author :");
                    String author = input.next(); //User input will be stored 
                    System.out.println("Qty : ");
                    int qty = input.nextInt(); //User input will be stored 
            		
            		// INSERT the user records to SQL
        			String sqlInsert = ("insert into books value('"+id+"','"+title+"','"+author+"','"+qty+"')");
        			System.out.println("The SQL query is: " + sqlInsert);
        			int countInserted = stmt.executeUpdate(sqlInsert);
        			System.out.println(countInserted + " records inserted.\n");
        			
            	} //close if userchoice 
            	
            	
            	//Part 1.3 : Update book in Database 
            	else if (userChoice == 2) {
            		Scanner input = new Scanner(System.in); // user input function 
            		System.out.println("\n----------------- Update Book -----------------\n"
            				+ "Please see List of Ebooks in Database below \n");
            		
            		//This will print out all books in database 
        			String strSelect = "select * from books" ;
        			System.out.println("The SQL query is: " + strSelect);
        			ResultSet rset = stmt.executeQuery(strSelect); //executing the command to see all values in bookstable 
        			
        			// Move the cursor to the next row
        			while(rset.next()) {
        				System.out.println(rset.getInt("id") + ", "
        						+ rset.getString("author") + ", "
        						+ rset.getString("title") + ", "
        						+ rset.getInt("qty"));
        			}//close while loop that displays all the books 
            		
        			//Getting user input in terms of the book they want to enter 
            		System.out.println("\nEnter Book ID to Update : "); 
            		String id = input.next(); //Stores the book id of the book the user would like to update in variable           
                    System.out.println("Title : ");
                    String title = input.next(); //User input will be stored 
                    System.out.println("Author :");
                    String author = input.next(); //User input will be stored 
                    System.out.println("Qty : ");
                    int qty = input.nextInt(); //User input will be stored 
                    
                    //This part creates the command to update the book selected by the user 
            		String strUpdate = ("update books set title = ('"+title+"'), author = ('"+author+"'), qty = ('"+qty+"') where id = ('"+id+"')");
        			System.out.println ("The SQL query is:" + strUpdate);
        			int countUpdated = stmt.executeUpdate(strUpdate); //update com,,mand 
        			System.out.println (countUpdated + " records Updated"); //count that says how many records have been changes 
  
        			
           		
            	} //Close if statement for update statement 
            	
            	//Part 1.4 : Delete book in Database 
            	else if (userChoice == 3) {
            		System.out.println("\n----------------- Delete Book -----------------\n");
            		Scanner input = new Scanner(System.in); // user input function 
            		
            		//Getting user input in terms of the book they want to enter 
            		System.out.println("\nEnter Book ID to Delete book : "); 
            		String id = input.next(); //Stores the book id of the book the user would like to update in variable 
            		
            		// DELETE records with id>=3000 and id<4000
        			String sqlDelete = ("delete from books where id = ('"+id+"')");
        			System.out.println("The SQL query is: " + sqlDelete);
        			int countDeleted = stmt.executeUpdate(sqlDelete);
        			System.out.println(countDeleted + " records deleted.\n" );
        			
        			
            	}//Close if for delete record
            	
            	//Part 1.5 : Search book in Database 
            	else if (userChoice == 4) {
            		System.out.println("\n----------------- Search Book -----------------");
            		Scanner input = new Scanner(System.in); // user input function 
            		
            		//User must select one of the following options below to search by that field
            		System.out.println("\nSelect option To Search for book : "); 
                    System.out.println("1 - Id ");
                    System.out.println("2 - Title ");
                    System.out.println("3 - Author ");
                    System.out.println("4 - Qty ");
            		int searchChoice = input.nextInt(); //Stores the book id of the book the user would like to update in variable
            		
            		//Book Id Search 
            		if (searchChoice == 1 ) {
            			System.out.println("\nEnter Book Id : "); 
                		String id = input.next(); //Stores the book id of the book the user would like to update in variable
                		
                		//This will print out all books in database 
            			String strSelect = ("select * from books where id = ('"+id+"')") ;
            			System.out.println("The SQL query is: " + strSelect);
            			ResultSet rset = stmt.executeQuery(strSelect); //executing the command to see all values in bookstable 
            			
            			// This will print out all the results that was selected above 
            			while(rset.next()) {
            				System.out.println(rset.getInt("id") + ", "
            						+ rset.getString("author") + ", "
            						+ rset.getString("title") + ", "
            						+ rset.getInt("qty"));
            			}//close while loop that displays all the books 

            		}//close if 1 
            		
            		if (searchChoice == 2) {        
                        System.out.println("Enter Book Title : ");
                        String title = input.next(); //User input will be stored 
                        
                      //This will print out all books in database 
            			String strSelect = ("select * from books where title = ('"+title+"')") ;
            			System.out.println("The SQL query is: " + strSelect);
            			ResultSet rset = stmt.executeQuery(strSelect); //executing the command to see all values in bookstable 
            			
            			// This will print out all the results that was selected above 
            			while(rset.next()) {
            				System.out.println(rset.getInt("id") + ", "
            						+ rset.getString("author") + ", "
            						+ rset.getString("title") + ", "
            						+ rset.getInt("qty"));
            			}//close while loop that displays all the books       
            		}//Close if 2 
            		
            		if (searchChoice == 3) {
                        System.out.println("Enter Book Author :");
                        String author = input.next(); //User input will be stored   
                        
                      //This will print out all books in database 
            			String strSelect = ("select * from books where author = ('"+author+"')") ;
            			System.out.println("The SQL query is: " + strSelect);
            			ResultSet rset = stmt.executeQuery(strSelect); //executing the command to see all values in bookstable 
            			
            			// This will print out all the results that was selected above 
            			while(rset.next()) {
            				System.out.println(rset.getInt("id") + ", "
            						+ rset.getString("author") + ", "
            						+ rset.getString("title") + ", "
            						+ rset.getInt("qty"));
            			}//close while loop that displays all the books 
            		}//Close if 3 
            		
            		if (searchChoice == 4) {
                        System.out.println(" Enter Book Qty : ");
                        int qty = input.nextInt(); //User input will be stored 
                        
                      //This will print out all books in database 
            			String strSelect = ("select * from books where qty = ('"+qty+"')") ;
            			System.out.println("The SQL query is: " + strSelect);
            			ResultSet rset = stmt.executeQuery(strSelect); //executing the command to see all values in bookstable 
            			
            			// This will print out all the results that was selected above 
            			while(rset.next()) {
            				System.out.println(rset.getInt("id") + ", "
            						+ rset.getString("author") + ", "
            						+ rset.getString("title") + ", "
            						+ rset.getInt("qty"));
            			}//close while loop that displays all the books 
            		}//close if 4 sub section 
            		 
            	}//close if 4 
            	
            	else if (userChoice == 0) {
            		System.exit(0);
            	}
            		
            	
            }//Close while loop 
  
			}
        
		catch(SQLException ex) {
			ex.printStackTrace();	
		}//Close catch 
	  
	}//Close main Method 
	
	
	
	//Part  : Method will return users selection 
	public static int menuOption() {

        int selection; //Create variable to hold user input
        Scanner input = new Scanner(System.in); // user must enter option 
        
        System.out.println("-------------------- E Book Database --------------------");
        
        System.out.println("\nChoose from option below : ");
        System.out.println("1 - Enter a Book ");
        System.out.println("2 - Update A Book ");
        System.out.println("3 - Delete A Book ");
        System.out.println("4 - Search A book ");
        System.out.println("0 - Exit ");
        
        selection = input.nextInt(); //User input will be stored as Selection
        return selection; //methood will return the option 
    }// Close menu method 
	

}
