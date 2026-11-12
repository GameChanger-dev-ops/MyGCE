# Current state

_Updated 2026-11-12 by Claude (cloud session), Phase 2. Keep under ~60 lines; overwrite stale lines.
Evidence labels: CI ✓ · local ✓ · device ✓ (phone) · unverified._

## Snapshot
- `main` = initial commit only. PR #1 (Phase 0 rules/docs + Phase 1 skeleton) open, targets `main`, CI green.
- Phase 2 (app shell + exam countdown) on `claude/phase2-shell`, stacked on Phase 1. Version `0.2.0-p2`.
  Home screen: countdown card, date picker, 4 subject placeholders ("Questions coming soon").

## Verified
| Area | Evidence |
|---|---|
| Phase 1 skeleton builds, lint passes, APK produced | CI ✓ run 43175843386 |
| Phase 1 APK installs and runs (owner: "it runs"; phone model not recorded) | device ✓ |
| Phase 2 build, 7 countdown unit tests, lint | unverified until CI runs on the Phase 2 PR |
| Phase 2 screen, date picker and saved date on a phone | unverified (owner to test) |

## Unverified / open
- Competitor (MyGCE-style apps) size, offline behaviour, subjects, ads, price.
- Rights to GCE Board / school past papers (licensing letter not sent).
- Student demand and willingness to pay (interviews not done).
- Google Play billing rules for Cameroon.
- Official GCE exam dates change yearly: the student enters it; hint text points to the official timetable.
- Everything Claude Code (ECC): unverified, not installed.
- No app icon yet (lint warning only).

## Blockers
Content rights block shipping real questions, not the app shell.

## Decisions to respect
0001 scope and MVP · 0002 toolchain and CI (Sipwise versions).

## Next action
Get CI green on the Phase 2 draft PR; owner tests the home screen and exam date on a phone.
