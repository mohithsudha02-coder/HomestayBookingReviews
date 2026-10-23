# Homestay Booking & Reviews

## Project Overview
The Homestay Booking & Reviews system is a Java console-based application that helps users manage homestay listings, bookings, payments, cancellations, and guest reviews.

## Features
- Add hosts and guests
- Add homestay listings with location and price
- Create bookings with check-in and check-out dates
- Check availability for selected dates
- Record payments using Cash or UPI
- Cancel bookings and calculate cancellation fees
- Submit reviews after a completed stay
- Display booking and project records

## Technologies Used
- Java
- Object-Oriented Programming (OOP)
- Java Collections Framework (`ArrayList`)
- Java Date and Time API (`LocalDate`)
- Eclipse IDE

## Project Structure
- `model` – Person, Host, Guest, Listing, Booking, Review
- `payment` – Payment, CashPayment, UpiPayment
- `service` – HomestayService, Cancellation
- `app` – Main application and menu

## How to Run
1. Open the project in Eclipse IDE.
2. Ensure all Java files are in their correct packages.
3. Open `Main.java` inside the `app` package.
4. Right-click the file and select **Run As → Java Application**.
5. Follow the console menu to use the application.

## Note
This version stores records in memory. The data is reset when the application is restarted.

## Author
Student Project