# SOFTWARE ENGINEERING IN JAVA

## Session 25 (05/02/2019)

- API specs
- Spring alternatives
- Engineering Design
- GItHub project integration in Slack



## Notes

#### API specs - URL

Roger was reviewing my homework and advising to me about API approach is not the same that web apps. In web apps we worry about returned HTML content or frontend stuff, whereas in web APIs we are only concerned with the **data** (e.g. JSON payloads) and we aren't worrying about how data will be shown.

When creating resources in RESTful API, we POST pointing to the plural name of the resource. So, for example, if the name of the resource is `account` we'll create accounts by sending the `POST` request to the URL `/accounts`. On the other hand, in a web app we might send it to the URL `/accounts/new`.

We will list accounts by sending the `GET` request to the URL `/accounts` and for specific accounts queries we send the request to the URL ` /account/:id`



#### API specs - Content Type

We can [retrieve different type of data][1] for web development but in API approach we are receiving data (e.g. [JSON][2] payloads). And even if APIs can return data they might not return any at all.

**Note:** Returning or not depends on the designer, for example for `DELETE` request it might not be desireable to return anything whereas than for `CREATE` request this could be.



#### Spring alternatives

[Micronaut Framework][3] seems very promising and its recommended for Microservices.

[Struts Framework][4] isn't recommended, it is not so good as Spring and over all had a [big recent discovered vulnerability][5].  



#### Engineering Design

We were working on the Engineering Design for the selected requirements. We work in class the requirement to list all accounts. It was defined the programming language and framework to use.

Also, were defined the HTTP endpoints behavior in the controller side, as well as the used POJOS.

**Note:** Code defined in Engineering Design has not to be exact, even not to compile, this is just to have a general understanding

Finally, the DBMS to use was defined, in this case we selected to use MySQL and we define which indexes we're going to use. The database schema was defined as follows:

| Column       | Type         | Key     | Nullable |
| ------------ | ------------ | ------- | -------- |
| id           | int(11)      | primary | No       |
| account_name | varchar(20)  |         | No       |
| fullname     | varchar(255) |         | No       |

**Indexes:** id

**Note:**: Is pretty important to search for needed `indexes` in our database schemas, since they speed our queries (from linear time to logarithmic time). And so this depends on our application purposes.


#### GitHub's repo integration in Slack

Integration of my [GitHub repo](github.com/javarb/wallet) was made within the class channel (#wallet) in Slack.  



### Homework

- [ ] Draw the Architectural Diagram

- [ ] Make the Engineering Design for`new account` requirement.



### Resources

- [HTTP Headers Content-Type][1]
- [JSON API][2]
- [Micronaut Framework][3]
- [Struts Framework][4]
- [CVE-2018-11776: The latest Apache Struts vulnerability][5]

[1]: https://developer.mozilla.org/es/docs/Web/HTTP/Headers/Content-Type
[2]: https://jsonapi.org/
[3]: https://micronaut.io/
[4]: https://struts.apache.org/
[5]: https://www.synopsys.com/blogs/software-security/cve-2018-11776-apache-struts-vulnerability/