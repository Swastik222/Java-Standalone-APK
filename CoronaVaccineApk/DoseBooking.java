package com.sm.multithreading.CoronaVaccineApp;


public class DoseBooking {
 private boolean booked = false;

 public void bookDose() {
     if (booked) {
         throw new RuntimeException("Dose already booked.");
     }
     booked = true;
 }

 public boolean isDoseBooked() {
     return booked;
 }
}
