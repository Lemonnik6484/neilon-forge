# Neilon-Forge
This project is licensed under the GNU GPL v3.0.
It is based on [Neilon](https://github.com/snackbag/neilon) by [SnackBag Studios](https://github.com/snackbag).
I'm, Lemonnik, only porting it to forge.

Neilon is a ~~Fabric~~ Forge text library to aid in working with Minecraft text.

**Roadmap**

- [X] Literal text
- [X] Translation text
- [X] Keybinding text
- [X] Basic text styling (bold, italic, underlined, strikethrough, magic)
- [X] Colors
- [ ] Gradients
- [X] Click actions
    - [X] Command running
    - [X] Command suggestions
    - [X] Opening URLs
    - [X] Copy to clipboard
- [X] Hover events
    - [X] Text
    - [X] Item
    - [X] Entity
- [X] QOL text methods
  - [X] Repeat
  - [X] Keybinding
  - [X] Translation
  - [X] Color
- [X] QOL features
  - [X] Automatic command click fixer, no longer requiring `/` in front
  - [X] Automatic URL fixer, no longer requiring `https://` in front

## Usage

See the [wiki](https://wiki.snackbag.net/w/neilon), // P.S. this wiki is made for fabric version, but it's the same on forge except importing
or [directly contribute to our wiki](https://github.com/snackbag/wiki)

## Update policy

Unless we see high demand, or we need it ourselves, we will follow the latest Minecraft version by looking
at [Create Fabric](https://modrinth.com/mod/create-fabric), as this mod is used primarily for
the [SnackBag Create mod server](https://snackbag.net/).

## Testing

There are two Neilon testing phases. First, we have set up JUnit tests for the most operations you can do. These tests
are followed by the ingame tests we issue to ensure everything works as intended.
