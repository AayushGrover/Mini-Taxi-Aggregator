import iiitb.ess201a7.a7base.*;
import java.util.ArrayList;

// import each of the fleets to be considered in the simulation
import iiitb.ess201a7.r16005.Fleet16005;
import iiitb.ess201a7.r16035.Fleet16035;
import iiitb.ess201a7.r16061.Fleet16061;
import iiitb.ess201a7.r16083.Fleet16083;
import iiitb.ess201a7.r16122.Fleet16122;

public class Demo {

	public static void main(String[] args) {

		Platform pf = new Platform();
		// v3. Instantiates Text-based display. Change this to call any other derived class of Display
		// Display disp = new TextDisplay();
		Display disp = new SwingDisplay();

		App app = new App(pf, disp);

		ArrayList<Fleet> fleets = new ArrayList<Fleet>();

		Fleet f1 = new Fleet16005("blue"); // note: colour currently not used by Display
		pf.addFleet(f1);
		fleets.add(f1);

		Fleet f2 = new Fleet16035("blue"); // note: colour currently not used by Display
		pf.addFleet(f2);
		fleets.add(f2);

		Fleet f3 = new Fleet16083("blue"); // note: colour currently not used by Display
		pf.addFleet(f3);
		fleets.add(f3);

		Fleet f4 = new Fleet16061("blue"); // note: colour currently not used by Display
		pf.addFleet(f4);
		fleets.add(f4);

		Fleet f5 = new Fleet16122("blue"); // note: colour currently not used by Display
		pf.addFleet(f5);
		fleets.add(f5);

		// for(int i=0;i<5;i++){
		// 	f1.addCar(20 + i);  // add cars, assigning max speed to each car
		// }

		int j = 1;
		for(Fleet fleet: fleets) {
			for (int i = 0; i < 5; i++) {
				fleet.addCar(20 + i); // add cars, assigning max speed to each car
			}
			
			ArrayList<? extends Car> cars = fleet.getCars();
			for (int i = 0; i < cars.size(); i++) {
				cars.get(i).setLocation(new Location(j * 40, i * 50 + 10*j));
			}
			j++;
		}
		// Similarly add other fleets if available. Assign a different colour to each fleet


		// start the app - populate the display and run the simulation
		app.init();

		// This will be replaced by callbacks from Display once the UI is in place

		// disp.requestTrip(new Location(10, 10), new Location(200,200));
		// disp.requestTrip(new Location(250, 100), new Location(100,300));

	}

}
