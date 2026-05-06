package com.notifications.channels.email;

import com.notifications.channels.email.providers.EmailProvider;
import com.notifications.core.Notification;
import com.notifications.core.NotificationResult;
import com.notifications.core.NotificationStatus;
import com.notifications.core.EmailNotification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailChannelTest {

    @Mock
    private EmailProvider provider;

    private EmailChannel emailChannel;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        EmailConfig config = EmailConfig.builder()
                .apiKey("test-key")
                .providerType("SENDGRID")
                .build();
        emailChannel = new EmailChannel(config);
        
        // Inyectamos el mock manualmente para el test de aislamiento
        Field field = EmailChannel.class.getDeclaredField("provider");
        field.setAccessible(true);
        field.set(emailChannel, provider);
    }

    @Test
    void testSendSuccess() {
        Notification notification = new EmailNotification(
                "test@example.com", 
                "Hello", 
                "World", 
                null
        );

        when(provider.send(any())).thenReturn(NotificationResult.success("Sent"));

        NotificationResult result = emailChannel.send(notification);

        assertTrue(result.isSuccess());
        assertEquals(NotificationStatus.SUCCESS, result.status());
        verify(provider, times(1)).send(notification);
    }

    @Test
    void testSendInvalidEmail() {
        Notification notification = new EmailNotification(
                "invalid-email", 
                "Sub", 
                "Body", 
                null
        );

        NotificationResult result = emailChannel.send(notification);

        assertFalse(result.isSuccess());
        assertEquals(NotificationStatus.INVALID, result.status());
        assertTrue(result.message().contains("Invalid email format"));
        verify(provider, never()).send(any());
    }
}
