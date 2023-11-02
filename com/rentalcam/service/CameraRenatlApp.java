package com.rentalcam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.rentalcam.model.Camera;
import com.rentalcam.model.User;

public class CameraRenatlApp {

	static double walletBalance = 1172.5; // Initial wallet balance

	private static User currentUser;
	private static List<Camera> cameraList = new ArrayList<>();


	public static void main(String[] args) {

		// Dummy data for demonstration
	       cameraList.add(new Camera("Canon", "5850", 25800));
	       cameraList.add(new Camera("sony", "c58", 25800));
	       cameraList.add(new Camera("samsung", "m850", 25800));
	       cameraList.add(new Camera("Canon", "n50", 25800));
		Scanner scanner = new Scanner(System.in);

		// Login system
		System.out.println("+------+--------------+--------------+");
		System.out.println("|   WELCOME TO CAMERA RENTAL APP     | \n");
		System.out.println("+------+--------------+--------------+");
		System.out.println("PLEASE LOGIN TO CONTINUE");
		System.out.print("USERNAME: ");
		String username = scanner.nextLine();
		System.out.print("PASSWORD: ");
		String password = scanner.nextLine();

		// Check login credentials
		if (username.equals("admin") && password.equals("admin123")) {
			currentUser = new User(username, password);
			int choice;
			do {
				displayMainMenu1();
				choice = scanner.nextInt();
				scanner.nextLine();  // Consume newline character
				switch (choice) {
				case 1:
					handleMyCameraSubMenu(scanner);
					break;
				case 2:
					rentCamera(scanner);
					break;
				case 3:
					viewMyAllCameras();
					break;
				case 4:
					viewMyWallet(scanner);
					break;
				case 5:
					
					break;
				default:
					System.out.println("Invalid choice. Please try again.");
				}
			} while (choice != 5);
		} else {
			System.out.println("Invalid username or password. Exiting application.");
		}
	}

	private static void displayMainMenu1() {
		System.out.println("\n1. MY CAMERA");
		System.out.println("2. RENT A CAMERA");
		System.out.println("3. VIEW ALL CAMERAS");
		System.out.println("4. MY WALLET");
		System.out.println("5. EXIT");
		System.out.print("ENTER YOUR CHOICE: ");
	}


	private static void handleMyCameraSubMenu(Scanner scanner) {
		int choice;
		do {
			System.out.println("\n1. ADD CAMERA");
			System.out.println("2. REMOVE CAMERA");
			System.out.println("3. VIEW MY CAMERAS");
			System.out.println("4. GO TO PREVIOUS MENU");
			System.out.print("ENTER YOUR CHOICE: ");
			choice = scanner.nextInt();
			scanner.nextLine();  // Consume newline character
			switch (choice) {
			case 1:
				addCamera(scanner);
				break;
			case 2:
				removeCamera(scanner,cameraList);
				break;
			case 3:
				viewMyCameras(cameraList);
				break;
			case 4:
				System.out.println("Going to the previous menu.");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 4);
	}


	//Add Camera

	private static void addCamera(Scanner scanner) {
		System.out.print("Enter the number of cameras you want to add: ");
		int numberOfCameras = scanner.nextInt();

		for (int i = 0; i < numberOfCameras; i++) {
			System.out.println("Enter details for Camera " + (i + 1) + ":");
			System.out.print("Enter The Camera Brand: ");
			String brand = scanner.next();
			System.out.print("Enter The Camera Model: ");
			String model = scanner.next();
			System.out.print("Enter The Camera Rent per day: ");
			double rentPerDay = scanner.nextDouble();

			// Creating Camera object and adding it to the list
			Camera camera = new Camera(brand, model, rentPerDay);
			cameraList.add(camera);
		}

		// Displaying the input values in table format
		System.out.println("Cameras Information ");
		System.out.println("+------+--------------+----------------------+--------------+");
		System.out.println("| ID   | Brand        | Model                | Rent/Day ($) |");
		System.out.println("+------+--------------+----------------------+--------------+");

		for (Camera camera : cameraList) {
			System.out.printf("| %-4d | %-12s | %-20s | %-12.2f |\n", camera.getId(), camera.getBrand(),
					camera.getModel(), camera.getRentPerDay());
		}

		System.out.println("+------+--------------+----------------------+--------------+");

	}

	//Remove camera
	private static void removeCamera(Scanner scanner, List<Camera> cameraList) {
		if (cameraList.isEmpty()) {
			System.out.println("No cameras to remove.");
			return;
		}

		// Displaying the existing cameras in table format
		displayCameraList(cameraList);

		int cameraId;
		while (true) {
			System.out.print("ENTER THE CAMERA ID TO REMOVE: ");
			if (scanner.hasNextInt()) {
				cameraId = scanner.nextInt();
				scanner.nextLine(); // Consume newline character
				if (cameraId >=1 && cameraId < cameraList.size()) {
					break;
				} else {
					System.out.println("Invalid camera ID. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid integer ID.");
				scanner.next(); // Consume invalid input
			}
		}
		int indexToRemove = cameraId - 1;
		// Removing the camera and displaying its details in table format
		Camera removedCamera = cameraList.remove(indexToRemove);
		System.out.println("CAMERA SUCCESSFULLY REMOVED FROM THE LIST:");
		displayCameraHeader();
		displayCameraDetails(removedCamera);
	}
	//Declaring the header part
	private static void displayCameraHeader() {
		System.out.println("+------+--------------+----------------------+--------------+---------------+");
		System.out.println("| ID   | Brand        | Model                | Rent/Day ($) | Status     |");
		System.out.println("+------+--------------+----------------------+--------------+---------------+");
	}
	//Declaring the camera details
	private static void displayCameraDetails(Camera camera) {
		System.out.printf("| %-4d | %-12s | %-20s | %-12.2f | %-13s |\n", camera.getId(), camera.getBrand(),
				camera.getModel(), camera.getRentPerDay(), camera.isAvailable() ? "Available" : "Rainted");
		System.out.println("-------------------------------------------------------------------------------");
	}

	private static void displayCameraList(List<Camera> cameraList) {
		System.out.println("Cameras Information ");
		displayCameraHeader();
		for (Camera camera : cameraList) {
			displayCameraDetails(camera);
		}
	}

	//Rent a camera
	private static void rentCamera(Scanner scanner) {
		System.out.println("FOLLOWING IS THE LIST OF AVAILABLE CAMERA(S)");
		System.out.println("-----------------------------------------------------------------------------");
		System.out.println(String.format("%-12s%-15s%-15s%-20s%-10s", "CAMERA ID", "BRAND", "MODEL", "PRICE (PER DAY)", "STATUS"));
		System.out.println("------------------------------------------------------------------------------");
		for (int i = 0; i < cameraList.size(); i++) {
			Camera camera = cameraList.get(i);
			System.out.println(String.format("%-12d%-15s%-15s%-20.2f%-10s", (i + 1), camera.getBrand(), camera.getModel(), camera.getRentPerDay(),camera.getStatus()));
		}
		System.out.println("-------------------------------------------------------------------------------");

		System.out.print("ENTER THE CAMERA ID YOU WANT TO RENT: ");
		int cameraId = scanner.nextInt();

		if (cameraId >= 1 && cameraId <= cameraList.size()) {
			Camera selectedCamera = cameraList.get(cameraId - 1);
			if (selectedCamera.isAvailable()) {
				double rentAmount = selectedCamera.getRentPerDay();
				if (walletBalance >= rentAmount) {
					walletBalance -= rentAmount;
					selectedCamera.markAsRented(); // Mark the camera as rented
					System.out.println("YOUR TRANSACTION FOR CAMERA " + selectedCamera.getBrand() + " " +
							selectedCamera.getModel() + " with rent INR." + rentAmount + " HAS SUCCESSFULLY COMPLETED.");
				} else {
					System.out.println("Insufficient wallet amount. Cannot rent the camera.");
				}
			} else {
				System.out.println("This camera is already rented. Please select another camera.");
			}
		} else {
			System.out.println("Invalid camera ID. Please select a valid camera.");
		}
	}

	//view my camera

	private static void viewMyAllCameras() {
		System.out.println("YOUR CAMERAS:");
	if(cameraList.isEmpty())
		{
		System.out.println("No cameras added yet.");
		}else {
			displayCameraList(cameraList);
		}}

	private static void viewMyCameras(List<Camera> cameraList) {
	    System.out.println("YOUR RENTED CAMERA LIST:");
	    boolean foundRentedCamera = false;

	    System.out.printf("%-4s | %-12s | %-20s | %-10s\n", "ID", "Brand", "Model", "Status");
	    System.out.println("------------------------------------------------------");

	    for (Camera camera : cameraList) {
	        if (camera.isAvailable()) {
	            System.out.printf("%-4d | %-12s | %-20s | %-10s\n",
	                    camera.getId(), camera.getBrand(), camera.getModel(), "Rented");
	            foundRentedCamera = true;
	            break;  // Exit the loop after finding the first rented camera
	        }
	    }

	    if (!foundRentedCamera) {
	        System.out.println("You haven't rented any cameras yet.");
	    }
	}



	private static void goToPreviousMenu() {
		System.out.println("Going to the previous menu.");
	}

	//View Wallet Balance
	private static void viewMyWallet(Scanner scanner) {
		System.out.println("YOUR CURRENT WALLET BALANCE IS INR." + walletBalance);
		System.out.print("DO YOU WANT TO DEPOSIT MORE AMOUNT TO YOUR WALLET? (1.YES 2.NO) - ");
		String depositChoice = scanner.nextLine();

		if (depositChoice.equals("yes")) {
			System.out.print("ENTER THE AMOUNT (INR): ");
			double depositAmount = scanner.nextDouble();
			walletBalance += depositAmount;
			System.out.println("YOUR WALLET BALANCE UPDATED SUCCESSFULLY. CURRENT WALLET BALANCE- INR." + walletBalance);
		} else {
			System.out.println("No money deposited. Current wallet balance remains unchanged.");
		} 


	}

}






























