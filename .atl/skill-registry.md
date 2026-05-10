# Skill Registry — polaris-uhc

## Project Skills

| Skill | Location | Trigger |
|---|---|---|
| minecraft-plugin-development | .agents/skills/minecraft-plugin-development/SKILL.md | Paper/Bukkit plugin tasks, commands, listeners, schedulers, state modeling |

## Project Conventions

### Build
- `./gradlew build` — clean + compile + shadowJar
- Output: `build/libs/polaris-uhc-*.jar`
- `pom.xml` present but not used; Gradle is source of truth

### Tech Stack
- Paper API `1.21.11-R0.1-SNAPSHOT`
- Java 21 (toolchain enforced)
- Lombok `1.18.44` with annotation processor
- HikariCP, FastBoard, FastInv, Reflections, SignGUI, PlaceholderAPI, ProtocolLib, Chunky, mcdev-utils

### Architecture
- Main class: `us.polarismc.polarisuhc.Main`
- Singleton managers via `Main` instance fields (no service locator)
- State managers: `UHCManager`, `TeamManager`, `PlayerManager`, `GameFlowManager`, `ArenaManager`, `HubManager`, `ChannelManager`, `InfoManager`, `ScenarioManager`
- Game phases driven by `GameTimer` + `GameEvent` system
- Custom crafts, scenarios, team colors/prefixes all configurable

### Registration
- Commands: `src/main/resources/plugin.yml` — update there AND in code when adding
- Listeners: `@EventHandler` + `PluginManager.registerEvents()`
- Resource filtering: `yml, yaml, json, properties` with `expand(project.properties)`
- `plugin.yml` uses `${project.version}` replaced at build time

### Shading
- `signgui` relocated to `us.polarismc.polarisuhc.signgui`
- Other deps: `compileOnly` (Paper API, PlaceholderAPI, ProtocolLib, Chunky) — not shaded

### No Tests
- No test framework configured
- Validate manually via Paper server test

## Detected Stack

- **Language**: Java 21
- **Build**: Gradle Kotlin DSL (build.gradle.kts)
- **Server**: Paper API 1.21.11
- **Dependencies**: 9 compile/implementation, 4 compileOnly, Lombok annotation processor
- **Plugins**: PlaceholderAPI, ProtocolLib, Chunky