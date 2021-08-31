package org.apache.tajo.isw2tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.apache.tajo.datum.DateDatum;
import org.apache.tajo.datum.Datum;
import org.apache.tajo.datum.DatumFactory;
import org.apache.tajo.exception.TajoRuntimeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class DateDatumTest {
    private static String DATE = "1980-04-01";
  
    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{-1, "4714-11-23"}, {0, "4714-11-24"}, {1, "4714-11-25"}, {2444331, "1980-04-01"}};
        return Arrays.asList(data);
    }

    @Parameter(0)
    public int value; // in Java -1 is 4714-11-23, 0 is 4714-11-24, 1 is 4714-11-25, 2444331 is 1980-04-01

    @Parameter(1)
    public String datum; // in Java -1 is 4714-11-23, 0 is 4714-11-24, 1 is 4714-11-25, 2444331 is 1980-04-01

    @Test
    public void createDateFromIntParamTest() {
      Datum d = DatumFactory.createDate(datum);
      Datum copy = DatumFactory.createDate(value);
      assertEquals(d.toString(), copy.toString());
    }
    
    @Test
    public void createDateFromInt() {
      Datum d = DatumFactory.createDate(DATE);
      Datum copy = DatumFactory.createDate(d.asInt4());
      assertEquals(d, copy);
    }
  
    @Test
    public void createDateFromString() {
      Datum d = DatumFactory.createDate(DATE);
      Datum copy = DatumFactory.createDate(d.asChars());
      assertEquals(d, copy);
    }

    @Test
    public void createDateFromNonValidString() {
      Datum d = null;
      try {
        d = DatumFactory.createDate("blabla");
      } catch (IllegalArgumentException e) {
        // excepted
        assertNull(d);
      }
    }
    
    @Test
    public void createDateFromYearMonthDay() {
      DateDatum d = DatumFactory.createDate(DATE);
      assertEquals(1980, d.getYear());
      assertEquals(4, d.getMonthOfYear());
      assertEquals(1, d.getDayOfMonth());
    }

    @Test
    public void createDateFromNotValidYearMonthDay() {
      DateDatum d = DatumFactory.createDate("0001-01-01");
      DateDatum d1 = DatumFactory.createDate(1, 1, 1);
      assertEquals(d.toString(), d1.toString());
    }
  
    @Test
    public void createDateFromDatum() {
      Datum d = DatumFactory.createDate(DATE);
      Datum d1 = DatumFactory.createDate(d);
      assertEquals(d1, d);
    }

    @Test
    public void createDateFromNonValidDatum() {
      Datum d = null;
      try {
        d = DatumFactory.createDate(DatumFactory.createFloat8(2444331));
      } catch (TajoRuntimeException e) {
        // excepted
        assertNull(d);
      }
    }

    @Test
    public void createDateFromNullDatum() {
      Object testObject = null;
      Datum d = null;
      try {
        d = DatumFactory.createDate((Datum) testObject);
      } catch (Exception e) {
        // excepted
        assertNull(d);
      }
    }

    @Test
    public void createDateFromDifferentDatum() {
      DateDatum d = DatumFactory.createDate(DATE);
      DateDatum d1 = DatumFactory.createDate(DatumFactory.createInt4(2444331));
      DateDatum d2 = DatumFactory.createDate(DatumFactory.createInt8(2444331));
      DateDatum d3 = DatumFactory.createDate(DatumFactory.createText(DATE));
      assertEquals(d1, d);
      assertEquals(d2, d);
      assertEquals(d3, d);
    }
    
    @Test
    public void testCompareTo() {
      DateDatum theday = DatumFactory.createDate("2014-11-12");
      DateDatum thedaybefore = DatumFactory.createDate("2014-11-11");

      assertTrue("theday major thedaybefore", theday.compareTo(thedaybefore) > 0);
      assertFalse("thedaybefore major theday", thedaybefore.compareTo(theday) > 0);
    }
  }
