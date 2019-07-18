# skills

PS: This repository mirrors another on [Github](https://github.com/colisweb/skills)

[![pipeline status](https://gitlab.com/colisweb-open-source/skills/badges/master/pipeline.svg)](https://gitlab.com/colisweb-open-source/skills/commits/master)  [![coverage report](https://gitlab.com/colisweb-open-source/skills/badges/master/coverage.svg)](https://gitlab.com/colisweb-open-source/skills/commits/master)

The `skills` library aims to provide models skill constraints and compare them.

A skill constraint is composed of a skill and a type of constraint applied on this skill.
A skill is simply a label. A constraint can be of type:

- **owned**
- **required**
- **forbidden** 

For two sets of skill constraints, `skills` can tell whether they match or not.

Two sets of skill constraints match if no constraint type in one set is in contradiction with another.

Given two sets `S1` and `S2`:

| Skill constraint        | Matches if                                                          |
| :---------------------: | :-----------------------------------------------------------------: |
| Owned [skill] in S1     | NOT Forbidden [skill] in S2                                         |
| Required [skill] in S1  | (Owned [skill] OR Required [skill]) AND NOT Forbidden [skill] in S2 |
| Forbidden [skill] in S1 | NOT Required [skill] AND NOT Owned [skill] in S2                    |
