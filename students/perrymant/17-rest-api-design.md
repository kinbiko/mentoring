# REST API Design Rulebook, Mark Massé, 2012
These are my condensed notes on Massé's book on REST API Design, with some additions from other sources where I needed more information to understand the concept.

## Scalability of the Web: REST
The constraints imposed on the Web’s scalability, which Fielding grouped into six categories and collectively referred to as the `Web’s architectural style`, which he named `“Representational State Transfer” (REST)`, are:

1. `Client-server`
The Web is a client-server based system, in which clients and servers have distinct parts to play.

2. `Uniform interface`
    1. `Identification of resources`, e.g. using URIs
    2. `Manipulation of resources through representations`, e.g. the resource is in JSON - but displayed in HTML, the representation is a way to interact with the resource but it is not the resource itself
    3. `Self-descriptive messages`: think JSON and XML. A self-describing message contains the data and metadata describing the format and meaning of a message. They usually contain all the data needed to understand the message, and all the information necessary to complete a task. Self-describing messages help in reducing the amount of coupling between components throughout the system, and in facilitating independent evolution of client-server components.
    4. `Hypermedia as the engine of application state (HATEOAS)`.
    A HATEOAS-based response might look like this:
    ```json
    {
        "name": "Alice",
        "links": [ {
            "rel": "self",
            "href": "http://localhost:8080/customer/1"
        } ]
    }
    ```
3. `Layered System`
The layered system constraints enable network-based intermediaries such as proxies and gateways to be transparently deployed between a client and server using the Web’s uniform interface. Generally speaking, a network-based intermediary will intercept client-server communication for a specific purpose. Network-based intermediaries are commonly used for enforcement of security, response caching, and load balancing.

4. `Cache`
Caching is one of web architecture’s most important constraints. The cache constraints instruct a web server to declare the cacheability of each response’s data. Caching response data can help to reduce client-perceived latency, increase the overall availability and reliability of an application, and control a web server’s load. In a word, caching reduces the overall cost of the Web. A cache may exist anywhere along the network path between the client and server. They can be in an organization’s web server network, within specialized `content delivery networks (CDNs)`, or inside a client itself.

5. `Stateless`
The stateless constraint dictates that a web server is not required to memorize the state of its client applications. As a result, each client must include all of the contextual information that it considers relevant in each interaction with the web server. Web servers ask clients to manage the complexity of communicating their application state so that the web server can service a much larger number of clients. This trade-off is a key contributor to the scalability of the Web’s architectural style.

6. `Code-On-Demand`
The Web makes heavy use of code-on-demand, a constraint which enables web servers to temporarily transfer executable programs, such as scripts or plug-ins, to clients.

## REST APIs
Web services are purpose-built web servers that support the needs of a site or any other application. Client programs use `application programming interfaces (APIs)` to communicate with web services. Generally speaking, an API exposes a set of data and functions to facilitate interactions between computer programs and allow them to exchange information. As depicted in the figure below, a Web API is the face of a web service, directly listening and responding to client requests:

    +--------+    Request     +-------------------+
    |        +-------------->>> Web ⌇ Web Service |
    | Client |                | API ⌇   backend   |
    |        <<<--------------+     ⌇             |
    +--------+    Response    +-------------------+

The REST architectural style is commonly applied to the design of APIs for modern web services. A Web API conforming to the REST architectural style is a REST API. A REST API consists of an assembly of interlinked resources. This set of resources is known as the REST API’s `resource model`.

## REST API questions for the design:
- Which request method should be used to update resource state?
- When should URI path segments be named with plural nouns?
- How do I map non-CRUD operations to my URIs?
- What is the appropriate HTTP response status code for a given scenario?
- How can I manage the versions of a resource’s state representations?
- How should I structure a hyperlink in JSON?

## REST API Identifier Design with URIs:
1. Rule: Generic URI syntax [RFC 3986]:
`URI = scheme "://" authority "/" path [ "?" query ] [ "#" fragment ]`
1. Rule: Forward slash separator (/) must be used to indicate a hierarchical relationship
1. Rule: A trailing forward slash (/) should not be included in URIs
1. Rule: Hyphens (-) should be used to improve the readability of URIs
1. Rule: Underscores (\_) should not be used in URIs
1. Rule: Lowercase letters should be preferred in URI paths
1. Rule: File extensions should not be included in URIs
1. Rule: Consistent subdomain names should be used for your APIs
1. Rule: Consistent subdomain names should be used for your `client developer portal`
1. Rule: A singular noun should be used for `document` names
1. Rule: A plural noun should be used for `collection` names
1. Rule: A plural noun should be used for `store` names
1. Rule: A verb or verb phrase should be used for `controller` names
1. Rule: Variable path segments may be substituted with identity-based values
1. Rule: `CRUD` function names should not be used in URIs
1. Rule: The query component of a URI may be used to filter collections or stores
    - example: GET /users?role=admin
1. Rule: The query component of a URI should be used to paginate collection or store results
    - example: GET /users?pageSize=25&pageStartIndex=50

- Many REST APIs have an associated website, known as a developer portal, to help on-board new clients with documentation, forums, and self-service provisioning of secure API access keys.
- A REST API is composed of four distinct resource archetypes: `document`, `collection`, `store`, and `controller`.

## Interaction Design with HTTP
- REST APIs embrace all aspects of the `HyperText Transfer Protocol`, version 1.1* (HTTP/ 1.1) including its `request methods`, `response codes`, and `message headers`.
- Clients specify the desired interaction method in the Request-Line part of an HTTP request message. RFC 2616 defines the Request-Line syntax: `Request-Line = Method SP Request-URI SP HTTP-Version CRLF`

### HTTP’s request methods
| Request | Description |
|---------|-------------|
| `DELETE` | HTTP request method used to remove its parent. |
| `GET` |  HTTP request method used to retrieve a representation of a resource’s state. |
| `HEAD` | HTTP request method used to retrieve the metadata associated with the resource’s state. |
| `OPTIONS` | HTTP request method used to retrieve metadata that describes a resource’s available interactions. |
| `POST` | HTTP request method used to create a new resource within a collection or execute a controller. |
| `PUT` | HTTP request method used to insert a new resource into a store or update a mutable resource. |


Rule: Always make proper use of the HTTP methods as specified by the rules.
Rule: `GET` must be used to retrieve a representation of a resource
Rule: `HEAD` should be used to retrieve response headers
Rule: `PUT` must be used to both insert and update a stored resource
Rule: `POST` must be used to create a new resource in a collection
Rule: `POST` must be used to execute controllers
Rule: `DELETE` must be used to remove a resource from its parent
Rule: `OPTIONS` should be used to retrieve metadata that describes a resource’s available interactions


### Response Status Codes:
| Code | Category    | Description |
|------|-------------|-------------|
| `1xx` | `Informational` |Communicates transfer protocol-level information.|
| `2xx` | `Success` | Indicates that the client’s request was accepted successfully.|
| `3xx` | `Redirection` | Indicates that the client must take some additional action in order to complete their request.|
| `4xx` | `Client Error` | This category of error status codes points the finger at clients.|
| `5xx` |`Server Error` | The server takes responsibility for these error status codes.|

### HTTP response success code summary
| Code | Name | Meaning |
|---|---|---|
| `200` | `(“OK”)` | should be used to indicate nonspecific success |
| `201` | `(“Created”)` | must be used to indicate successful resource creation |
| `202` | `(“Accepted”)` | must be used to indicate successful start of an asynchronous action |
| `204` | `(“No Content”)` | should be used when the response body is intentionally empty |
| `301` | `(“Moved Permanently”)` | should be used to relocate resources |
| `302` | `(“Found”)` | should not be used |
| `303` | `(“See Other”)` | should be used to refer the client to a different URI |
| `304` | `(“Not Modified”)`| should be used to preserve bandwidth (with conditional GET)|
| `307` | `(“Temporary Redirect”)` | should be used to tell clients to resubmit the request to another URI |

### HTTP response error code summary
| Code | Name | Meaning |
|---|---|---|
| `400` | `(“Bad Request”)` | may be used to indicate nonspecific failure |
| `401` | `(“Unauthorized”)` | must be used when there is a problem with the client’s credentials |
| `403` | `(“Forbidden”)` | should be used to forbid access regardless of authorization state |
| `404` | `(“Not Found”)` | must be used when a client’s URI cannot be mapped to a resource |
| `405` | `(“Method Not Allowed”)` | must be used when the HTTP method is not supported |
| `406` | `(“Not Acceptable”)` | must be used when the requested media type cannot be served |
| `409` | `(“Conflict”)` | should be used to indicate a violation of resource state |
| `412` | `(“Precondition Failed”)` | should be used to support conditional operations |
| `415` | `(“Unsupported Media Type”)` | must be used when the media type of a request’s payload cannot be processed |
| `500` | `(“Internal Server Error”)` | should be used to indicate API malfunction |

## Metadata Design


## Glossary:
- `API`: Application Programming Interface
- `Cache`: REST constraints that enable network-based intermediaries to hold on to resource state repre- sentations, which helps web servers meet the demands of their clients.
- `CRUD`: the four classic storage-oriented functions: create, retrieve, update, and delete.
- `Hypermedia`: An extension of hypertext that enables multiple formats to be combined and tethered together with links to design a multi-media information network.
- `HyperText Mark-up Language (HTML)`: to represent informative documents that contain links to related documents.
- `HyperText Transfer Protocol (HTTP)`: a message-based language that computers could use to communicate over the Internet.
- `Load balancing`: efficiently distributing incoming network traffic across a group of backend servers.
- `Response caching`: Caching the server response of a client request for future requests, in order to reduce the number of requests a client or proxy makes to a web server.
- `REST`: Representational State Transfer
- `REST API`: A web service interface that conforms to the Web’s architectural style.
- `Richardson Maturity Model`: three distinct levels of REST API maturity, which can help evaluate and concisely communicate the RESTfulness of many Web API designs. Each level corresponds with an aspect of the Web’s uniform interface that an API must embrace in order to be considered RESTful:
    1. URI
    2. HTTP
    3. Hypermedia
- `Uniform Resource Identifier (URI)`: a syntax that assigns each web document a unique address
- `Web API`: Used by clients to interact with a web service.
- `Web Service`: A web server programmed with specific, often reusable, logic.
