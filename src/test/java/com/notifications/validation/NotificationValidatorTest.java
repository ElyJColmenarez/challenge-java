package com.notifications.validation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NotificationValidatorTest {

    @Test
    void testValidEmail() {
        assertTrue(NotificationValidator.isValidEmail("test@example.com"));
        assertTrue(NotificationValidator.isValidEmail("user.name+tag@domain.co"));
        assertFalse(NotificationValidator.isValidEmail("invalid-email"));
        assertFalse(NotificationValidator.isValidEmail(null));
    }

    @Test
    void testValidPhone() {
        assertTrue(NotificationValidator.isValidPhone("+1234567890"));
        assertTrue(NotificationValidator.isValidPhone("+573001234567"));
        assertFalse(NotificationValidator.isValidPhone("1234567890")); // Missing +
        assertFalse(NotificationValidator.isValidPhone("+abcdefghij"));
        assertFalse(NotificationValidator.isValidPhone(null));
    }

    @Test
    void testValidToken() {
        assertTrue(NotificationValidator.isValidToken("some-token-123"));
        assertFalse(NotificationValidator.isValidToken(""));
        assertFalse(NotificationValidator.isValidToken("   "));
        assertFalse(NotificationValidator.isValidToken(null));
    }
}
