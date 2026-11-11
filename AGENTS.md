# AGENTS.md — rules for every contributor (human or AI)

MyGCE: native Android app (Kotlin + Jetpack Compose) for Cameroon GCE (O/A Level) revision.
Offline-first, light on data and storage, honest content. The product owner is a non-developer:
plain language, one manual action at a time. **No new task starts without the owner's "GO".**

## 1. Startup protocol (every session, every model)
1. `git status` and `git log --oneline -10` — never discard someone else's uncommitted work.
2. On a work branch: `git diff origin/main...HEAD --stat`.
3. Read `docs/CURRENT_STATE.md` (short; verified status, blockers, next action).
4. Read your task (owner message, GitHub issue, or CURRENT_STATE "Next action"). No task → ask.
5. Read only what the task needs (`docs/PROJECT_INDEX.md` first). Verify claims you rely on.

## 2. Reading by task
| Task | Read |
|---|---|
| Find code / tests | `docs/PROJECT_INDEX.md`, then only the listed files |
| Scope / what next | `docs/ROADMAP.md` (when it exists), `docs/DECISIONS/0001` |
| Why something is the way it is | `docs/DECISIONS/` (the one you need, not all) |
| Model choice, cost, reviews | `docs/AI_WORKFLOW.md` |
| CI / build problems | `.github/workflows/` (when it exists), `app/build.gradle.kts` |

## 3. Ground rules
- Stay in scope. No unrelated refactors, renames or reformatting.
- Architecture, public interfaces, permissions, dependencies, SDK levels or CI security change only
  with a decision record in `docs/DECISIONS/`.
- One task = one branch. Never commit or push to `main`. The owner merges only green PRs.
- Never invent Android APIs: check official docs; mark uncertainty `[verify]`.
- **Content integrity:** every question and worked solution has a recorded source and licence
  status (original / licensed / unverified). Never ship unverified-rights material. Never invent
  past papers or present generated questions as official GCE Board papers.
- Never fake data in production paths; simulated data is labelled "simulated" in UI and code.
- Never commit or put in prompts: secrets, keystores, tokens, phone numbers, student personal data.
- Never weaken, skip or delete tests to get green. Never claim a check you didn't run.
- Evidence labels: **CI ✓** (link), **local ✓**, **device ✓** (which phone), or **unverified**.

## 4. Build and test
CI (`.github/workflows/`) is the reference once it exists; it runs on PRs and `main`.
Locally (JDK 17 + Android SDK): `./gradlew assembleDebug testDebugUnitTest lintDebug`.
Cloud sandboxes can't download the Android SDK — use CI via a draft PR.

## 5. Code and content conventions
- Kotlin official style; small focused files; clear error handling, no silent `catch {}`.
- Pure logic (marking, packs, countdown) has no `android.*` imports and has JVM tests.
- All user-visible text in `strings.xml` (English first).
- Content packs: JSON, versioned schema, each pack <= 5 MB, app install target <= 15 MB.
- Comments explain *why*, not *what*.

## 6. Handoff protocol (end of every task or before switching model)
1. Build/tests/lint pass (CI or locally) — record real results with evidence labels.
2. Docs stay in sync with verified code, in the same commit: `docs/CURRENT_STATE.md` always;
   `docs/PROJECT_INDEX.md` if files moved; `docs/DECISIONS/` if design or scope changed.
3. Commit (`WIP:` prefix if unfinished) and push your branch — cloud sessions are deleted.
4. CURRENT_STATE must name exactly one next action. Overwrite stale lines; keep it under ~60 lines.
