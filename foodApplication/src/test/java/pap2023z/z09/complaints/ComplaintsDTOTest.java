package pap2023z.z09.complaints;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.MockitoAnnotations;
public class ComplaintsDTOTest {
    private ComplaintsDTO complaint;
    public final static int exampleInt = 1;
    public final static String exampleString = "a";
    public final static Boolean exampleBool = true;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        complaint = new ComplaintsDTO();
    }

    @Test
    public void testSetAndGetComplaintId() {

        complaint.setComplaintId(exampleInt);
        assertEquals(exampleInt, complaint.getComplaintId());
    }

    @Test
    public void testSetAndGetIsOpen() {

        complaint.setOpen(exampleBool);
        assertEquals(exampleBool, complaint.isOpen());
    }
    @Test
    public void testSetAndGetDesription() {

        complaint.setDescription(exampleString);
        assertEquals(exampleString, complaint.getDescription());
    }

    @Test
    public void testSetAndGetOrderId() {

        complaint.setOrderId(exampleInt);
        assertEquals(exampleInt, complaint.getOrderId());
    }

}