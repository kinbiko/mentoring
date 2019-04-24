# Generics

Generics are a language construct that defines a close but flexible relationship between two types. Can be intuitively thought of as an 'of-a' relationship, in the same way that inheritance can be thought of as a 'is-a' relationship.
With `generics`, you tell the compiler what type(s) another type is concerned with. The compiler inserts casts for you automatically and tells you at compile time if you try to insert an object of the wrong type. This results in programs that are more type-safe, but also flexible, in that a generic type only needs to be declared once regardless of how many types it is associated with.

A class or interface whose declaration has one or more *type parameters* is a *generic* class or interface.

A `List` interface can have a single type parameter, `E`, representing the element type of the list. The name of the interface is now `List<E>` (read “list of E”), but people often call it *List* for short. Generic classes and interfaces are collectively known as `generic types`.

Each generic type defines a set of *parameterized types*, which consist of the class or interface name followed by an angle-bracketed list of *actual type parameters* corresponding to the generic type's formal type parameters. For example, `List<String>` (read “list of string”) is a parameterized type representing a list whose elements are of type `String`.

It is convention to use a single character for type parameter names, and to use T to indicate that the parameter is a type, E to indicate that the parameter is an element, K to indicate that the parameter is a key, N to indicate the parameter is a number, and V to indicate that the parameter is a value.

`Unbounded wildcard types`: If you want to use a generic type but you don't know or care what the actual type parameter is, you can use a question mark instead. For example, the unbounded wildcard type for the generic type `Set<E>` is `Set<?>` (read “set of some type”). It is the most general parameterized Set type, capable of holding any type.

## Generics: Terms and Examples

| Term                    | Example                           |
|-------------------------|-----------------------------------|
| Parameterized type      |  `<String>`                        |
| Actual type parameter   |  `String`                          |
| Generic type            |  `List<E>`                         |
| Formal type parameter   |  `E`                               |
| Unbounded wildcard type |  `List<?>`                         |
| Raw type                |  `List`                            |
| Bounded type parameter  |  `<E extends Number>`              |
| Recursive type bound    |  `<T extends Comparable<T>>`       |
| Bounded wildcard type   |  `List<? extends Number>`          |
| Generic method          | `static <E> List<E> asList(E[] a)` |
| Type token              | `String.class`                     |
