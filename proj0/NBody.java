public class NBody {
	/** returns a double correspoinging to the 
	radius of the universe in the file */
	public static double readRadius(String filename) {
		In in = new In(filename);

		int N = in.readInt(); // # of plants in the file
		double R = in.readDouble();

		return R;
	}

	/** return an array of Planets corresponding to the 
	planets in the file 
	information: xxPos, yyPos, xxVel, yyVel, mass*/
	public static Planet[] readPlanets(String filename) {
		In in = new In(filename);

		int N = in.readInt();
		double R = in.readDouble();
		Planet[] ps = new Planet[N]; 
		int i = 0;

		while (!in.isEmpty() && i < N) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();

			Planet p = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
			ps[i] = p;
			i += 1;
		}
		return ps;
	}


	public static void main (String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double R = readRadius(filename);
		Planet[] ps = readPlanets(filename);

		// System.out.println(T + " " + dt + " " + R);
		// System.out.println(ps[0].mass);

		StdDraw.enableDoubleBuffering();

		/** drawing the background*/
		StdDraw.setScale(-R, R);
		String background = "images/starfield.jpg";
		StdDraw.picture(0, 0, background);
		StdDraw.show();

		/** drawing each planet */
		for (Planet p: ps) {
			p.draw();			
		}

		for (double time = 0; time < T; time += dt) {
			double[] xForces = new double[ps.length];
			double[] yForces = new double[ps.length];
			int waitTimeMilliseconds = 10;
			/** draws the background */
			StdDraw.picture(0, 0, background);
			
			for (int i = 0; i < ps.length; i += 1) {

				/** calculates ps[i]'s net forces*/
				xForces[i] = ps[i].calcNetForceExertedByX(ps);
				yForces[i] = ps[i].calcNetForceExertedByY(ps);
			}	

			for (int i = 0; i < ps.length; i += 1) {
				
				/** updates planets' positions */
				ps[i].update(dt, xForces[i], yForces[i]);
			}

			for (int i = 0; i < ps.length; i += 1) {	
				/** draws planets */
				ps[i].draw();
			}
			
			StdDraw.show();
			StdDraw.pause(waitTimeMilliseconds);			
		}

		StdOut.printf("%d\n", ps.length);
		StdOut.printf("%.2e\n", R);
		for (int i = 0; i < ps.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
	                  	  ps[i].xxPos, ps[i].yyPos, ps[i].xxVel,
	                  	  ps[i].yyVel, ps[i].mass, ps[i].imgFileName);   
		}
	}
}

