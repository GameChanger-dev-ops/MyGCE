# AI workflow and cost control (token economizer)

Goal: keep every session cheap and consistent. These are habits, not a tool.

## Model roles
| Job | Model |
|---|---|
| Architecture, scope, risky decisions, final review of hard changes | Opus |
| Normal building, tests, docs | Sonnet |
| Repetitive batch jobs (formatting question files, simple checks) | Haiku |

## Habits
- One task per session, then handoff (AGENTS §6). Don't let context grow for hours.
- Start from `CURRENT_STATE.md` + `PROJECT_INDEX.md`; read only the files the task needs.
- Don't re-read big files or repeat research already recorded in `docs/`.
- Use the `code-reviewer` subagent only before merging non-trivial changes.
- Content work in batches with a fixed template (see future `docs/content/TEMPLATE.md`).
- Spawn subagents only for genuinely independent research; each starts cold and costs tokens.

## Everything Claude Code (ECC)
Status: **unverified, not installed.** Before adopting anything: read its README and licence, read
every hook/script it would run, adopt only needed parts, record the choice in `docs/DECISIONS/`.
