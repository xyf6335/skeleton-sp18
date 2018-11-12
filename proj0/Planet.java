public class Planet {
	final static double g = 6.67e-11;

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	/**returns a double equal to the distance between the supplied planet and 
	the planet that is doing the calculation */
	public double calcDistance(Planet p) {
		double dx = p.xxPos - this.xxPos;
		double dy = p.yyPos - this.yyPos;
		double r = Math.sqrt(dx * dx + dy *dy);
		return r;
	}

	/**returns a double describing the force exerted on this planet by the given planet. */
	public double calcForceExertedBy(Planet p) {
		double r = calcDistance(p);
		double f = (g * this.mass * p.mass) / (r *r);
		return f;
	}

	/**these two methods describe the force exerted in the X and Y directions */
	public double calcForceExertedByX(Planet p) {
		double f = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dx = p.xxPos - this.xxPos;
		double fx = f * dx / r;
		return fx;
	}

	public double calcForceExertedByY(Planet p) {
		double f = calcForceExertedBy(p);
		double r = calcDistance(p);
		double dy = p.yyPos - this.yyPos;
		double fy = f * dy / r;
		return fy;
	}

	/**take in an array of Planets and calculate the net X and net Y force 
	exerted by all planets in that array upon the current Planet */
	public double calcNetForceExertedByX(Planet[] ps) {
		double fnx = 0;
		for (int i = 0; i < ps.length; i += 1) {
			if (this.equals(ps[i])) {
				continue;
			} else {
				fnx = fnx + calcForceExertedByX(ps[i]);
			}
		}
		return fnx;
	}

	public double calcNetForceExertedByY(Planet[] ps) {
		double fny = 0;
		for (int i = 0; i < ps.length; i += 1) {
			if (this.equals(ps[i])) {
				continue;
			} else {
				fny = fny + calcForceExertedByY(ps[i]);
			}
		}
		return fny;
	}

	public void update(double dt, double fnx, double fny) {
		double anx = fnx / this.mass;
		double any = fny / this.mass;
		xxVel = this.xxVel + dt * anx;
		yyVel = this.yyVel + dt * any;
		xxPos = this.xxPos + dt * xxVel;
		yyPos = this.yyPos + dt * yyVel;
	}

	public void draw() {
		String ActualImage = "images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, ActualImage);
		StdDraw.show();
	}	
}