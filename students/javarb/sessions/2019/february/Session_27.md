# SOFTWARE ENGINEERING IN JAVA

## Session 27 (05/03/2019)

- Reviewing and correction API design
- Password security
- Project planning
- Quiz

## Notes

#### Reviewing and correction API design

Several suggestion were made for the engineering design and it was finished (for the selected requirements).

After it, and to before to begin with implementation, was necessary to do some planning and also to talk about some security considerations.

#### Password security

About to store passwords we were talking about difference between hashing and encription.

Hashing refeers to convert a string of text in another string of certain lenght defined by the used hashing algorithm. So once a text is hashed this is impossible to turn back to its original string (at least in the best algorithms). Some of the algoriths are MD5 (not is recommended to be used [see here][1]), SHA and SHA-2.

About encription, this transforms the data (files) into unreadable characters. The difference with hashing is that we can return to the data by having the key with which the data was encrypted. Some popular algorithms are AES and PGP.

Finally, was said that at least in the first stages of our wallet application we aren't going to store passwords.

Also, has to be considered that deal with security in Spring can be complex. One solution to this (in really in any enviroment not just Spring) can be to use the [Auth0][3] SaaS (which uses [Oauth][5]). They provide integration with [Spring][4] and a very detailed and easy to follow documentation, covering different platforms, examples and technical information about security topics.

**Note:** Is recommended, be very serious and not to take security lightly. Always to use well known and established solutions for our applications security management.

#### Project Planing

We were planing the tasks, we defined some [issues][6] in the wallet [main project][8]. A new [project was created for the first issue][7] and inside it are the tasks to do in order to complete that issue. For other issues the same will be done.

Also, is important to remember that we are in the first milestone of the product and this approach will be repeated for each project we take on.

### Homework

- To do the [List Account issue tasks][6] defined in the [project][7] issue

### Resources

- [Hashing vs Encrypting ][1]
- [MD5 Hash generator][2]
- [Auth0][3]
- [Auth0 - Spring][4]
- [Oauth 2][5]

[1]: https://www.securityinnovationeurope.com/blog/page/whats-the-difference-between-hashing-and-encrypting
[2]: https://www.md5hashgenerator.com/
[3]: https://auth0.com/
[4]: https://auth0.com/docs/quickstart/backend/java-spring-security
[5]: https://oauth.net/2/
[6]: https://github.com/javarb/wallet/issues/6
[7]: https://github.com/javarb/wallet/projects/2
[8]: https://github.com/javarb/wallet/projects/1