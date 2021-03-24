public class TestPlanet {

  public static void main(String[] args) {
    checkPairwiseForce();
  }

  public static void checkPairwiseForce(){
    Planet Saturn = new Planet(2.3e12,9.5e11,0,0,6e26,"saturn.gif");
    Planet Sun = new Planet(1e12,2e11,0,0,2e30,"sun.gif");

    double F = Sun.calcForceExertedBy(Saturn);
    System.out.println("F = " + F + "should be approximately 3.6e22");
  }
}
