package org.hiatusuk;

import org.hiatusuk.messages.MyMessage;
import org.hiatusuk.messages.MyMessage.UserRole;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

public class WireTest {

    @Test
    public void testSerDeserEqualsAndHashcode() throws IOException {
        final MyMessage original = new MyMessage.Builder()
                .id(1981L)
                .role(UserRole.SERVANT)
                .userExternalReference("system7")
                .email("ahsahs@dsfsdf.com")
                .correlationId("453454-435345-111-323")
                .build();

        System.out.println("Created: " + original);

        final byte[] bytes = MyMessage.ADAPTER.encode(original).clone();
        final MyMessage decoded = MyMessage.ADAPTER.decode(bytes);

        // Test equality, every way we can
        TestUtils.testEqualsHashcode(original, decoded);

        // Just for fun...
        assertThat( bytes.length, equalTo(56));
        assertThat( MyMessage.ADAPTER.encodedSize(decoded), equalTo(56));
    }
}
