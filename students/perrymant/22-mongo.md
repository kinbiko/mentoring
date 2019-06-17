# MongoDB

> MongoDB is a document-oriented, leading NoSQL database, which offers linear scalability, thus making it a good contender for high-volume, high-performance systems across all the business domains. It has an edge over the majority of NoSQL solutions for its ease of use, high performance, and rich features. - Dasadia & Nayak: MongoDB Cookbook

- MongoDB is a NoSQL database that provides an alternative to the relational database paradigm through a document-based data model that can represent rich, hierarchical data structures.
- CRUD (Create,Read,Update,Delete) operations can be performed on the database.
- MongoDB uses JSON-like (BSON) documents (think rows), which consist of keys and values, and they can nest arbitrarily deep.
- Here's a typical MongoDB document:
```js
{
_id: ObjectId('f2d1e8e17ce8963b4bdf264a'),
  username: 'HumptyD',
  email: [
    'HumptyD@gmail.com',
    'super_egg@falling.com'
  ]
}
```

### Overview of common commands

```sh
> show dbs
> show collections
> use perrymant
> db.people.insert({name: "Thomas"})
> db.people.find()
> db.people.find().pretty()
> db.people.find({name: "Thomas"}).explain()
> db.people.createIndex({name: 1})
> db.people.createIndex({name: 1, email: -1}) //compound indexes
> db.people.getIndexes()
> db.people.dropIndexes()
> exit
```

### Commands: in depth

- `db.help()`: view all the commands.
- `show collections`: collections (like tables), test and play around with your mongodb environment
- `use <collection-name>`: starts a new collection, or uses a collection if pre-existing. `db.createCollections('<name>')` creates a new collection.
- `db.people.insert({name: "Thomas"})`: Create or insert operations add new documents to a collection in JSON format. If the collection does not currently exist, insert operations will create the collection.
- `db.people.find()`: Read operations retrieves documents from a collection; i.e. queries a collection for documents.
- `db.people.find().pretty()`: using find with `.pretty()` will display the results in a formatted way.
- `db.people.find().explain()`: provides information on the query plan, helps when considering how you might optimise a search:
```json
{
    "queryPlanner" : {
        "plannerVersion" : 1,
        "namespace" : "test.people",
        "indexFilterSet" : false,
        "parsedQuery" : {

        },
        "winningPlan" : {
            "stage" : "COLLSCAN",
            "direction" : "forward"
        },
        "rejectedPlans" : [ ]
    },
    "serverInfo" : {
        "host" : "81fc64f4db34",
        "port" : 27017,
        "version" : "4.0.9",
        "gitVersion" : "fc525e2d9b0e4bceff5c2201457e564362909765"
    },
    "ok" : 1
}
```
- There are many logical expressions for finding documents: `$and`, `$or`, `$not`, `$gte` (greater than or equal), `$lt` (less than), `$ne` (not equal to), `$type`, `$regex: 'expression'`.
- They take the form: `{ $and: [ { <exp1> }, { <exp2> } , ... , { <expN> } ] }`
- Here's an example with an AND, NOT (with a regex expression) and greater than or equal:
```js
db.student.find({$and:[{"s_name":{ $not: /^M.*/ }},{"grd_point":{ $gte: 31 }},{"class": "VI"}]}).pretty();
```
- Sort: `db.orders.find().sort( { age : -1, posts: 1 } )` results can be sorted in ascending (1) or descending (-1) order.
- Indexes are used in databases to turn unsorted data into sorted data, which is stored in memory (an index increases the amount of storage needed to represent a document and the time it takes to insert a document), and allows for faster searching, as they allow the database to search through less documents to satisfy a query.
- Without an index MongoDB has to scan through all of the documents in a collection to fulfill the query. Indexes trade off faster queries against storage space.
- Creating an index looks like this: `db.student.createIndex( { name: -1 } )`
- Compound indexes allow for nested (multiple fields) indexing: `db.collection.createIndex( { <field1>: <type>, <field2>: <type2>, ... } )`, which can further speed up searches. It means that you want to sort by `<field1>` first, then sort by `<field2>`.
    - In a compound index like this: `db.student.createIndex( { name: -1, age: 1 } )` the name field is ordered into descending order and the age field is ordred in ascending order.
    - Given the above index, we would no longer need an index in the `name` field, however we would need an index for the `age` field if we were searching based on `age`.
- Try to create indexes on `high-cardinality keys` (the keys with the highest variance of values) or put `high-cardinality keys` first in the compound index.
- When using the `explain()` method, it will state the kind of sorting algorithm in use, `COLLSCAN` (collection scan) is slower than the `IXSCAN` (index scan).

##### Useful to know:

- The default port is 27017.
- `ObjectId`: every document created in MongoDB will have an `ObjectId`, which looks like `_id: <5bb7...>` where the 12-byte hexadecimal number represents a 4-byte value representing the seconds since the Unix epoch, a 5-byte random value, and a 3-byte counter, starting with a random value. These are used to uniquely identify all objects in MongoID and acts as a primary key.
- Example databases available at: https://github.com/ozlerhakan/mongodb-json-files
- `MongoDB Compass` is an example of a GUI.

##### Steps to get Mongo running via Docker

```sh
$ docker pull mongo
$ docker run -d mongo # returns CONTAINER ID
$ docker exec -it <CONTAINER ID> mongo
```
