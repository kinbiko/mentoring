# SOFTWARE ENGINEERING IN JAVA

## Session 19 (04/12/2018)

- Testing to HTTP endpoints
- Review past homeworks

### Notes:

#### Testing HTTP endpoints

We cloned the a Roger's repository and from it he was explaining to me all concepts related with reach HTTP endpoint from tests.

His approach was to build a generic test to reach HTTP endpoints regardless the HTTP verb used.

#### Homework revision

Is recommended that the JS frontend shows API response values in text not numbers, this can be achieved using some library but the most simple thing to do is to return an JSON object in the API that contains a text with the calculated value and then use that string in the frontend.

**TIP:** Some popular frontend libraries that have all embedded are React and Angular. As they have corresponding learning curves, [Create React App][1] can be useful.

In other subject, about the `{}` being preferable than `[]` at the beginning of a JSON file, besides of the security reason, the key point is about not breaking backwards compatibility. For example if we have to change from `[]` to `{}`, all our clients using old API will break, or we will have to maintain both implementations  on the server. 

A reason of need to change from `[]` to `{}` is because if we need to change or add something to our response will be easier if we have an object instead an array. For example, if we are returning an array `[]` and want to return another thing, we'll have make a new object and add that new object to the JSON payload anyway. On the other hand, if we use `{}` from the beginning, we can add that array and any other (even objects) pretty easily by making it a property of that response object.



###  Homework

- [ ] Deploy dependencies in docker-compose.yml in talenthaven-api


### Resources:
- [Create React APP - GitHub site][1]
- [Create React APP - GitHub repo][2]

[1]: https://facebook.github.io/create-react-app/
[2]: https://github.com/facebook/create-react-app
