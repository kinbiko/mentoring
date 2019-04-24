# What is Markdown?

Often used to format readme files and creating rich text using a plain text editor, Markdown is a lightweight markup language that was created in 2004.

Unlike HTML, Markdown does not use tags. What you see is what you get, with no surprises.

# Why use Markdown?

Markdown is a popular tool on the popular software development website [GitHub](https://github.com). Create a file called `filename.md` in Markdown to view your document in GitHub with the proper formatting.

In addition to the reasons mentioned above, Markdown also offer allows you to *easily* create the following:

- Headings
- Lists
- Emphasis
- Blocks of code
- Monospace characters
- Blockquotes

# How to use Markdown

## Headings

For headings and subheadings, use `#`, and multiples of `#` thereof.

For example:

```markdown
# This is a heading

## This is a smaller heading
```

yields:

# This is a heading

## This is a smaller heading

and so on.

## Italics

Choice comes into play - wrap the text you wish to italicize in `*` or `_`

`*This text* is in italics` → *This text* is in italics

## Bold (Strong)

Wrap the text you wish to embolden with `**` or `__` (two of each)

`How very **bold** of you!` 

→ 

How very **bold** of you!

## Strikethrough

You may have noticed a pattern at this point. Wrap the text you wish to change with certain characters to make specific changes. For the strikethrough, use `~~`

`This is my ~~first~~ ~~second~~ third draft` 

→

This is my ~~first~~ ~~second~~ third draft

## Multiple simultaneous formatting options

Multiple commands can be used simultaneously!

`~~__*I N C E P T I O N*__~~`

→

~~__*I N C E P T I O N*__~~

## Separators

To place a horizontal line underneath a block of text to keep your content separate, simply input `---` or `___` (three of each) on a line below your text.

`---` yields:

---

## The escape character

The escape character nullifies the effects of the character(s) immediately following it.

For example:

```markdown
The word for \# varies around the world. In Japan, \# is
called a **sharp** (*shaa-pu*) symbol, whereas in the UK, it
goes by the name **hash** or **number key**.
```

The word for \# varies around the world. In Japan, \# is called a **sharp** (*shaa-pu*) symbol, whereas in the UK, it goes by the name **hash** or **number key**.

Forgetting an escape character before \# could result in **dire** consquences. See [here](#headings).

## Blockquotes

If you wish to add a quote, or simply want to make a statement or phrase "pop", add a `>` before the text. This applies to everything on the same line.

`> This is a quote`

> No, this is a quote!

## Hyperlinks

Linking to webpages is simple in Markdown, and takes the following format:

Click `[here](https://github.com/paImer "paImer's GitHub")` for an abundance of well-written, snappy code

→ Click [here](https://github.com/paImer "paImer's GitHub") for an abundance of well-written, snappy code

N.B. The quotations simply add a title to the link and are optional. Hover your mouse over the link to see the effect in action.

## Unordered lists

To create an unordered list (bullet points), precede each item with `-`. Create a nested item with indentation + `-`

### Shopping list

```markdown
- Milk
- Sugar
- Apples
    - The red kind
```

yields:

- Milk
- Sugar
- Apples
    - The red kind

## Ordered lists

How about numbering items? To start a list from 1, do exactly the same as for unordered lists, but replace _all_ the `-` with `1.`. To start from a number other than one, have this as your first number, then simply put `1.` for each item following it.

### To-do list

```markdown
1. Master Markdown
1. Go shopping
```

yields:

1. Master Markdown
1. Go shopping

### Starting from a number other than 1.

```markdown
300. Item #300
1. Item #301
1. Item #302
```

yields:

300. Item #300
1. Item #301
1. Item #302

Automatic numbering is really rather useful, _particularly_ when deleting items from your list. Imagine having an ordered list of 1000 items, and realising you don't need the first item anymore.

Now instead of having to find a way to change `2. → 1., 3. → 4., ..., 1000. → 999.`, Markdown will do it for you.

For nested lists (use indentation), `1.` will convert to a different ordered index character, namely `i, ii, iii, ...`, then `a, b, c, ...` for each sub-list after that.

```markdown
1. This
   1. Is
      1. A
         1. Nested
            1. List
```

→

1. This
   1. Is
      1. A
         1. Nested
            1. List

## Inline code

Wrap your code with a single pair of backticks ( \` ) for single lines. For multiple lines, use a pair of three backticks ( ``` )

```markdown
`1 + 2 + 3 = 6`
```

→

`1 + 2 + 3 = 6`

and

````markdown
```
If I knew how to code,
this is where I would write it.
```
````

→

```markdown
If I knew how to code,
this is where I would write it.
```

## In-file images

This is similar to embedding hyperlinks. The general format is:

```markdown
![Image name for your reference](https://`...`/image.png)
```

→

![Markdown Logo](https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Markdown-mark.svg/320px-Markdown-mark.svg.png)

Splendid!

# GitHub-flavoured Markdown

GitHub has its own variant of Markdown. In addition to everything written above, there are a few extra tweaks you should be aware of.

## Language-specific code blocks

Add the language your code is in immediately after the initial set of backticks for syntax highlighting. The majority of widely-used codes are supported.

````markdown
```python
while True:
    print("Hello")
```
````

→

```python
while True:
    print("Hello")
```

## Tables

When it comes to tables, `|` and `-` are your friends.

```markdown
|Header 1|Header 2|
|--------|--------|
|Item 1  |Item 3  |
|Item 2  |Item 4  |
```

yields:

|Header 1|Header 2|
|--------|--------|
|Item 1  |Item 3  |
|Item 2  |Item 4  |

N.B. The formatting restrictions here are quite loose. As long as each column begins with, ends with, contains the correct number of `|`, and the row below the header contains nothing but `-`, Markdown will know what to do.

## Creating task lists

In GitHub, you can create checklists in the following way:

```markdown
- [x] Task 1
- [x] Task 2
- [ ] Task 3
```

yielding:

- [x] Task 1
- [x] Task 2
- [ ] Task 3

and so on.

See [here](https://github.github.com/gfm/) for a much more in-depth look at this variety of Markdown.
