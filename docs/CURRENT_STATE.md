# Current state

_Updated 2026-11-12 by Claude (cloud session), Phase 3. Keep under ~60 lines; overwrite stale lines.
Evidence labels: CI ✓ · local ✓ · device ✓ (phone) · unverified._

## Snapshot
- `main` = initial commit only. Stack of draft PRs: #1 (Phase 0+1, targets `main`) ← #2 (Phase 2) ← Phase 3.
- Phase 3 (brand + polish) on `claude/phase3-brand-polish`, version `0.3.0-p3`: studio tile animation,
  app icon (adaptive + themed), Outfit font, light/dark theme, refined home (hero countdown, subject cards),
  edge-to-edge fix.

## Verified
| Area | Evidence |
|---|---|
| Phase 1 skeleton builds, lint passes, APK produced; installs and runs | CI ✓ run 43175843386; device ✓ (owner: "it runs", phone not recorded) |
| Phase 2 build, 7 countdown unit tests, lint | CI ✓ run 43176734334 (step passed; report not opened) |
| Phase 2 date picker and saved date on a phone | unverified (owner has not reported) |
| Phase 3 build, lint, APK | unverified until CI runs on the Phase 3 PR |
| Phase 3 look on a phone: splash, icon, fonts, dark mode, status bar spacing | unverified (owner to test) |

## Unverified / open
- Competitor (MyGCE-style apps) size, offline behaviour, subjects, ads, price.
- Rights to GCE Board / school past papers (licensing letter not sent).
- Student demand and willingness to pay (interviews not done).
- Google Play billing rules for Cameroon.
- Exam dates change yearly: student enters it; hint points to the official timetable.
- Everything Claude Code (ECC): unverified, not installed.

## Blockers
Content rights block shipping real questions, not the app shell.

## Decisions to respect
0001 scope and MVP · 0002 toolchain and CI · 0003 brand (palette, Outfit font, studio animation, icon).

## Next action
Get CI green on the Phase 3 draft PR; owner installs the APK and checks splash, icon, home screen,
dark mode. Then Phase 4 (content pack format) needs GO.
