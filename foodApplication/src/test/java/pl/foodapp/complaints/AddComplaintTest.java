package pl.foodapp.complaints;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import pl.foodapp.complaints.AddComplaint;

import static org.junit.jupiter.api.Assertions.*;
public class AddComplaintTest {

    @InjectMocks
    private AddComplaint complaint;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    private final static String exampleString = "review";
    private final static String exampleBadString = "";

    @Test
    public void testcheckDescription(){
        assertThrows(IllegalArgumentException.class, () -> complaint.checkDescription(exampleBadString));
        assertDoesNotThrow(() -> complaint.checkDescription(exampleString));
    }

}