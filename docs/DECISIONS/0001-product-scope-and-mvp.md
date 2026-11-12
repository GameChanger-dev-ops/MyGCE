# 0001 — Product scope and MVP
Status: proposed (owner confirmed defaults 2026-11-11; to be revisited after student interviews)

## Context
Goal: a cleaner, better alternative to existing Cameroon GCE revision apps (e.g. MyGCE-style apps).
We have not yet inspected the competitor; comparison numbers are `[verify]`. Main risks: rights to
past-paper content, willingness to pay, Play Store billing rules, data/storage limits on cheap phones.

## Decision
- MVP: **O Level only**, Mathematics, English Language, Biology, Chemistry.
- Native Kotlin + Jetpack Compose, minSdk 26 [verify against target devices].
- Offline-first; content as JSON packs (<= 5 MB each); app install target <= 15 MB.
- Content: original GCE-style questions and licensed papers only; every item records source + licence.
- MVP has **no AI, no payments, no accounts**. Free. English UI.
- Countdown to an exam date the student enters (official dates change yearly [verify]).
- No backend at first: static file hosting for packs.

## Out of scope until later decisions
A Level, timed mock mode, payments (check Google Play billing policy for Cameroon first), AI hints,
photo marking, voice tutor, French UI.

## Consequences
Content rights and teacher-checked solutions, not code, are the critical path.
