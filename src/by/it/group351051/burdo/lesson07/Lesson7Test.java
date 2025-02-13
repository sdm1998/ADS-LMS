package by.it.group351051.burdo.lesson07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Lesson7Test {

    @Test
    public void A() throws Exception {
        A_EditDist instance = new A_EditDist();
        assertEquals("A1 failed", instance.getDistanceEdinting("ab","ab"),0);
        assertEquals("A2 failed", instance.getDistanceEdinting("short","ports"),3);
        assertEquals("A3 failed", instance.getDistanceEdinting("distance","editing"),5);
        assertEquals("A4 failed", instance.getDistanceEdinting("labrador","gibraltar"),5);
        assertEquals("A5 failed", instance.getDistanceEdinting("скотина","котик"),3);
    }
    @Test
    public void B() throws Exception {
        B_EditDist instance = new B_EditDist();
        assertEquals("B1 failed", instance.getDistanceEdinting("ab","ab"),0);
        assertEquals("B2 failed", instance.getDistanceEdinting("short","ports"),3);
        assertEquals("B3 failed", instance.getDistanceEdinting("distance","editing"),5);
        assertEquals("B4 failed", instance.getDistanceEdinting("labrador","gibraltar"),5);
        assertEquals("B5 failed", instance.getDistanceEdinting("скотина","котик"),3);
    }

    @Test
    public void C() throws Exception {
        C_EditDist instance = new C_EditDist();
        assertEquals("C1 failed", instance.getDistanceEdinting("ab","ab"),"#,#,");
        assertEquals("C2 failed", instance.getDistanceEdinting("skotina","kotik"),"-s,#,#,#,#,~k,-a,");
        //путей может быть много, поэтому тут жестко проверить все сложно
        //надо найти и проверить их все, что делает тест сложнее реализации
        //возможно, что хватит только подсчета повторов.

        assertEquals("C3.1 failed", instance.getDistanceEdinting("short","ports").split("#").length,4);
        assertEquals("C3.2 failed", instance.getDistanceEdinting("short","ports"),"~p,-h,#,#,#,+s,");

        assertEquals("C4 failed", instance.getDistanceEdinting("distance","editing").split("#").length,5);
        assertEquals("C4.1 failed", instance.getDistanceEdinting("distance","editing"),"+e,#,#,-s,#,~i,#,~g,-e,");

        assertEquals("C5 failed", instance.getDistanceEdinting("","editing"),"+e,+d,+i,+t,+i,+n,+g,");
        assertEquals("C6 failed", instance.getDistanceEdinting("editing",""),"-e,-d,-i,-t,-i,-n,-g,");
    }

}
