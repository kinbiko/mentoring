# Persisting Data: data that outlives the process that created it

Taking an object that exists in runtime and store that object out to a bytestream - and take this bytestream and completely reproduce that object. This means that you can save the state of your program to your filesystem or database. This is known as 'serialization' and 'deserialization'.
Simply speaking serialization is a process of converting an object into stream of bytes so that it can be transferred over a network or stored in a persistent storage. A Java object is serializable if its class or any of its superclasses implements the `java.io.Serializable` interface.
Deserialzation is exact opposite - Fetch a stream of bytes from network or persistence storage and convert it back to the Object with the same state.
Requirements to being serializable: Primitives are serializable by default, but classes must implement `Serializable`.
- `Serializable`: an interface that is a marker interface (has no methods) - it indicates that the type can be serialized/deserialized.
Types that perform Serialization:
- `ObjectOutputStream`
- `ObjectInputStream`
Marking fields `transient` means that the field won't be serialized.
`Serializable` POJO methods HAVE to be `public`.
