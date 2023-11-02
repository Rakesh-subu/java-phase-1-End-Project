package com.rentalcam.model;

public class Camera {
    static int cameraIdCounter = 1; // Static variable to keep track of the last assigned ID
    int id;
    String brand;
    String model;
    double rentPerDay;
    boolean isAvailable;

    public Camera(String brand, String model, double rentPerDay) {
        this.id = cameraIdCounter++; // Assign ID and then increment the counter
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        
    }

	public static int getCameraIdCounter() {
		return cameraIdCounter;
	}

	public static void setCameraIdCounter(int cameraIdCounter) {
		Camera.cameraIdCounter = cameraIdCounter;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getRentPerDay() {
		return rentPerDay;
	}

	public void setRentPerDay(double rentPerDay) {
		this.rentPerDay = rentPerDay;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	  public void markAsRented() {
	        isAvailable = false;
	    }

	    // Method to mark the camera as available for rent
	    public void markAsAvailable() {
	        isAvailable = true;
	    }
	    public String getStatus() {
	        return isAvailable ? "Available" : "Rented";
	    }

		@Override
		public String toString() {
			return "Camera [id=" + id + ", brand=" + brand + ", model=" + model + ", rentPerDay=" + rentPerDay
					+ ", isAvailable=" + isAvailable + "]";
		}
	    
}


