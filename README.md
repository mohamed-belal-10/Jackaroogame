# Jackaroo

A Java implementation of **Jackaroo** — the Middle Eastern marble-racing card
game (a card-driven cousin of Trouble/Aggravation) — with a full rules engine,
a JavaFX GUI, and a CPU opponent.

Players race four marbles each around a shared board to their home zone,
using a standard deck (plus two wild cards) to field, move, swap, and
sabotage marbles instead of rolling dice.

## Features

- **Complete rules engine** — board/cell model, turn management, and legal-move
  validation, all decoupled from the UI behind a `GameManager` interface.
- **Full card set**, modeled as a class hierarchy rather than a lookup table:
  - **Standard cards** (`Ace`, `Four`, `Five`, `Seven`, `Ten`, `Jack`, `Queen`, `King`) — each encodes its own movement/action rules (fielding, forward/backward movement, splitting a move across two marbles, swapping with an opponent).
  - **Wild cards** (`Burner`, `Saver`) — `Burner` destroys a targeted marble and sends it home; `Saver` sends a marble straight to its safe zone.
- **Custom exception hierarchy** (`GameException` and ten subclasses) for every illegal action — illegal movement, invalid marble/card selection, illegal swap or destroy, discard/field restrictions, out-of-range splits — so the engine rejects illegal moves with a specific, catchable reason rather than a generic error.
- **CPU opponent** (`model.player.CPU`) for single-player games.
- **JavaFX GUI** (`view.Gui`) with card art and board graphics under `src/`.
- **JUnit test suite** (`Milestone2PublicTests`, `Milestone2PrivateTests`) covering the engine logic.

## Project structure

```
Jackaroo/
├── src/
│   ├── engine/            game loop, turn/board management
│   │   └── board/         Board, BoardManager, Cell, CellType, SafeZone
│   ├── exception/         GameException and its typed subclasses
│   ├── model/
│   │   ├── card/          Card, Deck (base types)
│   │   │   ├── standard/  Ace, Four, Five, Seven, Ten, Jack, Queen, King, Suit
│   │   │   └── wild/      Wild, Burner, Saver
│   │   └── player/        Player, CPU, Marble, Colour
│   ├── test/               JUnit milestone test suites
│   └── view/               Gui.java (JavaFX) + card/board image assets
├── Cards.csv               deck definition: card counts, names, effects, values, suits
├── .classpath / .project    Eclipse project files
└── bin/                     compiled .class output (Eclipse build)
```

## Requirements

- **JDK 8** (the Eclipse project is configured for `JavaSE-1.8`)
- **JavaFX** — bundled with the Oracle JDK 8 distribution; on OpenJDK 8+ you may need a separate JavaFX SDK on the module path
- **JUnit 4** to run the test suite

## Running it

**From Eclipse** (simplest — the repo is an Eclipse project):

1. `File → Import → Existing Projects into Workspace`, select the `Jackaroo/` folder.
2. Run `view.Gui` as a Java application (it has the JavaFX `main` entry point).

**From the command line**, with a JavaFX SDK available:

```bash
javac -cp src -d bin $(find src -name "*.java")
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.media -cp bin view.Gui
```

The GUI reads `Cards.csv` and the image/video assets from the working
directory (or classpath, depending on how they're loaded) — run from the
`Jackaroo/` project root.

## Running the tests

```bash
# From Eclipse: right-click test/Milestone2PublicTests.java → Run As → JUnit Test
```

Or with `javac`/`java` and JUnit 4 + Hamcrest jars on the classpath, compile
and run `test.Milestone2PublicTests` the same way as any other JUnit 4 suite.

## The deck (`Cards.csv`)

Each row defines one physical card: field-count, move-count, name,
effect description, numeric value, and suit. The engine loads this file to
build the `Deck` rather than hardcoding card behavior in one place, so the
card *data* (counts, values) and card *logic* (the `act()` implementation on
each `Card` subclass) are separate and independently editable.
