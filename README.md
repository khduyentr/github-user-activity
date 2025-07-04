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
[2025-06-26 14:20:56] User "khduyentr" (ID: 39858155)
- Event ID: 51382152865
- Profile: https://api.github.com/users/khduyentr
- Action: CreateEvent
- Repository: khduyentr/github-user-activity
- Repo URL: https://api.github.com/repos/khduyentr/github-user-activity

-----
```
🛠 Arguments Summary
Usage:
 ``` java GithubActivity <username> [per_page] [page]```

Examples:
  ```java GithubActivity khduyentr```
 ```java GithubActivity khduyentr 5 2```
