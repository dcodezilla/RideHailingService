package com.shivam.junittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class CabServiceUnitTests extends UnitTests {
    @Test
    public void testNewCabIntroduction() throws Exception {
        ResponseEntity responseEntity = cabService.introduceNewCabInSystem("1", "Cab1");
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    public void testAdditionOfDuplicateCabItShouldThrowError() throws Exception {
        cabService.introduceNewCabInSystem("1", "Cab1");
        Exception exception = Assertions.assertThrows(Exception.class, () -> cabService.introduceNewCabInSystem("1", "Cab1"));
        //System.out.println(exception.getMessage());
        Assertions.assertTrue(exception.getMessage().contains("Test"));
    }

    @Test
    public void testUpdateCabLocation() throws Exception {
        cabService.introduceNewCabInSystem("1", "Cab1");
        ResponseEntity responseEntity = cabService.updateCabLocation("1", 1.0, 1.5);
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }

    @Test
    public void testUpdateCabAvailability() throws Exception {
        ResponseEntity responseEntity = cabService.introduceNewCabInSystem("1", "Cab1");
        System.out.println(responseEntity.getBody());
        ResponseEntity responseEntityNew = cabService.updateCabAvailability("1", false);
        Assertions.assertEquals("200 OK", responseEntity.getStatusCode().toString());
    }
}
