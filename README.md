# wire-message-contracts

Using only the following protobuf message file:

```proto
syntax = "proto2";

package org.hiatusuk.messages;

message MyMessage {
  required int64 id = 1;
  required string email = 2;
  required string userExternalReference = 3;

  enum UserRole {
      MASTER = 1;
      SERVANT = 2;
  }

  optional UserRole role = 4;
  optional string correlationId = 5;
}
```

The Maven plugin from [square/wire](https://github.com/square/wire) generates code to allow the following Test:

```java
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

    final byte[] bytes = MyMessage.ADAPTER.e.encode(original).clone();
    final MyMessage decoded = MyMessage.ADAPTER.decode(bytes);

    // Test equality, every way we can
    TestUtils.testEqualsHashcode(original, decoded);
}
```

that demonstrates serialisation and deserialisation, with fully working `equals()` and `hashCode()` implementations.
