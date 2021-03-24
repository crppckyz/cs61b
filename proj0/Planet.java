public class Planet {

  private static double G = 6.67e-11; //Gravitational constant

  public double xxPos; //current X position
  public double yyPos; //current Y position
  public double xxVel; //current velocity in the X direction
  public double yyVel; //current velocity in the Y direction
  public double mass; //mass
  public String imgFileName;//name of the corresponding file

  /**
   * @param xP:  current X position
   * @param yP:  current Y position
   * @param xV:  current velocity in the X direction
   * @param yV:  current velocity in the Y direction
   * @param m:   mass
   * @param img: name of the corresponding file
   */

  public Planet(double xP, double yP, double xV, double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  /*
   Constructor to copy any planet - p
   */
  public Planet(Planet p) {
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  /*
   Calculate the distance between two planets
   */
  public double calcDistance(Planet p) {
    double xDiff = xxPos - p.xxPos;
    double yDiff = yyPos - p.yyPos;
    double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    return dist;
  }

  /*
    Calculate the force between two planets
   */
  public double calcForceExertedBy(Planet p) {
    double r = calcDistance(p);
    double F = G * (mass * p.mass) / (r * r);
    return F;
  }

  /*
    Calculate the force in the x direction
   */
  public double calcForceExertedByX(Planet p) {
    double F = calcForceExertedBy(p);
    double r = calcDistance(p);
    double fX = F * (p.xxPos - xxPos) / r;
    return fX;
  }

  /*
    Calculate the force in the y direction
   */
  public double calcForceExertedByY(Planet p) {
    double F = calcForceExertedBy(p);
    double r = calcDistance(p);
    double fY = F * (p.yyPos - yyPos) / r;
    return fY;
  }
/*
  Calculate the Net X Force exerted by all planets on planet p
 */
  public double calcNetForceExertedByX(Planet[] allPlanets) {
    double fXnet = 0;
    for (Planet p : allPlanets) {
      if (equals(p)) {
        continue;
      } else{
        fXnet += calcForceExertedByX(p);
      }
    }
    return fXnet;
  }
/*
  Calculate the Net X Force exerted by all planets on planet p
 */
  public double calcNetForceExertedByY(Planet[] allPlanets) {
    double fYnet = 0;
    for (Planet p : allPlanets) {
      if (this.equals(p)) {
        continue;
      } else{
        fYnet += calcForceExertedByY(p);
      }
    }
    return fYnet;
  }
  public void update(double t, double fX, double fY){
    double aX = fX / this.mass;
    double aY = fY / this.mass;
    this.xxVel += aX * t;
    this.yyVel += aY * t;
    this.xxPos += this.xxVel * t;
    this.yyPos += this.yyVel * t;
  }
  public void draw(){
    StdDraw.picture(xxPos,yyPos,"./images/"+imgFileName);
  }
}
