# Collections

Java provides Collection Framework which defines several classes and interfaces to represent a group of objects as a single unit.

The Collection interface (`java.util.Collection`) and Map interface (`java.util.Map`) are two main root interfaces of Java collection classes.

`Hierarchy of Collection Framework` (core interfaces only)

```
             Collection                Map
         /     /    \      \            |
        /      /      \     \           |
     Set    List    Queue  Dequeue   SortedMap
     /
    /
 SortedSet
```
---

```
Collection : Root interface with basic methods like add(), remove(),
             contains(), isEmpty(), addAll(), ... etc.

Set : Doesn't allow duplicates. Example implementations of Set
      interface are HashSet (Hashing based) and TreeSet (balanced
      BST based). Note that TreeSet implements SortedSet.

List : Can contain duplicates and elements are ordered. Example
       implementations are LinkedList (linked list based) and
       ArrayList (dynamic array based)

Queue : Typically order elements in FIFO order except exceptions
        like PriorityQueue.

Deque : Elements can be inserted and removed at both ends. Allows
        both LIFO and FIFO.

Map : Contains Key value pairs. Doesn't allow duplicate keys.  Example
      implementation are HashMap and TreeMap.
      TreeMap implements SortedMap.

The difference between Set and Map interface is, in Set we have only
keys, but in Map, we have key value pairs.
```

## Most common implementations

The following are good go-to implementations that will work well 99% of the time.

- `Set` -> `HashSet`
- `List` -> `ArrayList`
- `Map` -> `HashMap`
