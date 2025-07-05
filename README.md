# GitHub User Activity CLI
A simple Java command-line application to fetch and display recent GitHub activity for a specified user using the GitHub API.

📌Project idea inspired by [roadmap.sh/projects/github-user-activity](https://roadmap.sh/projects/github-user-activity)

## 🚀 Features
- Fetch recent GitHub events for any user

- Optional pagination: specify number of events (per_page) and page number

- Graceful error handling (invalid usernames, API issues, etc.)

- Supports GitHub API authentication via personal access token

- CLI interface with flexible arguments

## 📦 Compilation & Execution
Ensure Java is installed

Requires Java 8 or above

### Compile the project
```
cd src
javac *.java
```
### Run the application
```
java GithubActivity <username> [per_page] [page]
```
### 🔐 GitHub Token Setup
```
To increase rate limits and access private data (if needed):

Go to your GitHub settings → Developer settings → Personal access tokens

Generate a token (no scopes needed for public activity)

Save it as src/token.txt (same directory as your .java files)
src/
├── GithubActivity.java
├── ... other classes
└── token.txt  ← put your token here
```
### 📋 Usage Examples

✅ Basic usage (first 10 events by default)

```java GithubActivity khduyentr```


✅ Custom number of events and page

```java GithubActivity khduyentr 4 1```

Sample Output:
```
> FETCHING DATA ...
> PARAMS: {page:1, per_page:4}
> FOUND 4 EVENTS: 

[2025-06-25 16:16:07] User "khduyentr" (ID: 39858155)
- Event ID: 51337373315
- Profile: https://api.github.com/users/khduyentr
- Action: PushEvent
- Repository: khduyentr/task-tracker
- Repo URL: https://api.github.com/repos/khduyentr/task-tracker

> Commit:
  [Author]: Khanh Duyen(39858155+khduyentr@users.noreply.github.com)
  [Message]: Update README.md
  [URL]: https://api.github.com/repos/khduyentr/task-tracker/commits/000d77a2b9d65be06ca539785258d2e9b9f7e96b"}

-----
```
🛠 Arguments Summary
Usage:
 ``` java GithubActivity <username> [per_page] [page]```

Examples:
  ```java GithubActivity khduyentr```
 ```java GithubActivity khduyentr 5 2```
