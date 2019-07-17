# skills

PS: This repository mirrors another on [Github](http)

[![pipeline status](https://gitlab.com/colisweb-open-source/skills/badges/master/pipeline.svg)](https://gitlab.com/colisweb-open-source/skills/commits/master)  [![coverage report](https://gitlab.com/colisweb-open-source/skills/badges/master/coverage.svg)](https://gitlab.com/colisweb-open-source/skills/commits/master)

`skills` aims to provide models skill constraints and compare them.

A skill constraint is composed of a skill and a type of constraint applied on this skill.
A skill is simply a label. A constraint can be of type:

- **owned**
- **required**
- **forbidden** 

For two sets of skill constraints, `skills` can tell whether they are compatible or not.