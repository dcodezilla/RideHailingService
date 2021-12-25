package com.shivam.junittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class DriverServiceUnitTests extends UnitTests {
    @Test
    public void testNewDriverIntroduction() {
        ResponseEntity responseEntity = driverService.addDriverToTheSystem("3", "Driver1");
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    public void testAssigningNullDriverToCabItWillThrowNPE() throws Exception {
        initUsers();
        initDrivers();

        Exception exception = Assertions.assertThrows(NullPointerException.class, () -> cabService.introduceNewCabInSystem("c1",null));
        //System.out.println(exception.getMessage());
        Assertions.assertTrue(exception.getMessage().contains("driverId is marked non-null but is null"));
    }
}
