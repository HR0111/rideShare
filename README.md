# RideShare Project

Welcome to the RideShare Project repository! 🚖

This project is a comprehensive ride-sharing application with features for both riders and drivers. The application provides functionalities such as requesting rides, rating drivers, managing rides, and more.

## Features

- **For Riders:**
  - Request a ride
  - Cancel an ongoing ride
  - Rate drivers
  - View profile and ride history

- **For Drivers:**
  - Accept and start rides
  - End and cancel rides
  - Rate riders
  - View profile and ride history

- **Authentication:**
  - Sign up and log in
  - Onboard new drivers (admin only)
  - Refresh authentication tokens

## Controllers

### RiderController

![RiderController Screenshot](C:\Users\heman\OneDrive\Pictures\Screenshots/riderController.png)

- **Request a Ride**: `POST /riders/requestRide` - Request a new ride.
- **Cancel a Ride**: `POST /riders/cancelRide/{rideId}` - Cancel an ongoing ride.
- **Rate a Driver**: `POST /riders/rateDriver` - Rate a driver.
- **Get My Profile**: `GET /riders/getMyProfile` - Retrieve rider profile information.
- **Get My Rides**: `GET /riders/getMyRides` - Retrieve a paginated list of rides.

### DriverController

![DriverController Screenshot](C:\Users\heman\OneDrive\Pictures\Screenshots/driverController.png)

- **Accept a Ride Request**: `POST /drivers/acceptRide/{rideRequestId}` - Accept a ride request.
- **Start a Ride**: `POST /drivers/startRide/{rideRequestId}` - Start a ride with OTP verification.
- **End a Ride**: `POST /drivers/endRide/{rideId}` - End an ongoing ride.
- **Cancel a Ride**: `POST /drivers/cancelRide/{rideId}` - Cancel an ongoing ride.
- **Rate a Rider**: `POST /drivers/rateRider` - Rate a rider.
- **Get My Profile**: `GET /drivers/getMyProfile` - Retrieve driver profile information.
- **Get My Rides**: `GET /drivers/getMyRides` - Retrieve a paginated list of rides.

### AuthController

![AuthController Screenshot](C:\Users\heman\OneDrive\Pictures\Screenshots/authController.png)

- **Sign Up**: `POST /auth/signup` - Create a new user account.
- **Login**: `POST /auth/login` - Authenticate and obtain tokens. Tokens are stored in cookies.
- **Onboard New Driver**: `POST /auth/onBoardNewDriver/{userId}` - Admin only. Onboard a new driver using their user ID.
- **Refresh Token**: `POST /auth/refresh` - Refresh access tokens using a refresh token.

## Getting Started

1. **Clone the repository:**

   git clone https://github.com/HR0111/rideShare.git

2. Navigate to the project directory:
   
   cd rideShare

3. Install dependencies:

   ./mvnw install
  
4. Run the application:

   ./mvnw spring-boot:run
