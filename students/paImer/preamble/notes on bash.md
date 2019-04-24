# Notes on Bash

## Commands I've learnt

| Bash command | Description |
| ------------- | ------------- |
| `cd` | Change directory to the home directory (~)|
| `cd someDir` | Change directory to the `someDir`, assuming it exists in your current working directory |
| `..` | The folder "one up" from this one |
| `ls` | List files and directories in the current working directory |
| `touch someFile` | Create a new file called `someFile`, or change the 'last modified at' <br/> attribute of `someFile` if it already exists |
| `\` | The escape character. If the next character could mean something else <br/> (e.g. space, new line, `>`, `\` etc.) put a `\` before it to tell <br/>Bash to use the *literal* character of whatever comes next.|
| `rm someFile` | Removes the file `someFile` assuming it exists in your current working directory |
| `mkdir someDir` | Creates the directory `someDir` in your current working directory |
| `rmdir someDir` | Deletes the directory `someDir` assuming it exists in your current working directory |
