package com.addressbookprogramday_28;

import java.io.IOException;
import java.util.Scanner;

public class AddressBookMain{

    public static void main(String[] args){

        String firstName, lastName, address, city, state, email;
        int zip;
        long phoneNo;
        char option;

        OperateContact operate = new OperateContact();
        Scanner sc= new Scanner(System.in);
        do{
        
            AddressBook contact = new AddressBook();
            System.out.print("\nEnter First Name  : ");  
            firstName = sc.nextLine();
            contact.setFirstName(firstName);

            System.out.print("\nEnter Last Name  : ");  
            lastName = sc.nextLine();
            contact.setLastName(lastName);

            System.out.print("\nEnter E-mail  : ");
            email = sc.nextLine();
            contact.setEmail(email);

            System.out.print("\nEnter Address  : ");  
            address = sc.nextLine(); 
            contact.setAddress(address);

            System.out.print("\nEnter City  : ");  
            city = sc.nextLine(); 
            contact.setCity(city);

            System.out.print("\nEnter State  : ");  
            state = sc.nextLine();
            contact.setState(state);
            
            System.out.print("\nEnter Phone Number  : ");   
            phoneNo = sc.nextLong();
            contact.setPhoneNo(phoneNo);

            System.out.print("\nEnter Zip  : ");  
            zip = sc.nextInt(); 
            contact.setZip(zip);

            operate.storeContact(firstName, contact);

            System.out.print("\nDo you want to add one more contact? press Y / N : ");
            option = sc.next().charAt(0);
            sc.nextLine();
        }while(option == 'Y');
//        
//        System.out.print("\nDo you want to sort by person's city ? press Y / N : ");
//        char sortOption = sc.next().charAt(0);
//        if(sortOption == 'Y'){
//        	operate.sortByName();
//        }
//       
//        System.out.print("\nDo you want to search a person ? press Y / N : ");
//        char searchOption = sc.next().charAt(0);
//        if(searchOption == 'Y'){
//            operate.searchPerson();
//        }
//       
//        System.out.print("\nDo you want to edit? press Y / N : ");
//        char editOption = sc.next().charAt(0);
//        if(editOption == 'Y'){
//            operate.editContact();
//            operate.showContact();
//        }
//       
//        System.out.print("\nDo you want to delete? press Y / N : ");
//        char deleteOption = sc.next().charAt(0);
//        if(deleteOption == 'Y'){
//            operate.deleteContact();
//            operate.showContact();
//        }
//        
//        System.out.print("\nDo you want to delete? press Y / N : ");
//        char writeOption = sc.next().charAt(0);
//        if(writeOption == 'Y'){
//        	operate.writeContact();
//        }
        
        try {
			operate.writeContactAsCsv();
		} catch (IOException e) {
			e.printStackTrace();
		}
        sc.close();
    }
}
