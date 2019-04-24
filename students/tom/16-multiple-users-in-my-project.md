# Multiple users
- Multiple users should be able to make use of the system at the same time.
- Further consideration is needed for simultaneous transactions. For example, if Alice makes a transaction to Bob, and Bob makes a transaction to Alice at the same time then this needs to be carefully handled.
- All modifications of persistent objects performed within the boundary of the transaction will either [(link to SO article)](https://stackoverflow.com/questions/34832758/how-to-handle-transactions-with-concurrency-access-in-spring):
1. be commited at the end of the transaction (i.e. all modification are persisted in the DB)
1. be rollbacked at the end of the transaction (i.e. none of the modifications are persisted in the DB)

## Locking and Concurrency
`Locking` is a technique for handling database transaction concurrency (`concurrency`: the ability to work with the data on more than one queue at the same time.) When two or more database transactions concurrently access the same data, locking is used to ensure that only one transaction at a time can change the data.
There are generally two locking approaches: `optimistic` and `pessimistic`. Optimistic locking assumes that there
will be infrequent conflicts between concurrent transactions, that is, they won't often try to read and change the
same data at the same time. In optimistic locking, the objective is to give concurrent transactions a lot of freedom to process simultaneously, but to detect and prevent collisions. Two transactions can access
the same data simultaneously. However, to prevent collisions, a check is made to detect any changes made to the data since the data was last read.
[(information from Oracle.com)](https://blogs.oracle.com/enterprisetechtips/locking-and-concurrency-in-java-persistence-20)

`Pessimistic concurrency control` – This implies that the service locks the resource so that a client cannot updated it. While the resource is locked, no other client can modify it.

`Optimistic concurrency control` – This implies that a client first obtains a token for the update operation. Once the token is received, it allows the client to perform the update. However, the changes will only apply if the token is still valid.
[(information from 4psa.com)](https://blog.4psa.com/rest-best-practices-managing-concurrent-updates/)
