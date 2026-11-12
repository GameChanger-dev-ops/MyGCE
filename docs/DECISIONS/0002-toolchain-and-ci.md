# 0002 — Toolchain and CI
Status: accepted (owner GO for Phase 1, 2026-11-12)

## Context
Phase 1 needs a building skeleton. Sipwise already runs a toolchain verified by CI, and the owner
and AI workflow know it.

## Decision
Reuse Sipwise's versions as-is: Gradle 9.8.1, AGP 9.4.1 (built-in Kotlin, no kotlin-android plugin),
Kotlin 2.4.21, Compose BOM 2026.09.00, compileSdk 37, targetSdk 35, minSdk 26, JDK 17.
CI = `.github/workflows/ci.yml`: build debug APK, unit tests, lint, on PRs and `main`; read-only
token, no secrets. Debug builds use `.dev` app-ID suffix and Android's default debug key
(no keystore committed). Release signing is a later decision.

## Consequences
Versions were verified on Sipwise's CI, not yet on MyGCE's. Phase 1 is done only when MyGCE's own CI is green.
