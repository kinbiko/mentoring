# Mentoring

This repository contains lesson material for my mentoring efforts.

My students will be contributing their personal notes and reviewing each other's notes of topics from our sessions on a weekly basis.

## Contents

```
students -- lesson notes from each of my students, committed on a weekly basis.
lessons -- lesson material, slowly updated, derived from my students' notes, but organised and modified upon by myself
```

## Reviews

Please use the `#review-requests` channel in Slack to ask the other students if they could review your notes when submitting a PR. You may use `@here`/`@channel` to get people's attention.
Please keep an eye on this channel, as reviewing other people's PRs is a great way to learn about new topics and practice human skills in software engineering teams.
If we have already gone over a topic in our lessons that someone else is requesting a review for, please volunteer.
If nobody has volunteered as a reviewer for your PR within 24 hours I will assign one based on my judgement of who I think would be a good fit.
I might assign you as a reviewer even if you have no knowledge of the topic.
This is common in industry as well -- there's always a first person on the team to learn something new.

## Organisation

I believe in adopting a malleable syllabus and lesson plan according to each individual student.
Although a lot of the same content is taught to everyone, each student takes a different route, and might skip certain bits entirely and dive deeper into some topics than other students have.
In order to keep track of what I'm teaching (and not teaching) everyone however, I have found it necessary to start tracking this.
This is what the ["Topics" project](https://github.com/kinbiko/mentoring/projects/1) is for.
I have organised this project into the following categories (columns):

|Category|Description|
----------------------
| Java | The canonical object oriented programming language. The primary programming language taught in my sessions. |
| Git | Probably the most popular developer tool in the world. |
| Shell | Mastering the command line is a key skill for efficient development. |
| Theory and practices | Software Engineering/Computer Science/IT topics that are programming language, and occasionally even programming, agnostic. |
| Data & databases | Got information about something? You probably want to store this data in a database. |
| Frontend development | Topics relating to client side web development, such as HTML, CSS and JavaScript. |
| Devops and cloud development | Topics relating to CI/CD, Docker, and modern cloud practices. |
| Developer culture | The internet was made by developers. This category is about the online resources, tech and geek culture (good and bad) surrounding the software industry. |
| Potential alternative paths | This category contains introductory topics that I may consider making entire categories out of. A student should be mature in Java, Git, Shell, and Theory before I consider making a category out of one of these topics. |
| Suggestion box | Suggestion box for topics to cover in our sessions |

Students may add topics we've covered during our sessions (that I've forgotten to add) or topics you'd like to cover that you believe I may help with to the "Suggestion box".
Please make these **knowledge topics**, as opposed to chores that we had to do (e.g. "basic git concepts" is OK but "installing IntelliJ" is not).

### Per-student boards

Each student has a project board named after their GitHub username.
I've introduced these types of boards to all my students, but unfortunately, due to the coupling of repositories to projects it has become too difficult for me to keep track of what's in flight with each student at any given point.
These boards are here to centralise this information.
These boards should consist of cards -- **not GitHub issues** -- with descriptions and links to relevant PRs and issues.
E.g. if you're learning about TDD and are submitting a TDD exercise in Java under PR number `#523` then you should tag the card with `#523` (The PR), `#132` (TDD in Java issue) and `#38` (TDD theory issue).
Once a card has been moved to "Done", please add the label corresponding to your username to each associated issue.
This should help me and students who are tackling those topics in the future to see who to assign as a reviewer.
