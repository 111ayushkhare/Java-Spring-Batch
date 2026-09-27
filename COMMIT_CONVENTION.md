# Commit Convention

This repository follows the [Conventional Commits](https://www.conventionalcommits.org/) specification for commit messages.

## 1. Format

Each commit message consists of a **header**, an optional **body**, and an optional **footer**. The header has a special format that includes a **type**, an optional **scope**, and a **description**:

```
<type>(<scope>): <short description>

[optional body — explain WHY, not WHAT]

[optional footer — BREAKING CHANGE: ..., Closes #issue]
```

## 2. Allowed Types

| Type | Use for |
|------|---------|
| `feat` | New feature, new project addition |
| `fix` | Bug fix |
| `refactor` | Restructuring with no behaviour change |
| `style` | Formatting, whitespace only |
| `chore` | Tooling, repo setup, deps, CI |
| `docs` | README, comments, guides only |
| `test` | Adding or updating tests |
| `perf` | Performance improvement |
| `ci` | CI/CD config changes |
| `revert` | Reverts a previous commit |

## 3. Good vs Bad Commit Examples

### Good
- `feat(auth): add JWT login endpoint`
- `fix(parser): resolve null pointer exception on empty input`
- `docs: update setup instructions in README`
- `chore: add root .gitignore`

### Bad
- `added login endpoint` (imperative mood required)
- `Feat(auth): add JWT login endpoint` (type must be lowercase)
- `fix: fixed parser` (imperative mood required, description should be descriptive)
- `update README` (missing type)

## 4. Scope Convention

Scopes are optional but recommended when applicable. In a single-project repository, scopes can refer to the module or component being modified (e.g., `api`, `db`, `auth`, `ui`).

## 5. Full Workflow Sequence

1. **Always work on `dev` branch**
   ```bash
   git checkout dev
   ```
2. **Do the work**
3. **Stage files**
   ```bash
   git add <files>
   ```
4. **Commit with conventional message**
   ```bash
   git commit -m "feat(<scope>): <description>"
   ```
5. **Merge to main (via PR or local merge)**
   ```bash
   git checkout main
   git merge dev --no-ff -m "chore: merge <feature> into main"
   ```
6. **Push both**
   ```bash
   git push origin main
   git push origin dev
   ```
7. **Tag (on main)**
   ```bash
   git tag -a v1.0.0 -m "release: v1.0.0"
   git push origin --tags
   ```

## 6. External References

- [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/)
- [Angular Commit Message Guidelines](https://github.com/angular/angular/blob/22b96b9/CONTRIBUTING.md#commit)
- [Semantic Versioning (SemVer)](https://semver.org/)
