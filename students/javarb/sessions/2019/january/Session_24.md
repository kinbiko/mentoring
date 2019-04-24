# SOFTWARE ENGINEERING IN JAVA

## Session 24 (29/01/2019)

- Wallet Project

### Notes

#### Wallet Project

Roger purposed to me to work in a project covering all the software workflow which consists in:

- Requirements
- Product design (horizontal litte things)
- Engineering design
- Actual implementation

These steps are intended be worked in repetition cycles ([sprints][2]).

A [repository][1] and project were created in Github selecting create automatically a `.gitignore` file for Java projects.

#### Requirements

In order to complete first steps. a folder called `design` was created and inside it, a file called `requirements.md` which contains the list of all identified requirements for the project.

Requirements must be descriptive and concrete and not mix technical terminology, since they are intended to be read by non technical people.

Regarding to names, as well as with addresses related requirements, is need not to make strong restrictions. The users of our application must could be free to choose whatever way to write their names or addresses, for example chinese names can have 1 character. Also is need to mention that we have to implement some restrictions, such as minimum and maximun input lenght and very importantly we have also to sanitize our inputs to avoid data loss and security risks. This last is funny exemplified in [this cartoon][6].

**NOTE:** The list of the [OWASP 2017 vulnerabilities][5] shows us the number one application security risk for that year was about injection flaws such as SQL, NoSQL and LDAP injection.

#### Product Design

After that we have to select which requirements we will desing (Product Design phase). So, for this sprint we selected:

1. The application must allow to create an account.
1. The application must allow to see a list of all created accounts.

Once we did it, a file called `product-design-list-all-acounts.md` was created containing:

##### Scenario

Description of steps and restrictions to make the actions the requirement is intended for.

For example, for the selected requirements:

>List accounts
>
>Shows all accounts in system, has a button that allows to go to the account creation view.
>The name of each acount can be clicked and this will lead to a blank page (for the moment)
>
>Create account
>
>Shows a form to enter the account information:
>
>- Account name: Must be unique among all other names on the system.
>- Person fullname: should be minimum 1 character long or maximun 255 characters.
>
>Finally when we press submit button, if the content provided in the form is valid, this will redirect to list accounts page, else will show an feedback error message showing form mistakes.

##### Wireframes

These are mockups (images) for the interfaces that will be shown for the selected requirements, satisfying the proposed scenario. This can be done with a wireframe utility such as [Balsamiq][3] which is paid or any diagraming tool such [Draw.io][4] which is free.

And so, for this sprint requirements, the mocks to do where:

- List accounts page
- Forms page
- Forms page error

##### API contract

Here is described the communication between the client and the server. As in our requirements we said our application will be a REST API, we have to describe the data that is transmited in that kind of application for the selected requirements.

In general terms, these are the things to be described which are part of the REST API specification.

- Request headers
  - content-type: application/json, etc.

- Request HTTP method

- Request URL: Everything after the domain

- Request body: The actual JSON payload (not in GET)

- Response headers
  - content-type: application/json, etc.

- Response body: Depends the data to be sent in JSON.

- Response status code:

  - 1xx information (not seen commonly)
  - 2xx success
  - 3xx redirections
  - 4xx client error
  - 5xx server error
  
**NOTE:** For all these is expected a request/response pair:

### Homework

For the activities flow track we're using projects and kanban method embeeded into GitHub's interface.

- [ ] To do the project sprint tasks:
  - [x] These notes
  - [ ] Wireframes in Draw.io exported to PNG files placed in a corresponding repository location.  
  - [ ] Desing the API contracts

### Resources

- [Wallet Repo][1]
- [What is sprint][2]
- [Importance of sanitize - still not control -user inputs][6]
- [Balsamiq Wireframes][3]
- [Draw.io][4]
- [OWASP Top 10 - 2017][5]

[1]: https://github.com/javarb/wallet
[2]: https://searchsoftwarequality.techtarget.com/definition/Scrum-sprint
[3]: https://balsamiq.com/
[4]: https://www.draw.io/
[5]: https://www.owasp.org/images/7/72/OWASP_Top_10-2017_%28en%29.pdf.pdf
[6]: https://xkcd.com/327/