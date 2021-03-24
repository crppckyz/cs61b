public class NBody {

  public static double readRadius(String s) {
    In in = new In(s);

    in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  public static Planet[] readPlanets(String s) {
    In in = new In(s);
    int n = in.readInt();
    in.readDouble();
    Planet[] allPlanets = new Planet[n];
    for (int i = 0; i < n; i++) {
      double xCoor = in.readDouble();
      double yCoor = in.readDouble();
      double xVelo = in.readDouble();
      double yVelo = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();
      allPlanets[i] = new Planet(xCoor, yCoor, xVelo, yVelo, mass, img);
    }
    return allPlanets;
  }

  private static String backgroundImage = "./images/starfield.jpg";

  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] allPlanets = readPlanets(filename);

    StdDraw.setScale(-radius, radius);

    double t = 0.0;
    int n = allPlanets.length;
    double[] xForces = new double[n];
    double[] yForces = new double[n];

    while (t < T) {
      for (n = 0; n < allPlanets.length; n++) {
        xForces[n] = allPlanets[n].calcNetForceExertedByX(allPlanets);
        yForces[n] = allPlanets[n].calcNetForceExertedByY(allPlanets);
      }

      for (n = 0; n < allPlanets.length; n++) {
        allPlanets[n].update(dt, xForces[n], yForces[n]);
      }

      StdDraw.picture(0, 0, backgroundImage);

      for (Planet p : allPlanets) {
        p.draw();
      }

      StdDraw.show();

      StdDraw.enableDoubleBuffering();
      StdDraw.pause(10);
      t += dt;
    }

    StdOut.printf("%d\n", allPlanets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < allPlanets.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
          allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
          allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
    }
  }
}
