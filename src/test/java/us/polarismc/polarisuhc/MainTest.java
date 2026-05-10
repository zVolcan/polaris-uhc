package us.polarismc.polarisuhc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MainTest {

    private ServerMock server;

    @BeforeEach
    void setUp() {
        server = MockBukkit.mock();
    }

    @AfterEach
    void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    void onEnableDoesNotThrowAndInstanceIsNonNull() {
        Main plugin = MockBukkit.load(Main.class);
        assertNotNull(Main.getInstance(), "Main.getInstance() should not be null after onEnable()");
    }
}