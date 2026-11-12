# 0003 — Brand and visual direction
Status: accepted (owner request 2026-11-12)

## Context
The owner chose "C · Tiles" for Game Changer Studios (two tilted tiles, blue G and red C) and asked for
an app logo, a studio animation and a more refined look.

## Decision
- **Studio animation:** two tiles spring in from the sides, then "Studios / GAME CHANGER" fades up
  (~2.2 s, tap to skip, shown on each fresh launch, skipped if system animations are off).
- **App icon:** white "test paper" tile with a tick over a red tile on a blue gradient (family resemblance
  to the studio tiles, meaning = correct answer). Adaptive icon + monochrome (themed icons, Android 13+).
  Source: `docs/brand/mygce-icon.svg`.
- **Palette:** blue #2257E7, red #E5384B, ink #0D1117, paper #F5F6F8 + light and dark schemes. Fixed brand
  colours, no Android 12 dynamic colour.
- **Font:** Outfit (SIL OFL), bundled TTFs (400/600/700, latin subset, ~33 KB each) in `res/font/`;
  licence in `third_party/outfit/`. Dependency-free: no downloadable-font provider, so it works offline.
- **Edge to edge:** content respects system bars (targetSdk 35 draws under them).

## Consequences
+~100 KB of fonts. Latin subset covers English and French accents but not "∑". Subject chips use letters
("Ma", "En") not icons until real artwork exists. Visuals are verified on CI (build) and on a phone only.
