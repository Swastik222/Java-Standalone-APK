package com.sm.multithreading;

public class PetrolPumpSimulation {

	public static void main(String[] args) throws InterruptedException {

		PetrolPump pp = new PetrolPump();
		Car[] c = { new Car("BMW", pp), new Car("Audi", pp), new Car("Mercedes", pp), new Car("Jaguar", pp),
				new Car("Range Rover", pp) , new Car("Rolls Royal",pp)};
//		Thread[] t = new Thread[5];
//		t[0]=new Thread(c[0]);

		for (Car c1 : c) {
			Thread t = new Thread(c1);
			t.start();
			t.join();
		}

	}

}

class PetrolPump {
	public synchronized void refillCar(String carName) {
		System.out.println(carName + " started refilling...");

		try {
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println(e);
		}
		System.out.println(carName + " Completed refilling!.");
	}
}

class Car implements Runnable {
	String carName;
	PetrolPump petrolPump;

	public Car(String string, PetrolPump pp) {
		this.carName = string;
		this.petrolPump = pp;
	}

	@Override
	public void run() {
		petrolPump.refillCar(carName);

	}

}
