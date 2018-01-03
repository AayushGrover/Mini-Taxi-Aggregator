import iiitb.ess201a7.a7base.*;


// v3 - converted this to an abstract class. Default text-based implementation in class
// TextDisplay
public abstract class Display {

	// needed for 2-way communication between App and Display

	App app;

	public Display() {

	}

	public void setApp(App a) {
		app = a;
	}

	// v3 - added
	// Will be called by app at init time - and before call to any other display method
	// Derived call can override this and do any initialization needed
	public void init() {

	}

	public void update() {
		
		
	}

	public void requestTrip(Location start, Location dest) {
		app.handleTripRequest(start, dest);
	}

	// v3 - below methods changed to abstract
	public abstract void draw(Car car);

	public abstract void drawLine(Location a, Location b);
}
