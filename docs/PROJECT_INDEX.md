# Project index
- `AGENTS.md`, `CLAUDE.md` — rules.
- `docs/CURRENT_STATE.md` — status and next action.
- `docs/AI_WORKFLOW.md` — model roles, cost habits, ECC status.
- `docs/DECISIONS/` — 0001 scope and MVP, 0002 toolchain and CI, 0003 brand and visual direction.
- `docs/brand/mygce-icon.svg` — icon source. `third_party/outfit/` — font licence.
- `.claude/agents/code-reviewer.md` — report-only reviewer.
- `.github/workflows/ci.yml` — build, unit tests, lint, debug APK artifact.
- `gradle/libs.versions.toml` — dependency versions.

## App (`app/src/main/java/com/mygce/app/`)
- `MainActivity.kt` — hosts the home screen, reads/saves the exam date.
- `countdown/ExamCountdown.kt` — pure logic: days to exam, safe date parsing. Tests: `app/src/test/.../countdown/ExamCountdownTest.kt`.
- `subjects/Subject.kt` — the 4 MVP O Level subjects (names in strings.xml).
- `data/ExamDateStore.kt` — exam date in SharedPreferences (device only).
- `ui/HomeScreen.kt` — header, hero countdown, date picker, subject cards (placeholders), staggered entrance.
- `ui/theme/` — Color, Type (Outfit), Theme (light/dark).
- `ui/brand/StudioSplash.kt` — Game Changer Studios tile animation. `ui/brand/MyGceMark.kt` — icon drawn in code.
- `res/drawable/ic_launcher_*.xml`, `res/mipmap-anydpi/` — adaptive + monochrome icon. `res/font/` — Outfit.
- `app/src/main/res/values/strings.xml` — all user-visible text (English).
