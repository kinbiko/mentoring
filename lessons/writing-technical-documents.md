# Writing technical documents

Technical documents are useful to help others and yourself do initial research into solutions to problems you might have. For example, say you know you need to build a web service, but you don't know what technology/language to use. It would be beneficial to write a technical document explaining the requirements you need from this service, and checking how various technologies handle these requirements. Small example:

```
We need to quickly write a service to handle complex user forms that have a lot of validation and has to have really performant writes to a MongoDB database.

Technology suggestions:
=======================

Java: Frontend and backend code for validation is very different-looking. Writing to Mongo is easy only if we use Spring - and even then getting high performance requires us to use low-level driver methods. Would be time-consuming to implement. Not recommended.

Ruby: Writing to Mongo is relatively simple assuming we use mongoid. Development speed should be relatively quickly with a Rails service as our team consists of many skilled rails develpoers. Performance might be an issue.

Node: Front-end and backend validation can use the same code as it's JavaScript on both the front-end and backend. MongoDB is well supported through mongoose, and the MongoDB syntax is very similar to Javascript. Probably the way to go.
```

Almost more important than the actual content of your design and findings is the lucidity of the document. It should be unambiguous.
In particular, try and avoid using multiple nouns describing the same concept or thing. E.g. don't call something both an 'Account' and an 'Organisation'. Pick one and stick to it.
Avoid overly-generic nouns: 'DataObject' means nothing.
Avoid using abbreviations unless they themselves are well known. I.e. `API` (application programming interface) is OK but `TLA` (three letter acronym) is not.

It's OK to write code if it helps illustrate your thinking. This code does not need to compile as long as it conveys the intent.

Diagrams are excellent and can really help people understand more complex designs, but *don't add them if the process of changing the diagrams would require a lot of work*. Because diagrams *will have to change*. There are tools out there that help you do diagrams - none of them are particularly good, but [draw.io](https://www.draw.io/) is tolerable, and lets you sync to GitHub.
