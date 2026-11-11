# CLAUDE.md

@AGENTS.md

## Claude Code specifics
- Follow AGENTS.md §1 startup and §6 handoff. Read `docs/CURRENT_STATE.md` first.
- **GO rule:** brainstorm and propose freely, but don't start a new task until the owner says GO.
- Model roles and cost habits: `docs/AI_WORKFLOW.md` (Opus for decisions, Sonnet for building).
- Subagent `code-reviewer` (`.claude/agents/code-reviewer.md`): report-only review before merging
  non-trivial changes. Other subagents only for genuinely independent research.
- Cloud sessions: no Android SDK. Push the branch, open a draft PR (only for owner-approved work),
  read CI via GitHub MCP (`actions_list`, `get_job_logs`).

## Working with the product owner
- Beginner: plain English, one manual action at a time, exact taps/clicks and the expected result.
- Do the work yourself when you safely can; ask only for decisions and owner-only actions.
- Never report success without evidence (CI run link, test output, device observation).
