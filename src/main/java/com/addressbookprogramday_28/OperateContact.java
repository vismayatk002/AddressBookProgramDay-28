package com.addressbookprogramday_28;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.opencsv.CSVWriter;

public class OperateContact {

	HashMap<String, AddressBook> contactMap = new HashMap<>();
	public static String contactFile = "contactFile.txt";
	
    public void editContact(){

        String address;
        Scanner sc= new Scanner(System.in);
        System.out.print("\nEnter the First name for update Address : ");
        String editName = sc.nextLine();
        int flag = 1;
        for (String firstName : contactMap.keySet())
        {
            AddressBook contact = contactMap.get(firstName);

            if(editName.equals(contact.getFirstName())){
                System.out.print("\nEnter your new Address : ");
                address = sc.nextLine();
                contact.setAddress(address);
                flag = 0;
            }
        }
        if(flag == 1){
            System.out.print("\ncouldn't find the contact..");
        }
        else{
            System.out.print("\nYour contact updated.");
        }
    }
    
    public void deleteContact(){

        Scanner sc= new Scanner(System.in);
        System.out.print("\nEnter the First name for delete : ");
        String deleteName = sc.nextLine();
        int flag = 1;
        for (String firstName : contactMap.keySet())
        {
            AddressBook contact = contactMap.get(firstName);
            if(deleteName.equals(contact.getFirstName())){
                contactMap.remove(firstName);
                flag = 0;
            }
        }
        if(flag == 1){
            System.out.print("\ncouldn't find the contact..");
        } 
        else{
            System.out.print("\nYour contact deleted..");
        }
        sc.close();   
    }
    
    public void showContact(){

        for (String firstName : contactMap.keySet())
        {
            AddressBook contact = contactMap.get(firstName);

            System.out.print("\n-----------------");
            System.out.print("\nFirst Name  : " +  contact.getFirstName());
            System.out.print("\nLast Name   : " +  contact.getLastName());
            System.out.print("\nAddress     : " +  contact.getAddress());
            System.out.print("\nCity        : " +  contact.getCity());
            System.out.print("\nState       : " +  contact.getState());
            System.out.print("\nPone Number : " +  contact.getPhoneNo());
            System.out.print("\nE-mail      : " +  contact.getEmail());
            System.out.print("\nZip         : " +  contact.getZip());
        }
    }
    
    public void storeContact(String firstName, AddressBook contact){

        this.contactMap.put(firstName, contact);
    }
    
    public void searchPerson() {
    	 Scanner sc= new Scanner(System.in);
         System.out.print("\nEnter the city : ");
         String city = sc.nextLine();
         
         List<String> person = contactMap.entrySet() 
		         .stream() 
		         .filter(HashMap -> HashMap.getValue().getCity().equals(city))
		         .map(HashMap -> HashMap.getValue().getFirstName())
		         .collect(Collectors.toList());
         
         System.out.println(String.join(",", person));  
    }
    
    public void sortByName() {
    	
    	List<AddressBook> person = contactMap.entrySet()
    			.stream()
    			.sorted(HashMap.Entry.comparingByValue())
    			.map(HashMap -> HashMap.getValue())
		        .collect(Collectors.toList());
    	
    	for (AddressBook contact : person)
    	{
            System.out.print("\n-----------------");
            System.out.print("\nFirst Name  : " +  contact.getFirstName());
            System.out.print("\nLast Name   : " +  contact.getLastName());
            System.out.print("\nAddress     : " +  contact.getAddress());
            System.out.print("\nCity        : " +  contact.getCity());
            System.out.print("\nState       : " +  contact.getState());
            System.out.print("\nPone Number : " +  contact.getPhoneNo());
            System.out.print("\nE-mail      : " +  contact.getEmail());
            System.out.print("\nZip         : " +  contact.getZip());
        } 
    }
    
    //write contact into file
    public void writeContact() {
    	
    	StringBuffer contactBuffer = new StringBuffer();
    	contactMap.forEach((name,contact) -> {
    		String contactString = contact.getFirstName().toString().concat(", ");
    		contactString += contact.getLastName().toString().concat(", ");
    		contactString += contact.getAddress().toString().concat(", ");
    		contactString += contact.getCity().toString().concat(", ");
    		contactString += contact.getState().toString().concat(", ");
    		contactString += String.valueOf(contact.getPhoneNo()).concat(", ");
    		contactString += contact.getEmail().toString().concat(", ");
    		contactString += String.valueOf(contact.getZip()).concat("\n");
    		contactBuffer.append(contactString);
    	});
    	try {
    		
    		Files.write(Paths.get(contactFile),contactBuffer.toString().getBytes());
    		
    	}catch(IOException e) {
    		System.out.print("Unable to write contact into file" + e.getMessage());
    	}
    }
    
    //write contact as CSV file
    public void writeContactAsCsv() throws IOException {
    	
    	//Instantiating the CSVWriter class
        CSVWriter writer = new CSVWriter(new FileWriter("contact.csv"));
        String line[] = {"First Name", "Last Name", "Address", "City", "State", "Pone Number", "E-mail", "Zip"};
        List contactList = new ArrayList();
        contactList.add(line);
        contactMap.forEach((name,contact) -> {
        	
    		String line1[] = {contact.getFirstName().toString(), contact.getLastName().toString(), contact.getAddress().toString(), contact.getCity().toString(), contact.getState().toString(), String.valueOf(contact.getPhoneNo()), contact.getEmail().toString(), String.valueOf(contact.getZip())};
    		contactList.add(line1);
    		
        });
        //Writing data to the csv file
        writer.writeAll(contactList);
        writer.flush();
        System.out.println("Data entered");
    }
    
    //write contact into JSONfile 
    public void writeContactAsJson() {
    	
	    try {
	  
	    	// create a writer
	        Writer writer = new FileWriter("contact.json");
	
	        // convert map to JSON File
	        new Gson().toJson(contactMap, writer);
	
	        // close the writer
	        writer.close();
	        
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
    }
}