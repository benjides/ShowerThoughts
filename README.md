# ShowerThoughts

> Simple random r/ShowerThoughts Reddit app

A really simple app using the Android MVP Architecture to dispay random ShowerThoughts from Reddit.

The post retrival has been done following a high level of abstraction so it would be easy to adapt/extend to other projects which would need to consume the Reddit API

## Todo

- [ ] Fix time display
- [ ] Work on loading animation
- [ ] Filter posts

## Contributing

- The `master` branch is basically just a snapshot of the latest stable release. All development should be done in dedicated branches. **Do not submit PRs against the `master` branch.**

- Checkout a topic branch from the relevant branch, e.g. `dev`, and merge back against that branch.

- It's OK to have multiple small commits as you work on the PR - we will let GitHub automatically squash it before merging.


- If adding new feature:
  - Add accompanying test case.
  - Provide convincing reason to add this feature. Ideally you should open a suggestion issue first and have it greenlighted before working on it.

- If fixing a bug:
  - If you are resolving a special issue, add `(fix #xxxx[,#xxx])` (#xxxx is the issue id) in your PR title for a better release log, e.g. `update entities encoding/decoding (fix #3899)`.
  - Provide detailed description of the bug in the PR. Live demo preferred.
  - Add appropriate test coverage if applicable.

## Dependencies

- [Reddit](https://www.reddit.com/)
- [Retrofit](http://square.github.io/retrofit/)
- [ButterKnife](https://github.com/JakeWharton/butterknife)

## License

[MIT](http://opensource.org/licenses/MIT)

Copyright (c) 2017-present
