const gitHubForm = document.getElementById("gitHubForm");

gitHubForm.addEventListener("submit", e => {
  e.preventDefault();
  let usernameInput = document.getElementById("usernameInput");
  let gitHubUsername = usernameInput.value;
  requestUserFollowers(gitHubUsername);
  requestUserFollowing(gitHubUsername);
});

function requestUserFollowers(username) {
  const xhr = new XMLHttpRequest();
  const url = `https://api.github.com/users/${username}/followers`;

  xhr.open("GET", url, true);

  xhr.onload = function() {
    const data = JSON.parse(this.response);
    let ul = document.getElementById("userFollowers");
    let h3 = document.createElement("h3");
    h3.innerText = `Followers (${data.length}) :`;
    ul.appendChild(h3);

    for (let i in data) {
      let li = document.createElement("li");
      li.classList.add("list-group-item");
      li.innerHTML = `
                <p><strong>Nick:</strong> ${data[i].login}</p>
                <p><strong>Id:</strong> ${data[i].id}</p>
                <p><strong>Rodzaj użytkownika:</strong> ${data[i].type}</p>
                <p><strong>URL:</strong> <a href="${data[i].html_url}">${data[i].html_url}</a></p>
                <img src="${data[i].avatar_url}" alt="${data[i]}" height="100" width="100">
            `;
      ul.appendChild(li);
    }
  };
  xhr.send();
}

function requestUserFollowing(username) {
  const xhr = new XMLHttpRequest();
  const url = `https://api.github.com/users/${username}/following`;
  xhr.open("GET", url, true);

  xhr.onload = function() {
    const data = JSON.parse(this.response);

    let ul = document.getElementById("userFollowing");
    let h3 = document.createElement("h3");
    h3.innerText = `Following (${data.length}) :`;
    ul.appendChild(h3);

    for (let i in data) {
      let li = document.createElement("li");
      li.classList.add("list-group-item");
      li.innerHTML = `
                  <p><strong>Nick:</strong> ${data[i].login}</p>
                  <p><strong>Id:</strong> ${data[i].id}</p>
                  <p><strong>Rodzaj użytkownika:</strong> ${data[i].type}</p>
                  <p><strong>URL:</strong> <a href="${data[i].html_url}">${data[i].html_url}</a></p>
                  <img src="${data[i].avatar_url}" alt="${data[i]}" height="100" width="100">
              `;

      ul.appendChild(li);
    }
  };

  xhr.send();
}
