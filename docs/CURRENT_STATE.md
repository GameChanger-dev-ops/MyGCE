# Current state

_Updated 2026-11-12 by Claude (cloud session), Phase 1. Keep under ~60 lines; overwrite stale lines.
Evidence labels: CI ✓ · local ✓ · device ✓ (phone) · unverified._

## Snapshot
- `main` = initial commit only. Phase 0 (rules/docs) on `claude/phase0-rules`, not yet merged.
- Phase 1 skeleton (empty Compose app + CI) on `claude/phase1-skeleton`, stacked on Phase 0.
  Version `0.1.0-p1`. No real features, no tests yet.

## Verified
| Area | Evidence |
|---|---|
| Skeleton builds, lint passes | unverified until MyGCE CI runs (no Android SDK in cloud) |

## Unverified / open
- Competitor (MyGCE-style apps) size, offline behaviour, subjects, ads, price.
- Rights to GCE Board / school past papers (licensing letter not sent).
- Student demand and willingness to pay (interviews not done).
- Google Play billing rules for Cameroon.
- Everything Claude Code (ECC): unverified, not installed.
- No app icon yet (lint warning only).

## Blockers
Content rights block shipping real questions, not the skeleton.

## Decisions to respect
0001 scope and MVP · 0002 toolchain and CI (Sipwise versions).

## Next action
Get CI green on the Phase 1 draft PR, owner installs the debug APK and sees the MyGCE screen.
