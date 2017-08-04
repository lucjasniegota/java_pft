package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

  @Test
  public void testDistance1(){
    Point p1 = new Point(4,-7);
    Point p2= new Point(-1,-19);
    Assert.assertEquals(p2.distance(p1), 13.0);

  }
  @Test
  public void testDistance2(){
    Point p1 = new Point(5,-7);
    Point p2= new Point(-1,-19);
    Assert.assertEquals(p2.distance(p1), 13.416407864998739);

  }
}
